/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion.instagram;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.feedfusion.Setup;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;


/**
 *
 * @author Srajan
 */
public class INGetTimeline extends HttpServlet {

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
         
            
            if(Setup.checkSession(username, session)){
                 PreparedStatement pst=conn.prepareStatement("select access_token from ff_instagram where username= ? ;");
                 pst.setString(1,username);
                 ResultSet rs= pst.executeQuery();
                 String access_token = null;
                // System.out.println("okayt"); 
                while(rs.next()){
                    access_token =rs.getString("access_token");
                   
                }
                Token t=new Token(access_token,"a6fc64493c6f4e5fa6db58b34fd8071b");
                Instagram instagram = new Instagram(t);
                
               // System.out.println(access_token+"---"+access_token_secret);
                MediaFeed mediaFeed = instagram.getUserFeeds();
        List<MediaFeedData> mediaFeeds = mediaFeed.getData();

        for (MediaFeedData mediaData : mediaFeeds) {
            System.out.println("id : " + mediaData.getId());
            System.out.println("created time : " + mediaData.getCreatedTime());
            System.out.println("link : " + mediaData.getLink());
            System.out.println("tags : " + mediaData.getTags().toString());
            System.out.println();
        }
             

         
            }
            else
                out.println("\"illegal\"");
        } catch (SQLException ex) {
            Logger.getLogger(INGetTimeline.class.getName()).log(Level.SEVERE, null, ex);
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
