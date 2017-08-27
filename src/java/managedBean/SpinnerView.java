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
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SpinnerView {                   

    private int Nsecond_Red_up;
    private int Nsecond_Green_up;
    private int Nsecond_Red_left;

    public int getNsecond_Red_up() {
        return Nsecond_Red_up;
    }

    public void setNsecond_Red_up(int Nsecond_Red_up) {
        this.Nsecond_Red_up = Nsecond_Red_up;
    }

}
