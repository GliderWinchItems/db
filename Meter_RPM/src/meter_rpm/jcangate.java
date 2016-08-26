/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jcangate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author deh
 */
public class jcangate {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
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
            if (can1.id == 0xE1C00000){ /* UNIX time msg */
 //           can1.in_2int();     // Convert payload to two ints
                System.out.format("0xE1C00000\n"); 
            }               
        }
    }
    
}
