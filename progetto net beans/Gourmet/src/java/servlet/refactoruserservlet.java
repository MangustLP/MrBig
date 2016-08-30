/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ernrico
 */
public class refactoruserservlet extends HttpServlet {

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
        String uname = request.getParameter("nickname").toLowerCase();
        String email = request.getParameter("Email_");
        String passw = request.getParameter("Password_");
        
        HttpSession session = request.getSession(true);
        
        
        if ((fname != null)&&(lname != null)&&(uname != null)&&(email != null)&&(passw != null)){
        
            try{
                String query="UPDATE USERS SET NAME=\'"+fname+"\', SURNAME=\'"+lname+"\', NICKNAME=\'"+uname+"\', EMAIL=\'"+email+"\',PASSWORD=\'"+passw+"\' WHERE ID="+ session.getAttribute("ID");
                System.out.println(query);
                Statement ps = (Statement)  manager.getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ps.executeUpdate(query);
                ps.close();
                session.setAttribute("username", uname); 
                session.setAttribute("name",fname);    
                session.setAttribute("surname",lname);  
                RequestDispatcher rd = request.getRequestDispatcher("/account-registered.jsp");
                rd.forward(request, response);
                }
            catch(Exception se)
            {
                Logger.getLogger(RecensioneServlet.class.getName()).log(Level.SEVERE, null, se);
            }
        }
        else{
                request.setAttribute("messageERR", "Registrazione fallita");
                RequestDispatcher rd = request.getRequestDispatcher("/refactorinfoutente.jsp");
                rd.forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
