<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrar</title>
    </head>
    <body>
        
        
        <h1>Entre na sua conta</h1>
        
        <form method="POST" action="../LoginAndRegister">
            
            <input type="text" name="email" placeholder="example@example.com">
            <input type="password" name="password" placeholder="senha forte"/>
            
            <button type="submit" name="option" value="login">Entrar</button>
            <a href="../">Inicio</a>
            <a href="./register.jsp">Registrar-se</a>
        </form>
        
        
        <c:if test="${!empty infoAutenticate}">
            <script>
                alert("Não foi possível realizar a autenticação dessa conta. Email não existe ou senha está incorreta ou cadastre-se");        
            </script>
        </c:if>
        
    </body>
</html>
