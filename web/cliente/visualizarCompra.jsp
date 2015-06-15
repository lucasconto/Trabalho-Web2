<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Magazine Store</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="../css/shop-homepage.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="navCliente.jsp"/>
        <div class="container">
           <div class="row">
            <jsp:include page="menuCliente.jsp"/>
            <div class="col-md-9 ">   
            <h1>Compras</h1>
            <div id="accordion" class="panel-group">
               <div class="panel panel-default">
                   <div class="panel-heading" style="font-size: 20px">
                            <strong>Compras em Aberto</strong>
                   </div>
                       <div class="panel-body">
                           <table class="table table-striped table-bordered">
                               <tr>
                                   <th>Número da Compra #</th>
                                   <th>Data</th>
                                   <th>Valor Total</th>
                                   <th>Situação</th>
                                   <th>Marcar como entregue</th>
                               </tr>
                               <c:forEach var="pedido" items="${listaPedidosAbertos}">
                                   <td colspan="5">
                                  <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${pedido.idPedido}">
                                            <td>${pedido.idPedido}</td>
                                            <td><fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                            <td><fmt:formatNumber value="${pedido.valorTotal}" minFractionDigits="2" type="currency"/>  </td>
                                            <td>${pedido.statusPedido.descricao}</td>
                                        </a>
                                    </h4>
                                </div>
                                  </td>
                                <div id="collapse${pedido.idPedido}" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <table class="table table-striped table-bordered">
                                            <tr>
                                                <th>Item #</th>
                                                <th>Título</th>
                                                <th>Quantidade</th>
                                                <th>Valor Unitário</th>
                                            </tr>
                                            <c:forEach var="itemPedido" items="${pedido.itens}">
                                            <tr>
                                                <td>${itemPedido.idItemPedido}</td>
                                                <td>${itemPedido.produto.titulo}</td>
                                                <td>${itemPedido.quantidade}</td>
                                                <td><fmt:formatNumber value="${itemPedido.valorUnitario}" minFractionDigits="2" type="currency"/> </td>
                                             
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div> 
<!--                               <tr>
                                   <td>${pedido.idPedido}</td>
                                   <td><fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                   <td><fmt:formatNumber value="${pedido.valorTotal}" minFractionDigits="2" type="currency"/></td>
                                   <td>${pedido.statusPedido.descricao}</td>
                                   <td><a href="#">Marcar como entregue.</a></td>
                                </tr>-->
                               </c:forEach>
                           </table>
                       </div>
                   </div>
               </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="font-size: 20px">
                        <strong>Compras Finalizadas</strong>
                   </div>
                       <div class="panel-body">
                           <table class="table table-striped table-bordered">
                               <tr>
                                   <th>Número da Compra #</th>
                                   <th>Data</th>
                                   <th>Valor Total</th>
                                   <th>Situação</th>
                               </tr>
                               <tr>
                                   <c:forEach var="pedido" items="${listaPedidosFinalizados}">
                                       
                                   <td>${pedido.idPedido}</td>
                                   <td><fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                   <td><fmt:formatNumber value="${pedido.valorTotal}" minFractionDigits="2" type="currency"/></td>
                                   <td>${pedido.statusPedido.descricao}</td>
                                </tr>
                                   </c:forEach>
                           </table>
                       </div>
                   </div>
           </div>
            </div>
        </div>
        </div>
        <!--Inclui Rodapé-->
        <jsp:include page="../comum/rodape.jsp"/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
    </body>
</html>