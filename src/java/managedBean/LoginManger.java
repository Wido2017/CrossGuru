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
import javax.faces.context.FacesContext;

/**
 *
 * @author 曹锡鹏
 */
@Named(value = "loginManger")
@SessionScoped
public class LoginManger implements Serializable {

    /**
     * Creates a new instance of LoginManger
     */
    Police loginPolice = (Police) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("police");
    String isLogin;

    public LoginManger() {
    }

    public String getIsLogin() {
        if (loginPolice == null) {
            return "none";
        } else {
            return "yes";
        }
    }
}
