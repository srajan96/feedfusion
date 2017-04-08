/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion.facebook;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import java.util.List;
import java.sql.*;
import org.feedfusion.Setup;
/**
 *
 * @author Srajan
 */
public class FBPostUpdate extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try {
            /* TODO output your page here. You may use following sample code. */
            java.sql.Connection conn=(java.sql.Connection) Setup.getConnection();
            String username=request.getParameter("username");
            String session=request.getParameter("session");
            String status=request.getParameter("status");
            
            if(Setup.checkSession(username, session)){
                 PreparedStatement pst=conn.prepareStatement("select access_token from ff_facebook where username= ? ;");
                 pst.setString(1,username);
                 ResultSet rs= pst.executeQuery();
                 String access_token = null;
                // System.out.println("okayt"); 
                while(rs.next()){
                    access_token =rs.getString("access_token");
                   
                }
                //out.println(access_token);
              FacebookClient facebookClient = new DefaultFacebookClient(access_token,"8bb6800994144f6b4438a49aadcf5e4e", Version.VERSION_2_8);
                FacebookType publishMessageResponse =  facebookClient.publish("me/feed", FacebookType.class,Parameter.with("message", status));

                  //     out.println("Published message ID: " + publishMessageResponse.getId());
                       out.println("{\"success\":\"posted\"}");
            } else
                out.println("\"illegal\"");
         
         
        }catch(Exception e){
         out.println(e);
            out.println("{\"success\":\"error\"}");
            System.out.println(e);
        }
        finally {
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
