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

/**
 *
 * @author lorenzo
 */
@WebServlet(name = "ResearchQueryServlet", urlPatterns = {"/ResearchQueryServlet"})
public class ResearchQueryServlet extends HttpServlet {
    
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResearchQueryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResearchQueryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        String location =request.getParameter("search-location");
        String name = request.getParameter("search-name");
        String type = request.getParameter("search-type");
        System.out.println(location + " -- "+name);
        
        
        String query ="";
        //Questa cosa non esclude name se fossero inseriti sia name che address?
        //Query non worka sembra
        /*if (name != null){
            query = "SELECT * FROM RESTAURANTS WHERE NAME =  '%" +name +"%'";
        }
        if (location != null){
            query = "SELECT * FROM COORDINATES WHERE ADDRESS LIKE  '%" +location +"%'";
        }*/
        
        //Versione Denis GHEY
        /*if(name!=null){
            if(location!=null){
                //query = "SELECT * FROM RESTAURANTS WHERE NAME =  '%" +name +"%'" + "AND ADDRESS LIKE '%" +location +"%'";
                query="SELECT * FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID WHERE lower(NAME) LIKE  '%" +name.toLowerCase() +"%' AND lower(ADDRESS) LIKE '%" +location.toLowerCase() +"%'";
            }
            else
               query="SELECT * FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID WHERE lower(NAME) LIKE  '%" +name.toLowerCase() +"%'"; 
        }
        else
            query="SELECT * FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID WHERE lower(ADDRESS) LIKE '%" +location.toLowerCase() +"%'";
        */
        

        //versione forse completa (per n query)
        
        //query ricerca con tutto.
        //Ricerca per tipo nome e locazione
        if ((name != null)&&(location!=null)&&(type!= null)){    
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.NAME) LIKE  '%" +name.toLowerCase() +"%' "
                    + "AND lower(RESTAURANTS.ADDRESS) LIKE '%" +location.toLowerCase() +"%'"
                    + "AND lower(CUSINES.NAME) LIKE '%" + type.toLowerCase() + "%'";
        }
        //Ricerca per nome e locazione
        if ((name!= null)&&(location!=null)&&(type== null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.NAME) LIKE  '%" +name.toLowerCase() +"%' "
                    + "AND lower(RESTAURANTS.ADDRESS) LIKE '%" +location.toLowerCase() +"%'";
        }
        //Ricerca per nome e tipo
        if ((name!= null)&&(location==null)&&(type!= null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.NAME) LIKE  '%" +name.toLowerCase() +"%' "
                    + "AND lower(CUSINES.NAME) LIKE '%" + type.toLowerCase() + "%'";
        }
        //Ricerca per tipo e locazione
        if ((name== null)&&(location!=null)&&(type!= null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.ADDRESS) LIKE  '%" +location.toLowerCase() +"%' "
                    + "AND lower(CUSINES.NAME) LIKE '%" + type.toLowerCase() + "%'";
        }
        //solo tipo
        if ((name== null)&&(location==null)&&(type!= null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "AND lower(CUSINES.NAME) LIKE '%" + type.toLowerCase() + "%'";
        }
        //solo nome
        if ((name!= null)&&(location==null)&&(type== null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.NAME) LIKE  '%" +name.toLowerCase() +"%' ";
        }
        //solo locazione
        if ((name== null)&&(location!=null)&&(type== null)){
            query="SELECT * FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANT.ID"
                    + "INNER JOIN RESTAURANT_CUSINE ON RESTAURANT_CUSINE.ID_RESTAURANT = RESTAURANT.ID "
                    + "WHERE lower(RESTAURANT.ADDRESS) LIKE  '%" +location.toLowerCase() +"%' ";
        }
        
        
        System.out.println(query);
        
        try{
            
            Statement ps = (Statement) manager.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery(query);
            if (rs.first()){
                System.out.println("Name query returned data");
            }
            else{
                System.out.println("No data");
            }
            
            request.setAttribute("resultset", rs);

        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
