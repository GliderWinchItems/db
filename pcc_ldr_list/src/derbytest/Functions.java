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
@Table(name = "FUNCTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Functions.findAll", query = "SELECT f FROM Functions f"),
    @NamedQuery(name = "Functions.findByFuncName", query = "SELECT f FROM Functions f WHERE f.funcName = :funcName"),
    @NamedQuery(name = "Functions.findByCanidHex", query = "SELECT f FROM Functions f WHERE f.canidHex = :canidHex"),
    @NamedQuery(name = "Functions.findByDescription", query = "SELECT f FROM Functions f WHERE f.description = :description")})
public class Functions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FUNC_NAME")
    private String funcName;
    @Basic(optional = false)
    @Column(name = "CANID_HEX")
    private String canidHex;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    public Functions() {
    }

    public Functions(String funcName) {
        this.funcName = funcName;
    }

    public Functions(String funcName, String canidHex, String description) {
        this.funcName = funcName;
        this.canidHex = canidHex;
        this.description = description;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
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
        hash += (funcName != null ? funcName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Functions)) {
            return false;
        }
        Functions other = (Functions) object;
        if ((this.funcName == null && other.funcName != null) || (this.funcName != null && !this.funcName.equals(other.funcName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "derbytest.Functions[ funcName=" + funcName + " ]";
    }
    
}
