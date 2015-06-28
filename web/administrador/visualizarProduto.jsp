<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Visualizar Produto</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            function valida(form) 
	{

		if (form.editora.value=="Selecione") 
		{
			alert("Escolha uma editora.");
			form.nome.focus();
			return false;
		}
                
                if (form.categoria.value=="Selecione") 
		{
			alert("Escolha uma categoria.");
			form.nome.focus();
			return false;
		}
		
    }
        </script>
    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="navAdministrador.jsp"/>
        <div class="container">
            <div class="row">
                <jsp:include page="menuAdministrador.jsp"/>
            <div class=" col-sm-6">
                <h1>Vizualizar Produto</h1>
                <form class="form-horizontal" id="form" method="POST" action="./Produtos?action=buscarp" onsubmit="//return valida(this);"  enctype="multipart/form-data">
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Título</label>
                        <div class="col-sm-8">
                            <input name="titulo" id="titulo" type="text" value = "${produto.titulo}" disabled ="disabled" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Autor</label>
                        <div class="col-sm-8">
                            <input name="autor" id="autor" value = "${produto.autor}" disabled ="disabled" type="text" class="form-control"  placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Editora</label>
                        <div class="col-sm-8">
                            <select name="editora" id="editora" class="form-control" disabled ="disabled">
                                <option value="${produto.editora.nome}"> ${produto.editora.nome}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Categoria</label>
                        <div class="col-sm-8">
                            <select name="genero" id="categoria" class="form-control" disabled = "disabled">
                                <option value="${produto.genero.idGenero}"> ${produto.genero.nome}</option>
                            </select>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">Preço</label>
                        <div class="col-sm-8">
                            <div class="input-group">
                            <span class="input-group-addon">R$</span>
                            <input name="preco" id="preco" value = "<fmt:formatNumber value="${produto.preco}" minFractionDigits="2" type="number"/>" disabled ="disabled" type="text" class="form-control" placeholder="">

                            
                            </div>
                        </div>
                    </div>                     
                    <div class="form-group">
                            <input type="hidden" value="${escolha}" name="escolha"/>
                            <input type="hidden" value="${str}" name="str"/>
                            <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit" class="btn btn-success btn-lg btn-block ">Voltar</button>
                    </div>

                </form>
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