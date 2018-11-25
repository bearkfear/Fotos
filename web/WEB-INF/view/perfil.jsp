<%-- 
    Document   : perfil
    Created on : 25/11/2018, 01:02:23
    Author     : campo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil - <c:out value="${sessionScope['Fotos_User'].nome}" /></title>
    </head>
    <body>

        <a href="./user?action=dashboard">Inicio</a>
        <a href="./user?action=updateInformations">Editar informações pessoais</a>
        <a href="./user?action=logoff">Sair</a> 
        <h1>Seu perfil</h1>

        <img src="./img/user/${sessionScope['Fotos_User'].urlImg}">


        <h3>Nome: <b>${sessionScope['Fotos_User'].nome}</b></h3>
        <p>Sobre: ${sessionScope['Fotos_User'].sobre}</p>

        <h3>Suas imagens</h3>

        <c:forEach var="image" items="${sessionScope['Fotos_User'].imagens}">
            <c:out value="${image.descricao}"/>
            <br>
            <c:if test="${image.associacoes != null}">
                <c:forEach items="${image.associacoes}" var="associacoes">
                    <a href="./user?action=marcador&value=<c:out value="${associacoes.marcador.codigo}"/>">
                        #<c:out value="${associacoes.marcador.titulo}"/>
                    </a><br>
                </c:forEach>
            </c:if>
        </c:forEach>
    </body>
</html>
