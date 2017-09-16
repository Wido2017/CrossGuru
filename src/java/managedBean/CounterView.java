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
import org.primefaces.json.JSONObject;

@ManagedBean
public class CounterView implements Serializable {

    private int Nsecond_Red_Up = 0;
    private int Nsecond_Green_Up = 0;
    private int Nsecond_Yellow_Up = 0;
    private int Wsecond_Red_Up = 0;
    private int Wsecond_Green_Up = 0;
    private int Wsecond_Yellow_Up = 0;
    private int Esecond_Red_Up = 0;
    private int Esecond_Green_Up = 0;
    private int Esecond_Yellow_Up = 0;
    private int Ssecond_Red_Up = 0;
    private int Ssecond_Green_Up = 0;
    private int Ssecond_Yellow_Up = 0;
    private int Nsecond_Red_Left = 0;
    private int Nsecond_Green_Left = 0;
    private int Nsecond_Yellow_Left = 0;
    private int Wsecond_Red_Left = 0;
    private int Wsecond_Green_Left = 0;
    private int Wsecond_Yellow_Left = 0;
    private int Esecond_Red_Left = 0;
    private int Esecond_Green_Left = 0;
    private int Esecond_Yellow_Left = 0;
    private int Ssecond_Red_Left = 0;
    private int Ssecond_Green_Left = 0;
    private int Ssecond_Yellow_Left = 0;
    private JSONObject nljson=new JSONObject();
    private JSONObject nsjson=new JSONObject();
    private JSONObject sljson=new JSONObject();
    private JSONObject ssjson=new JSONObject();
    private JSONObject wljson=new JSONObject();
    private JSONObject wsjson=new JSONObject();
    private JSONObject eljson=new JSONObject();
    private JSONObject esjson=new JSONObject();
    private JSONObject outputjs = new JSONObject();

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
            nljson = js.getJSONObject("nl");
            if (nljson.getInt("red") != 0) {
                Nsecond_Red_Left = nljson.getInt("red");
            } else if (nljson.getInt("red") == 0 && nljson.getInt("green") != 0) {
                Nsecond_Green_Left = nljson.getInt("green");
            } else if (nljson.getInt("red") == 0 && nljson.getInt("green") == 0 && nljson.getInt("yellow") != 0) {
                Nsecond_Yellow_Left = nljson.getInt("yellow");
            } else if (nljson.getInt("red") == 0 && nljson.getInt("green") == 0 && nljson.getInt("yellow") == 0 && nljson.getInt("red1") != 0) {
                Nsecond_Red_Left = nljson.getInt("red1");
            }
            nsjson = js.getJSONObject("ns");
            if (nsjson.getInt("red") != 0) {
                Nsecond_Red_Up = nsjson.getInt("red");
            } else if (nsjson.getInt("red") == 0 && nsjson.getInt("green") != 0) {
                Nsecond_Green_Up = nsjson.getInt("green");
            } else if (nsjson.getInt("red") == 0 && nsjson.getInt("green") == 0 && nsjson.getInt("yellow") != 0) {
                Nsecond_Yellow_Up = nsjson.getInt("yellow");
            } else if (nsjson.getInt("red") == 0 && nsjson.getInt("green") == 0 && nsjson.getInt("yellow") == 0 && nsjson.getInt("red1") != 0) {
                Nsecond_Red_Up = nsjson.getInt("red1");
            }
            sljson = js.getJSONObject("sl");
            if (sljson.getInt("red") != 0) {
                Ssecond_Red_Left = sljson.getInt("red");
            } else if (sljson.getInt("red") == 0 && sljson.getInt("green") != 0) {
                Ssecond_Green_Left = sljson.getInt("green");
            } else if (sljson.getInt("red") == 0 && sljson.getInt("green") == 0 && sljson.getInt("yellow") != 0) {
                Ssecond_Yellow_Left = sljson.getInt("yellow");
            } else if (sljson.getInt("red") == 0 && sljson.getInt("green") == 0 && sljson.getInt("yellow") == 0 && sljson.getInt("red1") != 0) {
                Ssecond_Red_Left = sljson.getInt("red1");
            }
            ssjson = js.getJSONObject("ss");
            if (ssjson.getInt("red") != 0) {
                Ssecond_Red_Up = ssjson.getInt("red");
            } else if (ssjson.getInt("red") == 0 && ssjson.getInt("green") != 0) {
                Ssecond_Red_Up = ssjson.getInt("green");
            } else if (ssjson.getInt("red") == 0 && ssjson.getInt("green") == 0 && ssjson.getInt("yellow") != 0) {
                Ssecond_Yellow_Up = ssjson.getInt("yellow");
            } else if (ssjson.getInt("red") == 0 && ssjson.getInt("green") == 0 && ssjson.getInt("yellow") == 0 && ssjson.getInt("red1") != 0) {
                Ssecond_Red_Up = ssjson.getInt("red1");
            }
            eljson = js.getJSONObject("el");
            if (eljson.getInt("red") != 0) {
                Esecond_Red_Left = eljson.getInt("red");
            } else if (eljson.getInt("red") == 0 && eljson.getInt("green") != 0) {
                Esecond_Green_Left = eljson.getInt("green");
            } else if (eljson.getInt("red") == 0 && eljson.getInt("green") == 0 && eljson.getInt("yellow") != 0) {
                Esecond_Yellow_Left = eljson.getInt("yellow");
            } else if (eljson.getInt("red") == 0 && eljson.getInt("green") == 0 && eljson.getInt("yellow") == 0 && eljson.getInt("red1") != 0) {
                Esecond_Red_Left = eljson.getInt("red1");
            }
            esjson = js.getJSONObject("es");
            if (esjson.getInt("red") != 0) {
                Esecond_Red_Up = esjson.getInt("red");
            } else if (esjson.getInt("red") == 0 && esjson.getInt("green") != 0) {
                Esecond_Green_Up = esjson.getInt("green");
            } else if (esjson.getInt("red") == 0 && esjson.getInt("green") == 0 && esjson.getInt("yellow") != 0) {
                Esecond_Yellow_Up = esjson.getInt("yellow");
            } else if (esjson.getInt("red") == 0 && esjson.getInt("green") == 0 && esjson.getInt("yellow") == 0 && esjson.getInt("red1") != 0) {
                Esecond_Red_Up = esjson.getInt("red1");
            }
            wljson = js.getJSONObject("wl");
            if (wljson.getInt("red") != 0) {
                Wsecond_Red_Left = wljson.getInt("red");
            } else if (wljson.getInt("red") == 0 && wljson.getInt("green") != 0) {
                Wsecond_Green_Left = wljson.getInt("green");
            } else if (wljson.getInt("red") == 0 && wljson.getInt("green") == 0 && wljson.getInt("yellow") != 0) {
                Wsecond_Yellow_Left = wljson.getInt("yellow");
            } else if (wljson.getInt("red") == 0 && wljson.getInt("green") == 0 && wljson.getInt("yellow") == 0 && wljson.getInt("red1") != 0) {
                Wsecond_Red_Left = wljson.getInt("red1");
            }
            wsjson = js.getJSONObject("ws");
            if (wsjson.getInt("red") != 0) {
                Wsecond_Red_Up = wsjson.getInt("red");
            } else if (wsjson.getInt("red") == 0 && wsjson.getInt("green") != 0) {
                Wsecond_Green_Up = wsjson.getInt("green");
            } else if (wsjson.getInt("red") == 0 && wsjson.getInt("green") == 0 && wsjson.getInt("yellow") != 0) {
                Wsecond_Yellow_Up = wsjson.getInt("yellow");
            } else if (wsjson.getInt("red") == 0 && wsjson.getInt("green") == 0 && wsjson.getInt("yellow") == 0 && wsjson.getInt("red1") != 0) {
                Wsecond_Red_Up = wsjson.getInt("red1");
            }
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

    public int getSsecond_Green_Left() {
        return Ssecond_Green_Left;
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

    public int getEsecond_Yellow_Left() {
        return Esecond_Yellow_Left;
    }

    public int getEsecond_Yellow_Up() {
        return Esecond_Yellow_Up;
    }

    public int getNsecond_Yellow_Left() {
        return Nsecond_Yellow_Left;
    }

    public int getNsecond_Yellow_Up() {
        return Nsecond_Yellow_Up;
    }

    public int getSsecond_Yellow_Left() {
        return Ssecond_Yellow_Left;
    }

    public int getSsecond_Yellow_Up() {
        return Ssecond_Yellow_Up;
    }

    public int getWsecond_Yellow_Left() {
        return Wsecond_Yellow_Left;
    }

    public int getWsecond_Yellow_Up() {
        return Wsecond_Yellow_Up;
    }

    public void TurnNSlToGreen() {
        Nsecond_Red_Left = 0;
        Ssecond_Red_Left = 0;
        Nsecond_Yellow_Left = 1;
        Ssecond_Yellow_Left = 1;
        appendValue(nljson, 0, 102);
        appendValue(sljson, 0, 102);
        appendValue(nsjson, 34, 68);
        appendValue(ssjson, 34, 68);
        appendValue(wljson, 68, 34);
        appendValue(eljson, 68, 34);
        appendValue(wsjson, 102, 0);
        appendValue(esjson, 102, 0);
        appendObject();

    }

    public void TurnNSsToGreen() {
        Nsecond_Red_Up = Ssecond_Red_Up = 0;
        Nsecond_Yellow_Up = Ssecond_Yellow_Up = 1;
        appendValue(nsjson, 0, 102);
        appendValue(ssjson, 0, 102);
        appendValue(nljson, 34, 68);
        appendValue(sljson, 34, 68);
        appendValue(wsjson, 68, 34);
        appendValue(esjson, 68, 34);
        appendValue(wljson, 102, 0);
        appendValue(eljson, 102, 0);
        appendObject();

    }

    public void TurnWElToGreen() {
        Wsecond_Red_Left = Esecond_Red_Left = 0;
        Wsecond_Yellow_Left = Esecond_Yellow_Left = 1;
        appendValue(wljson, 0, 102);
        appendValue(eljson, 0, 102);
        appendValue(wsjson, 34, 68);
        appendValue(esjson, 34, 68);
        appendValue(nljson, 68, 34);
        appendValue(sljson, 68, 34);
        appendValue(nsjson, 102, 0);
        appendValue(wsjson, 102, 0);
        appendObject();

    }

    public void TurnWEsToGreen() {
        Wsecond_Red_Up = Esecond_Red_Up = 0;
        Wsecond_Yellow_Up = Esecond_Yellow_Up = 1;
        appendValue(wsjson, 0, 102);
        appendValue(esjson, 0, 102);
        appendValue(wljson, 34, 68);
        appendValue(eljson, 34, 68);
        appendValue(nsjson, 68, 34);
        appendValue(ssjson, 68, 34);
        appendValue(nljson, 102, 0);
        appendValue(sljson, 102, 0);
        appendObject();

    }

    private void appendObject() {
        outputjs.append("nl", nljson);
        outputjs.append("sl", sljson);
        outputjs.append("ns", nsjson);
        outputjs.append("ss", ssjson);
        outputjs.append("el", eljson);
        outputjs.append("wl", wljson);
        outputjs.append("es", esjson);
        outputjs.append("ws", wsjson);
    }

    private void appendValue(JSONObject jSONObject, int red, int red1) {
        jSONObject.put("red", red);
        jSONObject.put("green", 30);
        jSONObject.put("yellow", 4);
        jSONObject.put("red1", red1);
    }
}
