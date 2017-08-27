/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
<<<<<<< HEAD
 * @author Nicole Yang
=======
 * @author 曹锡鹏
>>>>>>> cxp
 */
@Entity
@Table(name = "area")
@XmlRootElement
@NamedQueries({
<<<<<<< HEAD
    @NamedQuery(name = "Area.findAll", query = "SELECT id FROM Area id")
=======
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
>>>>>>> cxp
    , @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id")
    , @NamedQuery(name = "Area.findByName", query = "SELECT a FROM Area a WHERE a.name = :name")
    , @NamedQuery(name = "Area.findByLocation", query = "SELECT a FROM Area a WHERE a.location = :location")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "location")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private Collection<Police> policeCollection;
    @OneToMany(mappedBy = "areaId")
    private Collection<Crossing> crossingCollection;

    public Area() {
    }

    public Area(String id) {
        this.id = id;
    }

    public Area(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Police> getPoliceCollection() {
        return policeCollection;
    }

    public void setPoliceCollection(Collection<Police> policeCollection) {
        this.policeCollection = policeCollection;
    }

    @XmlTransient
    public Collection<Crossing> getCrossingCollection() {
        return crossingCollection;
    }

    public void setCrossingCollection(Collection<Crossing> crossingCollection) {
        this.crossingCollection = crossingCollection;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return id;
=======
        return "entity.Area[ id=" + id + " ]";
>>>>>>> cxp
    }
    
}
