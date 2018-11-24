<%-- 
    Document   : dashboard
    Created on : 24/11/2018, 00:06:39
    Author     : campo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Oi</h1>
        
        <form action="./user" method="GET">
            <label>Pesquisar por uma imagem</label>
            <input type="text" name="value" />
            <button type="submit" name="action" value="find">Buscar</button>
        </form>
        
        
        <ul>
            <c:forEach items="${Fotos=Marcadores}" var="marcador" >
                <li><a href="./user?action=marcador&value=${marcador.codigo}">${marcador.titulo}</a></li>
            </c:forEach>

        </ul>

        
        
    </body>
</html>
