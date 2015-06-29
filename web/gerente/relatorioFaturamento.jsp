<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <script type="text/javascript">
            $(function () {
                $('.date-picker').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'MM yy',
                    onClose: function (dateText, inst) {
                        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                        $(this).datepicker('setDate', new Date(year, month, 1));
                    }
                });
            });
        </script>
        <style>
            .ui-datepicker-calendar {
                display: none;
            }
        </style>
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
        <c:if test="${sessionScope.logado eq null }">
            <jsp:forward page="/comum/login.jsp" />
        </c:if>
            <c:if test="${sessionScope.cliente.getPerfil()  < 3}">
            perfil negado
            <jsp:forward page="semPermissao.jsp" />
        </c:if>
        
        <div class="container">
            <div class="row">
                <jsp:include page="menuGerente.jsp"/>
                <div class="col-md-9">   
                    <h1>Relatórios de Faturamento</h1>
                    <div class="row">
                        <form class="form-inline" action="Gerentes?action=faturamentoMensal" method="post">
                            <div class="form-group">
                                <div class="col-md-12" style="padding-right: 0px">
                                    <h3>Faturamento Mensal:</h3>
                                </div>
                            </div>
                            <br/>
                            <div class="form-group">
                                <label class="control-label col-md-3">Mês:</label> 
                                <div class="col-md-9">
                                    <select name="mes" id="mes" class="form-control" required>
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
                            </div>
                            <jsp:useBean id="now" class="java.util.Date" />
                            <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
                            <div class="form-group">
                                <label class="control-label col-md-2">Ano: </label> 
                                <div class="col-md-4">
                                    <select name="ano" id="mes" class="form-control" required>
                                        <option value="">Selecione</option>
                                        <c:forEach var="i" begin="2012" end="${year}">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-5">
                                    <button type="submit" class="btn btn-success btn-md btn-block ">Gerar Relatório</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <br/>
                    <div class="row">
                        <form class="form-inline" action="Gerentes?action=faturamentoAnual" method="post">
                            <div class="form-group">
                                <div class="col-md-12" >
                                    <h3>Faturamento Anual:</h3>
                                </div>
                            </div>
                            <br/>
                            <div class="form-group">
                                <label class="control-label col-md-2">Ano: </label> 
                                <div class="col-md-4">
                                    <select name="ano" id="mes" class="form-control" required>
                                        <option value="">Selecione</option>
                                        <c:forEach var="i" begin="2012" end="${year}">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6">
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