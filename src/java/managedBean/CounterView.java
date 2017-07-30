/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

/**
 *
 * @author 曹锡鹏
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CounterView implements Serializable {

    private int Nsecond_Red_Up = 2;
    private int Nsecond_Green_Up = 0;

    public int getNsecond_Red_Up() {
        return Nsecond_Red_Up;
    }

    public int getNsecond_Green_Up() {
        return Nsecond_Green_Up;
    }

    public void NsecondRedUpDecrement() {
        if (Nsecond_Red_Up > 0 && Nsecond_Green_Up == 0) {
            Nsecond_Red_Up--;
            if (Nsecond_Red_Up == 0) {
                Nsecond_Green_Up = 3;
            }
        } else {
            return;
        }
    }

    public void NsecondGreenUpDecrement() {
        if (Nsecond_Green_Up > 0 && Nsecond_Red_Up == 0) {
            Nsecond_Green_Up--;
            if (Nsecond_Green_Up == 0) {
                Nsecond_Red_Up = 3;
            }
        } else {
            return;
        }
    }
}
