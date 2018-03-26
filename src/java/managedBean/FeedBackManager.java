/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author caoxipeng
 */
@Named(value = "feedBackManager")
@ManagedBean
public class FeedBackManager implements Serializable {

    /**
     * Creates a new instance of FeedBackManager
     */
    String feedbackString;
    public FeedBackManager() {
    }

    public void setFeedbackString(String feedbackString) {
        this.feedbackString = feedbackString;
    }

    public String getFeedbackString() {
        return feedbackString;
    }
    
    public void buttonListener(){
        addMessage("感谢您提交建议，我们会不断完善我们的系统!");
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, " ");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
