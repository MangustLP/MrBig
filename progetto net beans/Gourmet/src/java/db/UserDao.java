package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.ResearchQueryServlet;
public class UserDao {
    private DBManager dbmanger;
    public User UserDao(String id, Connection con) throws SQLException{
    
        String query="SELECT * FROM USERS WHERE ID="+id;
        ResultSet rs = null;
        Statement ps=null;
        try{
            ps = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(ResearchQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        User utente=new User();
        while(rs.next())
        {
        utente.setName(rs.getString("NAME"));
        utente.setSurname(rs.getString("SURNAME"));
        utente.setUsername(rs.getString("NICKNAME"));
        utente.setEmail(rs.getString("EMAIL"));
        }
        ps.close();
        rs.close();
        return utente;
        
    }
}