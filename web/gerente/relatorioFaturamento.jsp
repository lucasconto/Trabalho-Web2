<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Relatórios de Faturamento</title>
        
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
    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="navGerente.jsp"/>
                       
        <div class="container">
           <div class="row">
            <jsp:include page="menuGerente.jsp"/>
            <div class="col-md-9 ">   
            <h1>Relatórios de Faturamento</h1>
             <div class="row">
                 <form class="form" action="Gerentes?action=faturamentoMensal" method="post">
                     <div class="form-group">
                        <div class="col-md-3">
                            <strong>Faturamento Mensal:</strong>
                        </div>
                        <div class="col-md-2" style="padding: 10px">
                            Mês: <select name="mes" id="mes" class="form-control" required>
                                <option label="Selecione"></option>
                                <option value="01">Janeiro</option>
                                <option value="02">Fevereiro</option>
                                <option value="03">Março</option>
                                <option value="04">Abril</option>
                                <option value="05">Maio</option>
                                <option value="06">Junho</option>
                                <option value="07">Julho</option>
                                <option value="08">Agosto</option>
                                <option value="09">Setembro</option>
                                <option value="10">Outubro</option>
                                <option value="11">Novembro</option>
                                <option value="12">Dezembro</option>
                            </select>
                        </div>
                        <div class="col-sm-3" style="padding: 10px">
                            Ano: <input type="text"  maxlength="4" id="ano" name="ano" required/>
                        </div> 
                         <br/>
                        <div class="col-sm-offset-1 col-sm-3">
                            <button type="submit" class="btn btn-success btn-md btn-block ">Gerar Relatório</button>
                        </div>
                     </div>
                 </form>
                    </div>
            <div class="row">
                 <form class="form" action="Gerentes?action=faturamentoAnual" method="post">
                     <div class="form-group">
                        <div class="col-md-3">
                            <strong>Faturamento Anual:</strong>
                        </div>
                        <div class="col-sm-3" style="padding: 10px">
                            Ano: <input type="text"  maxlength="4" id="ano" name="ano" required/>
                        </div> 
                         <br/>
                        <div class="col-sm-offset-1 col-sm-3">
                            <button type="submit" class="btn btn-success btn-md btn-block ">Gerar Relatório</button>
                        </div>
                     </div>
                 </form>
                    </div>

            <br/>
                
           </div>
        </div>
        </div>
        <!--Inclui Rodapé-->
        <jsp:include page="../comum/rodape.jsp"/>
        
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
    </body>
</html>