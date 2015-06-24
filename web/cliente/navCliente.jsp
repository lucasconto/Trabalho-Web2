<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="../images/logo.png"/></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">Serviços</a>
                    </li>
                    <li>
                        <a href="#">Contato</a>
                    </li>
                </ul>
                <ul class="nav pull-right navbar-nav">
                    <li class="dropdown" id="menuCliente">
                      <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navCliente">
                          <span class="glyphicon glyphicon-picture"></span> Razer Cliente<strong class="caret"></strong>
                      
                      </a>
                      
                      <div class="dropdown-menu pull-right" style="padding:5px;  min-width: 200px;">
                          <ul>
                              <a href="visualizarCompra.jsp">
                              <li>Minhas Compras</li>
                              </a>
                              <a href="../cliente/Clientes?action=alterarPerfil">
                              <li>Alterar Perfil</li>
                              </a>
                              <a href="../cliente/index.jsp">
                              <li>Logout</li>
                              </a>
                          </ul>
                      </div>
                    </li>
                    <li class="dropdown" id="menuLogin">
                      <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">
                          <span class="glyphicon glyphicon-shopping-cart"></span>
                          <c:choose>
                              <c:when test="${sessionScope.carrinho.getNumeroItens() == null || sessionScope.carrinho.getNumeroItens() == 0}">[Vazio]</c:when>
                              <c:when test="${sessionScope.carrinho.getNumeroItens() == 1}">[${sessionScope.carrinho.getNumeroItens()} Item]</c:when>
                              <c:otherwise>[${sessionScope.carrinho.getNumeroItens()} Itens]</c:otherwise>
                          </c:choose>
                          
                          <strong class="caret"></strong>
                      </a>
                      <div class="dropdown-menu pull-right" style="padding:5px; ">
                          <table class="table table-striped">
                                  
                              <tr>
                                <th>Item</th>
                                <th>Quantidade</th>
                                <th>Total</th>
                              </tr>
                              <c:forEach var="itemCarrinho" items="${sessionScope.carrinho.listaItens}">
                              <tr>
                                  <td><span class="glyphicon glyphicon-picture"> ${itemCarrinho.produto.titulo}</span></td>
                                  <td>${itemCarrinho.quantidade}</td>
                                  <td><fmt:formatNumber value="${itemCarrinho.valorUnitario}" minFractionDigits="2" type="currency"/></td>
                              </tr>
                              </c:forEach> 
                          </table>
                          <a href="./carrinhoCompras.jsp">
                          <button class="btn btn-default">Ver Carrinho</button>
                          </a>
                          <h4 class="pull-right">Total <fmt:formatNumber value="${sessionScope.carrinho.total}" minFractionDigits="2" type="currency"/></h4>
                      </div>
                    </li>

              </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

