<%-- 
    Document   : showColors
    Created on : 28-abr-2021, 21:21:16
    Author     : Raul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
          <style type="text/css">
            body{
                <jsp:useBean id="colors" class="Colors" />
                background-color: <jsp:getProperty name="colors" property="backCol" />;
                color: <jsp:getProperty name="colors" property="textCol" />
            
        </style>
    <body>
  
        <h1>Show colors</h1>
    </body>
</html>
