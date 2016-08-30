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
        

        String query="SELECT RESTAURANTS.NAME AS NAME, RESTAURANTS.DESCRIPTION AS DESCRIPTION, RESTAURANTS.WEB_SITE_URL AS WEBSITE, COORDINATES.ADDRESS AS ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE, RESTAURANTS.GLOBAL_VALUE AS VALUE, RESTAURANTS.ID_OWNER AS OWNER ";
        query+="FROM RESTAURANTS INNER JOIN RESTAURANT_COORDINATE ON RESTAURANTS.ID=RESTAURANT_COORDINATE.ID_RESTAURANT INNER JOIN COORDINATES ON RESTAURANT_COORDINATE.ID_COORDINATE=COORDINATES.ID ";
        query+="WHERE RESTAURANTS.ID="+id;
        ResultSet rs = null;
        Statement ps=null;
        try{
            ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
        RistoranteEBJ mioristorante=new RistoranteEBJ();
        RecensioniDAO receDAO=new RecensioniDAO();
        rs.first();
        mioristorante.setId(id);
        mioristorante.setName(rs.getString("NAME"));
        mioristorante.setDescription(rs.getString("DESCRIPTION"));
        getPhotos(mioristorante, connection);
        mioristorante.setCuisine(getCuisines(id, connection));
        mioristorante.setWebsite(rs.getString("WEBSITE"));
        mioristorante.setAddress(rs.getString("ADDRESS"));
        mioristorante.setPrice(Integer.parseInt(rs.getString("PRICE")));
        mioristorante.setGlobalvalue(Integer.parseInt(rs.getString("VALUE")));
        mioristorante.setIdOwner(rs.getInt("OWNER"));
        System.out.println("Entro;");
        mioristorante.setNrecensioni(receDAO.RecensioniDAO(id, connection).size());
        System.out.println("Esco");
        rs.close();
        ps.close();
        return mioristorante;
    }
    
    private void getPhotos(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT PATH FROM PHOTOS INNER JOIN RESTAURANTS ON PHOTOS.ID_RESTAURANT=RESTAURANTS.ID WHERE RESTAURANTS.ID="+mioristorante.getId();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        ArrayList<String> mioarray=new ArrayList();
        if (rs.isBeforeFirst()) {
            while(rs.next()){
                mioarray.add(rs.getString(1));
            }
        }
        mioristorante.setPath(( mioarray));  
        ps.close();
        rs.close();
    }
    public ArrayList<String> getCuisines(int id, Connection connection) throws SQLException{
        String query="SELECT NAME FROM CUISINES INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_CUISINE=CUISINES.ID WHERE RESTAURANT_CUISINE.ID_RESTAURANT="+id;
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        ArrayList<String> mioarray=new ArrayList();
        if (rs.isBeforeFirst() ) {    
            while(rs.next()){
                mioarray.add(rs.getString(1));
            }
        } 
        ps.close();
        rs.close();
        return mioarray;
    }
    
    public void setflag(String idrestourant,Integer Username,Connection connection)
    {
        String query="UPDATE RESTAURANTS SET Flag=1,ID_OWNER="+Username+" WHERE ID="+idrestourant;
        int nr=0;
        try{
            Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            nr = ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    public ArrayList<String> getClaimedRestaurants(Connection connection) throws SQLException
    {
        String query="SELECT RESTAURANTS.ID,RESTAURANTS.NAME,USERS.NICKNAME FROM RESTAURANTS,USERS WHERE RESTAURANTS.ID_OWNER=USERS.ID AND FLAG=1";
        ArrayList<String> mioarray=new ArrayList();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        if (rs.isBeforeFirst()) {
            while(rs.next()){
                mioarray.add(rs.getString(1));                
                mioarray.add(rs.getString(2));           
                mioarray.add(rs.getString(3));   
            }
        }        
        ps.close();
        rs.close();
        return mioarray;
    }
    public void Claim(String idrestourant,Connection connection)
    {
        String query="UPDATE RESTAURANTS SET Flag=0 WHERE ID="+idrestourant;
        int nr=0;
        try{
            Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            nr = ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public ArrayList<String> getNames(int idowner, Connection connection) throws SQLException{
        String query="Select name from Restaurants where id_owner="+idowner;
        ArrayList<String> arraynomi=new ArrayList<>();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        while(rs.next())
            arraynomi.add(rs.getString(1));
        ps.close();
        rs.close();
        return arraynomi;
    }
    
    public ArrayList<Integer> getIds(int idowner, Connection connection) throws SQLException{
        String query="Select id from Restaurants where id_owner="+idowner;
        ArrayList<Integer> arrayid=new ArrayList<>();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery(query);
        while(rs.next())
            arrayid.add(rs.getInt(1));
        ps.close();
        rs.close();
        return arrayid;
    }
    public void Claim(String idrestaurant,String nickname, int accept, Connection connection) throws SQLException
    {
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String query="UPDATE RESTAURANTS SET Flag=0 WHERE ID="+idrestaurant;
        ps.executeUpdate(query);
        String query2="";
        if(accept==1)
        {            
            query2="UPDATE USERS SET TYPE=2 WHERE NICKNAME='"+nickname+"'";
            ps.executeUpdate(query2);
        }
        else{
            query2="UPDATE RESTAURANTS SET ID_OWNER=NULL WHERE ID="+idrestaurant;
            ps.executeUpdate(query2);
        }
        ps.close();
    }
}
