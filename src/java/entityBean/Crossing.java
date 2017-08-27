/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author QIULI
 */
@Entity
@Table(name = "crossing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crossing.findAll", query = "SELECT c FROM Crossing c")
    , @NamedQuery(name = "Crossing.findById", query = "SELECT c FROM Crossing c WHERE c.id = :id")
    , @NamedQuery(name = "Crossing.findByLocation", query = "SELECT c FROM Crossing c WHERE c.location = :location")})
public class Crossing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "location")
    private String location;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne
    private Area areaId;
    @JoinColumn(name = "camera_id_W", referencedColumnName = "id")
    @ManyToOne
    private Camera cameraidW;
    @JoinColumn(name = "camera_id_S", referencedColumnName = "id")
    @ManyToOne
    private Camera cameraidS;
    @JoinColumn(name = "camera_id_N", referencedColumnName = "id")
    @ManyToOne
    private Camera cameraidN;
    @JoinColumn(name = "camera_id_E", referencedColumnName = "id")
    @ManyToOne
    private Camera cameraidE;
    @JoinColumn(name = "trafficLight_id_W", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidW;
    @JoinColumn(name = "trafficLight_id_S", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidS;
    @JoinColumn(name = "trafficLight_id_N", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidN;
    @JoinColumn(name = "trafficLight_id_E", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidE;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crossingId")
    private Collection<TrafficFlow> trafficFlowCollection;

    public Crossing() {
    }

    public Crossing(String id) {
        this.id = id;
    }

    public Crossing(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Camera getCameraidW() {
        return cameraidW;
    }

    public void setCameraidW(Camera cameraidW) {
        this.cameraidW = cameraidW;
    }

    public Camera getCameraidS() {
        return cameraidS;
    }

    public void setCameraidS(Camera cameraidS) {
        this.cameraidS = cameraidS;
    }

    public Camera getCameraidN() {
        return cameraidN;
    }

    public void setCameraidN(Camera cameraidN) {
        this.cameraidN = cameraidN;
    }

    public Camera getCameraidE() {
        return cameraidE;
    }

    public void setCameraidE(Camera cameraidE) {
        this.cameraidE = cameraidE;
    }

    public Trafficlight getTrafficLightidW() {
        return trafficLightidW;
    }

    public void setTrafficLightidW(Trafficlight trafficLightidW) {
        this.trafficLightidW = trafficLightidW;
    }

    public Trafficlight getTrafficLightidS() {
        return trafficLightidS;
    }

    public void setTrafficLightidS(Trafficlight trafficLightidS) {
        this.trafficLightidS = trafficLightidS;
    }

    public Trafficlight getTrafficLightidN() {
        return trafficLightidN;
    }

    public void setTrafficLightidN(Trafficlight trafficLightidN) {
        this.trafficLightidN = trafficLightidN;
    }

    public Trafficlight getTrafficLightidE() {
        return trafficLightidE;
    }

    public void setTrafficLightidE(Trafficlight trafficLightidE) {
        this.trafficLightidE = trafficLightidE;
    }

    @XmlTransient
    public Collection<TrafficFlow> getTrafficFlowCollection() {
        return trafficFlowCollection;
    }

    public void setTrafficFlowCollection(Collection<TrafficFlow> trafficFlowCollection) {
        this.trafficFlowCollection = trafficFlowCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crossing)) {
            return false;
        }
        Crossing other = (Crossing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBean.Crossing[ id=" + id + " ]";
    }
    
}
