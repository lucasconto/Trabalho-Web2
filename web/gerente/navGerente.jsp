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
            <a class="navbar-brand" href="index.jsp"><img style="width: 260px" src="../images/logo.png"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav pull-right navbar-nav">
                <li class="dropdown" id="menuCliente">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navCliente">
                        <span class="glyphicon glyphicon-picture"></span> ${sessionScope.cliente.getNome()}<strong class="caret"></strong>

                    </a>

                    <div class="dropdown-menu pull-right" style="padding:5px;  min-width: 200px;">
                        <ul>
                            <a href="../comum/Login?action=logout">
                                <li>Logout</li>
                            </a>
                        </ul>
                    </div>
                </li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

