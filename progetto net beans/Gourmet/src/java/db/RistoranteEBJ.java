package db;

import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lorenzo
 */
//Beans Ristorante NOME[Name] INDIRIZZO[Address] DESCRIZIONE[Description] 
//                 URL_SITO_RISTORANTE[Web_url] PATH_IMMAGINE_RISTORANTE[Image_path]
//                 TIPO COUSINE[Cousine_type]

public class RistoranteEBJ {
    
    int Id;
    String Name;
    ArrayList<String> Photos=new ArrayList<>();
    String Description;
    int Globalvalue;
    int Nrecensioni;
    int Price;
    ArrayList<String> Cuisine;
    String Address;
    String Website;
    int IdOwner;
    
    
    public void setId(int param){
        this.Id=param;
    }
    
    public int getId(){
        return this.Id;
    }
      
    public String getName(){
        return Name;
    }
    
    public void setName(String param)
    {
        this.Name = param;
    }
    
    public String getWebsite(){
        return Website;
    }
    
    public void setWebsite(String param)
    {
        this.Website = param;
    }
    
    public String getDescription(){
        return Description;
    }
    
    public void setDescription(String param)
    {
        this.Description = param;
    }
    
    public ArrayList<String> getPhotos(){
        return Photos;
    }
    
    public void setPhotos(ArrayList<String> param)
    {
        this.Photos = param;
    }
    
    
    
      
    public int getGlobalvalue(){
        return Globalvalue;
    }
    
    public void setGlobalvalue(int param)
    {
        this.Globalvalue = param;
    }
    
        public int getIdOwner(){
        return IdOwner;
    }
    
    public void setIdOwner(int param)
    {
        this.IdOwner = param;
    }
    
   
    public int getPrice(){
        return Price;
    }
    
    public void setPrice(int param)
    {
        this.Price= param;
    }

   
    
     public int getNrecensioni(){
        return Nrecensioni;
    }
    
    public void setNrecensioni(int param)
    {
        this.Nrecensioni = param;
    }

   
    
      
    public ArrayList<String> getCuisine(){
        return Cuisine;
    }
    
    public void setCuisine(ArrayList<String> param)
    {
        this.Cuisine = param;
    }
    
    
    
    
    public String getAddress(){
        return Address;
    }
    
    public void setAddress(String param)
    {
        this.Address = param;
    }


    
}
