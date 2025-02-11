<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Cadastro de Cliente</title>

        <!--JQuery CSS-->
        <link rel="stylesheet" href="../js/jquery-ui.css">

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" />

        <!-- Custom CSS -->
        <link href="../css/shop-homepage.css" rel="stylesheet" />

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
            function formatar(src, mask) {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i);
                if (texto.substring(0, 1) != saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }

            $(function () {

                var start = new Date();
                start.setFullYear(start.getFullYear() - 100);
                var end = new Date();
                end.setFullYear(end.getFullYear() - 13);

                $('#datepicker').datepicker({
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
                    yearRange: -100 + ':' + end.getFullYear()
                });
            });

            function valida(form)
            {

                if (form.sexo.value == "Selecione")
                {
                    alert("Escolha um sexo.");
                    form.nome.focus();
                    return false;
                }

            }
            function verificarCPF(cpf)
            {
                c = cpf.value;
                var i;
                c = c.replace(".", "");
                c = c.replace(".", "");
                c = c.replace("-", "");
                s = c;
                var c = s.substr(0, 9);
                var dv = s.substr(9, 2);
                var d1 = 0;
                var v = false;

                for (i = 0; i < 9; i++)
                {
                    d1 += c.charAt(i) * (10 - i);
                }
                if (d1 == 0)
                {
                    alert("CPF Inválido");
                    v = true;
                    cpf.blur();
                    return false;
                }
                d1 = 11 - (d1 % 11);
                if (d1 > 9)
                    d1 = 0;
                if (dv.charAt(0) != d1)
                {
                    alert("CPF Inválido");
                    v = true;
                    cpf.blur();
                    return false;
                }

                d1 *= 2;
                for (i = 0; i < 9; i++)
                {
                    d1 += c.charAt(i) * (11 - i);
                }
                d1 = 11 - (d1 % 11);
                if (d1 > 9)
                    d1 = 0;
                if (dv.charAt(1) != d1)
                {
                    alert("CPF Inválido");
                    v = true;
                    cpf.blur();
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <!--Inclui cabeçalho-->
        <jsp:include page="../comum/navAnonimo.jsp"/>

        <div class="container">
            <div class=" col-sm-offset-3 col-sm-6">
                <h1>Dados Cadastrais</h1>
                <form class="form-horizontal" id="form" method="POST" action="Clientes?action=cadastrar" onsubmit="return valida(this);">
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Nome Completo</label>
                        <div class="col-sm-8">
                            <input type="text" name="nome" class="form-control" id="" placeholder="" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Sexo</label>
                        <div class="col-sm-8">
                            <select name="sexo" id="sexo" class="form-control">
                                <option >Selecione</option>
                                <option value="m" required>Masculino</option>
                                <option value="f" required>Feminino</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" id="cpf" class="col-sm-4 control-label">CPF</label>
                        <div class="col-sm-8">
                            <input type="text" name="cpf" class="form-control" maxlength="14" id="cpf" placeholder="000.000.000-00" OnKeyPress="formatar(this, '###.###.###-##')"  onblur="verificarCPF(this);" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Data de Nascimento</label>
                        <div class="col-sm-8">
                            <input type="text" name="nascimento" class="form-control" maxlength="10" id="datepicker" placeholder="dd/mm/aaa" OnKeyPress="formatar(this, '##/##/####')" readonly="readonly" style="background: white;" required/>
                        </div>
                    </div>      
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Telefone</label>
                        <div class="col-sm-8">
                            <input type="text" name="telefone" class="form-control" maxlength="14" id="" placeholder="DDD-0000-00000" OnKeyPress="formatar(this, '###-####-#####')" required/>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Email</label>
                        <div class="col-sm-8">
                            <input type="email" name="email" class="form-control"  id="" placeholder="seuemail@dominio.com"  required/>
                        </div>
                    </div>             
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Senha</label>
                        <div class="col-sm-8">
                            <input type="password" name="senha" class="form-control" id="" placeholder="" required/>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Confirme sua Senha</label>
                        <div class="col-sm-8">
                            <input type="password" name="confirmaSenha" class="form-control" id="" placeholder="" required/>
                        </div>
                    </div>       

                    <h2>Endereço de Entrega</h2>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">CEP</label>
                        <div class="col-sm-8">
                            <input type="text" name="cep" class="form-control" maxlength="9" id="" placeholder="00000-000" OnKeyPress="formatar(this, '#####-###')" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Endereço</label>
                        <div class="col-sm-8">
                            <input type="text" name="endereco" class="form-control" id="" placeholder="" required />
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Número</label>
                        <div class="col-sm-8">
                            <input type="text" name="numero" class="form-control" id="" placeholder="" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Complemento</label>
                        <div class="col-sm-8">
                            <input type="text" name="complemento" class="form-control" id="" placeholder="" required />
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Bairro</label>
                        <div class="col-sm-8">
                            <input type="text" name="bairro" class="form-control" id="" placeholder="" required />
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Cidade</label>
                        <div class="col-sm-8">
                            <input type="text" name="cidade" class="form-control" id="" placeholder="" required />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-4 control-label">Estado</label>
                        <div class="col-sm-8">
                            <input type="text" name="estado" class="form-control" id="" placeholder="" required />
                        </div>
                    </div> 
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit" class="btn btn-success btn-lg btn-block ">Cadastrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!--Inclui Rodapé-->
        <jsp:include page="../comum/rodape.jsp"/>    


        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
    </body>
</html>