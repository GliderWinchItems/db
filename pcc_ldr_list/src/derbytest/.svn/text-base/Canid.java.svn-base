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
@Table(name = "CANID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canid.findAll", query = "SELECT c FROM Canid c"),
    @NamedQuery(name = "Canid.findByCanidName", query = "SELECT c FROM Canid c WHERE c.canidName = :canidName"),
    @NamedQuery(name = "Canid.findByCanidHex", query = "SELECT c FROM Canid c WHERE c.canidHex = :canidHex"),
    @NamedQuery(name = "Canid.findByDescription", query = "SELECT c FROM Canid c WHERE c.description = :description")})
public class Canid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CANID_NAME")
    private String canidName;
    @Column(name = "CANID_HEX")
    private String canidHex;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    public Canid() {
    }

    public Canid(String canidName) {
        this.canidName = canidName;
    }

    public Canid(String canidName, String description) {
        this.canidName = canidName;
        this.description = description;
    }

    public String getCanidName() {
        return canidName;
    }

    public void setCanidName(String canidName) {
        this.canidName = canidName;
    }

    public String getCanidHex() {
        return canidHex;
    }

    public void setCanidHex(String canidHex) {
        this.canidHex = canidHex;
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
        hash += (canidName != null ? canidName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canid)) {
            return false;
        }
        Canid other = (Canid) object;
        if ((this.canidName == null && other.canidName != null) || (this.canidName != null && !this.canidName.equals(other.canidName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "derbytest.Canid[ canidName=" + canidName + " ]";
    }
    
}
