/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author deh
 */
public class PccConst {
    
    public PccConst(){
        
    }
    public static void fillList(Statement stmt) throws SQLException{
        String query = "select PAYLOAD_TYPE.* FROM PAYLOAD_TYPE";
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            int count = 0;
            
            //ArrayList<Canmsginfo> canidlist = new ArrayList<Canmsginfo>();

        while (rs.next()) {
            count += 1;
            StringIntPairs sip = new StringIntPairs(
                rs.getString ("PAYLOAD_TYPE_NAME"),
                rs.getInt   ("PAYLOAD_TYPE_CODE"),
                rs.getString("DESCRIPTION12")   // PAYLOAD_TYPE
            );  
        }
    }
}
