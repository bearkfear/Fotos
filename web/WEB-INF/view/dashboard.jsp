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
        <title>DASHBOARD</title>
    </head>
    <body>
        
        ${sessionScope['Fotos_User'].nome} <br>
        <a href="./user?action=perfil">Visualizar perfil</a>
        <a href="./user?action=logoff">Sair</a>
        
        
        
        <h1>DASHBOARD</h1>
        
        <form action="./user" method="GET">
            <label>Pesquisar por uma imagem</label>
            <input type="text" name="value" />
            <button type="submit" name="action" value="find">Buscar</button>
        </form>


        <c:if test="${Fotos_Marcadores != null}">
            <ul>
                <c:forEach items="${Fotos_Marcadores}" var="marcador" >
                    <li><a href="./user?action=marcador&value=${marcador.codigo}">#${marcador.titulo}</a></li>
                    </c:forEach>

            </ul>
        </c:if> 




    </body>
</html>
