<%@page errorPage="/erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo cliente</title>
        <link rel="stylesheet" href="css/login.css">
        <%@include file="utils/bootstrap.jsp"%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script type="text/javascript" src="jquery.maskedinput-1.1.4.pack.js"/></script>
        <script src="js/validacaoCadastro.js"></script>
    </head>
    <body class="text-center">  
        <div class="container">
            <form method="post" action="CadastroServlet?action=novoCliente" class="form-signin">
                <h1 class="mb-4 h-3 fw-normal ">Login</h1> 
                <c:if test="${msg != null}">
                    <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                           ${msg}
                    </span></br> 
                </c:if>
                <div class="form-floating">
                    <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="${cadastro.nome}" required>
                    <label for="nome">Nome</label>
                </div>
                <div class="form-floating">
                    <input type="email" class="form-control" id="email" placeholder="Email" name="email" value="${cadastro.email}" required>
                    <label for="email">Email</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha" value="${cadastro.senha}" required>
                    <label for="senha">Senha</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="senhaConfirm" placeholder="Confirme sua senha" name="senhaConfirm" value="${cadastro.senha}" required>
                    <label for="senhaConfirm">Confirme sua senha</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="cpf" placeholder="CPF" name="cpf" value="${cadastro.cpf}">
                    <label for="cpf">CPF</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="rua" placeholder="Rua" name="rua" value="${cadastro.rua}">
                    <label for="rua">Rua</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="nr" placeholder="Número da rua" name="nr" value="${cadastro.ruaNumero}">
                    <label for="nr">Número da rua</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="complemento" placeholder="Complemento" name="complemento" value="${cadastro.ruaComplemento}">
                    <label for="complemento">Complemento</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="bairro" placeholder="Bairro" name="bairro" value="${cadastro.bairro}">
                    <label for="bairro">Bairro</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="cep" placeholder="CEP" name="cep" value="${cadastro.cep}">
                    <label for="cep">CEP</label>
                </div>
                    
                <div class="form-floating">
                    <input type="text" class="form-control" id="telefone" placeholder="Telefone" name="telefone" value="${cadastro.telefone}">
                    <label for="telefone">Telefone</label>
                </div> 
                    
                <div class="form-floating">
                    <select class="form-control" id="uf" placeholder="Estado" name="uf">
                        <c:forEach var="estado" items="${estados}">
                            <option value="${estado.id}"
                                <c:if test="${estado.id == cadastro.cidade.estado.id}">
                                  selected
                                </c:if>
                            >${estado.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-floating">
                    <select class="form-control" id="cidade" placeholder="Cidade" name="cidade">
                    </select>
                </div>
                
                <input type="hidden" id="perfil" value="1" name="perfil">
                
                <input type="submit" value="Cadastrar" class="w-100 btn btn-lg btn-primary mt-3" />
            </form>
        </div>
    </body>
</html>
