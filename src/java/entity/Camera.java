/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "camera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camera.findAll", query = "SELECT c FROM Camera c")
    , @NamedQuery(name = "Camera.findById", query = "SELECT c FROM Camera c WHERE c.id = :id")
    , @NamedQuery(name = "Camera.findByIpAddress", query = "SELECT c FROM Camera c WHERE c.ipAddress = :ipAddress")
    , @NamedQuery(name = "Camera.findByType", query = "SELECT c FROM Camera c WHERE c.type = :type")})
public class Camera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ip_address")
    private String ipAddress;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "cameraidW")
    private Collection<Crossing> crossingCollection;
    @OneToMany(mappedBy = "cameraidS")
    private Collection<Crossing> crossingCollection1;
    @OneToMany(mappedBy = "cameraidN")
    private Collection<Crossing> crossingCollection2;
    @OneToMany(mappedBy = "cameraidE")
    private Collection<Crossing> crossingCollection3;

    public Camera() {
    }

    public Camera(String id) {
        this.id = id;
    }

    public Camera(String id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection() {
        return crossingCollection;
    }

    public void setCrossingCollection(Collection<Crossing> crossingCollection) {
        this.crossingCollection = crossingCollection;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection1() {
        return crossingCollection1;
    }

    public void setCrossingCollection1(Collection<Crossing> crossingCollection1) {
        this.crossingCollection1 = crossingCollection1;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection2() {
        return crossingCollection2;
    }

    public void setCrossingCollection2(Collection<Crossing> crossingCollection2) {
        this.crossingCollection2 = crossingCollection2;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection3() {
        return crossingCollection3;
    }

    public void setCrossingCollection3(Collection<Crossing> crossingCollection3) {
        this.crossingCollection3 = crossingCollection3;
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
        if (!(object instanceof Camera)) {
            return false;
        }
        Camera other = (Camera) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Camera[ id=" + id + " ]";
    }
    
}
