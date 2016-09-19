/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author deh
 */
public class Jcanpoll {

        private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static String databaseConnectionName = "jdbc:derby://localhost:1527/pcc";
        private static Object LocalDateTime;
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
   public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

       Connection connection = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        
                  //Check for DB drivers
        try{
            Class.forName(clientDriverName);
            Class.forName(driverName);
        }catch(java.lang.ClassNotFoundException e) {
            System.out.println("client Driver Name, or driver Name exception\n");
            throw e;
        }
    
// ==== Try to connect to the specified database ===============================
        try{
            
            System.out.println("List of CANIDs sorted by HEX ");
            java.util.Date date= new java.util.Date();
            System.out.format("// %s\n",new Timestamp(date.getTime()));
            
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection\n");
            
            stmt = connection.createStatement();
            ResultSet rs;
            
            Integer totalct = 0;
            
// ===== Fill ArrayList with database data =====================================
            ArrayList<Canmsginfo> canidlist = new ArrayList<>();
            CanmsginfoBuild cmib = new CanmsginfoBuild();
            cmib.genlist_Canid(stmt, canidlist);
           
// ===== Experimenting with constants
            PccConst pccconst = new PccConst();
            pccconst.fillList(stmt);
            
// ===== Test/debugging search =================================================
            long l1 = 0xE1C00000;   // Remember sign extends
            l1 = (l1 << 32) >>> 32; // Get rid of upper bits
            Long L1 = l1;           // Needed for comparisons
            Canmsginfo cmi2 = new Canmsginfo (L1); // CAN ID w remainder nulls
            int index = Collections.binarySearch(canidlist, cmi2); // Lookup
            System.out.format("########### search test binarySearch: index: %d %08X\n",index, L1); 
            
// ===== Setup connection to socket ============================================
            String ip;
            ip = "127.0.0.1";   // Default ip address
            //String ip = new String("10.1.1.80");
            int port = 32123; // Default port
        
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
            Canmsg2j can1; 
            can1 = new Canmsg2j();    // Received CAN message
            DbPayload dbpay = new DbPayload();

// =====  Set up display windows ===============================================
           /* Create and display the form */
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
            });
//        ArrayList<String> aList = new ArrayList<String>();

//        DefaultListModel<String> model = new DefaultListModel<String>();
//        for(String s:aList){
//        model.addElement(s);
//        }
        
        }
        
            catch(SQLException e) {
            throw e;
        }
        
        finally {
            if (pstmt != null) pstmt.close();
        }
        
//        System.out.format("DONE\n");
    }
}
