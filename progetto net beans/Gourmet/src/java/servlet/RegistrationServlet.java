/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author lorenzo
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("null")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String fname = request.getParameter("Name");
        String lname = request.getParameter("Surname");
        String uname = request.getParameter("nickname");
        String email = request.getParameter("Email_");
        String passw = request.getParameter("Password_");
        
        System.out.println("fname = "+fname);
        System.out.println("lname = "+lname);
        System.out.println("uname = "+uname);
        System.out.println("email = "+email);
        System.out.println("passw = "+passw);
        
        
        try{
        
            String query = "insert into USERS"
                            +"(NAME,SURNAME,NICKNAME,EMAIL,PASSWORD,TYPE)"
                            +"(?,?,?,?,?,?)";    
            //PreparedStatement ps = (PreparedStatement) manager.getCon().prepareStatement(query)     ;

            PreparedStatement ps = (PreparedStatement) manager.getCon().prepareStatement("INSERT INTO USERS (NAME,SURNAME,NICKNAME,EMAIL,PASSWORD,TYPE) VALUES(?,?,?,?,?,?)");
  
        
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,uname);
            ps.setString(4,email);
            ps.setString(5,passw);
            ps.setInt(6, 1);
            
            ps.executeUpdate();
            request.setAttribute("messageok", "Registrazione effettuata. Ora puoi fare il login");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        catch(Exception se)
        {
            request.setAttribute("messageerr", "Registrazione fallita");
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
