/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.text.SimpleDateFormat;

/**
 *
 * @author deh
 */
public class DbPayload {
    private final int NONE       =  0; // No payload bytes
    private final int U8_U32     =  5; //[0]: uint8_t; [1]-[4]: uint32_t
    private final int xFF        = 11; //[1]-[4]: Full-Float, first   byte  skipped
    private final int LAT_LON_HT = 20; //[0]:[1]:[2]-[5]: Fix type, bits fields, lat/lon/ht
    private final int U8_FF      = 21; //[0]:[1]-[5]: uint8_t, Full Float
    private final int UNIXTIME   = 24; //[0]: U8_U32 with U8 bit field stuff
    private final int UNDEF      =255; //Undefined
    
    
    public DbPayload(){
    }
    
    public String convert(CanDisplay cd){
        String s = "\t";
        Canmsg2j cmsg = new Canmsg2j();
        cmsg.pb = cd.pb;

        int ptc = cd.cmi.pay_type_code; // Numeric payload type 
        switch (ptc){
            case NONE:
                s  += String.format("dlc:%02X: ",cd.dlc);
                s  += "No payload bytes";
                break;
            case U8_U32: // U8_U32:
                if (cd.dlc != 5){
                         s  += String.format("ERR U8_U32: dlc not eq 5 %d: ",cd.dlc);
                         break;
                }
                s  += String.format("%d: %02X: ",cd.dlc,cd.pb[6]  );
                    for (int i = 7 ; i < cd.dlc+6; i++){
                    s += String.format("%02x ",cd.pb[i]);
                }
                cmsg.in_1int_n(1);
                s += String.format("%8d",cmsg.p0);
                break;
                
            case LAT_LON_HT:
                if (cd.dlc != 6){
                    s  += String.format("ERR LAT_LON_HT: dlc not eq 6 %d: ",cd.dlc);
                    break;
                }
 //               s  += String.format("%d: %02X:%02X: ",cd.dlc,cd.pb[6], cd.pb[7] );
                if ( !((((cd.pb[6] >> 4) & 0xf) == 0xd) || (((cd.pb[6] >> 4) & 0xf) == 0)) ){
                    s += String.format("GPS conversion NG: %02X",((cd.pb[6] >> 4) & 0xf));
                    break;
                }
                /* If no new data, don't print old data. */
                if (cd.count == 0){
                    s += "no new data";
                    break;
                }
                /* Unpack bits: Type of FIX code. */
                int x0 = cd.pb[6] & 0x3;
                String fix = "G3 ";
                if (x0 == 0) fix = "NF ";
                if (x0 == 1) fix = "G1 ";
                if (x0 == 2) fix = "G2 ";
                s += fix;
                /* Unpack bits: Number of satellits in FIX. */
                int nsat = cd.pb[7] >> 3;
                s += String.format("NSAT %d ",nsat);
                /* Select if payload is Lat, Lon, or Height. */
                if ((cd.pb[7] & 0x3) == 0){ // Latitude
                    cmsg.in_1int_n(2);  // Convert bytes to int
                    double f = (double)cmsg.p0;
                    s += String.format("Lat %12.9f ", f/60E5);
                }
                if ((cd.pb[7] & 0x3) == 1){ // Longitude
                    cmsg.in_1int_n(2);  // Convert bytes to int
                    double f = (double)cmsg.p0;
                    s += String.format("Lon %12.9f ", f/60E5);
                }
                    if ((cd.pb[7] & 0x3) == 2){ // Height
                    cmsg.in_1int_n(2);  // Convert bytes to int
                    double f = (double)cmsg.p0;
                    s += String.format("Ht %6.1f ", f/10);
                }
                break;

            case UNIXTIME: // Unix time U8_U32
                cmsg.in_1int_n(1);  // Convert bytes to int
                java.util.Date date= new java.util.Date(); // Get current date/time
                SimpleDateFormat ss = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                s += ss.format(date); // Add date time to display string
                break;
                
            case U8_FF:
                if (cd.dlc != 5){
                         s  += String.format("ERR U8_FF: dlc not eq 5 %d: ",cd.dlc);
                         break;
                }
                s  += String.format("%d: %02X: ",cd.dlc,cd.pb[6]  );
                cmsg.in_1int_n(1);  // Convert bytes to int
//s += String.format(" %08x ",cmsg.p0);
                double f = Float.intBitsToFloat(cmsg.p0); // Convert int bits
                s += String.format("%12.6f ", f);
                break;
            case UNDEF:
                s += String.format("Payload UNDEF ");
                s += String.format("dlc:%02X: ",cd.dlc);
                for (int i = 6 ; i < cd.dlc+6; i++){
                    s += String.format("%02x ",cd.pb[i]);
                }
                break;
                
            default: // Type not found
                s ="cmi.pay_type_code: Type Not In final definitions";
                break;
        }       
        return s;
    } 
}