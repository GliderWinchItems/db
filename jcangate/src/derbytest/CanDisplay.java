/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.*;

/**
 *
 * @author deh
 */
public class CanDisplay implements Comparable<CanDisplay>, Comparator<CanDisplay>{
    
    public Long can_hex;    // CAN id
    public int count;       // Number of msgs 
    public String vartext;  // Decoded values
    public String fixtext;  // Description and stuff
    public Canmsginfo cmi;  // Database stuff
    
    public CanDisplay(){
    }    
    public CanDisplay(Long id){
        this.can_hex       = id;
    }
    public CanDisplay(Long id, Canmsginfo c){
        this.can_hex    = id;
        this.cmi        = c;
    }
    public CanDisplay(Long id, String s){
        this.can_hex    = id;
        this.cmi.can_name = s;
    }
    public void setCount(Integer ct){
        count = ct;
    }
    public void setVartext(String s){
        vartext = s;
    }
    public void setFixtext(String s){
        fixtext = s;
    }
    public void setCan_hex(Long id){
        can_hex = id;
    }
    public int incCount(){
        count += 1;
        return count;
    }

    @Override
    public int compareTo(CanDisplay o) {
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return can_hex.compareTo(o.can_hex);
 
    }

    @Override
    public int compare(CanDisplay o1, CanDisplay o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
