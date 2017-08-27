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
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.faces.bean.ManagedBean;
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
    @EJB
    private session.CrossingFacade crossingFacade;
    private List<Area> areas = null;
    private Area selectedArea;
    private List<Crossing> areaHasCrossings;
    private Crossing selectedCrossing;

    /**
     * Creates a new instance of crossManager
     */
    public crossManager() {
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    public List<Area> getAreas() {
        areas = areaFacade.findAll();
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

    public void onRowSelect(SelectEvent event) {
        selectedArea = (Area) event.getObject();
        areaHasCrossings = new ArrayList<>();
        List<Crossing> crossings = crossingFacade.findAll();
        Iterator iterator = crossings.iterator();
        while (iterator.hasNext()) {
            Crossing c = (Crossing) iterator.next();
            if (c.getAreaId().getId() == null ? selectedArea.getId() == null : c.getAreaId().getId().equals(selectedArea.getId())) {
                areaHasCrossings.add(c);
            }
        }
        FacesMessage msg = new FacesMessage("Area Selected", ((Area) event.getObject()).getName() + " " + ((Area) event.getObject()).getLocation());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowSelectCrossing(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Crossing Selected", ((Crossing) event.getObject()).getId() + " " + ((Crossing) event.getObject()).getLocation());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onClickCreateButton() {
        selectedCrossing = new Crossing();
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

    public void updateSelectCrossing() {
        crossingFacade.edit(selectedCrossing);
        FacesMessage msg = new FacesMessage(" ", "修改成功");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createNewCrossing() {
        selectedCrossing.setAreaId(selectedArea);
        crossingFacade.create(selectedCrossing);
        selectedCrossing = new Crossing();
        FacesMessage msg = new FacesMessage(" ", "创建成功");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        areaHasCrossings.clear();
        List<Crossing> crossings = crossingFacade.findAll();
        Iterator iterator = crossings.iterator();
        while (iterator.hasNext()) {
            Crossing c = (Crossing) iterator.next();
            if (c.getAreaId().getId() == selectedArea.getId()) {
                areaHasCrossings.add(c);
            }
        }
    }

    public void deleteSelectCrossing() {
        if (selectedCrossing != null) {
            crossingFacade.remove(selectedCrossing);
            selectedCrossing = null;
            areaHasCrossings.clear();
            List<Crossing> crossings = crossingFacade.findAll();
            Iterator iterator = crossings.iterator();
            while (iterator.hasNext()) {
                Crossing c = (Crossing) iterator.next();
                if (c.getAreaId().getId() == null ? selectedArea.getId() == null : c.getAreaId().getId().equals(selectedArea.getId())) {
                    areaHasCrossings.add(c);
                }
            }
        }
    }
}
