<%-- 
    Document   : cadastroProduto
    Created on : Jul 26, 2021, 7:01:01 PM
    Author     : filipefreitas
--%>

<%@page errorPage="/erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo produto</title>
        <link rel="stylesheet" href="css/login.css">
        <%@include file="utils/bootstrap.jsp"%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script type="text/javascript" src="jquery.maskedinput-1.1.4.pack.js"/></script>
        <script src="js/validacaoCadastro.js"></script>
    </head>
<body class="text-center">  
    
    <div class="container">
        <form method="post" action="CategoriaProdutoServlet?action=novoProduto" class="form-signin">
            <h1 class="mb-4 h-3 fw-normal ">Entre novo produto:</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    ${msg}
                </span></br> 
            </c:if>
            <div class="form-floating">
                <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" required>
                <label for="descricao">Nome</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" id="descricao" placeholder="Descrição" name="descricao" required></textarea>
                <label for="descricao">Descrição</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="peso" placeholder="Peso" name="peso" required>
                <label for="descricao">Peso</label>
            </div>
            <div class="form-floating">
                <select class="form-control" id="categoria" placeholder="Categoria" name="categoria">
                    <c:forEach var="cat" items="${categoria}">
                        <option value="${cat.id}">${cat.descricao}</option>
                    </c:forEach>
                </select>
                <label for="categoria">Categoria</label>
            </div>

            <input type="submit" value="Cadastrar" class="w-100 btn btn-lg btn-primary mt-3" />
        </form>
        <div>
            <br>
        <a href="CategoriaProdutoServlet" class="btn btn-outline-danger">Voltar</a>
        </div>
    </div>
    
</body>
</html>


