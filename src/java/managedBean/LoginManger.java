/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Administrator;
import entity.Crossing;
import entity.Police;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import jsf.util.JsfUtil;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "loginManger")
@ManagedBean
public class LoginManger implements Serializable {

    /**
     * Creates a new instance of LoginManger
     */
    @EJB
    private sessionBean.CrossingFacade ejbFacade;

    Police loginPolice = (Police) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("police");
    Administrator loginAdministrator = (Administrator) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrator");
    List<Crossing> crossingList;
    Crossing currentCrossing;

    public Crossing getCurrentCrossing() {
        return currentCrossing;
    }

    public void setCurrentCrossing(Crossing currentCrossing) {
        this.currentCrossing = currentCrossing;
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        if (crossingList != null) {
            return JsfUtil.getSelectItems(crossingList, true);
        }
        return null;
    }

    public void doRefresh(){
//        if(loginPolice==null)return;
//        getCrossingList();
//        if(crossingList.isEmpty())
//            return;
        List<Crossing> crossings=ejbFacade.findAll();
        Crossing cross=ejbFacade.find("20170810");
        ejbFacade.refresh(cross);
        for (Crossing crossing : crossings) {
             ejbFacade.refresh(crossing);
        }       
    }
    
    public LoginManger() {
    }

    public Police getLoginPolice() {
        return loginPolice;
    }

    public Administrator getLoginAdministrator() {
        return loginAdministrator;
    }

    public void setLoginPolice(Police loginPolice) {
        this.loginPolice = loginPolice;
    }

    public void setLoginAdministrator(Administrator loginAdministrator) {
        this.loginAdministrator = loginAdministrator;
    }

    public List<Crossing> getCrossingList() {
        crossingList = (List<Crossing>) loginPolice.getAreaId().getCrossingCollection();
        return crossingList;
    }

    public String getCrossingArray() {
        if (loginPolice == null) {
            return null;
        }
        getCrossingList();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        int i = 0;
        getCrossingList();
        int crossingNo = crossingList.size();
        for (; i < crossingNo; i++) {
            Crossing crossing = crossingList.get(i);
            jsonArrayBuilder
                    .add(Json.createObjectBuilder()
                            .add("id", crossing.getId())
                            .add("location", crossing.getLocation())
                            .add("coordx", crossing.getCoordx())
                            .add("coordy", crossing.getCoordy())
                            .add("currentFlowW", crossing.getCurrentFlowW())
                            .add("currentFlowN", crossing.getCurrentFlowN())
                            .add("currentFlowS", crossing.getCurrentFlowS())
                            .add("currentFlowE", crossing.getCurrentFlowE()));
        }
        JsonArray ja = jsonArrayBuilder.build();

        String jsonString = ja.toString();

        return jsonString;
    }

    public String isLogin1() {
        if (loginPolice == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("提示", "请登录后查看记录！"));
            return "none";
        } else {
            return "ok_jiankong";
        }
    }

    public String isLogin2() {
        if (loginPolice == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("提示", "请登录后查看记录！"));
            return "none";
        } else {
            return "ok";
        }
    }

    public String isLogout() {
        if (loginAdministrator == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("提示", "已注销！"));
            return "logout";
        } else {
            return "ok";
        }
    }

    public String policeLogoutListener() {
        loginPolice = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("police", null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("提示", "已注销！"));
        return "home";
    }

    public void adminLogoutListener() {
        loginAdministrator = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrator", null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("提示", "已注销！"));
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
