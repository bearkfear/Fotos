<%-- 
    Document   : register
    Created on : 24/11/2018, 00:30:53
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
        <h1>Registrar-se</h1>

        <form method="POST" action="../RegisterAndLogin">
            nome: 
            <input type="text" name="name" /> <br>
            Sobre:
            <input type="text" name="about" /><br>
            Email:
            <input type="text" name="email" /><br>
            Senha:
            <input type="password" name="password" /><br>
            <button type="submit" name="option" value="register">Cadastrar</button>
            <a href="../">Inicio</a>
            <a href="./login.jsp">Entrar</a>
        </form>



    <c:if test="${!empty infoRegister}">
        <script>
            alert("Não foi possível realizar o cadastro da sua conta. Tente novamente mais tarde");
        </script>
    </c:if>

</body>
</html>
