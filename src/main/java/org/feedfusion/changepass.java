/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pavilion
 */
public class changepass extends HttpServlet {

    final String dbPassword="Try123zxs";
    final String dbName="fusion";
    final String dbUser="root";
    final String dbPath="localhost:3306";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username=request.getParameter("username");
        String curpass=request.getParameter("curpass");
        String newpass=request.getParameter("newpass");
        String sessionid=request.getParameter("sessionid");
        
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName,dbUser,dbPassword);
            PreparedStatement stmt =conn.prepareStatement("select sessionid from session where username=(?) and sessionid=(?);"); 
            stmt.setString(1,username);
            stmt.setString(2,sessionid);
            //out.print(stmt);
            ResultSet rs=stmt.executeQuery();
            String op="";
            
            
            if(rs.next()){
                PreparedStatement stmt2 =conn.prepareStatement("update users set password=sha1(?) where username=(?) and password=sha1(?);"); 
                stmt2.setString(1,newpass);
                stmt2.setString(2,username);
                stmt2.setString(3,curpass);
                //out.print(stmt2);
                int rs2=stmt2.executeUpdate();
                if(rs2==1){
                    op="\"true\"";
                }
                else{
                    op="\"false\"";  
                }
                
            }
            else{
              op="\"illegal\"";
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
