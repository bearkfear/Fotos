<%-- 
    Document   : new
    Created on : 18/10/2018, 03:41:42
    Author     : Enrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="../materialize/css/materialize.css">
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>foto.com</title>
    </head>

    <body>

        <nav class="indigo z-depth-2">
            <div class="nav-wrapper container">
                <a href="../index.jsp" class="brand-logo">Foto.com</a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="../index.jsp">Home</a></li>
                    <li class="active"><a href="cadastro.jsp">Cadastrar-se</a></li>
                    <!-- Links da navbar -->

                </ul>
            </div>

        </nav>

        <ul class="sidenav" id="mobile-demo">
            <!-- Links da side-nav -->
            <li><a href="../index.jsp">Home</a></li>
            <li class="active"><a href="cadastro.html">Cadastrar-se</a></li>

        </ul>

        <div class="divider"></div>

        <!-- cADASTRO
        
            Nome, email, senha e sobre
        -->


        <!--
        
            formulario: 
                
                nome, email, pass, sobre
        -->
        <div class="row">
            <div class="col s12 m8 offset-m2  container">
                <h3 class="center-align">Faça o seu cadastro</h3>
                <p class="center-align">Envie suas imagens agora fazendo seu cadastro!</p>

                
                
                <!-- 
                
                    /cadastrar ?
                    ../cadastrar ?
                --> 
                <form action="../cadastrar" method="POST">

                    
                    <!-- nome -->
                    <div class="input-field col s12 m8 offset-m2">
                        <input type="text" name="nome" id="nome">
                        <label for="nome">Nome</label>
                        <span class="helper-text">Digite o seu nome</span>
                    </div>
                    
                    <!-- Email -->
                    <div class="input-field col s12 m8 offset-m2">
                        
                        <i class="material-icons prefix">email</i>
                        <input type="email" name="email" id="email">
                        <label for="email">Email</label>
                        <span class="helper-text">Digite o seu Email</span>

                    </div>
                    
                    <!-- senha -->
                    <div class="input-field col s12 m8 offset-m2">
                        <i class="material-icons prefix">lock</i>
                        <input type="password" name="pass" id="pass">
                        <label for="pass">Senha</label>
                        <span class="helper-text">Digite uma senha bem forte</span>

                    </div>
                    
                    <!-- sobre -->
                    <div class="input-field col s12 m8 l6 offset-m2 offset-l3">
                        <textarea name="sobre" id="sobre" class="materialize-textarea"></textarea>
                        <label for="sobre">Sobre</label>
                        <span class="helper-text">Conte algo sobre vocÃª brevemente</span>
                    </div>
                    
                    
                    <!-- cadastrar -->
                    <div class="col s12 offset-s4 offset-m2">

                        <button type="submit" class="btn green waves-effect waves-light" name="opcao" value="cadastrar">Cadastrar</button>
                        <c:if test="${not empty resposta}">
                        <blockquote>${resposta}</blockquote>
                        </c:if>
                    </div>
                    


                </form>
            </div>

        </div>

        <div class="divider"></div>
        <div class="row">
            <div class="container">
                <div class="col s12 m12 l12 center-align">

                    <h4 class="center-align">Novidades</h4>

                    <div class="col s12 m3 ">
                        <i class="material-icons large indigo-text">person</i>
                        <br>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam eum deleniti
                        excepturi quae provident pariatur quod adipisci impedit voluptatum, ipsum amet mollitia incidunt
                        veniam consequatur dolorem culpa delectus quos ullam!</div>
                    <div class="col s12 m3">
                        <i class="material-icons large indigo-text">lock</i>
                        <br>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam eum deleniti
                        excepturi quae provident pariatur quod adipisci impedit voluptatum, ipsum amet mollitia incidunt
                        veniam consequatur dolorem culpa delectus quos ullam!</div>
                    <div class="col s12 m3">
                        <i class="material-icons large indigo-text">email</i><br>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam eum deleniti
                        excepturi quae provident pariatur quod adipisci impedit voluptatum, ipsum amet mollitia incidunt
                        veniam consequatur dolorem culpa delectus quos ullam!</div>
                    <div class="col s12 m3">
                        <i class="material-icons large indigo-text">help</i><br>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Facilis ipsum
                        reiciendis iusto quod necessitatibus cupiditate odio, a ad quibusdam non inventore veniam, natus
                        voluptatem animi! Cum accusamus delectus eligendi officiis.</div>

                </div>
            </div>
        </div>




        <!--FOOTER-->

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
        <script>
            /**  Jquery inicializador da sidenav  */
            $(document).ready(function () {
                $('.sidenav').sidenav();
            });

            /**
             * Jquery mOdal inicializador
             */

        </script>
    </body>

</html>