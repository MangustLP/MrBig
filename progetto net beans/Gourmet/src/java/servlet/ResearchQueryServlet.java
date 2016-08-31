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
//@WebServlet(name = "ResearchQueryServlet", urlPatterns = {"/ResearchQueryServlet"})
public class ResearchQueryServlet extends HttpServlet {
    
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
        

        
        String location =request.getParameter("search-location");
        String name = request.getParameter("search-name");
        String order= request.getParameter("order");
        String radioS = request.getParameter("radio");
        String Bselected[]=request.getParameterValues("tipologia-cucina");
        
                                

        
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
        query="SELECT COUNT(REID) AS COUNT, RID, RNAME, GLOBALVALUE, ADDRESS, PRICE FROM ";
        query+="(SELECT DISTINCT REVIEWS.ID AS REID, RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON, COORDINATES.ADDRESS as ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE FROM RESTAURANTS ";
        query+="INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT ";
        query+="INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID LEFT JOIN REVIEWS ON RESTAURANTS.ID=REVIEWS.ID_RESTAURANT ";
        if ((name!= null)&&(location!=null)){
            /*query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON, COORDINATES.ADDRESS as ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE FROM RESTAURANTS  "//CUISINES.NAME as CNAME
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "*/
                    //non ci sono ancora photo+ "INNER JOIN PHOTO ON PHOTO.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN CUISINES ON RESTAURANT_CUISINE.ID_CUISINE = CUISINES.ID";
                    query+= " WHERE lower(RESTAURANTS.NAME) LIKE  '%" +name.toLowerCase() +"%' "
                    + "AND lower(COORDINATES.ADDRESS) LIKE '%" +location.toLowerCase() +"%'";
        }
        //solo nome
        if ((name!= null)&&(location==null)){
            /*query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON, COORDINATES.ADDRESS as ADDRESS RESTAURANTS.ID_PRICE_RANGE AS PRICE FROM RESTAURANTS"// CUISINES.NAME as CNAME 
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo//+ "INNER JOIN PHOTOS ON PHOTOS.ID_RESTAURANT = RESTAURANTS.ID" */
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN CUISINES ON RESTAURANT_CUISINE.ID_CUISINE = CUISINES.ID";
                   query+= " WHERE lower(RESTAURANTS.NAME) LIKE  '%" +name.toLowerCase() +"%' ";
        }
        //solo locazione
        if ((name== null)&&(location!=null)){
            /*query="SELECT DISTINCT RESTAURANTS.ID AS RID, RESTAURANTS.NAME AS RNAME, RESTAURANTS.GLOBAL_VALUE AS GLOBALVALUE, COORDINATES.LATITUDE AS LAT, COORDINATES.LONGITUDE AS LON, COORDINATES.ADDRESS as ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE FROM RESTAURANTS "//CUISINES.NAME as CNAME
                    + "INNER JOIN RESTAURANT_COORDINATE on RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT "
                    + "INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID "
                    //non ci sono ancora photo//+ "INNER JOIN PHOTOS ON PHOTOS.ID_RESTAURANT = RESTAURANTS.ID"*/
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID "
                    //+ "INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_RESTAURANT = RESTAURANTS.ID"
                    //+ "INNER JOIN CUISINES ON RESTAURANT_CUISINE.ID_CUISINE = CUISINES.ID";
                    query+= " WHERE lower(RESTAURANT.ADDRESS) LIKE  '%" +location.toLowerCase() +"%' ";
        }
        query+=") AS T1 GROUP BY RID, RNAME, GLOBALVALUE, ADDRESS, PRICE ";
        
        if(order!=null){
            if(order.equals("Rank"))
                query=query+" ORDER BY GLOBALVALUE DESC, COUNT DESC";
            if(order.equals("Price"))
                query=query+" ORDER BY PRICE";
            if(order.equals("Alphabetic"))
                query=query+" ORDER BY RNAME";
        }
        else
            query=query+" ORDER BY GLOBALVALUE DESC, COUNT DESC";
        
        try{
            System.err.println("stofacendoqualcs");
            Statement ps = (Statement) manager.getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery(query);
            ArrayList<RistoranteEBJ> rsdata = new ArrayList<RistoranteEBJ>();
            if (rs.first()){
                while (!rs.isAfterLast()){
                //valori da assegnare al bean
                
                    
                    int setid = rs.getInt("RID");
                    String setname = rs.getString("RNAME");
                    int  sgv = rs.getInt("GLOBALVALUE");
                    String address = rs.getString("ADDRESS");
                    int price = rs.getInt("PRICE");
                    //String cusinet = rs.getString("CNAME");
                    
                    if(radioS == null){
                        RistoranteEBJ temp = new RistoranteEBJ();
                        temp.setId(setid);
                        temp.setName(setname);
                        temp.setGlobalvalue(sgv);
                        temp.setAddress(address);
                        temp.setPrice(price);
                        //temp.setImage_path(rs.getString("PATH"));
                        rsdata.add(temp);
                    }
                    else if((radioS.equals("low-price"))&&(price == 1)){//&&(ceckTypocusine(cusinet,Bselected))){
                        RistoranteEBJ temp = new RistoranteEBJ();
                        temp.setId(setid);
                        temp.setName(setname);
                        temp.setGlobalvalue(sgv);
                        temp.setAddress(address);
                        temp.setPrice(price);
                        //temp.setImage_path(rs.getString("PATH"));
                        rsdata.add(temp);
                    }
                    else if((radioS.equals("medium-price"))&&(price == 2)){//&&(ceckTypocusine(cusinet,Bselected))){
                        RistoranteEBJ temp = new RistoranteEBJ();
                        temp.setId(setid);
                        temp.setName(setname);
                        temp.setGlobalvalue(sgv);
                        temp.setAddress(address);
                        temp.setPrice(price);
                        //temp.setImage_path(rs.getString("PATH"));
                        rsdata.add(temp);
                    }
                    else if((radioS.equals("high-price"))&&(price == 3)){//&&(ceckTypocusine(cusinet,Bselected))){
                        RistoranteEBJ temp = new RistoranteEBJ();
                        temp.setId(setid);
                        temp.setName(setname);
                        temp.setGlobalvalue(sgv);
                        temp.setAddress(address);
                        temp.setPrice(price);
                        //temp.setImage_path(rs.getString("PATH"));
                        rsdata.add(temp);
                    }
                    else if(radioS.equals("every-price")){//&&(ceckTypocusine(cusinet,Bselected))){
                        RistoranteEBJ temp = new RistoranteEBJ();
                        temp.setId(setid);
                        temp.setName(setname);
                        temp.setGlobalvalue(sgv);
                        temp.setAddress(address);
                        temp.setPrice(price);
                        //temp.setImage_path(rs.getString("PATH"));
                        rsdata.add(temp);
                    }
                    
  
                    
                    rs.next();
                }
            }
            else{
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
    
    public boolean ceckTypocusine(String param,String Bselected[]){
        
        try
        {
            

            for(int i=0;i<Bselected.length;i++)
            {
                if(Bselected[i].equals(param))
                {
                    return true;
                }
                if(Bselected[i].equals(param))
                {
                    return true;
                }
                if(Bselected[i].equals(param))
                {
                    return true;
                }
                if(Bselected[i].equals(param))
                {
                    return true;
                }
                if(Bselected[i].equals(param))
                {
                    return true;
                }
                if(Bselected[i].equals(param))
                {
                    return true;
                    
                }
            }
        }
        catch(Exception  e)
        {
            return true;
        } 
        return false;
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
