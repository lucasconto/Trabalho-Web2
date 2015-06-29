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
            <a class="navbar-brand" href="./Comuns"><img src="../images/logo.png"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav pull-right navbar-nav">
                <c:choose>
                    <c:when test="${sessionScope.cliente.getNome() == null}">
                        <li class="dropdown" id="menuLogin">
                            <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">Entrar<strong class="caret"></strong></a>

                            <div class="dropdown-menu pull-left" style="padding:20px; min-width: 250px;">
                                <form class="form" id="formLogin" method="post" action="../comum/Login?action=login">
                                    <div style="padding: 5px">
                                        <input name="email" class="form-control" id="username" type="text" placeholder="Email">
                                    </div>
                                    <div style="padding: 5px">
                                        <input name="senha" class="form-control" id="password" type="password" placeholder="Senha"><br>
                                    </div>
                                    <div style="padding: 5px">
                                        <button type="submit" id="btnLogin" class="btn" >Entrar</button>
                                    </div>
                                </form>
                            </div>
                        </li>                                  
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown" id="menuCliente">
                            <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navCliente">
                                <span class="glyphicon glyphicon-user "></span> ${sessionScope.cliente.getNome()} <strong class="caret"></strong>

                            </a>
                            <div class="dropdown-menu pull-right" style="padding:5px;  min-width: 200px;">
                                <ul>
                                    
                                    <li><a href="visualizarCompra.jsp">Minhas Compras</a></li>
                                    
                                    <li><a href="../cliente/Clientes?action=alterarPerfil">Alterar Perfil</a></li>
                                    
                                    <li><a href="../comum/Login?action=logout">Logout</a></li>
                                </ul>
                            </div>
                        </li>                                  
                    </c:otherwise>
                </c:choose>



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
                                    <td>${itemCarrinho.produto.titulo}</td>
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

