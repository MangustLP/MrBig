package db;
import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String name;
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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