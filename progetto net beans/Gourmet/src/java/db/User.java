package db;
import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String fullname;
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public void setType(String Type)
    {
        type=Type;
    }
    
    public String getType()
    {
        return type;
    }
}