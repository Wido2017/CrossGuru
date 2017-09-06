/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "shiduanManager")
@ManagedBean
public class ShiduanManager {

    private Boolean minute = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("minuteBool");
    private String selectHour = new String();
    private List<SelectItem> hours;

    /**
     * Creates a new instance of ShiduanManager
     */
    public ShiduanManager() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectHour", selectHour);
    }
    @PostConstruct
    public void init() {
        hours = new ArrayList<>();
        hours.add(new SelectItem("00:00-01:00", "00:00-01:00"));
        hours.add(new SelectItem("01:00-02:00", "01:00-02:00"));
        hours.add(new SelectItem("02:00-03:00", "02:00-03:00"));
        hours.add(new SelectItem("03:00-04:00", "03:00-04:00"));
        hours.add(new SelectItem("04:00-05:00", "04:00-05:00"));
        hours.add(new SelectItem("05:00-06:00", "05:00-06:00"));
        hours.add(new SelectItem("06:00-07:00", "06:00-07:00"));
        hours.add(new SelectItem("07:00-08:00", "07:00-08:00"));
        hours.add(new SelectItem("08:00-09:00", "08:00-09:00"));
        hours.add(new SelectItem("09:00-10:00", "09:00-10:00"));
        hours.add(new SelectItem("10:00-11:00", "10:00-11:00"));
        hours.add(new SelectItem("11:00-12:00", "11:00-12:00"));
        hours.add(new SelectItem("12:00-13:00", "12:00-13:00"));
        hours.add(new SelectItem("13:00-14:00", "13:00-14:00"));
        hours.add(new SelectItem("14:00-15:00", "14:00-15:00"));
        hours.add(new SelectItem("15:00-16:00", "15:00-16:00"));
        hours.add(new SelectItem("16:00-17:00", "16:00-17:00"));
        hours.add(new SelectItem("17:00-18:00", "17:00-18:00"));
        hours.add(new SelectItem("18:00-19:00", "18:00-19:00"));
        hours.add(new SelectItem("19:00-20:00", "19:00-20:00"));
        hours.add(new SelectItem("20:00-21:00", "20:00-21:00"));
        hours.add(new SelectItem("21:00-22:00", "21:00-22:00"));
        hours.add(new SelectItem("22:00-23:00", "22:00-23:00"));
        hours.add(new SelectItem("23:00-24:00", "23:00-24:00"));
    }
    
    public void selectHourEvent() {
        minute = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectHour", selectHour);
    }

    public Boolean getMinute() {
        return minute;
    }

    public void setMinute(Boolean minute) {
        this.minute = minute;
    }

    public List<SelectItem> getHours() {
        return hours;
    }

    public void setHours(List<SelectItem> hours) {
        this.hours = hours;
    }

    public String getSelectHour() {
        return selectHour;
    }

    public void setSelectHour(String selectHour) {
        this.selectHour = selectHour;
    }

}
