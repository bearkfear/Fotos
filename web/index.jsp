<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Imagem"%>
<%@page import="dao.ImagemDao"%>
<%pageContext.setAttribute("imagens", new ImagemDao().readImagens(0));%>



<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="materialize/css/materialize.css">
        <link rel="stylesheet" href="materialize/css/grid-gallery.min.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>foto.com</title>
    </head>

    <body>
        
        
        <nav class="indigo z-depth-2">
            <div class="nav-wrapper container">
                <a href="./index.jsp" class="brand-logo">Foto.com</a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <!-- Links da navbar -->
                <ul class="right hide-on-med-and-down">
                    <li class="active"><a href="./index.jsp">Home</a></li>
                    <li><a href="./pages/cadastro.jsp">Cadastrar-se</a></li>
                    <li>
                        <a href="#entrar" class="modal-trigger btn-large btn-floating waves-effect waves-light green">
                            <i class="material-icons">person</i>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>





        <ul class="sidenav" id="mobile-demo">
            <!-- Links da side-nav -->
            <li class="active"><a href="./index.jsp">Home</a></li>
            <li><a href="./pages/cadastro.jsp">Cadastrar-se</a></li>
            <li><a href="#entrar" class="modal-trigger">Entrar<i class="material-icons right">person</i></a></li>
        </ul>




        <!-- Acessar conta -->
        <div class="modal" id="entrar">
            <div class="modal-content">
                <h4 class="center">Entre na sua conta</h4>
                <p class="center">Acesse sua conta digitando seu email</p>
                <form action="logar" method="POST">
                    <!-- Email -->
                    <div class="row">
                        <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                            <i class="material-icons prefix">account_circle</i>
                            <input type="email" id="email" name="email" placeholder="example@example.com">
                            <label for="email">Email</label>
                        </div>
                        <!-- Botão de sair e enviar -->
                    </div>
                    <div class="row">
                        <div class="col s12 m8 l6 offset-s1 offset-m3 offset-l4">
                            <button type="submit" class="btn waves-effect waves-light indigo white-text" name="opcao" value="entrar">Entrar</button>
                            <a href="#" class="btn waves-effect waves-light white black-text modal-close">Sair</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>








        <!-- BUSCAR IMAGEM  --> 
        <div class="row">
            <form class="col s12" id="buscarImagem">
                <div class="row">
                    <h4 class="center-align">Buscar imagem</h4>
                    <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                        <i class="material-icons prefix">search</i>
                        <input type="text" id="img" name="nomeImagem" autofocus required id="nomeImagem"/>  
                        <label for="img">Nome da imagem</label>
                        <span class="helper-text">Precione "Enter" para buscar</span>

                    </div>
                    <div class="col s12 center">
                        <button type="submit" class="btn waves-effect waves-light indigo btn-large" id="procurar">Buscar</button>
                    </div>
                </div>
            </form>
        </div>




        <div class="divider"></div>


        <!-- ONDE FICA AS IMAGENS SALVAS NO SERVIDOR -->

    
        <div class="gg-box">
            <c:forEach items="${imagens}" var="imagem">
                <div class="gg-element">
                    <a data-fancybox="gallery" href="img/${imagem.getUrl()}" data-caption="<strong>${imagem.getTitulo()}</strong><br> ${imagem.getDescricao()}">
                        <img src="img/${imagem.getUrl()}" title="${imagem.getTitulo()}">
                    </a>
                </div>
            </c:forEach>
        </div>







        <footer class="page-footer indigo">
            <div class="container">
                <div class="row">
                    <div class="col l12 s12 center-align">
                        <h5 class="white-text">Foto.com</h5>
                        <p class="grey-text text-lighten-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime
                            quos voluptatibus ex maiores aliquam tenetur libero, sequi, exercitationem, error quas quia
                            necessitatibus nobis ullam natus ut velit atque reprehenderit corporis..</p>
                    </div>

                </div>
            </div>
            <div class="footer-copyright indigo darken-4">
                <div class="container center-align">
                     Copyright UFSM
                </div>
            </div>
        </footer>


        <!-- JQUERY  --> 
        <script src="materialize/js/jquery-3.3.1.min.js"></script>

        <!-- MATERIALIZE JS  --> 
        <script src="materialize/js/materialize.js"></script>

        <!-- FANCYBOX GALERIA DE FOTOS  --> 
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.2/dist/jquery.fancybox.min.css" />
        <script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.2/dist/jquery.fancybox.min.js"></script>


        <!-- SCRIPTS PERSONALIZADOS  --> 
        <script src="materialize/js/index.js"></script>


        <script>

            var formulario = document.getElementById("buscarImagem");

            formulario.addEventListener("click", function () {
                var imagens = document.getElementById("showImagens");
                var nome = document.getElementById("nomeImagem");

                var resposta = buscarImagem(nome);
                imagens.innerHTML = resposta;
            });


        </script>
    </body>

</html>