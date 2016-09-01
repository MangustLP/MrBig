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
        

        String query="SELECT RESTAURANTS.NAME AS NAME, RESTAURANTS.DESCRIPTION AS DESCRIPTION, RESTAURANTS.WEB_SITE_URL AS WEBSITE, COORDINATES.ADDRESS AS ADDRESS, RESTAURANTS.ID_PRICE_RANGE AS PRICE, RESTAURANTS.GLOBAL_VALUE AS VALUE, RESTAURANTS.ID_OWNER AS OWNER,RESTAURANTS.PRIMARYPHOTO AS PRIMARYPHOTO ";
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
            mioristorante.setPrymary(rs.getInt("PRIMARYPHOTO"));
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
            ArrayList<String> mioarray=new ArrayList<String>();
            while(rs.next()){
                mioarray.add(rs.getString(1));
            }
            mioristorante.setPhotos(( mioarray));
        }
        rs.close();
    }
    public void getNotFlaggedPhotos(RistoranteEBJ mioristorante, Connection connection) throws SQLException{
        String query="SELECT NAME FROM PHOTOS WHERE FLAG=0 AND ID_RESTAURANT="+mioristorante.getId();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            ArrayList<String> mioarray=new ArrayList<String>();
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
            mioarray = new ArrayList<String>();
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
    
    public void setflagPhoto(String namep,Connection connection)
    {
        String query="UPDATE PHOTOS SET Flag=0 "
                + "WHERE NAME=\'"+namep+"\'";
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
        ArrayList<String> mioarray=new ArrayList<String>();
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
    public ArrayList<String> getNames(String idowner, Connection connection) throws SQLException{
        String query="Select name from Restaurants where id_owner="+idowner;
        ArrayList<String> arraynomi=new ArrayList<String>();
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            while(rs.next())
                arraynomi.add(rs.getString("name"));
        }
        rs.close();
        return arraynomi;
    }
    
    public String getName(String idR, Connection connection) throws SQLException{
        String query="Select name from Restaurants where id="+idR;
        String temp ="";
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            rs.next();
            temp = rs.getString("name");
            rs.close();
        return temp;
        }
    }
    
    public ArrayList<Integer> getIds(int idowner, Connection connection) throws SQLException{
        String query="Select id from Restaurants where id_owner="+idowner;
        ArrayList<Integer> arrayid=new ArrayList<Integer>();
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
    
    public ArrayList<String> getPhotosContested(Connection connection) throws SQLException{
        ArrayList<String> photos=new ArrayList<String>();
        Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        Statement ps2 = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs;
        ResultSet rs2;
        String query="SELECT PHOTOS.NAME,PHOTOS.ID_RESTAURANT,USERS.NAME,USERS.SURNAME,PHOTOS.ID_OWNER "
                + "FROM PHOTOS "
                + "INNER JOIN USERS ON PHOTOS.ID_OWNER = USERS.ID "
                + "WHERE PHOTOS.FLAG=1";
        
        rs=ps.executeQuery(query);
        while(rs.next()){
            
            String namep= rs.getString(1);
            String idr= rs.getString(2);
            String nameprop= rs.getString(3);
            String surnameprop= rs.getString(4);
            String idow = rs.getString(5);
            photos.add(namep);//name photo
            photos.add(idr);//id restourant photo
            int idr2=Integer.parseInt(idr);
            String query2= "SELECT USERS.NAME,USERS.SURNAME "
                + "FROM RESTAURANTS "
                + "INNER JOIN USERS ON USERS.ID = RESTAURANTS.ID_OWNER "
                + "WHERE RESTAURANTS.ID =  "+idr2+" ";
                
            rs2 = ps2.executeQuery(query2);
            photos.add(nameprop);//user name p
            photos.add(surnameprop);//user surname p
            photos.add(idow);
            rs2.next();
            photos.add(rs2.getString(1)); //user name s
            photos.add(rs2.getString(2)); //user surname s
        }
        System.out.println(photos.size());
        return photos;
        
    }
    
    public void removePhoto(String name, Connection connection) throws SQLException{
        /*String query="DELETE FROM PHOTOS WHERE NAME = \'"+name+"\'";
        int nr=0;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            nr = ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  */ //non possibile causa mancato on delete cascade alla creazione del db
        String query="UPDATE PHOTOS SET ID_RESTAURANT=97, FLAG=0 WHERE NAME= \'"+name +"\'";
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void FlagImages(String name,Connection connection)
    {
        String query="UPDATE PHOTOS SET FLAG=1 WHERE NAME=\'"+name+"\'";
        int nr=0;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            nr = ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public int PrymaryImage(String Name,String idR,Connection connection)
    {
        int r=0;
        int IdF=0;
        String query1="SELECT ID FROM PHOTOS WHERE NAME=\'"+Name+"\'";
        ResultSet rs1;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs1 = ps.executeQuery(query1);
            while(rs1.next())
            {
                IdF=rs1.getInt("ID");                
            }
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
        String query2="UPDATE RESTAURANTS SET PRIMARYPHOTO="+IdF+"WHERE ID="+idR;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            ps.executeUpdate(query2);
            ps.close();
            r=1;
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return IdF;
    }
    
    public void UpdateRestaurant(String id, String name, String Description,String web,Connection connection )
    {
        String query="UPDATE RESTAURANTS SET NAME=\'"+name+"\', DESCRIPTION=\'"+Description+"\',WEB_SITE_URL=\'"+web+"\' WHERE ID="+id;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            ps.executeUpdate(query);
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public boolean IsPrimary(String id,String name,Connection connection)
    {
        String query="SELECT ID FROM PHOTOS WHERE NAME=\'"+name+"\'";
        int idI=0;
        ResultSet rs1;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs1 = ps.executeQuery(query);
            while(rs1.next())
            {
                idI=rs1.getInt("ID");                
            }
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(id.equals(idI+""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
public ArrayList<String> getCoordinates(int idR,Connection connection) throws SQLException{
        ArrayList<String> temp = new ArrayList<String>(); 
        String query = "SELECT LATITUDE,LONGITUDE FROM COORDINATES "
                + "INNER JOIN RESTAURANT_COORDINATE ON RESTAURANT_COORDINATE.ID_COORDINATE = COORDINATES.ID "
                + "WHERE  ID_RESTAURANT = " +idR;
        ResultSet rs;
        try (Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            rs.next();
            temp.add(rs.getString("LATITUDE"));
            temp.add(rs.getString("LONGITUDE"));
        }
        
        return temp;
        
    }

    public int GetFlag(String idR, Connection connection){
        String query="SELECT flag FROM RESTAURANTS WHERE ID="+idR;
        ResultSet rs;
        int mioreturn=0;
        try(Statement ps = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
            rs = ps.executeQuery(query);
            while(rs.next())
            {
                mioreturn=rs.getInt(1);                
            }
             ps.close();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mioreturn;
           
    }

    
}

    
