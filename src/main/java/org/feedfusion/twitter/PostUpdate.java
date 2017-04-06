/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion.twitter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.feedfusion.Setup;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Srajan
 */
public class PostUpdate extends HttpServlet {

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
            Connection conn=Setup.getConnection();
            String username=request.getParameter("username");
            String session=request.getParameter("session");
            String status=request.getParameter("status");
            
            if(Setup.checkSession(username, session)){
                 PreparedStatement pst=conn.prepareStatement("select access_token,access_token_secret from ff_twitter where username= ? ;");
                 pst.setString(1,username);
                 ResultSet rs= pst.executeQuery();
                 String access_token = null,access_token_secret=null;
                // System.out.println("okayt"); 
                while(rs.next()){
                    access_token =rs.getString("access_token");
                    access_token_secret=rs.getString("access_token_secret");
                }
               // System.out.println(access_token+"---"+access_token_secret);
                
               ConfigurationBuilder cb = new ConfigurationBuilder();
               cb.setDebugEnabled(true)
              .setOAuthConsumerKey("Olwk4ncLNgYZcROLvP9oAFrgv")
              .setOAuthConsumerSecret("eht2OHYflAV1Cu8GP9XA46zm7KbiivY35TytvJ91aMX67brKEF")
              .setOAuthAccessToken(access_token)
              .setOAuthAccessTokenSecret(access_token_secret);
                
               TwitterFactory tf = new TwitterFactory(cb.build());
               Twitter twitter = tf.getInstance();
               twitter.updateStatus(status);
               out.println("{\"success\":true}");
            } else
                out.println("\"illegal\"");
         
          
        }catch(Exception e){
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
