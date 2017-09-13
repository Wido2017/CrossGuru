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
    private JSONObject nljson;
    private JSONObject nsjson;
    private JSONObject sljson;
    private JSONObject ssjson;
    private JSONObject wljson;
    private JSONObject wsjson;
    private JSONObject eljson;
    private JSONObject esjson;

    private boolean nlRed;
    private boolean nlGreen;
    private boolean nsRed;
    private boolean nsGreen;
    private boolean slRed;
    private boolean slGreen;
    private boolean ssRed;
    private boolean ssGreen;
    private boolean elRed;
    private boolean elGreen;
    private boolean esRed;
    private boolean esGreen;
    private boolean wlRed;
    private boolean wlGreen;
    private boolean wsRed;
    private boolean wsGreen;
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

    public void setElGreen(boolean elGreen) {
        this.elGreen = elGreen;
    }

    public void setElRed(boolean elRed) {
        this.elRed = elRed;
    }

    public void setEsGreen(boolean esGreen) {
        this.esGreen = esGreen;
    }

    public void setEsRed(boolean esRed) {
        this.esRed = esRed;
    }

    public void setNlGreen(boolean nlGreen) {
        this.nlGreen = nlGreen;
    }

    public void setNlRed(boolean nlRed) {
        this.nlRed = nlRed;
    }

    public void setNsGreen(boolean nsGreen) {
        this.nsGreen = nsGreen;
    }

    public void setNsRed(boolean nsRed) {
        this.nsRed = nsRed;
    }

    public void setSlGreen(boolean slGreen) {
        this.slGreen = slGreen;
    }

    public void setSlRed(boolean slRed) {
        this.slRed = slRed;
    }

    public void setSsRed(boolean ssRed) {
        this.ssRed = ssRed;
    }

    public void setSsGreen(boolean ssGreen) {
        this.ssGreen = ssGreen;
    }

    public void setWsGreen(boolean wsGreen) {
        this.wsGreen = wsGreen;
    }

    public void setWsRed(boolean wsRed) {
        this.wsRed = wsRed;
    }
    

    public boolean isElGreen() {
        return elGreen;
    }

    public boolean isElRed() {
        return elRed;
    }

    public boolean isEsGreen() {
        return esGreen;
    }

    public boolean isEsRed() {
        return esRed;
    }

    public boolean isNlGreen() {
        return nlGreen;
    }

    public boolean isNlRed() {
        return nlRed;
    }

    public boolean isNsGreen() {
        return nsGreen;
    }

    public boolean isNsRed() {
        return nsRed;
    }

    public boolean isSlGreen() {
        return slGreen;
    }

    public boolean isSlRed() {
        return slRed;
    }

    public boolean isSsGreen() {
        return ssGreen;
    }

    public boolean isSsRed() {
        return ssRed;
    }

    public boolean isWlGreen() {
        return wlGreen;
    }

    public boolean isWlRed() {
        return wlRed;
    }

    public boolean isWsGreen() {
        return wsGreen;
    }

    public boolean isWsRed() {
        return wsRed;
    }
}
