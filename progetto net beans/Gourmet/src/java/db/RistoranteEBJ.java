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
    String Path;
    int Globalvalue;
    int Classifica;
    int Nrecensioni;
    String[] Cuisine;
    double Latitude;
    double Longitude;
    
    
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
    
        public String getPath(){
        return Path;
    }
    
    public void setPath(String param)
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
    
    
   
    public int getClassifca(){
        return Classifica;
    }
    
    public void setClassifica(int param)
    {
        this.Classifica= param;
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
    
    
    
    
    public double getLatitude(){
        return Latitude;
    }
    
    public void setLatitude(double param)
    {
        this.Latitude = param;
    }
    
    public double getLongitude(){
        return Longitude;
    }
    
    public void setLongitude(double param)
    {
        this.Longitude = param;
    }

    
}
