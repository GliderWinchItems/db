/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author deh
 */
@Entity
@Table(name = "CANID", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Canid.findAll", query = "SELECT c FROM Canid c"),
    @NamedQuery(name = "Canid.findByCanidName", query = "SELECT c FROM Canid c WHERE c.canidName = :canidName "),
    @NamedQuery(name = "Canid.findByCanidHex", query = "SELECT c FROM Canid c WHERE c.canidHex = :canidHex"),
    @NamedQuery(name = "Canid.findByCanidType", query = "SELECT c FROM Canid c WHERE c.canidType = :canidType"),
    @NamedQuery(name = "Canid.findByCanMsgFmt", query = "SELECT c FROM Canid c WHERE c.canMsgFmt = :canMsgFmt"),
    @NamedQuery(name = "Canid.findByDescription", query = "SELECT c FROM Canid c WHERE c.description = :description")})
public class Canid implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CANID_NAME")
    private String canidName;
    @Basic(optional = false)
    @Column(name = "CANID_HEX")
    private String canidHex;
    @Basic(optional = false)
    @Column(name = "CANID_TYPE")
    private String canidType;
    @Basic(optional = false)
    @Column(name = "CAN_MSG_FMT")
    private String canMsgFmt;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    public Canid() {
    }

    public Canid(String canidName) {
        this.canidName = canidName;
    }

    public Canid(String canidName, String canidHex, String canidType, String canMsgFmt, String description) {
        this.canidName = canidName;
        this.canidHex = canidHex;
        this.canidType = canidType;
        this.canMsgFmt = canMsgFmt;
        this.description = description;
    }

    public String getCanidName() {
        return canidName;
    }

    public void setCanidName(String canidName) {
        String oldCanidName = this.canidName;
        this.canidName = canidName;
        changeSupport.firePropertyChange("canidName", oldCanidName, canidName);
    }

    public String getCanidHex() {
        return canidHex;
    }

    public void setCanidHex(String canidHex) {
        String oldCanidHex = this.canidHex;
        this.canidHex = canidHex;
        changeSupport.firePropertyChange("canidHex", oldCanidHex, canidHex);
    }

    public String getCanidType() {
        return canidType;
    }

    public void setCanidType(String canidType) {
        String oldCanidType = this.canidType;
        this.canidType = canidType;
        changeSupport.firePropertyChange("canidType", oldCanidType, canidType);
    }

    public String getCanMsgFmt() {
        return canMsgFmt;
    }

    public void setCanMsgFmt(String canMsgFmt) {
        String oldCanMsgFmt = this.canMsgFmt;
        this.canMsgFmt = canMsgFmt;
        changeSupport.firePropertyChange("canMsgFmt", oldCanMsgFmt, canMsgFmt);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
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
        return "jcanpoll.Canid[ canidName=" + canidName + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
