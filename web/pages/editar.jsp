<%-- 
    Document   : new
    Created on : 18/10/2018, 03:41:42
    Author     : Enrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    if ( request.getSession().getAttribute("usuarioLogado") == null ) {
        response.sendRedirect("../index.jsp");
    }

%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>DashBoard</title>
    <link rel="stylesheet" href="../materialize/css/materialize.css">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

    <nav class="indigo">
        <div class="nav-wrapper container">


            <a href="dashboard.html" class="brand-logo"><i class="material-icons">image</i>foto.com</a>
            <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li>
                    <a href="#" class="dropdown-trigger valign-wrapper" data-target="dropDownUser">
                        <img src="../img/download.jpg" alt="Imagem de perfil usuario" class="responsive-img" width="30px"
                            height="30px" style="border-radius: 5px">&nbsp;
                        NomeUsuario
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




    <!-- imagem e formulario sobre a imagem a ser editada -->


    <div class="row" style="margin-top: 30px">
        <h3 class="container center-align">Editar informaÃ§Ãµes da imagem</h3>
    </div>
    <div class="row">

        <form action="#" method="POST" class="">

            <!-- InformaÃ§Ãµes sobre a imagem : titulo e descriÃ§Ã£o  -->

            <!-- Titulo da iamgem -->
            <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                <input type="text" name="titulo" id="titulo " autofocus>
                <label for="titulo">Titulo da imagem</label>
                <span class="helper-text">Digite um titulo chamativo para a sua imagem</span>
            </div>

            <!-- Descricao da iamgem -->

            <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                <textarea name="drescricao" id="descricao" class="materialize-textarea"></textarea>
                <label for="descricao">DescriÃ§Ã£o</label>
                <span class="helper-text">FaÃ§a uma descriÃ§Ã£o sobre a sua imagem</span>
            </div>

            <input type="hidden" name="codigo" value="codigoImagem">
            <div class="center col s12 m8 l6 offset-m2 offset-l3">
                <button class="btn indigo darken-4 waves-effect waves-light pulse" value="save">Salvar <i class="material-icons left">edit</i></button>
                <button class="btn red darken-4 waves-effect waves-light" value="delete">Excluir <i class="material-icons left">remove</i></button>

            </div>

        </form>


    </div>




    <!-- Preview da imagem a ser editada-->

    <div class="row center">

        <div class="col s12 m12 l12 center" style="margin-top:30px;">
            <img src="../img/download.jpg" alt="" style="border-radius:10px" width="50%" height="auto" class="responsive-img">

        </div>

    </div>






    <!-- Footer page-->

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