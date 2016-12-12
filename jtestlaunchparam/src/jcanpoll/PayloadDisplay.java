/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

/**
 *
 * @author deh
 */
public class PayloadDisplay {

    public CanDisplay cd;

    public PayloadDisplay (){
        cd = new CanDisplay();
    }
    
    public String toString(Canmsg2j cmsg, Canmsginfo cmi ){
        cd.cmsg = cmsg;
        cd.setCmsg2(cmsg);
        cd.cmi = cmi;
//cd.cmi.pay_type_code = 24;
        DbPayload dpl = new DbPayload();
        return dpl.convert(cd);        
    }
    
}
