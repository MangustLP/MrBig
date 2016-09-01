/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Dega
 */
public class RecensioniEBJ {
    int Id;
    int Value;
    int Food;
    int Service;
    int Pricevalue;
    int Atmosphere;
    String Name;
    String Description;
    String Timestamp;
    String User;
    
    public void setId(int param){
        this.Id=param;
    }
    
    public int getId(){
        return this.Id;
    }
    public void setValue(int param){
        this.Value=param;
    }
    
    public int getValue(){
        return this.Value;
    }
    public void setFood(int param){
        this.Food=param;
    }
    
    public int getFood(){
        return this.Food;
    }
    public void setService(int param){
        this.Service=param;
    }
    
    public int getService(){
        return this.Service;
    }
        public void setPricevalue(int param){
        this.Pricevalue=param;
    }
    
    public int getPricevalue(){
        return this.Pricevalue;
    }
    public void setAtmosphere(int param){
        this.Atmosphere=param;
    }
    public int getAtmosphere(){
        return this.Atmosphere;
    }
    
    public String getName(){
        return this.Name;
    }
    public void setName(String param){
        this.Name=param;
    }
    
    public String getDescription(){
        return this.Description;
    }
    public void setDescription(String param){
        this.Description=param;
    }
    
    public String getTimestamp(){
        return this.Timestamp;
    }
    public void setTimestamp(String param){
        this.Timestamp=param;
    }
    
    public String getUser(){
        return this.User;
    }
    public void setUser(String param){
        this.User=param;
    }
    
    
}
