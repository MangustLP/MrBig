package db;
import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String name;
    private String type;
    private String email;
    private int id;
    private String surname;
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

     public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
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