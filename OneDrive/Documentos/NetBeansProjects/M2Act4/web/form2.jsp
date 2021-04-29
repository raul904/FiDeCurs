<%-- 
    Document   : form2
    Created on : 29-abr-2021, 12:21:26
    Author     : Raul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="ChooseColors2.jsp" method="post">
            Foreground color:
            <input type="text" name="foreground"/>
                <br>
            Background color:
            <input type="text" name="background"/>
      
            <br>
            <input type="submit" value="Show color" />
    </body>
</html>
