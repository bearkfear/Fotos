<%-- 
    Document   : dashboard
    Created on : 24/11/2018, 00:06:39
    Author     : campo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./user" method="GET">
            <label>Pesquisar por uma imagem</label>
            <input type="text" name="value" />
            <button type="submit" name="action" value="find">Buscar</button>
        </form>


        <c:if test="${Fotos_Marcadores != null}">
            <ul>
                <c:forEach items="${Fotos_Marcadores}" var="marcador" >
                    <li><a href="./user?action=marcador&value=${marcador.codigo}">${marcador.titulo}</a></li>
                    </c:forEach>

            </ul>
        </c:if> 




    </body>
</html>
