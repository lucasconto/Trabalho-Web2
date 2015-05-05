<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Cadastro de Categoria</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="../administrador/navAdministrador.jsp"/>
        <div class="container">
           <div class="row">
            <div class="col-md-3">
                <p class="lead">Magazine Store</p>
                <div class="list-group">
                    <a href="buscarCliente.jsp" class="list-group-item">Buscar Cliente</a>
                    <a href="../administrador/cadastrarProduto.jsp" class="list-group-item">Cadastrar Produto</a>
                    <a href="../administrador/cadastrarCategoria.jsp" class="list-group-item">Cadastrar Categoria</a>
                    <a href="../administrador/cadastrarEditora.jsp" class="list-group-item">Cadastrar Editora</a>
                </div>
            </div>
            <div class="col-md-9 ">   
            <h1>Clientes</h1>
            <div id="accordion" class="panel-group">
               <div class="panel panel-default">
                   <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Evandro João</a>
                            <!--<div class="pull-right">Evandro João</div>-->
                       </h4>
                   </div>
                   <div id="collapseOne" class="panel-collapse collapse">
                       <div class="panel-body">
                           <p>HTML stands for HyperText Markup Language. HTML is the main markup language for describing the structure of Web pages. <a href="http://www.tutorialrepublic.com/html-tutorial/" target="_blank">Learn more.</a></p>
                       </div>
                   </div>
               </div>
               <div class="panel panel-default">
                   <div class="panel-heading">
                       <h4 class="panel-title">
                           <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">2. What is Bootstrap?</a>
                       </h4>
                   </div>
                   <div id="collapseTwo" class="panel-collapse collapse in">
                       <div class="panel-body">
                           <p>Bootstrap is a powerful front-end framework for faster and easier web development. It is a collection of CSS and HTML conventions. <a href="http://www.tutorialrepublic.com/twitter-bootstrap-tutorial/" target="_blank">Learn more.</a></p>
                       </div>
                   </div>
               </div>
               <div class="panel panel-default">
                   <div class="panel-heading">
                       <h4 class="panel-title">
                           <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">3. What is CSS?</a>
                       </h4>
                   </div>
                   <div id="collapseThree" class="panel-collapse collapse">
                       <div class="panel-body">
                           <p>CSS stands for Cascading Style Sheet. CSS allows you to specify various style properties for a given HTML element such as colors, backgrounds, fonts etc. <a href="http://www.tutorialrepublic.com/css-tutorial/" target="_blank">Learn more.</a></p>
                       </div>
                   </div>
               </div>
           </div>

                
                

                <form class="form-horizontal">
                        <div class="form-group">
                        <div class="row">
                            <label class="col-md-2 control-label pull-left clearfix" for="">Buscar por:</label>
                        </div>
                           <div class="col-md-2">
                            <select class="form-control">
                                <option>Nome</option>
                                <option>CPF</option>
                                <option>Email</option>
                            </select>
                        </div>
                            <div class="col-md-7">
                                <input type="text" id="" class="form-control" placeholder="1234">
                            </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-success  btn-block ">Buscar</button>
                        </div>
                    </div>

                    </div>
                </form>
            </div>
        </div>
        </div>
        <!--Inclui Rodapé-->
        <jsp:include page="rodape.jsp"/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
    </body>
</html>