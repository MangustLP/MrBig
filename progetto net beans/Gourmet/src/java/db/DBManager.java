package db;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DBManager implements Serializable {
    // transient == non viene serializzato

    private transient Connection con;
    
    public DBManager(String dburl) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver", true, getClass().getClassLoader());
        } catch(Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        Connection con = DriverManager.getConnection(dburl, "gourmetadmin", "gourmetpassword");
        this.con = con;
    }

    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
    
    public Connection getCon (){ 
        return this.con;
    }
    
    public User login(String username, String password) throws SQLException{
        PreparedStatement stm=con.prepareStatement("Select NICKNAME, NAME, TYPE from USERS where NICKNAME=? and PASSWORD=?");
        try{
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet results=stm.executeQuery();

            if(results.next()){
                User user=new User();
               /* user.setUsername(results.getString(1));
                user.setName(results.getString(2));
                user.setType(results.getString(3));
                return user;*/
            }
            
            return null;
        }
        finally{
            stm.close();
        }
    }
}