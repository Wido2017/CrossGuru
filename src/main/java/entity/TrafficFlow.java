/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caoxipeng
 */
@Entity
@Table(name = "traffic_flow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrafficFlow.findAll", query = "SELECT t FROM TrafficFlow t")
    , @NamedQuery(name = "TrafficFlow.findById", query = "SELECT t FROM TrafficFlow t WHERE t.id = :id")
    , @NamedQuery(name = "TrafficFlow.findByTime", query = "SELECT t FROM TrafficFlow t WHERE t.time = :time")
    , @NamedQuery(name = "TrafficFlow.findByCrossingN", query = "SELECT t FROM TrafficFlow t WHERE t.crossingN = :crossingN")
    , @NamedQuery(name = "TrafficFlow.findByCrossingS", query = "SELECT t FROM TrafficFlow t WHERE t.crossingS = :crossingS")
    , @NamedQuery(name = "TrafficFlow.findByCrossingW", query = "SELECT t FROM TrafficFlow t WHERE t.crossingW = :crossingW")
    , @NamedQuery(name = "TrafficFlow.findByCrossingE", query = "SELECT t FROM TrafficFlow t WHERE t.crossingE = :crossingE")})
public class TrafficFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "crossing_N")
    private Long crossingN;
    @Column(name = "crossing_S")
    private Long crossingS;
    @Column(name = "crossing_W")
    private Long crossingW;
    @Column(name = "crossing_E")
    private Long crossingE;
    @JoinColumn(name = "crossing_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Crossing crossingId;

    public TrafficFlow() {
    }

    public TrafficFlow(Integer id) {
        this.id = id;
    }

    public TrafficFlow(Integer id, Date time) {
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getCrossingN() {
        return crossingN;
    }

    public void setCrossingN(Long crossingN) {
        this.crossingN = crossingN;
    }

    public Long getCrossingS() {
        return crossingS;
    }

    public void setCrossingS(Long crossingS) {
        this.crossingS = crossingS;
    }

    public Long getCrossingW() {
        return crossingW;
    }

    public void setCrossingW(Long crossingW) {
        this.crossingW = crossingW;
    }

    public Long getCrossingE() {
        return crossingE;
    }

    public void setCrossingE(Long crossingE) {
        this.crossingE = crossingE;
    }

    public Crossing getCrossingId() {
        return crossingId;
    }

    public void setCrossingId(Crossing crossingId) {
        this.crossingId = crossingId;
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
        if (!(object instanceof TrafficFlow)) {
            return false;
        }
        TrafficFlow other = (TrafficFlow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.crossguru_maven.TrafficFlow[ id=" + id + " ]";
    }
    
}
