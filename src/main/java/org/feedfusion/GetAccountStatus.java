/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feedfusion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.feedfusion.Setup;
/**
 *
 * @author Srajan
 */
public class GetAccountStatus extends HttpServlet {

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
                 PreparedStatement pst1=conn.prepareStatement("select username from ff_twitter where username=? ;");
                 PreparedStatement pst2=conn.prepareStatement("select username from ff_instagram where username=? ;");
                 PreparedStatement pst3=conn.prepareStatement("select username from ff_facebook where username=? ;");
                 pst1.setString(1,username);
                 pst2.setString(1,username);
                 pst3.setString(1,username);
                 ResultSet rs1=pst1.executeQuery();
                 ResultSet rs2=pst2.executeQuery();
                 ResultSet rs3=pst3.executeQuery();
                 
                 String twitter="false";
                 String instagram="false";
                 String facebook="false";
                 
                 if(rs1.next()) twitter="true";
                 if(rs2.next()) instagram="true";
                 if(rs3.next()) facebook="true";
                 String op="{";
                 op+="\"facebook\":"+facebook+",";
                 op+="\"twitter\":"+twitter+",";
                 op+="\"instagram\":"+instagram+"}";
                 out.println(op);
            }
            else
                out.println("{\"status\":false}");
            
        }catch(Exception e){
            out.println(e);
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
