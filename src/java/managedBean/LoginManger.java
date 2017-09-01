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

    public LoginManger() {
    }

    public Police getLoginPolice() {
        return loginPolice;
    }

    public void setLoginPolice(Police loginPolice) {
        this.loginPolice = loginPolice;
    }

    public String isLogin() {
        if (loginPolice == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("提示", "请登录后查看记录！"));
            return "none";
        } else {
            return "ok";
        }
    }

    public void logoutListener() {
        loginPolice = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("police", null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("提示", "已注销！"));
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
