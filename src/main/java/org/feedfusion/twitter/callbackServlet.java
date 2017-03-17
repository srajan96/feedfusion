/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion.twitter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author Srajan
 */
public class callbackServlet extends HttpServlet {

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
      // Get twitter object from session
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		//Get twitter request token object from session
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		try {
			// Get twitter access token object by verifying request token 
		    AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
		    request.getSession().removeAttribute("requestToken");
		    
		    // Get user object from database with twitter user id
		    UserPojo user = TwitterDAO.selectTwitterUser(accessToken.getUserId());
		    if(user == null) {
		       // if user is null, create new user with given twitter details 
		       user = new UserPojo();
		       user.setTwitter_user_id(accessToken.getUserId());
		       user.setTwitter_screen_name(accessToken.getScreenName());
		       user.setAccess_token(accessToken.getToken());
		       user.setAccess_token_secret(accessToken.getTokenSecret());
		       TwitterDAO.insertRow(user);
		       user = TwitterDAO.selectTwitterUser(accessToken.getUserId());
		    } else {
		       // if user already there in database, update access token
		       user.setAccess_token(accessToken.getToken());
		       user.setAccess_token_secret(accessToken.getTokenSecret());
		       TwitterDAO.updateAccessToken(user);
		    }
		    request.setAttribute("user", user);

    request.setAttribute("user", user);
} catch (Exception e ) {
    PrintWriter out=response.getWriter();
    out.println(e);
    //throw new ServletException(e);
}/* catch(DBException e){
    throw new ServletException(e);
}
*/
             StringBuffer  dashURL = request.getRequestURL();
		    int index = dashURL.lastIndexOf("/");
		    dashURL.replace(index, dashURL.length(), "").append("/dashboard");
response.sendRedirect(dashURL.toString());
//request.getRequestDispatcher("/status.jsp").forward(request, response);

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
