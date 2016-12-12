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
public class PccFinal {
    public static final int NONE       =  0; // No payload bytes
    public static final int U8_U32     =  5; //[0]: uint8_t; [1]-[4]: uint32_t
    public static final int xFF        = 11; //[1]-[4]: Full-Float, first   byte  skipped
    public static final int LAT_LON_HT = 20; //[0]:[1]:[2]-[5]: Fix type, bits fields, lat/lon/ht
    public static final int U8_FF      = 21; //[0]:[1]-[5]: uint8_t, Full Float
    public static final int U8         = 23; //[0]: uint8_t');
    public static final int UNIXTIME   = 24; //[0]: U8_U32 with U8 bit field stuff
    public static final int UNDEF      =255; //Undefined
    public static final int U8_U8_U32  = 15; //	6, ' [0]:[1]:[2]-[5]: uint8_t[0],uint8_t[1],uint32_t,');

/* Launch parameter test */

// Codes for command type    
public static final  int CMD_LAUNCH_PARM_HDSHK   = 38;        // Send msg to handshake transferring launch parameters
public static final  int CMD_SEND_LAUNCH_PARM    = 39;        // Send msg to send burst of parameters

public static final  int LAUNCH_PARAM_BURST_SIZE = 32;   // Burst size for PC

// Parameter test list
public static final double launchparameter_dbl[] = {
    1.1,    // 1
    2.2,    // 2    
    3.3,    // 3    
    4.4,    // 4    
    5.5,    // 5    
    6.6,    // 6    
    7.7,    // 7    
    8.8,    // 8    
    9.1,    // 9    
    10.2,   // 10    
    11.3,   // 11
    12.4,   // 12    
    13.5,   // 13    
    14.6,   // 14    
    15.7,   // 15    
    16.8,   // 16    
    17.1,   // 17    
    18.2,   // 18    
    19.3,   // 19    
    20.4,   // 20
    21.5,   // 21
    22.6,   // 22
    23.7,   // 23
    24.8,   // 24
    25.1,   // 25
    26.2,   // 26
    27.3,   // 27
    28.4   // 28
    };    


}
