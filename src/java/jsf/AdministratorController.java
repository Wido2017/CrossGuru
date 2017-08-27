package jsf;

import entity.Administrator;
import jsf.util.JsfUtil;
<<<<<<< HEAD
import jsf.util.PaginationHelper;
import sessionBean.AdministratorFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
=======
import jsf.util.JsfUtil.PersistAction;
import session.AdministratorFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
>>>>>>> cxp
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
<<<<<<< HEAD
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
=======
>>>>>>> cxp

@Named("administratorController")
@SessionScoped
public class AdministratorController implements Serializable {

<<<<<<< HEAD
    private Administrator current;
    private DataModel items = null;
    @EJB
    private sessionBean.AdministratorFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private String id;
    private String pwd;
    private String error_ = "0";
    private String IDresult;
    private String pwdresult;
    private Administrator loginAdministrator;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setError_(String error_) {
        this.error_ = error_;
    }

    public String getError_() {
        return error_;
    }

    public String getIDresult() {
        return IDresult;
    }

    public void setIDresult(String IDresult) {
        this.IDresult = IDresult;
    }

    public String getPwdresult() {
        return pwdresult;
    }

    public void setPwdresult(String pwdresult) {
        this.pwdresult = pwdresult;
    }

    public Administrator getLoginAdministrator() {
        return loginAdministrator;
    }

    public void setLoginAdministrator(Administrator loginAdministrator) {
        this.loginAdministrator = loginAdministrator;
    }

=======
    @EJB
    private session.AdministratorFacade ejbFacade;
    private List<Administrator> items = null;
    private Administrator selected;
>>>>>>> cxp

    public AdministratorController() {
    }

    public Administrator getSelected() {
<<<<<<< HEAD
        if (current == null) {
            current = new Administrator();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AdministratorFacade getFacade() {
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
        current = (Administrator) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Administrator();
        selectedItemIndex = -1;
        return "Created";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AdministratorCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Administrator) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AdministratorUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String adminiUpdate() {
        try {
            getFacade().edit(loginAdministrator);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AdministratorUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Administrator) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AdministratorDeleted"));
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
=======
        return selected;
    }

    public void setSelected(Administrator selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdministratorFacade getFacade() {
        return ejbFacade;
    }

    public Administrator prepareCreate() {
        selected = new Administrator();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdministratorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdministratorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdministratorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Administrator> getItems() {
        if (items == null) {
            items = getFacade().findAll();
>>>>>>> cxp
        }
        return items;
    }

<<<<<<< HEAD
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

    public Administrator getAdministrator(java.lang.String id) {
        return ejbFacade.find(id);
=======
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Administrator getAdministrator(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Administrator> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Administrator> getItemsAvailableSelectOne() {
        return getFacade().findAll();
>>>>>>> cxp
    }

    @FacesConverter(forClass = Administrator.class)
    public static class AdministratorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdministratorController controller = (AdministratorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "administratorController");
            return controller.getAdministrator(getKey(value));
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
            if (object instanceof Administrator) {
                Administrator o = (Administrator) object;
                return getStringKey(o.getId());
            } else {
<<<<<<< HEAD
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Administrator.class.getName());
=======
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Administrator.class.getName()});
                return null;
>>>>>>> cxp
            }
        }

    }

<<<<<<< HEAD
    public String processLogin() {
        try {
            this.IDresult = (String) ejbFacade.FindID(id);   //找到对应的ID
            this.pwdresult = (String) ejbFacade.FindPassword(id, pwd);  //检测此ID的密码是否等于数据库中的密码，，返回密码；否，返回Null
            if ((IDresult != null) && (pwdresult != null)) {
                loginAdministrator = ejbFacade.findAdminiById(IDresult);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrator", loginAdministrator);
                return "success";
            } else {
                this.error_ = "error";
                return "failure";
            }
        } catch (Exception e) {
            this.error_ = "error";
            return "failure";
        }
    }

=======
>>>>>>> cxp
}
