/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
/**
 *
 * @author lenovo
 */
@ManagedBean
public class GrowlView {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void saveMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("发送成功！", "您的消息：" +message));
        
    }
}
