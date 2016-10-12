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
}
