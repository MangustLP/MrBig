package db;



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
    String[] Path;
    String Description;
    int Globalvalue;
    int Nrecensioni;
    int Price;
    String[] Cuisine;
    String Address;
    String Website;
    
    
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
    
    public String[] getPath(){
        return Path;
    }
    
    public void setPath(String[] param)
    {
        this.Path = param;
    }
    
    
    
      
    public int getGlobalvalue(){
        return Globalvalue;
    }
    
    public void setGlobalvalue(int param)
    {
        this.Globalvalue = param;
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

   
    
      
    public String[] getCuisine(){
        return Cuisine;
    }
    
    public void setCuisine(String[] param)
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
