/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.*;

/**
 *
 * @author deh
 */
public class Canmsginfo implements Comparable<Canmsginfo>, Comparator<Canmsginfo>{
    
        // ( <database table name where this item originates> )
        public Long can_hex;            // 32b word with CAN id (STM32 CAN format) (CANID)
        public int pay_type_code;       // Payload type code (PAYLOAD_TYPE)
        public String can_msg_fmt;     // 
        public String can_name;         // CAN ID name (CANID)
        public String descript_canid;  // Description CAN ID (CANID)
        public String descript_payload; // Description of payload (PAYLOAD_TYPE)
    /* *********************************************************************
    * Constructors
    * ********************************************************************* */
    public Canmsginfo(){
    }
    
    public Canmsginfo(String id, int n2, String s0, String s1, String s2, String s3){
        this.can_hex       = Long.parseLong(id, 16);
        this.pay_type_code = n2;
        this.can_msg_fmt     = s0;
        this.can_name        = s1;
        this.descript_canid  = s2;
        this.descript_payload= s3;
    }
    public Canmsginfo(Canmsginfo cmi){
        this.can_hex       = cmi.can_hex;
        this.pay_type_code = cmi.pay_type_code;
        this.can_msg_fmt     = cmi.can_msg_fmt;
        this.can_name        = cmi.can_name;
        this.descript_canid  = cmi.descript_canid;
        this.descript_payload= cmi.descript_payload;
    }
       public Canmsginfo(Long id){
        this.can_hex       = id;
       }
       
    @Override
    public int compareTo(Canmsginfo o) {
        //ion("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
        return can_hex.compareTo(o.can_hex);
    }

    @Override
    public int compare(Canmsginfo o1, Canmsginfo o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public long getId() {
    return this.can_hex;
  }
    
 
 
}
