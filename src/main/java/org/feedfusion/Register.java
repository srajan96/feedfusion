package org.feedfusion;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Object;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import java.util.Enumeration;
/**
 *
 * @author Srajan
 */
public class Register extends HttpServlet {
    //Database connection parameters
  final String dbPassword=Setup.DB_PASSWORD;
    final String dbName=Setup.DB_NAME;
    final String dbUser=Setup.DB_USERNAME;
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
            throws ServletException, IOException, SQLException,JSONException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        out.print("Hi ");
         String name="",username="",email="",password="";
//         out.println("\n"+request);
//         out.println("\nbefore");
//         
//        Enumeration params = request.getParameterNames(); 
//            while(params.hasMoreElements()){
//                String paramName = (String)params.nextElement();
//                out.println("Parameter Name - ");
//                out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
//            }

//        try{
//            JSONObject obj = new JSONObject(request.getParameter("userdata"));
//            name=obj.getString("name");
//            username=obj.getString("username");
//            email=obj.getString("email");
//            password=obj.getString("pass"); 
//            out.println("Hi "+name+" "+username+" "+email+" "+password);
//        }
//        catch(Exception e){
//            out.println(e);
//        }
        
        name=request.getParameter("name");
        username=request.getParameter("username");
        email=request.getParameter("email");
        password=request.getParameter("pass");
        
               
        try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName,dbUser,dbPassword);
             PreparedStatement updateemp =conn.prepareStatement("insert into users(username,password,name,email)values(?,sha1(?),?,?)"); 
             updateemp.setString(1,username);
             updateemp.setString(2,password);
             updateemp.setString(3, name);
             updateemp.setString(4, email);
             updateemp.executeUpdate();
             String op="{registered:true}";
             out.print(true);
        } 
        catch(Exception e){
            out.print(false);
           e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
