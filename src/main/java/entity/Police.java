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
 * @author caoxipeng
 */
@Entity
@Table(name = "police")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Police.findAll", query = "SELECT p FROM Police p")
    , @NamedQuery(name = "Police.findById", query = "SELECT p FROM Police p WHERE p.id = :id")
    , @NamedQuery(name = "Police.findByPassword", query = "SELECT p FROM Police p WHERE p.password = :password")
    , @NamedQuery(name = "Police.findByName", query = "SELECT p FROM Police p WHERE p.name = :name")
    , @NamedQuery(name = "Police.findBySex", query = "SELECT p FROM Police p WHERE p.sex = :sex")
    , @NamedQuery(name = "Police.findByTel", query = "SELECT p FROM Police p WHERE p.tel = :tel")})
public class Police implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "tel")
    private String tel;
    @OneToMany(mappedBy = "policeId")
    private Collection<OperationRecord> operationRecordCollection;
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Administrator administratorId;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Area areaId;

    public Police() {
    }

    public Police(String id) {
        this.id = id;
    }

    public Police(String id, String password, String name, String sex, String tel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @XmlTransient
    public Collection<OperationRecord> getOperationRecordCollection() {
        return operationRecordCollection;
    }

    public void setOperationRecordCollection(Collection<OperationRecord> operationRecordCollection) {
        this.operationRecordCollection = operationRecordCollection;
    }

    public Administrator getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Administrator administratorId) {
        this.administratorId = administratorId;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
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
        if (!(object instanceof Police)) {
            return false;
        }
        Police other = (Police) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.crossguru_maven.Police[ id=" + id + " ]";
    }
    
}
