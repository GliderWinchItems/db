/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;
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
    ArrayList<StringIntPairs> paylist = new ArrayList<StringIntPairs>();
    
    public PccConst(){
        
    }
    public void fillList(Statement stmt) throws SQLException{
        String query = "select PAYLOAD_TYPE.* FROM PAYLOAD_TYPE ORDER BY PAYLOAD_TYPE_CODE";
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
        int count = 0;
        System.out.format("seq code name    description12\n");
        while (rs.next()) {
                            // Convert sql/db info into Canmsginfo
                StringIntPairs sip = new StringIntPairs(
                    rs.getString ("PAYLOAD_TYPE_NAME"),
                    rs.getInt    ("PAYLOAD_TYPE_CODE"),
                    rs.getString ("DESCRIPTION12")
                );
                System.out.format("%2d %3d\t%s\t%s\n",count,sip.n,sip.s,sip.description);
                paylist.add(sip);
                count += 1;
        }
    }
}
