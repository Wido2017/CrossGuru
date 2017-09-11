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
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.json.JSONObject;

@ManagedBean
@ViewScoped
public class CounterView implements Serializable {
 
    private int Nsecond_Red_Up = 15;
    private int Nsecond_Green_Up = 0;
    private int Wsecond_Red_Up=15;
    private int Wsecond_Green_Up=0;
    private int Esecond_Red_Up= 15;
    private int Esecond_Green_Up=0;
    private int Ssecond_Red_Up=15;
    private int Ssecond_Green_Up=0;
    private int Nsecond_Red_Left = 11;
    private int Nsecond_Green_Left =0;
    private int Wsecond_Red_Left=9;
    private int Wsecond_Green_Left=0;
    private int Esecond_Red_Left=16;
    private int Esecond_Green_Left=0;
    private int Ssecond_Red_Left=12;
    private int Ssecond_Green_left=0;
    private String RLight ="red";

    public CounterView() {
        try {
            String host = "localhost";
            int port = 51567;

            Socket client = new Socket(host, port);

            DataOutputStream outputStream = null;
            outputStream = new DataOutputStream(client.getOutputStream());

            DataInputStream inputStream = null;
            String strInputStream = "";

            inputStream = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            byte[] by = new byte[20480];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int nbyte;
            while ((nbyte = inputStream.read(by)) != -1) {
                baos.write(by, 0, nbyte);
            }
            strInputStream = new String(baos.toByteArray());

            JSONObject js = new JSONObject(strInputStream);
            JSONObject nljson=js.getJSONObject("nl");
            Nsecond_Red_Up=nljson.getInt("red");
            System.out.println(js.toString());
        } catch (IOException ex) {
            Logger.getLogger(CounterView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public int getWsecond_Red_Left() {
        return Wsecond_Red_Left;
    }

    public int getWsecond_Green_Left() {
        return Wsecond_Green_Left;
    }

    public int getSsecond_Red_Left() {
        return Ssecond_Red_Left;
    }

    public int getSsecond_Green_left() {
        return Ssecond_Green_left;
    }

    public int getNsecond_Red_Left() {
        return Nsecond_Red_Left;
    }

    public int getNsecond_Green_Left() {
        return Nsecond_Green_Left;
    }

    public int getEsecond_Red_Left() {
        return Esecond_Red_Left;
    }

    public int getEsecond_Green_Left() {
        return Esecond_Green_Left;
    }

    public String getRLight() {
        return RLight;
    }
    
    
    public int getNsecond_Red_Up() {
        return Nsecond_Red_Up;
    }

    public int getNsecond_Green_Up() {
        return Nsecond_Green_Up;
    }

    public int getEsecond_Green_Up() {
        return Esecond_Green_Up;
    }

    public int getEsecond_Red_Up() {
        return Esecond_Red_Up;
    }

    public int getSsecond_Green_Up() {
        return Ssecond_Green_Up;
    }

    public int getSsecond_Red_Up() {
        return Ssecond_Red_Up;
    }

    public int getWsecond_Green_Up() {
        return Wsecond_Green_Up;
    }

    public int getWsecond_Red_Up() {
        return Wsecond_Red_Up;
    }
    
    public void WsecondRedUpDecrement(){
        if (Wsecond_Red_Up > 0&& Wsecond_Green_Up ==0){
            Wsecond_Red_Up--;
            if (Wsecond_Red_Up==0) {
                Wsecond_Green_Up=15;
            }
        }else{
            return;
        }
    }
    
    public void WsecondGreenUpDecrement(){
        if (Wsecond_Green_Up>0&&Wsecond_Red_Up==0){
            Wsecond_Green_Up--;
            if (Wsecond_Green_Up==0) {
                Wsecond_Red_Up=15;
            }
        }else{
            return;
        }
    }
    
    public void SsecondRedUpDecrement(){
        if (Ssecond_Red_Up > 0&& Ssecond_Green_Up ==0){
            Ssecond_Red_Up--;
            if (Ssecond_Red_Up==0) {
                Ssecond_Green_Up=15;
            }
        }else{
            return;
        }
    }
    public void SsecondGreenUpDecrement(){
        if (Ssecond_Green_Up>0&&Ssecond_Red_Up==0){
            Ssecond_Green_Up--;
            if (Ssecond_Green_Up==0) {
                Ssecond_Red_Up=15;
            }
        }else{
            return;
        }
    }
    public void EsecondRedUpDecrement(){
        if (Esecond_Red_Up > 0&& Esecond_Green_Up ==0){
            Esecond_Red_Up--;
            if (Esecond_Red_Up==0) {
                Esecond_Green_Up=15;
            }
        }else{
            return;
        }
    }

    public void EsecondGreenUpDecrement(){
        if (Esecond_Green_Up>0&&Esecond_Red_Up==0){
            Esecond_Green_Up--;
            if (Esecond_Green_Up==0) {
                Esecond_Red_Up=15;
            }
        }else{
            return;
        }
    }
    
    public void NsecondRedUpDecrement() {
        if (Nsecond_Red_Up > 0 && Nsecond_Green_Up == 0) {
            Nsecond_Red_Up--;
            if (Nsecond_Red_Up == 0) {
                Nsecond_Green_Up = 15;
            }
        } else {
            return;
        }
    }

    public void NsecondGreenUpDecrement() {
        if (Nsecond_Green_Up > 0 && Nsecond_Red_Up == 0) {
            Nsecond_Green_Up--;
            if (Nsecond_Green_Up == 0) {
                Nsecond_Red_Up = 15;
            }
        } else {
            return;
        }
    }
    
    public void NsecondRedLeftDecrement(){
        if(Nsecond_Red_Left>0&& Nsecond_Green_Left==0){
            Nsecond_Red_Left--;
            if (Nsecond_Red_Left==0) {
                Nsecond_Green_Left=12;
            }
        }else{
            
        }
    }
}
