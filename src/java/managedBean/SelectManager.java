/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "selectManager")
@ManagedBean
public class SelectManager {

    private Date selectDate = new Date();
    private Date currentDate = new Date();
    private String selectDanwei = new String();
    private boolean minute;


    /**
     * Creates a new instance of SelectManager
     */
    public SelectManager() {
    }


    public void Danwei() {
        minute = selectDanwei.equals("分钟");
        Boolean minuteBoolean=minute;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("minuteBool", minuteBoolean);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectDanwei", selectDanwei);
    }

    public void Date() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectDate", selectDate);
    }


    public void buttonAction(ActionEvent actionEvent) {
        minute = selectDanwei.equals("分钟");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectDate", selectDate);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectDanwei", selectDanwei);
        addMessage("正在查询...");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setSelectDanwei(String selectDanwei) {
        this.selectDanwei = selectDanwei;
    }

    public String getSelectDanwei() {
        return selectDanwei;
    }

    public boolean isMinute() {

        return minute;
    }

    public void setMinute(boolean minute) {
        this.minute = minute;
    }

}
