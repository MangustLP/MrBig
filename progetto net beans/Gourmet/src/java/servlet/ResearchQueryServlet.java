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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.RistoranteEBJ;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        
        String location =request.getParameter("search-location");
        String name = request.getParameter("search-name");
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
        

        //versione forse completa 
        
        //query ricerca con tutto.
        //Ricerca per tipo nome e locazione
        //Ricerca per nome e locazione
        // NUOVA QUERY SENZA WHERE SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID 
        if ((name!= null)&&(location!=null)){
            query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    + "WHERE lower(RESTAURANTS.NAME) LIKE  '%" +name.toLowerCase() +"%' "
                    + "AND lower(COORDINATES.ADDRESS) LIKE '%" +location.toLowerCase() +"%'";
        }
        //solo nome
        if ((name!= null)&&(location==null)){
            query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo*/+ "INNER JOIN PHOTOS ON PHOTOS.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    + "WHERE lower(RESTAURANTS.NAME) LIKE  '%" +name.toLowerCase() +"%' ";
        }
        //solo locazione
        if ((name== null)&&(location!=null)){
            query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON FROM RESTAURANTS "
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo*/+ "INNER JOIN PHOTOS ON PHOTOS.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    + "WHERE lower(RESTAURANT.ADDRESS) LIKE  '%" +location.toLowerCase() +"%' ";
        }
        
        
        System.out.println(query);
        
        try{
            
            Statement ps = (Statement) manager.getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery(query);
            if (rs.first()){
                System.out.println("Name query returned data");
            }
            else{
                System.out.println("No data");
            }
            
            
            
            ArrayList<RistoranteEBJ> rsdata = new ArrayList<RistoranteEBJ>();
            
            while (rs.isBeforeFirst()){
                //valori da assegnare al bean
                
                RistoranteEBJ temp = new RistoranteEBJ();
                temp.setId(rs.getInt("RID"));
                temp.setName(rs.getString("RNAME"));
                temp.setGlobalvalue(rs.getInt("GLOBALVALUE"));
                temp.setLatitude(rs.getDouble("LAT"));
                temp.setLongitude(rs.getDouble("LON"));
                //temp.setImage_path(rs.getString("PATH"));
                rsdata.add(temp);
                
            }
            RequestDispatcher rd = request.getRequestDispatcher("/research.jsp");
            request.setAttribute("resultset", rsdata);
            rd.forward(request, response);
            
            /*HttpSession session = request.getSession(true);
            session.setAttribute("resultset", rsdata);
            response.sendRedirect(request.getContextPath() + "/research.jsp");*/
            

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
