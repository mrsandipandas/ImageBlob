<%-- 
    Document   : index
    Created on : Jan 7, 2010, 2:47:37 PM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

 <html>
    <head>
        <title>Add Photo</title>
    </head>
    <body>
        <h2>Add Photo</h2>
        <form id="form1" enctype="multipart/form-data" action="/ImageBlobTest2/AddPhotoServlet" method="post">
            <table>
                <tr>
                    <td>Enter Photo Id :</td>
                    <td><input  type="text"  name="id"/></td>
                </tr>
                <tr>
                    <td>Enter Title For Photo :</td>
                    <td><input  type="text"  name="title"/></td>
                </tr>
                <tr>
                    <td>Select Photo  </td>
                    <td><input type="file"  name="photo" />
                </tr>
            </table>
            <p/>
            <input type="submit" value="Add Photo"/>
        </form>

        <p/>
        <a href="/ImageBlobTest2/ListPhotosServlet">List Photos </a>
    </body>
</html>
