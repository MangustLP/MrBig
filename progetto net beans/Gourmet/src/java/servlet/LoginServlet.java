package servlet;

import db.DBManager;
import db.User;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");      
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggeduser=null;
        String username = req.getParameter("nickname").toLowerCase();
        String password = req.getParameter("pass");  
        
        try {
            loggeduser=manager.login(username, password);
            if (loggeduser!=null){
                HttpSession session = req.getSession(true);
                if(loggeduser.getType().toString().equals("1"))
                    session.setAttribute("type", "registered");
                if(loggeduser.getType().toString().equals("2"))
                    session.setAttribute("type", "restaurateur");
                if(loggeduser.getType().toString().equals("3"))
                    session.setAttribute("type", "admin");
                session.setAttribute("username", username);
                session.setAttribute("ID",loggeduser.getID());   
                session.setAttribute("name",loggeduser.getName());    
                session.setAttribute("surname",loggeduser.get);                 
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
            else{
                req.setAttribute("messageerr", "Username/password non esistente !");
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}