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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
        <title>Perfil - <c:out value="${sessionScope['Fotos_User'].nome}" /></title>
    </head>

    <body >


        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container"> 
                <a class="navbar-brand" href="user?action=dashboard">
                    <i class="fa d-inline fa-lg fa-stop-circle"></i>
                    <b> AppFotos</b>
                </a> 
                <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar10">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbar10">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">


                            <a 
                                class="nav-link dropdown-toggle" 
                                href="http://example.com" id="navbarDropdownMenuLink" 
                                data-toggle="dropdown" 
                                aria-haspopup="true" 
                                aria-expanded="false">${sessionScope['Fotos_User'].nome}</a>


                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                
                                <a class="dropdown-item"  href="./user?action=upload">Enviar Imagens</a> 
                                <a class="dropdown-item"  href="./user?action=perfil">Perfil</a> 
                                <a class="dropdown-item" href="./user?action=logoff">Sair</a> </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>






        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="display-4">${sessionScope['Fotos_User'].nome}<br></h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="blockquote">
                            <p class="mb-0">Sobre</p>
                            <div class="blockquote-footer">${sessionScope['Fotos_User'].sobre}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="">Suas imagens<br></h1>
                    </div>
                </div>
            </div>
        </div>

        <div class="py-5" style="">
            <h2 class="text-center">Resultado</h2>
            <div class="container">

                <c:forEach var="image" items="${sessionScope['Fotos_User'].imagens}" >
                    
                     <div class="row">
                                <div class="">
                                    <div class="card"> 
                                        <img class="card-img-top" src="./assets/img/${image.url}" alt="<c:out value="${image.descricao}"/>">
                                        <div class="card-body">
                                            <h4 class="card-title"><c:out value="${image.descricao}"/></h4>
                                            <c:if test="${image.associacoes != null}">
                                                <c:forEach items="${image.associacoes}" var="associacoes">
                                                    <a href="./user?action=marcador&value=<c:out value="${associacoes.marcador.codigo}"/>">
                                                        #<c:out value="${associacoes.marcador.titulo}"/>
                                                    </a>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    
                </c:forEach>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>

</html>
