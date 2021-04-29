<%-- 
    Document   : ChooseColors2
    Created on : 29-abr-2021, 1:51:17
    Author     : Raul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
       <style>
        body{
            <jsp:useBean id="colores" class="prova.Colors" scope="session"/>
            <jsp:setProperty name="colores" property="*"/>

            background: <jsp:getProperty name="colores" property="background"/>;
            color: <jsp:getProperty name="colores" property="foreground"/>;
        }

    </style>
    <body>
          <h1>Show Colors (2)</h1>
           <p>Blah, blah, blah</p>
    </body>
</html>
