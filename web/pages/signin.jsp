<%-- 
    Document   : new
    Created on : 18/10/2018, 03:41:42
    Author     : Enrico
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Entre na sua conta</title>
        <link rel="stylesheet" href="${contexto}/materialize/css/materialize.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">



    </head>

    <body>
        <nav class="indigo z-depth-2">
            <div class="nav-wrapper container">
                <a href="index.jsp" class="brand-logo">Foto.com</a>
            </div>

        </nav>


        <div class="row">
            <h3 class="center-align">Acesse sua conta</h3>


            <!-- A imagem pode ser que nÃ£o apareÃ§a
            
                Se: 
                 O usuario digitar o login certo irÃ¡ aparecer
                 senÃ£o: 
                    NÃ£o serÃ¡ exibida nenhuma imagem
            -->
            
            
            
            <div class="col s12 center">
                <img src="${contexto}/img/user/${imagem}" alt="Imagem de perfil" class="responsive-img" width="120px" height="120px"
                     style="border-radius: 25px">
            </div>


        </div>

                     
                     

        <div class="col s12 m6">

            <!-- Formulario que autentica o usuario, mostra a imagem e contniua com o seu email
            mas da a liberdade de alterar o email caso seja necessÃ¡rio -->

            <form action="logar" method="POST" id="formulario" class="container">

                <div class="row">
                    <div class="input-field col s12 m7 l4 offset-m2 offset-l4">
                        <i class="material-icons prefix indigo-text">email</i>
                        <input value="${email}" id="email" type="email" name="email" required>
                        <label for="email">Email</label>
                        <span class="helper-text">Seu email já foi definido. Digite sua senha</span>
                    </div>
                    <div class="row">

                        <div class="input-field col s12 m7 l4 offset-m2 offset-l4">
                            <i class="material-icons prefix indigo-text">lock</i>
                            <input type="password" id="password" name="pass" autofocus required>
                            <span class="helper-text">Digite a sua senha</span>
                            <!--<blockquote class="col s12 m7 l4 offset-l1">
                                <strong>SENHA INCORRETA!</strong>
                            </blockquote> -->
                        </div>

                    </div>

                    <div class="row">
                        <div class="input-field col s12 m7 l4 offset-m3 offset-l5">
                            <button type="submit" class="btn waves-effect waves-light indigo pulse" value="eandp" name="opcao">Entrar</button>
                            <a href="index.jsp" class="btn grey waves-effect waves-light">Voltar</a>
                        </div>
                    </div>
            </form>

        </div>
        <script src="${contexto}/materialize/js/jquery-3.3.1.min.js"></script>
        <script src="${contexto}/materialize/js/materialize.js"></script>
        
    </body>

</html>