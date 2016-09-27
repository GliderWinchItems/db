/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jcanpoll;

import javax.xml.bind.DatatypeConverter;

/**
 * CAN message
 * 
 * Handle the conversion between binary and ascii/hex format CAN messages.
 * In-line coding instead of loops, used in hopes of speeding conversions.
 * 
 * @author deh
 */
public class Canmsg2j {
    public int seq; // Sequence number (if used)
    /**
     * 32b word with CAN id (STM32 CAN format)
     */
    public int id;  // 32b word with CAN id (STM32 CAN format)
    public int dlc; // Payload count (number of bytes in payload)
    public int p0;  // Assembled Integer of payload bytes [0]-[3]
    public int p1;  // Assembled Integer of payload bytes [4]-[7]
    public short[]ps;// Assembled Short from pairs of payload bytes
    public long pl; // Assembled Long from payload bytes [0]-[7]
    public byte[] pb;// Binary bytes as received and converted from ascii/hex input
    public int chk; // Byte checksum
    public int val; // zero = no error; negative for error
    
    /* *********************************************************************
    * Constructors
    * ********************************************************************* */
    public Canmsg2j(){
        pb = new byte[15]; ps = new short[4];
    }
    public Canmsg2j(int iseq, int iid){
        seq = iseq; id = iid; pb = new byte[15]; ps = new short[4];
    }
    public Canmsg2j(int iseq, int iid, int idlc){
        seq = iseq; id = iid; dlc = idlc; pb = new byte[15]; ps = new short[4];
    }
    public Canmsg2j(byte[] b){
        pb = new byte[15];
    }
    /* *************************************************************************
    Compute CAN message checksum on binary array
    param   : byte[] b: Array of binary bytes in check sum computation\
    param   : int m: Number of bytes in array to use in computation
    return  : computed checksum 
    ************************************************************************ */
      private byte checksum(byte[] b, int m){
          /* Convert pairs of ascii/hex chars to a binary byte */
          int chktot = 0xa5a5;    // Initial value for computing checksum
          for (int i = 0; i < m; i ++){ 
              chktot += (pb[i] & 0xff);  // Build total (int) from byte array
      }
           /* Add in carries and carry from adding carries */
          chktot += (chktot >> 16); // Add carries from low half word
          chktot += (chktot >> 16); // Add carry from above addition
          chktot += (chktot >> 8);  // Add carries from low byte
          chktot += (chktot >> 8);  // Add carry from above addition  
          return (byte)chktot;
      }    

      /** *********************************************************************
    * Check message for errors and Convert incoming ascii/hex CAN msg to an array of bytes
    *   plus assemble the bytes comprising CAN ID into an int.
   
     * @param msg
     * @return  
     *  * Return: 0 = OK;
    *        -1 = message too short (less than 14)
    *        -2 = message too long (greater than 30)
    *        -3 = number of bytes not even
    *        -4 = payload count is negative or greater than 8
    *        -5 = checksum error
    * *********************************************************************
     */
      /**
       * Check message for errors and Convert incoming ascii/hex CAN msg to an array of bytes
    *   plus assemble the bytes comprising CAN ID into an int.
    *   Return: 0 = OK;
    *        -1 = message too short (less than 14)
    *        -2 = message too long (greater than 30)
    *        -3 = number of bytes not even
    *        -4 = payload count is negative or greater than 8
    *        -5 = checksum error
       * @param msg
       * @return 
       */
   public int convert_msgtobin (String msg){
   /* msg = String with ascii/hex of a CAN msg */
    String x;
    int m = msg.length();
    if (m < 14) return -1;  // Too short for a valid CAN msg
    if (m > 30) return -2;  // Longer than the longest CAN msg
    if ((m & 0x1) != 0) return -3; // Not even: asci1: hex must be pairs
    
    pb = DatatypeConverter.parseHexBinary(msg); // Convert ascii/hex to byte array
    
    /* Check computed checksum versus recieved checksum.  */
    byte chkx = checksum(pb,(m/2) - 1);
    if (chkx != pb[((m/2)-1)]){ 
        System.out.println(msg);
        for (int j = 0; j < (m/2); j++){
           System.out.format("%02X ",pb[j]);
        }
        System.out.format("chkx: %02X" + " pb[((m/2) -1)]: %02X\n", chkx, pb[((m/2) -1)] );
        return -5; // Return error code
     }
    
    /* Check that the payload count is within bounds */
    if (pb[5] < 0) return -4;    // This should not be possible
    if (pb[5] > 8) return -4;    // Too large means something wrong.
       
    /* Extract some items that are of general use */
    seq = (pb[0] & 0xff);     // Sequence number
    dlc = (pb[5] & 0xff);     // Save payload ct in an easy to remember variable
    id = (((((((pb[4] & 0xff) << 8) | (pb[3] & 0xff)) << 8) | (pb[2] & 0xff)) << 8) | pb[1]);
   
    return 0;
   }
   /* Combine payload bytes [0]-[3] to an Integer */
   public void in_1int(){
        p0 = (((((((pb[9] & 0xff) << 8) | (pb[8] & 0xff)) << 8) | (pb[7] & 0xff)) << 8) | (pb[6] & 0xff));
   }
     /* Combine payload bytes [n]-[n+3] to an Integer */
   public void in_1int_n(int n){
        p0 = (((((((pb[9+n] & 0xff) << 8) | (pb[8+n] & 0xff)) << 8) | (pb[7+n] & 0xff)) << 8) | (pb[6+n] & 0xff));
   }
   /* Combine payload bytes [0]-[3] and [4]-[7] to two Integers */
   public void in_2int(){
        p0 = (((((((pb[ 9] & 0xff) << 8) | (pb[ 8] & 0xff)) << 8) | (pb[ 7] & 0xff)) << 8) | (pb[ 6] & 0xff));
        p1 = (((((((pb[13] & 0xff) << 8) | (pb[12] & 0xff)) << 8) | (pb[11] & 0xff)) << 8) | (pb[10] & 0xff));
   }
   /* Combine payload bytes [0]-[7] to one long */
   public long in_1long(){
  {
        if (pb[5] != 8)
        {
            val = -1;   // insufficient payload length
            return 0;
        } else
        {
            int x0 = (((((
                  (pb[ 9] <<          8) | (pb[ 8] & 0xff)) << 8) 
                | (pb[ 7] & 0xff)) << 8) | (pb[ 6] & 0xff));
            int x1 = (((((
                  (pb[13] <<          8) | (pb[12] & 0xff)) << 8)
                | (pb[11] & 0xff)) << 8) | (pb[10] & 0xff));
            // Combine to make a long
            long lng = ((long)x1 << 32) | (x0 & 0xffffffffL);
            val = 0;
            return lng;
        }
    }
       }
   
   /* *********************************************************************
    * Prepare CAN msg: Convert the array pb[] to hex and add checksum
    * The binary array pb[] is expected to have been set up.
    * ********************************************************************* */
   /**
    * Prepare CAN msg: Convert the array pb[] to hex and add checksum
    * The binary array pb[] is expected to have been set up.
    * @return 
    */
    public String out_prep(){  // Convert payload bytes from byte array
        
       /* A return of 'null' indicates an error */
       if (dlc > 8) return null;
       if (dlc < 0) return null;
       
       /* Setup Id bytes, little endian */
       pb[1] = (byte)(id & 0xff);
       pb[2] = (byte)((id >>  8) & 0xff);
       pb[3] = (byte)((id >> 16) & 0xff);
       pb[4] = (byte)((id >> 24) & 0xff);
       
       pb[5] = (byte)dlc;    // Payload size
       
       int msglength = (dlc + 6); // Length not including checksum
  
       pb[(msglength)] = checksum(pb,msglength); // Place checksum in array
      
       
       /* Convert binary array to ascii/hex */
       StringBuilder x = new StringBuilder(DatatypeConverter.printHexBinary(pb));
       msglength = (msglength + 1) * 2;
       x.setLength(msglength);
       x.append("\n"); // Line terminator
       String s = x.toString();
System.out.format("len s: %d\n",s.length());      
System.out.format("out_prep: |%s|\n",s);
       return s;
    }
   /* *********************************************************************
    * Convert to payload byte array little endian
    * ********************************************************************* */
    /**
     * Convert to payload byte array little endian
     * @param n 
     */
    public void out_int0(int n){
        pb[6] = (byte)(n & 0xff);
        pb[7] = (byte)((n >>  8) & 0xff);
        pb[8] = (byte)((n >> 16) & 0xff);
        pb[9] = (byte)((n >> 24) & 0xff);
        dlc = 4;   // set payload count (dlc)
    }
   /* *********************************************************************
    * Convert to payload byte array little endian
    * ********************************************************************* */
    /**
     * Convert to payload byte array little endian
     * @param n 
     */
    public void out_int1(int n){
        pb[10] = (byte)(n & 0xff);
        pb[11] = (byte)((n >>  8) & 0xff);
        pb[12] = (byte)((n >> 16) & 0xff);
        pb[13] = (byte)((n >> 24) & 0xff);
        dlc = 8;   // set payload count (dlc)
    }
   /* *********************************************************************
    * Convert long to payload byte array little endian
    * ********************************************************************* */
    /**
     * Convert long to payload byte array little endian
     * @param l 
     */
    public void out_1long(long l){
        pb[ 6] = (byte)( l & 0xff);
        pb[ 7] = (byte)((l >>  8) & 0xff);
        pb[ 8] = (byte)((l >> 16) & 0xff);
        pb[ 9] = (byte)((l >> 24) & 0xff);
        pb[10] = (byte)((l >> 32) & 0xff);
        pb[11] = (byte)((l >> 40) & 0xff);
        pb[12] = (byte)((l >> 48) & 0xff);
        pb[13] = (byte)((l >> 56) & 0xff);
        dlc = 8;   // set payload count (dlc)
    }
    /**
     * Convert long to payload byte array little endian
     * Variable number of bytes
     * @param l // Bytes packed into a long 
     * @param n // Payload size
     */
    public void out_1longvar(long l, int n){
        for (int i = 0; i < n; i++){
            pb[i+6] = (byte)((l >> (i * 8)) & 0xff);
        }
    }
   /* *********************************************************************
    * Convert shorts to payload byte array, little endian 
    * ********************************************************************* */
    /**
     * Convert shorts to payload byte array, little endian
     * @param s
     * @return 
     */
    public boolean out_nshort(Short[] s){
        int x;
        x = s.length;
        if (x == 0){    // JIC
            dlc = 0; return true; // Set payload size and return
        }
        if (x < 0) return false;    // Should not be possible
        if (x > 4) return false;    // Oops!
        
        for (int i = 0; i < x; x += 1){
            pb[((2*i) + 6)] = (byte)((s[i] >> 8) & 0xff);
            pb[((2*i) + 7)] = (byte)(s[i] & 0xff);
        }
        dlc = (x * 2);  // Set payload size
        return true;
    }
}
