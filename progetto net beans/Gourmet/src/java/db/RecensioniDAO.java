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
 * @author Dega
 */
public class RecensioniDAO {
    private DBManager dbmanger;
    
    public ArrayList<RecensioniEBJ> RecensioniDAO(int id, Connection con) throws SQLException{
        String query="SELECT * FROM REVIEWS WHERE ID_RESTAURANT="+id;
        ResultSet rs = null;
        try{
            Statement ps = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
        ArrayList<RecensioniEBJ> mioarray=new ArrayList<>();
        RecensioniEBJ miarecensione;
        while(rs.next()){
            miarecensione=new RecensioniEBJ();
            miarecensione.setId(rs.getInt("id"));
            miarecensione.setValue(rs.getInt("global_value"));
            miarecensione.setFood(rs.getInt("food"));
            miarecensione.setService(rs.getInt("service"));
            miarecensione.setPricevalue(rs.getInt("value_for_money"));
            miarecensione.setAtmosphere(rs.getInt("atmosphere"));
            miarecensione.setName(rs.getString("name"));
            miarecensione.setDescription(rs.getString("description"));
            miarecensione.setTimestamp(rs.getTimestamp("date_creation").toString().split(" ")[0]);
            miarecensione.setUser(getUser(rs.getString("id_creator"), con));
            mioarray.add(miarecensione);
        }
        return mioarray;
    }
    
    private String getUser(String id, Connection con) throws SQLException{
        String user="";
        String query="SELECT nickname FROM USERS WHERE ID="+id;
        ResultSet rs = null;
         Statement ps = null;
        try{
            ps = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs.next())
            user=rs.getString(1);
        rs.close();
        ps.close();
        return user;
    }
    
}
