package org.feedfusion;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *
 * @author Srajan
 */
public class Login extends HttpServlet {

    final String dbPassword=Setup.DB_PASSWORD;
    final String dbName=Setup.DB_NAME;
    final String dbUser=Setup.DB_USERNAME;
    final String dbPath="localhost:3306";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName,dbUser,dbPassword);
            PreparedStatement stmt =conn.prepareStatement("select username from users where username=(?) and password=sha1(?);"); 
            stmt.setString(1,username);
            stmt.setString(2,password);
            //out.print(stmt);
            ResultSet rs=stmt.executeQuery();
            String op="";
            String sessionid="";
            UUID uniqueKey;
            
            
            
            
            if(rs.next()){
                PreparedStatement stmt2 =conn.prepareStatement("select sessionid from session where username=(?);"); 
                stmt2.setString(1,username);
                //out.print(stmt2);
                ResultSet rs2=stmt2.executeQuery();
                if(rs2.next()){
                    sessionid=rs2.getString("sessionid");
                }
                else{
                    while(true){
                        uniqueKey = UUID.randomUUID();
                        sessionid= uniqueKey.toString();

                        PreparedStatement stmt1 = conn.prepareStatement("insert into session values(?,?);"); 
                        stmt1.setString(1,username);
                        stmt1.setString(2,sessionid);
                        //out.print(stmt1);
                        if(stmt1.executeUpdate()==1){
                            break;
                        }

                    }    
                }
                
                op="{\"login_correct\":true,\"sessionid\":\""+sessionid+"\" }";
            
            }
            else{
              op="{\"login_correct\":false}";
            }
             out.print(op);
        }catch(Exception e){
            out.println(e);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
