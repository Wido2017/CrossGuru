package jsf;

import entity.Crossing;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import sessionBean.CrossingFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Named("crossingController")
@SessionScoped
public class CrossingController implements Serializable {

    private Crossing current;
    private DataModel items = null;
    @EJB
    private sessionBean.CrossingFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CrossingController() {
    }

    public Crossing getSelected() {
        if (current == null) {
            current = new Crossing();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CrossingFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Crossing) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Crossing();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CrossingCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Crossing) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CrossingUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Crossing) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CrossingDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Crossing getCrossing(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Crossing.class)
    public static class CrossingControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CrossingController controller = (CrossingController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "crossingController");
            return controller.getCrossing(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Crossing) {
                Crossing o = (Crossing) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Crossing.class.getName());
            }
        }

    }
    
    public String getCrossingListJSON(){
        getItems();
        JSONArray jsona=new JSONArray();
        
        for(Object item:items){
            JSONObject jsono =new JSONObject();
            Crossing crossing=(Crossing)item;
            /* id */
            jsono.put("id", crossing.getId());
            
            /* location */
            jsono.put("location", crossing.getLocation());
            
            /* coord on BMap */
            jsono.put("coordlng", crossing.getCoordlng());
            jsono.put("coordlat", crossing.getCoordlat());
            
            /* Current traffic flow */
            jsono.put("currentFlow_E", crossing.getCurrentFlowE());
            jsono.put("currentFlow_W", crossing.getCurrentFlowW());
            jsono.put("currentFlow_S", crossing.getCurrentFlowS());
            jsono.put("currentFlow_N", crossing.getCurrentFlowN());
            
            /* Neighbor crossing id */            
            jsono.put("next_E_id", crossing.getNextEid()!=null?crossing.getNextEid().getId():"");
            jsono.put("next_W_id", crossing.getNextWid()!=null?crossing.getNextWid().getId():"");
            jsono.put("next_S_id", crossing.getNextSid()!=null?crossing.getNextSid().getId():"");
            jsono.put("next_N_id", crossing.getNextNid()!=null?crossing.getNextNid().getId():"");
            
            /* Distance to neighbor crossing */
            jsono.put("next_E_d", crossing.getNextEd());
            jsono.put("next_W_d", crossing.getNextWd());
            jsono.put("next_S_d", crossing.getNextSd());
            jsono.put("next_N_d", crossing.getNextNd());
            
            jsona.add(jsono);
        }
        return jsona.toString();
//        for(int i=0;i<items.getRowCount();i++){
//            JSONObject jsono=new JSONObject();
//            items.setRowIndex(i);
//            Crossing c=(Crossing)items.getRowData();
//            
//            jsono.put("id", c.getId());
//        }
            
    }

}
