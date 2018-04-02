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
 * @author lenovo
 */
@Entity
@Table(name = "administrator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a")
    , @NamedQuery(name = "Administrator.findById", query = "SELECT a FROM Administrator a WHERE a.id = :id")
    , @NamedQuery(name = "Administrator.findByPassword", query = "SELECT a FROM Administrator a WHERE a.password = :password")
    , @NamedQuery(name = "Administrator.findByName", query = "SELECT a FROM Administrator a WHERE a.name = :name")
    , @NamedQuery(name = "Administrator.findBySex", query = "SELECT a FROM Administrator a WHERE a.sex = :sex")
    , @NamedQuery(name = "Administrator.findByTel", query = "SELECT a FROM Administrator a WHERE a.tel = :tel")})
public class Administrator implements Serializable {

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
    @OneToMany(mappedBy = "administratorId")
    private Collection<OperationRecord> operationRecordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administratorId")
    private Collection<Police> policeCollection;

    public Administrator() {
    }

    public Administrator(String id) {
        this.id = id;
    }

    public Administrator(String id, String password, String name, String sex, String tel) {
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

    @XmlTransient
    public Collection<Police> getPoliceCollection() {
        return policeCollection;
    }

    public void setPoliceCollection(Collection<Police> policeCollection) {
        this.policeCollection = policeCollection;
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
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Administrator[ id=" + id + " ]";
    }
    
}
