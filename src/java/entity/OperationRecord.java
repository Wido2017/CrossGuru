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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 曹锡鹏
 */
@Entity
@Table(name = "operation_record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperationRecord.findAll", query = "SELECT o FROM OperationRecord o")
    , @NamedQuery(name = "OperationRecord.findById", query = "SELECT o FROM OperationRecord o WHERE o.id = :id")
    , @NamedQuery(name = "OperationRecord.findByOperateTime", query = "SELECT o FROM OperationRecord o WHERE o.operateTime = :operateTime")
    , @NamedQuery(name = "OperationRecord.findByOperateTable", query = "SELECT o FROM OperationRecord o WHERE o.operateTable = :operateTable")
    , @NamedQuery(name = "OperationRecord.findByOperator", query = "SELECT o FROM OperationRecord o WHERE o.operator = :operator")})
public class OperationRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operate_time")
    @Temporal(TemporalType.DATE)
    private Date operateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "operate_table")
    private String operateTable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "operator")
    private String operator;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "operate_type")
    private String operateType;
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    @ManyToOne
    private Administrator administratorId;
    @JoinColumn(name = "police_id", referencedColumnName = "id")
    @ManyToOne
    private Police policeId;

    public OperationRecord() {
    }

    public OperationRecord(Integer id) {
        this.id = id;
    }

    public OperationRecord(Integer id, Date operateTime, String operateTable, String operator, String operateType) {
        this.id = id;
        this.operateTime = operateTime;
        this.operateTable = operateTable;
        this.operator = operator;
        this.operateType = operateType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateTable() {
        return operateTable;
    }

    public void setOperateTable(String operateTable) {
        this.operateTable = operateTable;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Administrator getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Administrator administratorId) {
        this.administratorId = administratorId;
    }

    public Police getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Police policeId) {
        this.policeId = policeId;
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
        if (!(object instanceof OperationRecord)) {
            return false;
        }
        OperationRecord other = (OperationRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OperationRecord[ id=" + id + " ]";
    }
    
}
