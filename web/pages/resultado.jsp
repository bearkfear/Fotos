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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
        <title>Resultado da busca</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container"> <a class="navbar-brand" href="./">
                    <i class="fa d-inline fa-lg fa-stop-circle"></i>
                    <b> AppFotos</b>
                </a>
            </div>
        </nav>
        <div class="">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb">

                            <c:forEach items="${Fotos_Marcadores}" var="marcador" >
                                <li class="breadcrumb-item"><a href="./find?action=marcador&value=${marcador.codigo}">#${marcador.titulo}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-5 text-center justify-content-center align-items-center flex-row">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Pesquisar</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="mx-auto col-lg-6">


                        <form class="form-inline justify-content-center"  method="GET" action="find">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Pesquisar Imagem" name="value">
                                <div class="input-group-append"><button class="btn btn-primary" type="submit" name="action" value="buscar"><i class="fa fa-search"></i></button></div>
                            </div>
                        </form>



                        <p class="small">Pressione "enter" para buscar</p>
                    </div>
                </div>
            </div>
        </div>
        

        <div class="py-5" style="">
            <h2 class="text-center">Resultado</h2>
            <div class="container">

                <c:choose>
                    <c:when test="${Fotos_Images != null}">
                        <c:forEach items="${Fotos_Images}" var="image">
                            <div class="row">
                                <div class="">
                                    <div class="card"> 
                                        <img class="card-img-top" src="./assets/img/${image.url}" alt="<c:out value="${image.descricao}"/>">
                                        <div class="card-body">
                                            <h4 class="card-title"><c:out value="${image.descricao}"/></h4>
                                            <c:if test="${image.associacoes != null}">
                                                <c:forEach items="${image.associacoes}" var="associacoes">
                                                    <a href="./find?action=marcador&value=<c:out value="${associacoes.marcador.codigo}"/>">
                                                        #<c:out value="${associacoes.marcador.titulo}"/>
                                                    </a>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>Nenhuma correspondência para <b><c:out value="${correspondence}"/></b></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>

</html>