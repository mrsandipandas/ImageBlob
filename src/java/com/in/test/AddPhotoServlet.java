/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author sada3260
 */
public class AddPhotoServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JdbcConnection jdbcConn = new JdbcConnection();
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }

            // parse request
            List items = sfu.parseRequest(request);
            FileItem  id = (FileItem) items.get(0);
            String photoid =  id.getString();
            System.out.println("photoid = " + photoid);

            FileItem title = (FileItem) items.get(1);
            String   phototitle =  title.getString();

            System.out.println("phototitle = " + phototitle);
            // get uploaded file
            FileItem file = (FileItem) items.get(2);

            // Connect to Oracle
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = jdbcConn.getConnection(); 
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("insert into photos values(?,?,null)");
            ps.setInt(1, Integer.parseInt(photoid));
            ps.setString(2, phototitle);
            // size must be converted to int otherwise it results in error


            //ps.setBinaryStream(3, file.getInputStream(), (int) file.getSize());

            ps.executeUpdate();
            con.commit();
            con.close();

            UploadCard uploadImage = new UploadCard();
            uploadImage.addCardImage(photoid,file);

            out.println("Photo Added Successfully. <p> <a href='/ImageBlobTest2/ListPhotosServlet'>List Photos </a>");
        }
        catch(Exception ex) {
            ex.printStackTrace();
            out.println( "Error --> " + ex.getMessage());
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
