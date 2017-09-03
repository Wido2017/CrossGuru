/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Administrator;
import entity.Police;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
    Police loginPolice = (Police) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("police");
    Administrator loginAdministrator = (Administrator) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrator");

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

    public String isLogout(){
        if (loginAdministrator == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("提示", "已注销！"));
            return "logout";
        } else {
            return "ok";
        }
    }
    public void policeLogoutListener() {
        loginPolice = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("police", null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("提示", "已注销！"));
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
