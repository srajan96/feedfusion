/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion.facebook;



import facebook4j.FacebookFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.ResponseList;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Srajan
 */
public class FBGetTimeline2 extends HttpServlet {

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
           ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
              .setOAuthAppId("1978232082408711")
              .setOAuthAppSecret("8bb6800994144f6b4438a49aadcf5e4e")
              .setOAuthAccessToken("EAAcHMQyOJQcBAPGUHZB5o5kjlznqegOMWIXUFrCMgFqgDVYWyiOOiK74jvt2LQZA1uvGR3Hkjmos5nV6Iw5dJR79lGb86PnSjwren9Y4mGDbF8eMPQw28WyR5fgIEFxNc6CBOV1eRYk0f5jngzvXWOJwKTD4X3mUn2LwTj7FVn2UvbZBwE1lbdDYWKrSAEZD")
              .setOAuthPermissions("email");
            FacebookFactory ff = new FacebookFactory(cb.build());
            Facebook facebook = (Facebook) ff.getInstance();
            ResponseList<Post> feed = facebook.getHome();
            for(Post p:feed){
                System.out.println(p.getId());
                out.println(p.getId());
            }
            
        } catch (FacebookException ex) {
            Logger.getLogger(FBGetTimeline2.class.getName()).log(Level.SEVERE, null, ex);
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
