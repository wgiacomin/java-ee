<%@page errorPage="/erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo cliente</title>
        <link rel="stylesheet" href="css/cadastro.css">
        <%@include file="utils/bootstrap.jsp"%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class="text-center">  
    <div class="container">
        <form method="post" action="CadastroServlet?action=alterar_senha" class="form-signin" name="form" id="form">
            <h1 class="mb-4 h-3 fw-normal ">Alterar Senha</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    ${msg}
                </span></br> 
            </c:if>
            <div class="form-floating">
                <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha" minlength="6" required>
                <label for="senha">Senha</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="senhaConfirm" placeholder="Confirme sua senha" name="senhaConfirm" minlength="6" required>
                <label for="senhaConfirm">Confirme sua senha</label>
            </div>
            <a href="#" class="w-100 btn btn-lg btn-primary mt-3" onClick="valida()">Salvar</a>
            <a class="w-100 btn btn-lg btn-secondary mt-3" href="HomeServlet">Voltar</a>
        </form>
    </div>
</body>
</html>

<script>
    function valida() {
        var senha = document.forms["form"]["senha"].value;
        var senhaC = document.forms["form"]["senhaConfirm"].value;

        if (senha.length < 7){
             alert("A senha deve possuir 6 ou mais caracteres.")
             return;
        }

        if (senha === senhaC) {
            $("#form").submit();
        } else {
            alert("As senhas não conferem.")
        }
    }
</script>
