<%@page import="java.util.List"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty sessionScope.logado || empty sessionScope.logado.nome || sessionScope.logado.id == 0 || sessionScope.logado.perfil.id == 0}">
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script type="text/javascript" src="jquery.maskedinput-1.1.4.pack.js"/></script>
</head>
<body class="d-flex flex-column h-100">
    <header class="py-3 mb-3 border-bottom">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark mb-4">
            <div class="container-fluid d-grid" style="grid-template-columns: 1fr 1fr;">
                <div class="collapse navbar-collapse">
                    <c:choose>
                        <c:when test="${sessionScope.logado.perfil.id == 1}">
                            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                <li class="nav-item "><a href="HomeServlet" class="nav-link active mx-3">Home</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${sessionScope.logado.perfil.id == 2}">
                            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                <li class="nav-item "><a href="HomeServlet" class="nav-link active mx-3">Home</a></li>
                                <li class="nav-item"><a href="CategoriaProdutoServlet" class="nav-link active mx-3">Cadastrar Produto/Categoria</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${sessionScope.logado.perfil.id == 3}">
                            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                <li class="nav-item "><a href="HomeServlet" class="nav-link active mx-3">Home</a></li>
                                <li class="nav-item"><a href="ListagemServlet?action=only_open" class="nav-link active mx-3">Atendimentos</a></li>
                                <li class="nav-item"><a href="GerenteServlet" class="nav-link active mx-3">Gerentes/Funcionários</a></li>
                                <li class="nav-item"><a href="./relatorios.jsp" class="nav-link active mx-3">Relatórios</a></li>
                            </ul>
                        </c:when>
                    </c:choose>
                </div>
                <div class="d-flex align-items-center justify-content-md-end">     
                    <div class="flex-shrink-0 dropdown">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownuser" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="utils/logo.png" alt="mdo" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownuser" style="position: absolute; inset: 0px auto auto 0px; margin: 0px; transform: translate(-110px, 34px);">
                            <c:choose>
                                <c:when test="${sessionScope.logado.perfil.id == 1}">
                                    <a href="CadastroServlet?action=formAlterarCliente" class="dropdown-item">Alterar Dados</a>
                                    </c:when>
                                    <c:when test="${sessionScope.logado.perfil.id == 2}">
                                    <a href="CadastroServlet?action=formAlterarCliente" class="dropdown-item">Alterar Dados</a>
                                    </c:when>
                                </c:choose>
                            <li>
                                <a class="dropdown-item" href="./alterarSenha.jsp">Alterar Senha</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="LogoutServlet">Sair</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div> 
        </nav>
    </header>
    <main class="flex-shrink-0">
        <div class="p-4"></div>
        <div class="container my-4">
            <c:if test="${msg != null}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${msg}"/>
                </div>
            </c:if>


