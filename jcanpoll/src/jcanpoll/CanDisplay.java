/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

import java.util.Comparator;

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
    public Canmsg2j cmsg;   // Latest CAN msg
    
    public int id;  // 32b word with CAN id (STM32 CAN format)
    public int dlc; // Payload count (number of bytes in payload)
    public int p0;  // Assembled Integer of payload bytes [0]-[3]
    public int p1;  // Assembled Integer of payload bytes [4]-[7]
    public long pl; // Assembled Long  from payload bytes [0]-[7]
    public byte[] pb;// Binary bytes as received and converted from ascii/hex input
    
    public CanDisplay(){
    }    
    public CanDisplay(Long id){
        this.can_hex       = id;
    }
    public CanDisplay(Long id, Canmsginfo c){
        this.can_hex    = id;
        this.cmi        = c;
    }
       public CanDisplay(Long id, Canmsginfo c, Canmsg2j m){
        this.can_hex    = id;
        this.cmi        = c;
        cmsg = new Canmsg2j();
    }
    public CanDisplay(Long id, String s){
        this.can_hex    = id;
        this.cmi.can_name = s;
    }
    public CanDisplay(Canmsg2j can,Canmsginfo c ){
        this.cmi = c;
        cmsg = new Canmsg2j();
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
    public void setCmsg2(Canmsg2j c){
        id  = c.id;
        dlc = c.dlc;
        p0  = c.p0;
        p1  = c.p1;
        pb  = c.pb;
        cmsg = c;
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
