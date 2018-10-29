<%-- 
    Document   : new
    Created on : 18/10/2018, 03:41:42
    Author     : Enrico
--%>

<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Imagem"%>
<%@page import="dao.ImagemDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    if (request.getSession().getAttribute("usuarioLogado") == null) {
        response.sendRedirect("../index.jsp");
    }

%>

<!DOCTYPE html>
<html lang="pt">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Seu perfil - ${sessionScope['usuarioLogado'].getNome()}</title>
        <link rel="stylesheet" href="../materialize/css/materialize.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>

    <body>

        <nav class="indigo">
            <div class="nav-wrapper container">


                <a href="dashboard.jsp" class="brand-logo"><i class="material-icons">image</i>foto.com</a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <a href="#" class="dropdown-trigger valign-wrapper" data-target="dropDownUser">
                            <img src="../img/user/${sessionScope['usuarioLogado'].getUrlImg()}" alt="Imagem de perfil usuario" class="responsive-img" width="30px"
                                 height="30px" style="border-radius: 5px">&nbsp;&nbsp;${sessionScope['usuarioLogado'].getNome()}
                        </a>
                    </li>

                    <!-- conteudo links sem side nav-->
                </ul>
            </div>
        </nav>

        <ul id="dropDownUser" class="dropdown-content">
            <li><a href="#" class="black-text"><i class="material-icons">person</i>Perfil</a></li>
            <li><a href="#" onclick="sair();" class="black-text"><i class="material-icons">close</i> Sair</a></li>
        </ul>

        <ul class="sidenav" id="mobile-demo">

            <!-- sidenav-->
            <li><a href="#"><i class="material-icons">image</i>foto.com</a></li>
            <li><a href="#"><i class="material-icons">person</i>Perfil</a></li>
            <li><a href="#" onclick="sair();"><i class="material-icons">close</i>Sair</a></li>
        </ul>



        <div class="row">

            <!-- Imagem do Usuario -->
            <div class="row container center" style="margin-top: 30px">
                <div class="col s12 m12 l12">
                    <img src="../img/user/${sessionScope['usuarioLogado'].getUrlImg()}" alt="Imagem de perfil do usuario" class="responsive-img" width="200px"
                         height="200px" style="border-radius: 10px">
                </div>
            </div>
            <!-- nome do usuario-->

            <div class="row">

                <div class="col s12 m12 l12 center-align">
                    <h4 style="text-transform:capitalize">${sessionScope['usuarioLogado'].getNome()}</h4>

                </div>
            </div>

            <!-- Sobre o usuario -->
            <div class="row">
                <div class="col s12 m8 l6 offset-m2 offset-l3 center-align container">
                    <p class="flow-text">
                        ${sessionScope['usuarioLogado'].getSobre()}
                    </p>
                </div>

            </div>


        </div>




        <div class="divider"></div>

        <!-- Todas as imagens do usuario -->

        <div class="row">
            <h2 class="center">Suas imagens</h2>

            <%                Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogado");
                ArrayList<Imagem> imagens = new ImagemDao().readImagens(u.getCodigo());

                if (imagens == null) {

                } else {

                    for (Imagem imagem : imagens ) {

            %>

            <div class="col s12 m4 l2">
                <div class="card">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator responsive-img" src="../img/<% out.print(imagem.getUrl()); %>">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4 truncate"><% imagem.getTitulo(); %><i class="material-icons right">more_vert</i></span>
                        <p><a href="#">Ver imagem</a></p>
                        <p><a href="editar.html">Editar informações</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4"><% imagem.getTitulo(); %><i class="material-icons right">close</i></span>
                        <p><% imagem.getDescricao(); %></p>
                    </div>
                </div>


            </div>
            <%                    }
                }

                

            %>





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
                    Â© 2018 Copyright UFSM
                </div>
            </div>
        </footer>





        <script src="../materialize/js/jquery-3.3.1.min.js"></script>
        <script src="../materialize/js/materialize.js"></script>

        <script src="../materialize/js/index.js"></script>

    </body>

</html>