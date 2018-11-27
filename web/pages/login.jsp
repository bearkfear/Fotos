<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
    </head>

    <body class="bg-dark">
        <div class="py-5 text-center text-white w-100 h-100" style="">
            <div class="container">
                <div class="row">
                    <div class="p-5 col-lg-6 col-10 mx-auto border">
                        <h1 class="mb-4">Entrar na sua conta</h1>


                        <form  method="POST" action="<c:if test="${path == true}">./RegisterAndLogin</c:if><c:if test="${path != true}">../RegisterAndLogin</c:if>">
                                <div class="form-group"> 
                                    <input type="email" class="form-control" placeholder="E-mail" name="email"> 
                                </div>
                                <div class="form-group"> 
                                    <input type="password" class="form-control" placeholder="Senha" name="password"> 
                                    <small class="form-text text-muted text-right"></small>
                                </div>
                                <button type="submit" class="btn btn-primary mx-1" name="option" value="login">Entrar</button>
                            <a class="btn btn-light mx-1" href="<c:if test="${path != true}">../</c:if>
                               <c:if test="${path == true}">./</c:if>">Voltar</a>
                            </form>



                        </div>
                    </div>
                </div>
            </div>
        <c:if test="${!empty infoAutenticate}">
            <script>
                alert("Não foi possível realizar a autenticação dessa conta. Email não existe ou senha está incorreta ou cadastre-se");
            </script>
        </c:if>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>

</html>