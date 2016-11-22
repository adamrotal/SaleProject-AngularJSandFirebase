/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mac
*/
package bean;

import java.sql.*;  

public class getFromDatabase {    
public static boolean validateLogin(LoginBean bean){  
    boolean status=false;  
    try{  
        Connection con=Connector.getCon();  
        PreparedStatement ps=con.prepareStatement("select email,password from user where email=? and password=?");  

        ps.setString(1,bean.getEmail());  
        ps.setString(2, bean.getPass());  

        ResultSet rs=ps.executeQuery();  
        status=rs.next();  
    }catch(SQLException e){}
    return status;  
    }  
} 
