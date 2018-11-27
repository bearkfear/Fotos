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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
    </head>

    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container"> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar12">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-center" id="navbar12"> <a class="navbar-brand d-none d-md-block" href="./">
                        <i class="fa d-inline fa-lg fa-circle"></i>
                        <b> AppFotos</b>
                    </a>
                    <ul class="navbar-nav">
                        <li class="nav-item"> <a class="nav-link" href="./pages/login.jsp">Entrar</a> </li>
                        <li class="nav-item"> <a class="nav-link text-primary" href="./pages/register.jsp">Cadastrar-se</a> </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="py-0">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb">

                            <c:forEach items="${Fotos_Marcadores}" var="marcador" >
                                <li class="breadcrumb-item"><a href="./find?action=marcador&value=${marcador.codigo}">${marcador.titulo}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-5 d-flex justify-content-center align-items-center flex-row">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">




                        <form class="form-inline justify-content-center"  method="GET" action="find">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Pesquisar Imagem" name="value">
                                <div class="input-group-append"><button class="btn btn-primary" type="submit" name="action" value="buscar"><i class="fa fa-search"></i></button></div>
                            </div>
                        </form>




                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>

</html>