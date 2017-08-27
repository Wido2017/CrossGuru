/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entityBean.Camera;
import entityBean.Crossing;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author QIULI
 */
@Named(value = "crossingController")
@SessionScoped
public class CrossingController implements Serializable {

    private Camera positionCamera;
    private Crossing currentCrossing;
    
    public CrossingController() {
    }
    
}
