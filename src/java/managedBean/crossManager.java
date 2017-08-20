/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Crossing;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import session.CrossingFacade;

import entity.Area;
import java.io.Serializable;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import session.AreaFacade;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "crossManager")
@SessionScoped
public class crossManager implements Serializable {

    @EJB
    private session.AreaFacade areaFacade;
    private List<Area> areas = null;
    private Area selectedArea;
    private List<Crossing> areaHasCrossings;
    private Crossing selectedCrossing;

    private Area createArea;

    /**
     * Creates a new instance of crossManager
     */
    public crossManager() {
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    public List<Area> getAreas() {
            areas = getAreaFacade().findAll();
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Area getSelectedArea() {
        return selectedArea;
    }

    public void setSelectedArea(Area selectedArea) {
        this.selectedArea = selectedArea;
    }

    public Area getCreateArea() {
        return createArea;
    }

    public void setCreateArea(Area createArea) {
        this.createArea = createArea;
    }

    public void onRowSelect(SelectEvent event) {
        areaHasCrossings = (List<Crossing>) selectedArea.getCrossingCollection();
        FacesMessage msg = new FacesMessage("Area Selected", ((Area) event.getObject()).getName() + " " + ((Area) event.getObject()).getLocation());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Crossing> getAreaHasCrossings() {
        return areaHasCrossings;
    }

    public void setAreaHasCrossings(List<Crossing> areaHasCrossings) {
        this.areaHasCrossings = areaHasCrossings;
    }

    public Crossing getSelectedCrossing() {
        return selectedCrossing;
    }

    public void setSelectedCrossing(Crossing selectedCrossing) {
        this.selectedCrossing = selectedCrossing;
    }

    public void deleteSelectArea() {
        if (selectedArea != null) {
            areaFacade.remove(selectedArea);
            selectedArea = null;
        }
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Area areaPrepareCreate() {
        createArea = new Area();
        initializeEmbeddableKey();
        return createArea;
    }

    public void aeraCreate() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AreaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            areas = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AreaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AreaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            createArea = null; // Remove selection
            areas = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (createArea != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getAreaFacade().edit(createArea);
                } else {
                    getAreaFacade().remove(createArea);
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
}
