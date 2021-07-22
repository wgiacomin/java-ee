<%@page import="java.util.List"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty sessionScope.logado || empty sessionScope.logado.nome || sessionScope.logado.id == 0 || sessionScopre.logado.perfil.id == 0}">
    <c:set var="msg" scope="request" value="Usuário deve se autenticar para acessar o sistema."/>
    <jsp:forward page="index.jsp" />
</c:if>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body class="d-flex flex-column h-100">
        <header>
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark mb-4">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <c:choose>
                            <c:when test="${sessionScope.logado.perfil.id == 1}">
                                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                    <li class="nav-item "><a href="#" class="nav-link active mx-3">Novo Atendimento</a></li>
                                    <li class="nav-item"><a href="HomeServlet" class="nav-link active mx-3">Meus Atendimentos</a></li>
                                    <li class="nav-item"><a href="CadastroServlet?action=formAlterarCliente" class="nav-link active mx-3">Alterar Dados</a></li>
                                </ul>
                            </c:when>
                            <c:when test="${sessionScope.logado.perfil.id == 2}">
                                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                    <li class="nav-item"><a href="HomeServlet" class="nav-link active mx-3">Atendimento</a></li>
                                    <li class="nav-item"><a href="#" class="nav-link active mx-3">Cadastros</a></li>
                                </ul>
                            </c:when>
                            <c:when test="${sessionScope.logado.perfil.id == 3}">
                                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                    <li class="nav-item"><a href="HomeServlet" class="nav-link active mx-3">Atendimentos</a></li>
                                    <li class="nav-item"><a href="GerenteServlet" class="nav-link active mx-3">Cadastros</a></li>
                                    <li class="nav-item"><a href="#" class="nav-link active mx-3">Relatórios</a></li>
                                </ul>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="d-flex">
                        <a href="LogoutServlet"><button class="btn btn-outline-danger">Sair</button></a>
                    </div>
                </div> 
            </nav>
        </header>
        <main class="flex-shrink-0">
            <div class="p-4"></div>
            <div class="container my-5">


