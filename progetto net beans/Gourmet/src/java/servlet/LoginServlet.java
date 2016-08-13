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
        String username = req.getParameter("nickname");
        String password = req.getParameter("pass");
         
        try {
            loggeduser=manager.login(username, password);
            if (loggeduser!=null){
                HttpSession session = req.getSession(true);
                session.setAttribute("logged", loggeduser);
                resp.sendRedirect(req.getContextPath() + "/index");
            }
            else{
                req.setAttribute("message", "Username/password non esistente !");
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*try {

            user = manager.authenticate(username, password);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        // se non esiste, ridirigo verso pagina di login con messaggio di errore
        if (user == null) {
            // metto il messaggio di errore come attributo di Request, così nel JSP si vede il messaggio

            req.setAttribute("message", "Username/password non esistente !");

            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);

        } else {

            // imposto l'utente connesso come attributo di sessione
            // per adesso e' solo un oggetto String con il nome dell'utente, ma posso metterci anche un oggetto User
            // con, ad esempio, il timestamp di login

            HttpSession session = req.getSession(true);
            user.setType("registrato");
            session.setAttribute("user", user.getUsername());
            session.setAttribute("type",user.getType());
            resp.sendRedirect(req.getContextPath() + "/index");
        }*/
    }

}