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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lorenzo
 */
@WebServlet(name = "RecensioneServlet", urlPatterns = {"/RecensioneServlet"})
public class RecensioneServlet extends HttpServlet{
    
    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
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
        
        HttpSession session = request.getSession(true);  
            
        String recensione = request.getParameter("description");
        String valueR = request.getParameter("radio");
        String foodR = request.getParameter("radiof");
        String serviceR = request.getParameter("radios");
        String priceR = request.getParameter("radiov");
        String athmoR = request.getParameter("radioa");
        String id = request.getParameter("id");
        Integer idR = null;
        if(id != null)  idR = Integer.parseInt(id);
        String name = (String)session.getAttribute ("username");
        Integer idC = (Integer) session.getAttribute("ID");
        
        try {
            PreparedStatement ps = (PreparedStatement) manager.getCon().prepareStatement("INSERT INTO REVIEWS "
                    + "(GLOBAL_VALUE,FOOD,SERVICE,VALUE_FOR_MONEY,ATMOSPHERE,NAME,DESCRIPTION,ID_RESTAURANT,ID_CREATOR)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,valueR);
            ps.setString(2,foodR);
            ps.setString(3, serviceR);
            ps.setString(4, priceR);
            ps.setString(5, athmoR);
            ps.setString(6, name);
            ps.setString(7, recensione);
            if (idR != null)ps.setInt(8, idR);
            if (idC != null)ps.setInt(9, idC);
            //manca id_photo
            ps.executeQuery();
            
            request.setAttribute("messageOK", "Recensione ok");
            
        } catch (SQLException ex) {
            
            request.setAttribute("messageERR", "Recensione Error");
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
