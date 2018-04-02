/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author lenovo
 */
@Entity
@Table(name = "crossing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crossing.findAll", query = "SELECT c FROM Crossing c")
    , @NamedQuery(name = "Crossing.findById", query = "SELECT c FROM Crossing c WHERE c.id = :id")
    , @NamedQuery(name = "Crossing.findByLocation", query = "SELECT c FROM Crossing c WHERE c.location = :location")
    , @NamedQuery(name = "Crossing.findByCoordx", query = "SELECT c FROM Crossing c WHERE c.coordx = :coordx")
    , @NamedQuery(name = "Crossing.findByCoordy", query = "SELECT c FROM Crossing c WHERE c.coordy = :coordy")
    , @NamedQuery(name = "Crossing.findByCurrentFlowE", query = "SELECT c FROM Crossing c WHERE c.currentFlowE = :currentFlowE")
    , @NamedQuery(name = "Crossing.findByCurrentFlowW", query = "SELECT c FROM Crossing c WHERE c.currentFlowW = :currentFlowW")
    , @NamedQuery(name = "Crossing.findByCurrentFlowS", query = "SELECT c FROM Crossing c WHERE c.currentFlowS = :currentFlowS")
    , @NamedQuery(name = "Crossing.findByCurrentFlowN", query = "SELECT c FROM Crossing c WHERE c.currentFlowN = :currentFlowN")
    , @NamedQuery(name = "Crossing.findByNextEd", query = "SELECT c FROM Crossing c WHERE c.nextEd = :nextEd")
    , @NamedQuery(name = "Crossing.findByNextWd", query = "SELECT c FROM Crossing c WHERE c.nextWd = :nextWd")
    , @NamedQuery(name = "Crossing.findByNextSd", query = "SELECT c FROM Crossing c WHERE c.nextSd = :nextSd")
    , @NamedQuery(name = "Crossing.findByNextNd", query = "SELECT c FROM Crossing c WHERE c.nextNd = :nextNd")})
public class Crossing implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crossingId")
    private Collection<TrafficFlow> trafficFlowCollection;

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "coordx")
    private BigDecimal coordx;
    @Column(name = "coordy")
    private BigDecimal coordy;
    @Column(name = "currentFlow_E")
    private Integer currentFlowE;
    @Column(name = "currentFlow_W")
    private Integer currentFlowW;
    @Column(name = "currentFlow_S")
    private Integer currentFlowS;
    @Column(name = "currentFlow_N")
    private Integer currentFlowN;
    @Column(name = "next_E_d")
    private Integer nextEd;
    @Column(name = "next_W_d")
    private Integer nextWd;
    @Column(name = "next_S_d")
    private Integer nextSd;
    @Column(name = "next_N_d")
    private Integer nextNd;
    @OneToMany(mappedBy = "nextEid")
    private Collection<Crossing> crossingCollection;
    @JoinColumn(name = "next_E_id", referencedColumnName = "id")
    @ManyToOne
    private Crossing nextEid;
    @OneToMany(mappedBy = "nextWid")
    private Collection<Crossing> crossingCollection1;
    @JoinColumn(name = "next_W_id", referencedColumnName = "id")
    @ManyToOne
    private Crossing nextWid;
    @OneToMany(mappedBy = "nextSid")
    private Collection<Crossing> crossingCollection2;
    @JoinColumn(name = "next_S_id", referencedColumnName = "id")
    @ManyToOne
    private Crossing nextSid;
    @OneToMany(mappedBy = "nextNid")
    private Collection<Crossing> crossingCollection3;
    @JoinColumn(name = "next_N_id", referencedColumnName = "id")
    @ManyToOne
    private Crossing nextNid;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne
    private Area areaId;
    @JoinColumn(name = "trafficLight_id_E", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidE;
    @JoinColumn(name = "trafficLight_id_N", referencedColumnName = "id")
    @ManyToOne
    private Trafficlight trafficLightidN;
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

    public BigDecimal getCoordx() {
        return coordx;
    }

    public void setCoordx(BigDecimal coordx) {
        this.coordx = coordx;
    }

    public BigDecimal getCoordy() {
        return coordy;
    }

    public void setCoordy(BigDecimal coordy) {
        this.coordy = coordy;
    }

    public Integer getCurrentFlowE() {
        return currentFlowE;
    }

    public void setCurrentFlowE(Integer currentFlowE) {
        this.currentFlowE = currentFlowE;
    }

    public Integer getCurrentFlowW() {
        return currentFlowW;
    }

    public void setCurrentFlowW(Integer currentFlowW) {
        this.currentFlowW = currentFlowW;
    }

    public Integer getCurrentFlowS() {
        return currentFlowS;
    }

    public void setCurrentFlowS(Integer currentFlowS) {
        this.currentFlowS = currentFlowS;
    }

    public Integer getCurrentFlowN() {
        return currentFlowN;
    }

    public void setCurrentFlowN(Integer currentFlowN) {
        this.currentFlowN = currentFlowN;
    }

    public Integer getNextEd() {
        return nextEd;
    }

    public void setNextEd(Integer nextEd) {
        this.nextEd = nextEd;
    }

    public Integer getNextWd() {
        return nextWd;
    }

    public void setNextWd(Integer nextWd) {
        this.nextWd = nextWd;
    }

    public Integer getNextSd() {
        return nextSd;
    }

    public void setNextSd(Integer nextSd) {
        this.nextSd = nextSd;
    }

    public Integer getNextNd() {
        return nextNd;
    }

    public void setNextNd(Integer nextNd) {
        this.nextNd = nextNd;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection() {
        return crossingCollection;
    }

    public void setCrossingCollection(Collection<Crossing> crossingCollection) {
        this.crossingCollection = crossingCollection;
    }

    public Crossing getNextEid() {
        return nextEid;
    }

    public void setNextEid(Crossing nextEid) {
        this.nextEid = nextEid;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection1() {
        return crossingCollection1;
    }

    public void setCrossingCollection1(Collection<Crossing> crossingCollection1) {
        this.crossingCollection1 = crossingCollection1;
    }

    public Crossing getNextWid() {
        return nextWid;
    }

    public void setNextWid(Crossing nextWid) {
        this.nextWid = nextWid;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection2() {
        return crossingCollection2;
    }

    public void setCrossingCollection2(Collection<Crossing> crossingCollection2) {
        this.crossingCollection2 = crossingCollection2;
    }

    public Crossing getNextSid() {
        return nextSid;
    }

    public void setNextSid(Crossing nextSid) {
        this.nextSid = nextSid;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection3() {
        return crossingCollection3;
    }

    public void setCrossingCollection3(Collection<Crossing> crossingCollection3) {
        this.crossingCollection3 = crossingCollection3;
    }

    public Crossing getNextNid() {
        return nextNid;
    }

    public void setNextNid(Crossing nextNid) {
        this.nextNid = nextNid;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Trafficlight getTrafficLightidE() {
        return trafficLightidE;
    }

    public void setTrafficLightidE(Trafficlight trafficLightidE) {
        this.trafficLightidE = trafficLightidE;
    }

    public Trafficlight getTrafficLightidN() {
        return trafficLightidN;
    }

    public void setTrafficLightidN(Trafficlight trafficLightidN) {
        this.trafficLightidN = trafficLightidN;
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
        return "entity.Crossing[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TrafficFlow> getTrafficFlowCollection() {
        return trafficFlowCollection;
    }

    public void setTrafficFlowCollection(Collection<TrafficFlow> trafficFlowCollection) {
        this.trafficFlowCollection = trafficFlowCollection;
    }
    
}
