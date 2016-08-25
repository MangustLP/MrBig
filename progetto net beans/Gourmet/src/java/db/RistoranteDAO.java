/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.ResearchQueryServlet;

/**
 *
 * @author happy
 */
public class RistoranteDAO {
    private DBManager manager;
    
    public RistoranteEBJ RistoranteDAO(int id,Connection connection ) throws SQLException{
        
        
        System.out.println(id);
        String query="SELECT RESTAURANTS.NAME AS NAME, RESTAURANTS.DESCRIPTION AS DESCRIPTION, RESTAURANTS.WEB_SITE_URL AS WEBSITE, COORDINATES.ADDRESS AS ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE, RESTAURANTS.GLOBAL_VALUE AS VALUE ";
        query+="FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE ON RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID ";
        query+="WHERE RESTAURANTS.ID="+id;
        System.out.println(query);
        ResultSet rs = null;
        try{
            Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
        RistoranteEBJ mioristorante=new RistoranteEBJ();
        rs.first();
        mioristorante.setId(id);
        mioristorante.setName(rs.getString("NAME"));
        mioristorante.setDescription(rs.getString("DESCRIPTION"));
        getPhotos(mioristorante, connection);
        getCuisines(mioristorante, connection);
        mioristorante.setWebsite(rs.getString("WEBSITE"));
        mioristorante.setAddress(rs.getString("ADDRESS"));
        mioristorante.setPrice(Integer.parseInt(rs.getString("PRICE")));
        mioristorante.setGlobalvalue(Integer.parseInt(rs.getString("VALUE")));
        return mioristorante;
    }
    
    private void getPhotos(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT PATH FROM PHOTOS INNER JOIN RESTAURANTS ON PHOTOS.ID_RESTAURANT=RESTAURANTS.ID WHERE RESTAURANTS.ID="+mioristorante.getId();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        ArrayList<String> mioarray=new ArrayList();
        rs.first();
        while(!rs.next()){
            mioarray.add(rs.getString("PATH"));
            rs.next();
        }
        mioristorante.setPath(( mioarray));     
    }
    private void getCuisines(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT NAME FROM CUISINES INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_CUISINE=CUISINES.ID WHERE RESTAURANT_CUISINE.ID_RESTAURANT="+mioristorante.getId();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        ArrayList<String> mioarray=new ArrayList();
        rs.first();
        while(!rs.next()){
            mioarray.add(rs.getString("NAME"));
            rs.next();
        }
        mioristorante.setCuisine(mioarray);     
    }    
}
