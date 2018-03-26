package jsf;

import entity.TrafficFlow;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import sessionBean.TrafficFlowFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import org.primefaces.event.SelectEvent;

@Named("trafficFlowController")
@SessionScoped
public class TrafficFlowController implements Serializable {

    private TrafficFlow current;
    private DataModel items = null;
    private DataModel rightTrafficFlows_ = null;
    @EJB
    private sessionBean.TrafficFlowFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<TrafficFlow> rightTrafficFlows;
    private TrafficFlow selectedTrafficFlow=null;
    private Date selectedTime;
    private List<TrafficFlow> trafficFlows;
    
    
    
    public List<TrafficFlow> getTrafficFlows(){
        return trafficFlows;
    }
    
    
    
    public List<TrafficFlow> getRightTrafficFlows_(){
        if (rightTrafficFlows_ == null) {
            rightTrafficFlows_ = getPagination().createPageDataModel();
        }
        Iterator iterator=rightTrafficFlows_.iterator();
            while (iterator.hasNext()) {
            TrafficFlow t = (TrafficFlow) iterator.next();
            if (selectedTime == t.getTime()) {
                rightTrafficFlows.add(t);
            }
        }
        return rightTrafficFlows;
        
    }
    
    public void setRightTrafficFlows(List<TrafficFlow> rightTrafficFlows){
        this.rightTrafficFlows=rightTrafficFlows;
    }
    
    public void setSelectedTime(Date time){
        this.selectedTime=time;
    }
    
    public Date getSelectedTime(){
        return selectedTime;
    }
    
    public void onRowSelect(){
            //selectedTrafficFlow=(TrafficFlow)event.getObject();
            rightTrafficFlows=new ArrayList<>();
            trafficFlows = ejbFacade.findAll();
            Iterator iterator=trafficFlows.iterator();
            while (iterator.hasNext()) {
            TrafficFlow t = (TrafficFlow) iterator.next();
            if (selectedTime == t.getTime()) {
                rightTrafficFlows.add(t);
            }
        }
        }

    public TrafficFlowController() {
    }

    public TrafficFlow getSelected() {
        if (current == null) {
            current = new TrafficFlow();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TrafficFlowFacade getFacade() {
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
        current = (TrafficFlow) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TrafficFlow();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrafficFlowCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TrafficFlow) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrafficFlowUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TrafficFlow) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrafficFlowDeleted"));
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

    public TrafficFlow getTrafficFlow(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TrafficFlow.class)
    public static class TrafficFlowControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrafficFlowController controller = (TrafficFlowController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trafficFlowController");
            return controller.getTrafficFlow(getKey(value));
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
            if (object instanceof TrafficFlow) {
                TrafficFlow o = (TrafficFlow) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TrafficFlow.class.getName());
            }
        }
        
        

    }

}
