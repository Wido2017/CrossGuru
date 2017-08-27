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
import entity.Crossing;
import entity.TrafficFlow;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.CrossingController;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@Named("ChartView")
@ManagedBean
public class LineChartView implements Serializable {

    private LineChartModel lineModel;

    private Date selectDate = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectDate");
    private String selectDanwei = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectDanwei");
    private Crossing selectedCrossing = (Crossing) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectCrossing");
    private DateFormat dateFormat_month = new SimpleDateFormat("yyyy-MM");
    private DateFormat dateFormat_day = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dateFormat_hour = new SimpleDateFormat("yyyy-MM-dd HH");
    private DateFormat dateFormat_minute = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @PostConstruct
    public void init() {
        createLineModel();
    }
    @EJB
    private session.CrossingFacade crossingFacade;
    @EJB
    private session.TrafficFlowFacade trafficFlowFacade;

    public LineChartModel getLineModel() {
        return lineModel;
    }

    private void createLineModel() {
        lineModel = initCategoryModel();
        lineModel.setLegendPosition("e");
        lineModel.setAnimate(true);
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("时间段"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("数量(十万辆)");
        yAxis.setMin(0);
        yAxis.setMax(200);
        Axis xAxis = lineModel.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        ChartSeries line = new ChartSeries();
        line.setLabel("车辆数");
        if (selectDate == null) {
            selectDate = new Date();
        }
        if (selectDanwei == null) {
            selectDanwei = "天";
        }

        if (crossingFacade.findAll() == null) {
            line.set(null, null);
            model.addSeries(line);
            return model;
        }
        if (selectedCrossing == null && selectDanwei.equals("天") && selectDate != null) {
            String selectDateString = dateFormat_day.format(selectDate);
            String formatDate_day = new String();
            String endDateString = dateFormat_day.format(selectDate);
            String startDateString = dateFormat_month.format(selectDate).concat("-01");
            List<String> datesList = collectLocalDates(LocalDate.parse(startDateString), LocalDate.parse(endDateString));
            Iterator datesIterator = datesList.iterator();
            Crossing c = crossingFacade.findAll().get(0);
            Collection<TrafficFlow> tfs = c.getTrafficFlowCollection();
            boolean setline = false;
            while (datesIterator.hasNext()) {
                String dateString = (String) datesIterator.next();
                Iterator it = tfs.iterator();
                long avg = 0;
                while (it.hasNext()) {
                    TrafficFlow tf = (TrafficFlow) it.next();
                    formatDate_day = dateFormat_day.format(tf.getTime());
                    if (formatDate_day.equals(dateString)) {
                        avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                    }
                }
                if (avg != 0) {
                    setline = true;
                    line.set(dateString, avg);
                }
            }
            if (setline == false) {
                line.set(null, null);
            }
        } else if (selectedCrossing == null && selectDanwei.equals("小时") && selectDate != null) {
            try {
                String selectDateString = dateFormat_hour.format(selectDate);
                String formatDate_hour = new String();
                String endDateString = dateFormat_day.format(selectDate).concat(" 24");
                String startDateString = dateFormat_day.format(selectDate).concat(" 00");
                boolean setline = false;
                List<String> hoursList = new ArrayList<>();
                Date begin = dateFormat_hour.parse(startDateString);
                Date end = dateFormat_hour.parse(endDateString);
                Calendar cal = Calendar.getInstance();
                while (begin.before(end)) {
                    hoursList.add(dateFormat_hour.format(begin));
                    cal.setTime(begin);
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    begin = cal.getTime();
                }

                Iterator hoursIterator = hoursList.iterator();
                Crossing c = crossingFacade.findAll().get(0);
                Collection<TrafficFlow> tfs = c.getTrafficFlowCollection();
                while (hoursIterator.hasNext()) {
                    String hourString = (String) hoursIterator.next();
                    Iterator it = tfs.iterator();
                    long avg = 0;
                    while (it.hasNext()) {
                        TrafficFlow tf = (TrafficFlow) it.next();
                        formatDate_hour = dateFormat_hour.format(tf.getTime());
                        if (formatDate_hour.equals(hourString)) {
                            avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                        }
                    }
                    if (avg != 0) {
                        String addHourString = (String) hourString.subSequence(10, 13);
                        setline = true;
                        line.set(addHourString.concat(":00"), avg);
                    }
                }
                if (setline == false) {
                    line.set(null, null);
                }
            } catch (ParseException ex) {
                Logger.getLogger(LineChartView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selectedCrossing == null && selectDanwei.equals("分钟") && selectDate != null) {
            try {
                String selectHour = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectHour");
                if (selectHour.equals("")) {
                    selectHour = "00:00-01:00";
                }
                String selectDateString = dateFormat_day.format(selectDate);
                String startString = selectDateString.concat(" " + selectHour.substring(0, 5));
                String endString = selectDateString.concat(" " + selectHour.substring(6));
                String formatDate_minute = new String();
                boolean setline = false;
                List<String> minuteList = new ArrayList<>();
                Date begin = dateFormat_minute.parse(startString);
                Date end = dateFormat_minute.parse(endString);
                Calendar cal = Calendar.getInstance();
                while (begin.before(end)) {
                    minuteList.add(dateFormat_minute.format(begin));
                    cal.setTime(begin);
                    cal.add(Calendar.MINUTE, 1);
                    begin = cal.getTime();
                }
                Iterator minuteIterator = minuteList.iterator();
                Crossing c = crossingFacade.findAll().get(0);
                Collection<TrafficFlow> tfs = c.getTrafficFlowCollection();
                while (minuteIterator.hasNext()) {
                    String minuteString = (String) minuteIterator.next();
                    Iterator it = tfs.iterator();
                    long avg = 0;
                    while (it.hasNext()) {
                        TrafficFlow tf = (TrafficFlow) it.next();
                        formatDate_minute = dateFormat_minute.format(tf.getTime());
                        if (formatDate_minute.equals(minuteString)) {
                            avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                        }
                    }
                    if (avg != 0) {
                        setline = true;
                        String minuteString1 = formatDate_minute.substring(11, 16);
                        line.set(minuteString1, avg);
                    }

                }
                if (setline == false) {
                    line.set(null, null);
                }
            } catch (ParseException ex) {
                Logger.getLogger(LineChartView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selectedCrossing != null && selectDanwei.equals("天") && selectDate != null) {
            String selectDateString = dateFormat_day.format(selectDate);
            String formatDate_day = new String();
            String endDateString = dateFormat_day.format(selectDate);
            String startDateString = dateFormat_month.format(selectDate).concat("-01");
            List<String> datesList = collectLocalDates(LocalDate.parse(startDateString), LocalDate.parse(endDateString));
            Iterator datesIterator = datesList.iterator();
            Collection<TrafficFlow> tfs = selectedCrossing.getTrafficFlowCollection();
            boolean setline = false;
            while (datesIterator.hasNext()) {
                String dateString = (String) datesIterator.next();
                Iterator it = tfs.iterator();
                long avg = 0;
                while (it.hasNext()) {
                    TrafficFlow tf = (TrafficFlow) it.next();
                    formatDate_day = dateFormat_day.format(tf.getTime());
                    if (formatDate_day.equals(dateString)) {
                        avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                    }
                }
                if (avg != 0) {
                    setline = true;
                    line.set(dateString, avg);
                }
            }
            if (setline == false) {
                line.set(null, null);
            }
        } else if (selectedCrossing != null && selectDanwei.equals("小时") && selectDate != null) {
            try {
                String selectDateString = dateFormat_hour.format(selectDate);
                String formatDate_hour = new String();
                String endDateString = dateFormat_day.format(selectDate).concat(" 24");
                String startDateString = dateFormat_day.format(selectDate).concat(" 00");
                boolean setline = false;
                List<String> hoursList = new ArrayList<>();
                Date begin = dateFormat_hour.parse(startDateString);
                Date end = dateFormat_hour.parse(endDateString);
                Calendar cal = Calendar.getInstance();
                while (begin.before(end)) {
                    hoursList.add(dateFormat_hour.format(begin));
                    cal.setTime(begin);
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    begin = cal.getTime();
                }

                Iterator hoursIterator = hoursList.iterator();
                Collection<TrafficFlow> tfs = selectedCrossing.getTrafficFlowCollection();
                while (hoursIterator.hasNext()) {
                    String hourString = (String) hoursIterator.next();
                    Iterator it = tfs.iterator();
                    long avg = 0;
                    while (it.hasNext()) {
                        TrafficFlow tf = (TrafficFlow) it.next();
                        formatDate_hour = dateFormat_hour.format(tf.getTime());
                        if (formatDate_hour.equals(hourString)) {
                            avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                        }
                    }
                    if (avg != 0) {
                        String addHourString = (String) hourString.subSequence(10, 13);
                        setline = true;
                        line.set(addHourString.concat(":00"), avg);
                    }
                }
                if (setline == false) {
                    line.set(null, null);
                }
            } catch (ParseException ex) {
                Logger.getLogger(LineChartView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selectedCrossing != null && selectDanwei.equals("分钟") && selectDate != null) {
            try {
                String selectHour = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectHour");
                if (selectHour.equals("")) {
                    selectHour = "00:00-01:00";
                }
                String selectDateString = dateFormat_day.format(selectDate);
                String startString = selectDateString.concat(" " + selectHour.substring(0, 5));
                String endString = selectDateString.concat(" " + selectHour.substring(6));
                String formatDate_minute = new String();
                boolean setline = false;
                List<String> minuteList = new ArrayList<>();
                Date begin = dateFormat_minute.parse(startString);
                Date end = dateFormat_minute.parse(endString);
                Calendar cal = Calendar.getInstance();
                while (begin.before(end)) {
                    minuteList.add(dateFormat_minute.format(begin));
                    cal.setTime(begin);
                    cal.add(Calendar.MINUTE, 1);
                    begin = cal.getTime();
                }
                Iterator minuteIterator = minuteList.iterator();
                Collection<TrafficFlow> tfs = selectedCrossing.getTrafficFlowCollection();
                while (minuteIterator.hasNext()) {
                    String minuteString = (String) minuteIterator.next();
                    Iterator it = tfs.iterator();
                    long avg = 0;
                    while (it.hasNext()) {
                        TrafficFlow tf = (TrafficFlow) it.next();
                        formatDate_minute = dateFormat_minute.format(tf.getTime());
                        if (formatDate_minute.equals(minuteString)) {
                            avg = (avg + (tf.getCrossingE() + tf.getCrossingN() + tf.getCrossingS() + tf.getCrossingW()) / 4) / 2;
                        }
                    }
                    if (avg != 0) {
                        setline = true;
                        String minuteString1 = formatDate_minute.substring(11, 16);
                        line.set(minuteString1, avg);
                    }

                }
                if (setline == false) {
                    line.set(null, null);
                }
            } catch (ParseException ex) {
                Logger.getLogger(LineChartView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        model.addSeries(line);

        return model;
    }

    public static List<String> collectLocalDates(LocalDate start, LocalDate end) {
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                // 由于最后要的是字符串，所以map转换一下
                .map(LocalDate::toString)
                // 把流收集为List
                .collect(Collectors.toList());
    }

}
