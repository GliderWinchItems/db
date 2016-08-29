/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

/**
 *
 * @author deh
 */
public class Canmsginfo {
    
        // ( <database table name where this item originates> )
        public long can_hex;        // 32b word with CAN id (STM32 CAN format) (CANID)
        public int pay_type_code;   // Payload type code (PAYLOAD_TYPE)
        public String can_msg_fmt;     // 

        public String can_name;     // CAN ID name (CANID)
        public String descript_canid;  // Description CAN ID (CANID)
        public String descript_payload; // Description of payload (PAYLOAD_TYPE)
    /* *********************************************************************
    * Constructors
    * ********************************************************************* */
    public Canmsginfo(){
    }
    
    public Canmsginfo(String id, int n2, String s0, String s1, String s2, String s3){
        can_hex       = Long.parseLong(id, 16);
        pay_type_code = n2;
        can_msg_fmt     = s0;
        can_name        = s1;
        descript_canid  = s2;
        descript_payload= s3;
    }
    
    
}
