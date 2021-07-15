<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty sessionScope.logado || empty sessionScope.logado.nome || sessionScope.logado.id == 0 || sessionScopre.logado.perfil == 0}">
    <c:set var="msg" scope="request" value="Usuário deve se autenticar para acessar o sistema."/>
    <jsp:forward page="/index.jsp" />
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <c:choose>
                    <c:when test="${sessionScope.logado.perfil.id == 1}">
                        <ul class="nav navbar-nav">
                            <li><a href="#">Novo Atendimento</a></li>
                            <li><a href="#">Meus Atendimentos</a></li>
                            <li><a href="#">Alterar Dados</a></li>
                        </ul>
                    </c:when>
                    <c:when test="${sessionScope.logado.perfil.id == 2}">
                        <ul class="nav navbar-nav">
                            <li><a href="#">Atendimento</a></li>
                            <li><a href="#">Cadastros</a></li>
                        </ul>
                    </c:when>
                    <c:when test="${sessionScope.logado.perfil.id == 3}">
                        <ul class="nav navbar-nav">
                            <li><a href="#">Atendimento</a></li>
                            <li><a href="#">Cadastrar Pessoas</a></li>
                            <li><a href="#">Relatórios</a></li>
                        </ul>
                    </c:when>
                </c:choose>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Sair</a></li>
                </ul>
            </div> 
        </nav>

