package db;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBManager implements Serializable {
    // transient == non viene serializzato

    private final transient Connection con;
    
    public DBManager(String dburl) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver", true, getClass().getClassLoader());
        } catch(Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        Connection conect = DriverManager.getConnection(dburl, "gourmetadmin", "gourmetpassword");
        this.con = conect;
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
        String query="Select NICKNAME, NAME, TYPE,ID,SURNAME from USERS where NICKNAME='"+username+"' and PASSWORD='"+password+"'";
        PreparedStatement stm=con.prepareStatement(query);
        try{
            ResultSet results=stm.executeQuery();
            if(results.next()){
                User user=new User();
                user.setUsername(results.getString(1));
                user.setName(results.getString(2));
                user.setType(results.getString(3));
                user.setID(results.getInt(4));
                user.setSurname(results.getString(5));
                return user;
            }
            
            return null;
        }
        finally{
            stm.close();
        }
    }
}