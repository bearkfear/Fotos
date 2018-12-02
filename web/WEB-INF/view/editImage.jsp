<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
        <title>Informações da imagem</title>
    </head>

    <body>
        <div class="text-center py-5">
            <div class="container">
                <div class="row">
                    <div class="mx-auto col-lg-6 col-10">
                        <h1>Coleta de informações</h1>
                        <p class="mb-3">
                            Edite a descrição da sua imagem para melhor entendimento. Você pode associar sua
                            imagem a os
                            marcadores já existentes e, também pode criar marcadores
                        </p>



                        <form class="text-left" method="POST" action="./user">
                            <div class="form-group">
                                <label for="descricaoImg">Descrição da imagem</label>
                                <input type="text" class="form-control" id="descricaoImg" name="nomeImagem" placeholder="Uma incrível descrição">
                                <input type="hidden" value="${imagem.codigo}" name="codigoImagem">
                            </div>
                            <div class="form-group">
                                <label>Associe marcadores a sua imagem</label>

                                <div class="form-group" id="allMarcadores">


                                    <c:forEach items="${marcadores}" var="marcador">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="${marcador.codigo}" name="marcador" value="${marcador.codigo}">
                                            <label class="custom-control-label" for="${marcador.codigo}">${marcador.titulo}</label>
                                            <br>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <h5>Criar marcador</h5>
                                <button type="button" class="btn btn-primary btn-block btn-sm my-1" id="addmarcador">Adicionar novo marcador</button>
                                <div class="input-group" id="tempEntryMarcador"></div>
                            </div>
                            <button type="submit" class="btn btn-primary" name="option" value="processaDetalhesImagem">Salvar informações</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h4 class="">Imagem enviada:</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12"> <img class="img-fluid d-block" src="./assets/img/${imagem.url}">
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
        <script src="./assets/javascript/main.js"></script>
    </body>

</html>