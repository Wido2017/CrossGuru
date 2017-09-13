package org.primefaces.showcase.view.df;
 
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
 
@Named("dfView")
public class DFView {
         
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}