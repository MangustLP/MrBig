
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
public class RistoranteEBJ {
    @Id
    String Name;
      
    public String getName(){
        return Name;
    }
    
    public void setName(String param)
    {
        this.Name = param;
    }
    
    @Id
    String Address;
      
    public String getAddress(){
        return Address;
    }
    
    public void setAddress(String param)
    {
        this.Address = param;
    }
    
    @Id
    String Description;
      
    public String getDescription(){
        return Description;
    }
    
    public void setDescription(String param)
    {
        this.Description = param;
    }

    @Id
    String Web_url;
      
    public String getWeb_url(){
        return Web_url;
    }
    
    public void setWeb_url(String param)
    {
        this.Web_url = param;
    }

    @Id
    String Image_path;
      
    public String getImage_path(){
        return Image_path;
    }
    
    public void setImage_path(String param)
    {
        this.Image_path = param;
    }
    
    @Id
    String Cousine_type;
    
    public String getCousine_type(){
        return Cousine_type;
    }
    
    public void setCousine_type(String param)
    {
        this.Cousine_type = param;
    }

    
}
