/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
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
        ResultSet rs;
        Statement ps;
        try{
            ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
            RistoranteEBJ mioristorante=new RistoranteEBJ();
            RecensioniDAO receDAO=new RecensioniDAO();
            rs.first();
            mioristorante.setId(id);
            mioristorante.setName(rs.getString("NAME"));
            mioristorante.setDescription(rs.getString("DESCRIPTION"));
            getPhotos(mioristorante, connection);
            getNotFlaggedPhotos(mioristorante, connection);
            mioristorante.setCuisine(getCuisines(id, connection));
            mioristorante.setWebsite(rs.getString("WEBSITE"));
            mioristorante.setAddress(rs.getString("ADDRESS"));
            mioristorante.setPrice(Integer.parseInt(rs.getString("PRICE")));
            mioristorante.setGlobalvalue(Integer.parseInt(rs.getString("VALUE")));
            mioristorante.setIdOwner(rs.getInt("OWNER"));
            mioristorante.setNrecensioni(receDAO.RecensioniDAO(id, connection).size());
            rs.close();
            ps.close();
            return mioristorante;
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    private void getPhotos(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT NAME FROM PHOTOS WHERE ID_RESTAURANT="+mioristorante.getId();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            ArrayList<String> mioarray=new ArrayList<>();
            while(rs.next()){
                mioarray.add(rs.getString(1));
            }
            mioristorante.setPhotos(( mioarray));
        }
        rs.close();
    }
     private void getNotFlaggedPhotos(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT NAME FROM PHOTOS WHERE FLAG=0 AND ID_RESTAURANT="+mioristorante.getId();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            ArrayList<String> mioarray=new ArrayList<>();
            while(rs.next()){
                mioarray.add(rs.getString(1));
            }
            mioristorante.setNotFlaggedPhotos(( mioarray));
        }
        rs.close();
    }
    
    public ArrayList<String> getCuisines(int id, Connection connection) throws SQLException{
        String query="SELECT NAME FROM CUISINES INNER JOIN RESTAURANT_CUISINE ON RESTAURANT_CUISINE.ID_CUISINE=CUISINES.ID WHERE RESTAURANT_CUISINE.ID_RESTAURANT="+id;
        ResultSet rs;
        ArrayList<String> mioarray;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            mioarray = new ArrayList<>();
            if (rs.isBeforeFirst() ) {
                while(rs.next()){
                    mioarray.add(rs.getString(1));
                }
            }
        }
        rs.close();
        return mioarray;
    }
    
    public void setflag(String idrestourant,Integer Username,Connection connection)
    {
        String query="UPDATE RESTAURANTS SET Flag=1,ID_OWNER="+Username+" WHERE ID="+idrestourant;
        int nr=0;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            nr = ps.executeUpdate(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    public ArrayList<String> getClaimedRestaurants(Connection connection) throws SQLException
    {
        String query="SELECT RESTAURANTS.ID,RESTAURANTS.NAME,USERS.NICKNAME FROM RESTAURANTS,USERS WHERE RESTAURANTS.ID_OWNER=USERS.ID AND FLAG=1";
        ArrayList<String> mioarray=new ArrayList<>();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            if (rs.isBeforeFirst()) {
                while(rs.next()){
                    mioarray.add(rs.getString(1));
                    mioarray.add(rs.getString(2));   
                    mioarray.add(rs.getString(3));
                }
            }
        }
        rs.close();
        return mioarray;
    }
    public void Claim(String idrestourant,Connection connection)
    {
        String query="UPDATE RESTAURANTS SET Flag=0 WHERE ID="+idrestourant;
        int nr=0;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            nr = ps.executeUpdate(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public ArrayList<String> getNames(int idowner, Connection connection) throws SQLException{
        String query="Select name from Restaurants where id_owner="+idowner;
        ArrayList<String> arraynomi=new ArrayList<>();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            while(rs.next())
                arraynomi.add(rs.getString(1));
        }
        rs.close();
        return arraynomi;
    }
    
    public ArrayList<Integer> getIds(int idowner, Connection connection) throws SQLException{
        String query="Select id from Restaurants where id_owner="+idowner;
        ArrayList<Integer> arrayid=new ArrayList<>();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            while(rs.next())
                arrayid.add(rs.getInt(1));
        }
        rs.close();
        return arrayid;
    }
    public void Claim(String idrestaurant,String nickname, int accept, Connection connection) throws SQLException
    {
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            String query="UPDATE RESTAURANTS SET Flag=0 WHERE ID="+idrestaurant;
            ps.executeUpdate(query);
            String query2;
            if(accept==1)
            {
                query2="UPDATE USERS SET TYPE=2 WHERE NICKNAME='"+nickname+"'";
                ps.executeUpdate(query2);
            }
            else{
                query2="UPDATE RESTAURANTS SET ID_OWNER=NULL WHERE ID="+idrestaurant;
                ps.executeUpdate(query2);
            }
        }
    }
    public void updateGlobalValue(int idRestaurant) throws SQLException{
        try (Statement ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs;
            String query="SELECT GLOBAL_VALUE FROM REVIEWS WHERE ID_RESTAURANT="+idRestaurant;
            rs=ps.executeQuery(query);
            double avg=0;
            int count=0;
            while(rs.next()){
                avg+=rs.getInt(1);
                count++;
            }
            avg/=count;
            query="UPDATE RESTAURANTS SET GLOBAL_VALUE="+Math.round(avg)+" WHERE ID="+idRestaurant;
            System.out.println(query);
            ps.executeUpdate(query);
            rs.close();
        }
                                       
    }
    
    public void FlagImages(String name, Connection connection) throws SQLException
    { 
        try (
            Statement ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            String query="UPDATE PHOTOS SET FLAG=1 WHERE NAME=\'"+name+"\'";
            ps.executeUpdate(query);
            ps.close();
        }
    }
    public void PrymaryImage(String name,String Id, Connection connection) throws SQLException
    {   
        int idphoto=0;
        try ( Statement ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) 
        {
            ResultSet rs;
            String query="SELECT ID FROM PHOTOS WHERE NAME=\'"+name+"\'";
            rs=ps.executeQuery(query);
            while(rs.next())
            {
                idphoto=rs.getInt("ID");
            }
            ps.close();
            rs.close();
        }  
        
        
        try ( Statement ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) 
        {
            String query="UPDATE RESTAURANTS SET PRIMARYPHOTO="+idphoto+" WHERE ID="+Id;
            ps.executeUpdate(query);
            ps.close();
        }  
    }
   /* public ArrayList<String> getPhotos(int idR) throws SQLException{
        ArrayList<String> photos=new ArrayList<>();
        /*Statement ps = (Statement) DriverManager.getConnection("jdbc:derby://localhost:1527/GourmetDB","gourmetadmin","gourmetpassword").createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs;
        String query="SELECT NAME from PHOTOS WHERE ID_RESTAURANT="+idR;
        rs=ps.executeQuery(query);
        while(rs.next())
            photos.add(rs.getString(1));//
        return photos;
        
    }*/
}
