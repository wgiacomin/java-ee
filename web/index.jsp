<%@page errorPage="/erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
        <%@include file="utils/bootstrap.jsp"%>
    </head>
    <body class="text-center">  
        <div class="container">
            <form method="post" action="LoginServlet" class="form-signin">
                <h1 class="mb-4 h-3 fw-normal ">Login</h1> 
                <c:if test="${msg != null}">
                    <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                        <c:out value="${msg}"/>
                    </span></br> 
                </c:if>
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="Email" name="login">
                    <label for="floatingInput">Email</label>
                </div>
                <div class="form-floating mb-4">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Senha" name="senha">
                    <label for="floatingPassword">Senha</label>
                </div>
                
                <a href="CadastroServlet?action=formNovoCliente" id="cadastro" name="cadastro" class="">Cadastrar-se</a>
                
                <input type="submit" value="Login" class="w-100 btn btn-lg btn-primary mt-3" />
                
            </form>
        </div>
    </body>
</html>
