/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deh
 */
@Entity
@Table(name = "CAN_UNIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanUnit.findAll", query = "SELECT c FROM CanUnit c"),
    @NamedQuery(name = "CanUnit.findByUnitName", query = "SELECT c FROM CanUnit c WHERE c.unitName = :unitName"),
    @NamedQuery(name = "CanUnit.findByProgPath", query = "SELECT c FROM CanUnit c WHERE c.progPath = :progPath"),
    @NamedQuery(name = "CanUnit.findByCanidUnitHex", query = "SELECT c FROM CanUnit c WHERE c.canidUnitHex = :canidUnitHex"),
    @NamedQuery(name = "CanUnit.findByDescription", query = "SELECT c FROM CanUnit c WHERE c.description = :description")})
public class CanUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UNIT_NAME")
    private String unitName;
    @Basic(optional = false)
    @Column(name = "PROG_PATH")
    private String progPath;
    @Column(name = "CANID_UNIT_HEX")
    private String canidUnitHex;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    public CanUnit() {
    }

    public CanUnit(String unitName) {
        this.unitName = unitName;
    }

    public CanUnit(String unitName, String progPath, String description) {
        this.unitName = unitName;
        this.progPath = progPath;
        this.description = description;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getProgPath() {
        return progPath;
    }

    public void setProgPath(String progPath) {
        this.progPath = progPath;
    }

    public String getCanidUnitHex() {
        return canidUnitHex;
    }

    public void setCanidUnitHex(String canidUnitHex) {
        this.canidUnitHex = canidUnitHex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitName != null ? unitName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CanUnit)) {
            return false;
        }
        CanUnit other = (CanUnit) object;
        if ((this.unitName == null && other.unitName != null) || (this.unitName != null && !this.unitName.equals(other.unitName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "derbytest.CanUnit[ unitName=" + unitName + " ]";
    }
    
}
