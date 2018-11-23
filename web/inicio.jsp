<%-- 
    Document   : index
    Created on : 21/11/2018, 07:53:43
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


        <form method="POST" action="find">
            <label>Pesquisar por imagem</label>
            <input type="text" name="nomeImagem" />
            <button type="submit" name="buscar">Pesquisar</button>
        </form>

        <ul>
            <c:forEach items="${Fotos=Marcadores}" var="marcador" >
                <li><a href="./find?marcador=${marcador.codigo}">${marcador.titulo}</a></li>
            </c:forEach>

        </ul>


    </body>
</html>
