<%-- 
    Document   : resultado
    Created on : 23/11/2018, 00:18:20
    Author     : campo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>



    </head>
    <body>

        <a href="./user?action=dashboard">Início</a>

        ${sessionScope['Fotos_User'].nome} <br>
        <a href="./user?action=perfil">Visualizar perfil</a>
        <a href="./user?action=logoff">Sair</a>



        <h1>DASHBOARD</h1>

        <form action="./user" method="GET">
            <label>Pesquisar por uma imagem</label>
            <input type="text" name="value" value="<c:out value="${correspondence}"/>" />
            <button type="submit" name="action" value="find">Buscar</button>
        </form>


        <c:if test="${Fotos_Marcadores != null}">
            <ul>
                <c:forEach items="${Fotos_Marcadores}" var="marcador" >
                    <li><a href="./user?action=marcador&value=${marcador.codigo}">#${marcador.titulo}</a></li>
                    </c:forEach>

            </ul>
        </c:if> 


        <h2>Resultado</h2>

        <c:choose>
            <c:when test="${Fotos_Images != null}">
                
                <c:forEach items="${Fotos_Images}" var="image">
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


            </c:when>
            <c:otherwise>
                <p>Nenhuma correspondência para <b><c:out value="${correspondence}"/></b></p>
            </c:otherwise>
        </c:choose>


    </body>
</html>
