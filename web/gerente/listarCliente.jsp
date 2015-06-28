<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Cadastro de Categoria</title>

        <!--JQuery CSS-->
        <link rel="stylesheet" href="../js/jquery-ui.css">

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../css/shop-homepage.css" rel="stylesheet">

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery.min.js"></script>
        <script src="../js/jquery-ui.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>

            $(function () {

                var start = new Date();
                start.setFullYear(start.getFullYear() - 100);
                var end = new Date();
                end.setFullYear(end.getFullYear() - 13);

                $('#datepicker,#datepicker2').datepicker({
                    dateFormat: 'dd/mm/yy',
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                    nextText: 'Próximo',
                    prevText: 'Anterior',
                    changeMonth: true,
                    changeYear: true,
                    minDate: start,
                    maxDate: end,
                    yearRange: -100 + ':' + end.getFullYear(),
                });
            });

        </script>

    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="./navGerente.jsp"/>

        <div class="container">
            <div class="row">
                <jsp:include page="menuGerente.jsp"/>
                <div class="col-md-9 ">   
                    <h1>Clientes</h1>
                    <form action="./Gerentes?action=buscarc" method="post" class="form-horizontal">
                        <div class="form-group">
                            <div class="row">
                                <label class="col-md-2 control-label pull-left clearfix" >Buscar por:</label>
                            </div>
                            <div class="col-md-2">
                                <select name="escolha" class="form-control">
                                    <option value="nome" ${escolha == 'nome' ? 'selected' : ''}>Nome</option>
                                    <option value="cpf" ${escolha == 'cpf' ? 'selected' : ''}>CPF</option>
                                    <option value="email" ${escolha == 'email' ? 'selected' : ''}>Email</option>
                                </select>
                            </div>
                            <div class="col-md-7">
                                <input type="text" name="str" class="form-control" placeholder="1234" value="${str}">
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-success  btn-block ">Buscar</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Ação</th>
                        </tr>
                        <c:forEach var="cliente" items="${listaClientes}">
                            <tr>
                                <td>${cliente.nome}</td>
                                <td>${cliente.cpf}</td>
                            <td>
                                <div class="text-center">
                                    <form action="./Gerentes?action=visualizarCompras&id=${cliente.idCliente}" method="post" style="display: inline">
                                        <a href="#" onclick="this.parentNode.submit();">
                                            <span title="Visualizar Compras" class="glyphicon glyphicon-shopping-cart" style="font-size: 20px"></span>
                                            <input type="hidden" value="${escolha}" name="escolha"/>
                                            <input type="hidden" value="${str}" name="str"/>
                                        </a>
                                    </form>
                                    <form action="./Gerentes?action=visualizarAcoes&id=${cliente.idCliente}" method="post" style="display: inline">
                                        <a href="#" onclick="this.parentNode.submit();">
                                            <span title="Visualizar Ações" class="glyphicon glyphicon-list-alt" style="font-size: 20px"></span>
                                            <input type="hidden" value="${escolha}" name="escolha"/>
                                            <input type="hidden" value="${str}" name="str"/>
                                        </a>
                                    </form>
                                    <form action="./Gerentes?action=visualizarc&id=${cliente.idCliente}" method="post" style="display: inline">
                                        <a href="#" onclick="this.parentNode.submit();">
                                            <span title="Visualizar" class="glyphicon glyphicon-eye-open" style="font-size: 20px"></span>
                                            <input type="hidden" value="${escolha}" name="escolha"/>
                                            <input type="hidden" value="${str}" name="str"/>
                                        </a>
                                    </form>
                                    <form action="./Gerentes?action=valterarc&id=${cliente.idCliente}" method="post" style="display: inline">
                                        <a href="#" onclick="this.parentNode.submit();">
                                            <span title="Alterar" class="glyphicon glyphicon-pencil" style="font-size: 20px"></span>
                                            <input type="hidden" value="${escolha}" name="escolha"/>
                                            <input type="hidden" value="${str}" name="str"/>
                                        </a>
                                    </form>
                                        <!--<a href="#" onclick="this.parentNode.submit();">-->
                                        <a href="" data-toggle="modal" data-target="#exampleModal" data-escolha="${escolha}" data-str="${str}" data-idcliente="${cliente.idCliente}">
                                            <span title="Excluir" class="glyphicon glyphicon-trash" style="font-size: 20px"></span>
<!--                                            
                                            <input type="hidden" value="${escolha}" name="escolha"/>
                                            <input type="hidden" value="${usuario.perfil}" name="perfil"/>
                                            <input type="hidden" value="${str}" name="str"/>-->
                                        </a>
                                </div>
                            </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">Exclusao de Cliente</h4>
            </div>
            <div class="modal-body">
                <form action="Gerentes?action=excluirc" method="post">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="cliente-id" name="id"/>
                        <input type="hidden" class="form-control" id="escolha-id" name="escolha"/>
                        <input type="hidden" class="form-control" id="str-id" name="str"/>
                    </div>                            
                    <div class="form-group">
                        <label for="genero-nome" class="control-label">Tem certeza de que deseja excluir este usuário?</label>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Nao</button>
                        <button type="submit" class="btn btn-primary">Sim</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
            </div>
        </div>
        </div>
                            
        <!--Inclui Rodapé-->
        <jsp:include page="../comum/rodape.jsp"/>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        
        <script>
            $(function () {

                $('#exampleModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var idCliente = button.data('idcliente'); // Extract info from data-* attributes
                    var escolha = button.data('escolha'); // Extract info from data-* attributes
                    var str = button.data('str'); // Extract info from data-* attributes
                    var modal = $(this);
                    modal.find('#cliente-id').val(idCliente);
                    modal.find('#escolha-id').val(escolha);
                    modal.find('#str-id').val(str);
                })
            });
        </script>
    </body>
</html>
