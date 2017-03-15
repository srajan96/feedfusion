/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.StatusUpdate;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import twitter4j.conf.ConfigurationBuilder;
import com.squareup.okhttp.*;
/**
 *
 * @author Pavilion
 */
public class GetHomePageData extends HttpServlet {

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
        try{
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetHomePageData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetHomePageData at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
            ConfigurationBuilder cb = new ConfigurationBuilder();
           cb.setDebugEnabled(true)
          .setOAuthConsumerKey("Olwk4ncLNgYZcROLvP9oAFrgv")
          .setOAuthConsumerSecret("eht2OHYflAV1Cu8GP9XA46zm7KbiivY35TytvJ91aMX67brKEF")
          .setOAuthAccessToken("1668200646-cR34ReTvqAUgQGNvFQWlbGNkGgksXvq7yFHWlO6")
          .setOAuthAccessTokenSecret("VAU21C4JHFCrbBfwDfTkdLEkSkpjBD7VFkFowFwLPAuoC");
	
	
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
      
        List<Status> statuses;
        statuses= twitter.getHomeTimeline();
        out.println("Showing home timeline.");
        for (Status status : statuses) 
            out.println(status.getUser().getName() + ":" +     status.getText());

        }
        catch(Exception e){
            
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
