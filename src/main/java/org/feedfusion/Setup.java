/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Srajan
 */
public class Setup {
    
  public static String DB_URL = "jdbc:mysql://139.59.64.249:3306/feedfusion";
  public static String DB_NAME="feedfusion";
  public static String DB_USERNAME = "test";
  public static String DB_PASSWORD = "fujion";
  
  public static String CONSUMER_KEY = "Olwk4ncLNgYZcROLvP9oAFrgv";
  public static String CONSUMER_SECRET = "eht2OHYflAV1Cu8GP9XA46zm7KbiivY35TytvJ91aMX67brKEF";
  
  public static Connection conn=null;
  public static Connection getConnection(){
      if(conn!=null)
          return conn;
      else{
          try {
              Class.forName("com.mysql.jdbc.Driver");
              conn=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
              return conn;
          } catch (Exception e) {
              System.out.println(e);
          }
      }
      return conn;
      
  }
  
  public static boolean checkSession(String username,String sessionid){
      try {
          if(conn==null)
              conn=getConnection();
           PreparedStatement stmt= conn.prepareStatement("select *from session where username=? and sessionid=?; ");
           stmt.setString(1,username);
           stmt.setString(2,sessionid);
           ResultSet rs=stmt.executeQuery();
           if(rs.next())
               return  true;
           else return false;
           
      } catch (SQLException ex) {
          System.out.println(ex);
      }
     return false;   
  }
}
