/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;


/**
 *
 * @author deh
 */
public class Derbytest {

        private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static String databaseConnectionName = "jdbc:derby://localhost:1527/pcc;create=true";
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
           
        Connection connection = null;
        Statement stmt = null;
      
       String queryA = 
"SELECT * FROM PARAM_LIST\n" +
"       ORDER BY FUNCTION_TYPE";    
        
       
       // Some header stuff
       System.out.println("// PARAM_LIST V NUMBER_TYPE");
       System.out.println("#ifndef PARAMVTYPE");
       System.out.println("#define PARAMVTYPE");
       System.out.println("struct PRMTYP{");
       System.out.println("\tuint16_t id;\t// Parmeter code");
       System.out.println("\tuint16_t type;\t// Number type code of this parameter;");
       System.out.println("};\n#endif\n");
       
//Check for DB drivers
        try{
            Class.forName(clientDriverName);
            Class.forName(driverName);
        }catch(java.lang.ClassNotFoundException e) {
            System.out.println("client Driver Name, or driver Name exception\n");
            throw e;
        }
    
        //Try to connect to the specified database
        try{
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection!\n");
            
            stmt = connection.createStatement();   
            
            // Go through list: make #define for number of parameters for each function
            ResultSet rs = stmt.executeQuery(queryA);
            String curfunction = "";
            int flag = 0;
            int count = 0;
            String c1 = "";
            
            while (rs.next()) {
                 c1 = rs.getString("FUNCTION_TYPE");
                 if (!(c1.equals(curfunction))){
                     if (flag == 1){
                         System.out.format("#define %s_PARAM_COUNT\t%d \n",curfunction, count);
                         count = 0;
                     }
                     curfunction = c1;
                     flag = 1;
                 }else{
                     count += 1;
                 }
            }
            System.out.format("#define %s_PARAM_COUNT\t%d \n",c1, count);

             // Go through list and produce table for each function
            rs = stmt.executeQuery(queryA);           
            curfunction = "";
            flag = 0;
            while (rs.next()) {
                 c1 = rs.getString("FUNCTION_TYPE");
                 if (!(c1.equals(curfunction))){
                     if (flag == 1){
                         System.out.println("#endif\n");
                     }
                 curfunction = c1;
                 System.out.format("#ifdef %s\n", c1);
                 }
                 else{
                 System.out.format("{%-24s\t",   rs.getString("PARAM_NAME"));
                 System.out.format("  %-10s},",  rs.getString("TYPE_NAME"));
                 System.out.format("\t/* %s" + " */\n",  rs.getString("DESCRIPTION"));
                 flag = 1;

                     }
            }
            System.out.println("#endif\n");
 
             
        }catch(SQLException e) {
            //TODO Fix error handling
            e.printStackTrace();
            throw e;
 
        }
        
        
        finally {
            if (stmt != null) stmt.close();
        }
    }
   
}