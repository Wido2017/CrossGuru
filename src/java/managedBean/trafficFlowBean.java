/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.TrafficFlow;
import jsf.util.JsfUtil;
import sessionBean.TrafficFlowFacade;

import entity.Crossing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import sessionBean.AreaFacade;

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
 * @author niccoleynh
 */
@Named(value = "trafficFlowBean")
@SessionScoped
public class trafficFlowBean implements Serializable{
    @EJB
    private sessionBean.TrafficFlowFacade TrafficFlowFacade;
    @EJB
    private sessionBean.CrossingFacade crossingFacade;
    private List<TrafficFlow> TrafficFlows = null;
    private TrafficFlow selectedTrafficFlow;
    private List<Crossing> crossHasFlow;
    private Crossing selectedCrossing;

    /**
     * Creates a new instance of trafficFlowBean
     */
    public trafficFlowBean() {
    }
    
    public TrafficFlowFacade getTrafficFlowFacade(){
        return TrafficFlowFacade;
    }
    
    public List<TrafficFlow> getTrafficFlow(){
        TrafficFlows=TrafficFlowFacade.findAll();
        return TrafficFlows;
    }
    
    public void setTrafficFlows(List<TrafficFlow> TrafficFlows){
    this.TrafficFlows=TrafficFlows;
}
    
    public TrafficFlow getSelectedTrafficFlow(){
        return selectedTrafficFlow;
    }
    
    public void setSelectedTrafficFlow(TrafficFlow selectedTrafficFlow){
        this.selectedTrafficFlow=selectedTrafficFlow;
    }
    
    public List<Crossing> getCrossHasFlow(){
        return crossHasFlow;
    }
    
    public void setCrossHasFlow(List<Crossing> crossHasFlow){
        this.crossHasFlow=crossHasFlow;
    }
    
    
    public Crossing getSelectedCrossing() {
        return selectedCrossing;
    }

    public void setSelectedCrossing(Crossing selectedCrossing) {
        this.selectedCrossing = selectedCrossing;
    }
    
}
