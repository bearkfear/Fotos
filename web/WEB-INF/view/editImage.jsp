<%-- 
    Document   : editImage
    Created on : 25/11/2018, 05:06:38
    Author     : campo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
        type="text/css">
    <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
    <title>Editar Imagem</title>
</head>

<body>
    
    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="p-5 col-lg-6">
                    <h1>Adicione informações a sua imagem</h1>
                    <form action="./addMarcadores" method="POST">


                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Descrição da imagem" required="required"  name="nomeImagem" value="${imagem.descricao}">
                            <input type="hidden" name="codigoImagem" value="${imagem.codigo}">
                        </div>
                        <div class="form-group" id="marcadores"> 
                            <input type="text" class="form-control" name="marcadores[]" placeholder="Marcador">
                         </div> 
                         <button type="button" class="btn btn-seconday" onclick="adicionarMarcador();">Adiconar Marcador</button>
                         <button type="submit" class="btn btn-primary">Salvar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
    <script>
        var marcadores = document.getElementById("marcadores");

        function adicionarMarcador(e) {
            var marcador = document.createElement('input');
            marcador.classList.add('form-control');
            marcador.setAttribute('type', 'text');
            marcador.setAttribute('name', 'marcadores[]');
            marcadores.appendChild(marcador);
        }
    </script>
</body>

</html>