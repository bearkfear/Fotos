<%-- 
    Document   : new
    Created on : 18/10/2018, 03:41:42
    Author     : Enrico
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.ImagemDao"%>
<%@page import="model.Imagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    if (request.getSession().getAttribute("usuarioLogado") == null) {
        response.sendRedirect("../index.jsp");
    }

%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>DashBoard - ${sessionScope['usuarioLogado'].getNome()}</title>
        <link rel="stylesheet" href="../materialize/css/materialize.css">

        <link rel="stylesheet" href="../materialize/css/grid-gallery.min.css">

        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>

    <body>

        <nav class="indigo">
            <div class="nav-wrapper container">


                <a href="dashboard.jsp" class="brand-logo"><i class="material-icons">image</i>foto.com</a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">

                    <!-- conteudo links sem side nav-->
                    <li>
                        <a href="#" class="dropdown-trigger valign-wrapper" data-target="dropDownUser">
                            <img src="../img/user/${sessionScope['usuarioLogado'].getUrlImg()}" alt="Imagem de perfil usuario"
                                 class="responsive-img" width="30px" height="30px" style="border-radius: 5px">&nbsp;Bem vindo,&nbsp;<Strong>${sessionScope['usuarioLogado'].getNome()}</strong>
                        </a>
                    </li>


                    <li>
                        <a href="#uploadImagens" class="btn btn-floating btn-large green pulse modal-trigger"><i class="material-icons">add</i></a>
                    </li>


                </ul>
            </div>
        </nav>

        <ul id="dropDownUser" class="dropdown-content">
            <li><a href="perfil.jsp" class="black-text"><i class="material-icons right">person</i>Perfil</a></li>
            <li><a href="#" class="black-text" onclick="sair()"><i class="material-icons right">close</i> Sair</a></li>
        </ul>


        <ul class="sidenav" id="mobile-demo">



            <!-- sidenav-->
            <li>
                <div class="user-view" >
                    <div class="background">
                        <img src="../img/office.jpg">
                    </div>
                    <img class="circle" src="../img/user/${sessionScope['usuarioLogado'].getUrlImg()}"/>
                    <span class="white-text">${sessionScope['usuarioLogado'].getNome()}</span>
                    <br/>
                    <span class="white-text">${sessionScope['usuarioLogado'].getEmail()}</span>
                </div>


            </li>

            <li><a href="dashboard.html"><i class="material-icons">image</i>foto.com</a></li>

            <li><a href="perfil.html"><i class="material-icons">person</i>Perfil</a></li>
            <li><a href="#uploadImagens" class="modal-trigger"><i class="material-icons">add</i>Enviar imgens</a></li>
            <li><a href="#" onclick="sair();"><i class="material-icons">close</i>Sair</a></li>
        </ul>



        <!-- Modal para enviar imagens -->
        <div class="modal bottom-sheet" id="uploadImagens">

            <div class="modal-content container">
                <div class="row">
                    <div class="col s12">
                        <h4>
                            Enviar imagens
                        </h4>
                    </div>
                </div>
            </div>
            <div class="row">


                <form action="../img" method="POST" enctype="multipart/form-data">


                    <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                        <div class="file-field input-field ">

                            <div class="btn center-align">
                                <span>Selecionar</span>
                                <input type="file" multiple accept="image/*" name="imagens">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text" placeholder="Selecione uma ou mais imagens">
                            </div>
                        </div>

                    </div>

                    <div class="input-field col s12 m8 l6 offset-m2 offset-l3 center">

                        <!-- 
                            Botão enviar imagens
                        -->


                        <button type="submit" class="btn waves-effect waves-light btn-large indigo">Enviar
                            <i class="material-icons right">send</i></button>

                        <!-- 
                            Fechar modal
                        -->
                        <a href="#!" class="modal-close waves-effect waves-light btn-flat black-text">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>




        <div class="row">
            <form class="col s12 ">
                <div class="row">
                    <h4 class="center-align">Buscar imagem</h4>
                    <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                        <i class="material-icons prefix">search</i>
                        <input type="text" id="img" name="nomeImagemLogado" autofocus required>
                        <label for="img">Nome da imagem</label>
                        <span class="helper-text">Precione "Enter" para buscar</span>

                    </div>
                    <div class="col s12 center">
                        <button type="submit" class="btn waves-effect waves-light indigo btn-large">Buscar</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="divider"></div>




        <!-- ONDE FICA AS IMAGENS SALVAS NO SERVIDOR -->



        <div class="gg-box" >

            <%                ArrayList<Imagem> imagens = new ImagemDao().readImagens();
                if (imagens == null) {
                } else {

                    for (Imagem imagem : imagens) {
            %>



            <div class="gg-element">
                <a data-fancybox="gallery" href="../img/<% out.print(imagem.getUrl()); %>" data-caption="<strong><% out.print(imagem.getTitulo()); %></strong><br><% out.print(imagem.getDescricao()); %> "><img
                        src="../img/<% out.print(imagem.getUrl()); %>" title="<% out.print(imagem.getTitulo()); %>"></a>
            </div>

            <%
                    }
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




            <form action="../logoff" method="GET" id="sair" class="display: hidden;">
        </form>




        <script src="../materialize/js/jquery-3.3.1.min.js"></script>
        <script src="../materialize/js/materialize.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.2/dist/jquery.fancybox.min.css" />
        <script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.2/dist/jquery.fancybox.min.js"></script>
        <script src="../materialize/js/index.js"></script>

    </body>

</html>