/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Crossing;
import entity.Police;
import entity.TrafficFlow;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "barChartView")
@ManagedBean
@SessionScoped
public class BarChartView implements Serializable{

    /**
     * Creates a new instance of BarChartView
     */
    private BarChartModel barModel;
    private ChartSeries crossings = new ChartSeries();
    
    private Date selectDate = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectDate");
    private String selectDanwei = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectDanwei");
    private Police loginPolice=(Police) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("police");
    
    private DateFormat dateFormat_month = new SimpleDateFormat("yyyy-MM");
    private DateFormat dateFormat_day = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dateFormat_hour = new SimpleDateFormat("yyyy-MM-dd HH");
    private DateFormat dateFormat_minute = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @PostConstruct
    public void init() {
        createBarModel();
    }
    @EJB
    private sessionBean.CrossingFacade crossingFacade;
    @EJB
    private sessionBean.TrafficFlowFacade trafficFlowFacade;

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setLegendPosition("e");
        barModel.setAnimate(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("路口");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("数量(辆)");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {

        BarChartModel model = new BarChartModel();

        crossings.setLabel("车辆数");
        if(loginPolice.getAreaId().getCrossingCollection().isEmpty())
        {
            crossings.set("无数据！",0);
            model.addSeries(crossings);
            return model;
        }
        List<Crossing> crossingList = (List<Crossing>) loginPolice.getAreaId().getCrossingCollection();
        Iterator iterator = crossingList.iterator();
        long avg = 0;
        while (iterator.hasNext()) {
            Crossing c = (Crossing) iterator.next();
            Collection<TrafficFlow> trafficFlows = c.getTrafficFlowCollection();
            if (trafficFlows.isEmpty()) {
                continue;
            }
            Iterator iterator1 = trafficFlows.iterator();
            long sum = 0;
            while (iterator1.hasNext()) {
                TrafficFlow t = (TrafficFlow) iterator1.next();
                sum = sum + t.getCrossingE() + t.getCrossingN() + t.getCrossingS() + t.getCrossingW();
            }
            avg = sum / (4 * trafficFlows.size());
            crossings.set(c.getLocation(), avg);
        }
        model.addSeries(crossings);

        return model;
    }

    public void itemSelect(ItemSelectEvent event) {
        Map datasMap = crossings.getData();
        Iterator it = datasMap.entrySet().iterator();
        String key = "";
        for (int i = 0; i <= event.getItemIndex(); i++) {
            Map.Entry mapentry = (Map.Entry) it.next();
            key = mapentry.getKey().toString();
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "选择路口:", key);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        List<Crossing> crossingList = crossingFacade.findAll();
        it = crossingList.iterator();
        Crossing selectCrossing=new Crossing();
        while (it.hasNext()) {
            Crossing c = (Crossing) it.next();
            if (c.getLocation().equals(key)) {
                selectCrossing = c;
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectCrossing", selectCrossing);
    }
}
