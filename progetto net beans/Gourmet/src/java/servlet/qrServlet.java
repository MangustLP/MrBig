/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author lorenzo
 */
@WebServlet(name = "qrServlet", urlPatterns = {"/qrServlet"})
public class qrServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
                
        
                RequestDispatcher rd = request.getRequestDispatcher("/restaurant.jsp");

                String name = (String)request.getParameter("name");
                String web = (String)request.getParameter("sitoweb");
                
                 
                
                String qrtext = "Nome Ristorante: "+name
                                +"Sito web: "+web;
		ByteArrayOutputStream out = QRCode.from(qrtext).to(ImageType.PNG).stream();

                response.setContentType("image/png");

                response.setContentLength(out.size());



                OutputStream outStream = response.getOutputStream();
          
                outStream.write(out.toByteArray());
                outStream.flush();

                outStream.close();
                        
                 
                request.setAttribute("qrimage", "ok");
                rd.forward(request, response);
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
