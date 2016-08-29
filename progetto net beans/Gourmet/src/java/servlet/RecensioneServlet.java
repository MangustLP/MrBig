/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author lorenzo
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
@WebServlet(name = "RecensioneServlet", urlPatterns = {"/RecensioneServlet"})
public class RecensioneServlet extends HttpServlet{
    
    private DBManager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
            
        String recensione = request.getParameter("description");
        String valueR = request.getParameter("radio");
        String foodR = request.getParameter("radiof");
        String serviceR = request.getParameter("radios");
        String priceR = request.getParameter("radiov");
        String athmoR = request.getParameter("radioa");
        String id = request.getParameter("id");
        Integer idR = null;
        if(id != null)  idR = Integer.parseInt(id);
        String name = (String)session.getAttribute ("username");
        Integer idC = (Integer) session.getAttribute("ID");
        System.out.println("IDR="+idR+" IDC="+idC+" username= "+name+" priceR="+priceR);
        final Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        if(!fileName.isEmpty())
        {            
            System.out.println("dentro"+fileName); 
            String savePath = getServletContext().getRealPath("/upload_image"); 
            savePath=savePath.replace("build\\web\\", "");
            final String path = savePath;
            String[]ext=fileName.split("\\.");
            OutputStream out = null;
            InputStream filecontent = null;
            System.out.println(path + "/"+fileName);
            try {
                out = new FileOutputStream(new File(path + "/"+"provanome."+ext[1]));
                filecontent = filePart.getInputStream();
                
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
              }
            } 
            catch (FileNotFoundException fne) {
                request.setAttribute("message", "File not found");
            }
            finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
        }
        
        /*try {
            PreparedStatement ps = (PreparedStatement) manager.getCon().prepareStatement("INSERT INTO REVIEWS "
                    + "(GLOBAL_VALUE,FOOD,SERVICE,VALUE_FOR_MONEY,ATMOSPHERE,NAME,DESCRIPTION,ID_RESTAURANT,ID_CREATOR)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,valueR);
            ps.setString(2,foodR);
            ps.setString(3, serviceR);
            ps.setString(4, priceR);
            ps.setString(5, athmoR);
            ps.setString(6, name);
            ps.setString(7, recensione);
            if (idR != null)ps.setInt(8, idR);
            if (idC != null)ps.setInt(9, idC);
            //manca id_photo
            ps.executeQuery();
            
            request.setAttribute("messageOK", "Recensione ok");
            
        } catch (SQLException ex) {
            
            request.setAttribute("messageERR", "Recensione Error");
        }
     */   
    }
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
