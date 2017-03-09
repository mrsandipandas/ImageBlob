/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.commons.fileupload.FileItem;

public class UploadCard {

    public void addCardImage(String id, FileItem image) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();
            //conn.setAutoCommit(false);
            File imageFile = new File(image.getName());
            System.out.println("imageFile location = " + imageFile.getAbsolutePath());
            InputStream inputStream = (InputStream) new FileInputStream(imageFile);
            System.out.println("File size = " + inputStream.available());
            pstmt = conn.prepareStatement("update photos set photo = ? where id =?");

            pstmt.setBinaryStream(1, inputStream, (int) imageFile.length());
            pstmt.setInt(2, Integer.parseInt(id));
            //pstmt.setBinaryStream(2, cardImage.getInputStream(), (int) cardImage.getSize());
            int rowCount = pstmt.executeUpdate();
            System.out.println("rowCount = " + rowCount);
            System.out.println("################card added successfully####################");



            ////////////////////////////////////////



        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("problem in closing addCardImage connection");
                e.printStackTrace();
            }
        }

    }
}

