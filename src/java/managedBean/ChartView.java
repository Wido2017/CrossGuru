/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

/**
 *
 * @author 曹锡鹏
 */
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartView implements Serializable {

    private LineChartModel lineModel;
    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createLineModel();
        createBarModel();
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createLineModel() {
        lineModel = initCategoryModel();
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("时间段"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("数量(十万辆)");
        yAxis.setMin(0);
        yAxis.setMax(200);
        Axis xAxis=lineModel.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new ChartSeries();
        series1.setLabel("车辆数");
        series1.set("0:00-2:00", 10);
        series1.set("2:00-4:00", 8);
        series1.set("4:00-6:00", 11);
        series1.set("6:00-8:00", 150);
        series1.set("8:00-10:00", 80);
        series1.set("10:00-12:00", 110);
        series1.set("12:00-14:00", 120);
        series1.set("14:00-16:00", 70);
        series1.set("16:00-18:00", 180);
        series1.set("18:00-20:00", 90);
        series1.set("20:00-22:00", 80);
        series1.set("22:00-24:00", 40);


        model.addSeries(series1);

        return model;
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setLegendPosition("e");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("路口");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("数量(十万辆)");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("车辆数");
        boys.set("路口1", 200);
        boys.set("路口2", 190);
        boys.set("路口3", 185);
        boys.set("路口4", 160);
        boys.set("路口5", 155);


        model.addSeries(boys);

        return model;
    }

}
