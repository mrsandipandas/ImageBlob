/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayPhotoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            OutputStream os = response.getOutputStream();
            con = jdbcConn.getConnection();//DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            ps = con.prepareStatement("select photo from photos where id = ?");
            String id = request.getParameter("id");
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob b = rs.getBlob("photo");
                response.setContentType("image/jpeg");
                response.setContentLength((int) b.length());
                InputStream is = b.getBinaryStream();
                System.out.println("inputstream length = " + is.available());
                byte buf[] = new byte[(int) b.length()];
                is.read(buf);
                os.write(buf);
            }
            os.close();
        } //        try {
        //            OutputStream os = response.getOutputStream();
        //            con = jdbcConn.getConnection();
        //            String ecardId = request.getParameter("id");
        //
        //            ps = con.prepareStatement("select photo from photos where id = ?");
        //            String id = request.getParameter("id");
        //            ps.setInt(1,Integer.parseInt(id));
        //            rs = ps.executeQuery();
        //
        //
        //            byte[] imgData = card.getCategoryCardImage(ecardId);
        //            response.setContentType("image/jpeg");
        //            os.write(imgData);
        //            os.flush();
        //      }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
            try {
                ps.close();
            } catch (SQLException ex) {
            }
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
