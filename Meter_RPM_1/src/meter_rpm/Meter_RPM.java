/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package meter_rpm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.SwingUtilities;

/**
 *
 * @author deh
 */
public class Meter_RPM {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
       final Stackoverflow so;
                so = new Stackoverflow(25, "ENGINE RPM");
          String ip;
        ip = "127.0.0.1";   // Default ip address
        //String ip = new String("10.1.1.80");
        int port = new Integer (32123); // Default port
        
    /* Deal with the arguments on the command line */
        if (args.length > 2){
            System.out.format("Only two args allowed, we saw %d\n", args.length);
            System.exit(-1);
        } 
        if (args.length == 2){
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        Socket socket = new Socket(ip, port);
          BufferedReader in = 
            new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
          Canmsg2 can1; 
            can1 = new Canmsg2();    // Received CAN message
            int ret;
            final int[] eng;
            eng = new int[4];
   // ======== Endless loop ====================================================
            while (true) {
              String msg = in.readLine();         // Get a line from socket
     //         System.out.format("%s\n",msg);
              ret = can1.convert_msgtobin(msg);   // Convert ascii/hex msg to binary byte array
              if (ret != 0){ // Did the conversion pass all the checks?
                    System.out.format("Input conversion error: %d\n", ret); // No show the error code
                    continue;
                    }
           if (can1.id == 0x40800000){ /* Is this CAN ID an engine sensor message? */
            can1.in_2int();     // Convert payload to two ints  ...
            eng[0] = can1.p0;   // Manifold pressure (not scaled)
            eng[1] = can1.p1;   // RPM (not scaled)
            
            /* Scale readings for display purposes. */
            final double scaled0 = can1.p1 * .1; // RPM
            final double scaled1 = ((double)(can1.p0) / 103.88) - 0.1; // Inches of mercury
          
            SwingUtilities.invokeLater(new Runnable() 
            {
             @Override
            public void run(){
             
                
//if (SwingUtilities.isEventDispatchThread())
//{
//  System.err.println("Is running on EDT");
//}
//else
//{
//  System.err.println("Is not running on EDT");
//}   
                so.setValue(scaled0, scaled1);
          
                }
            });            
            
                
                }               
        }
    }
    
}
