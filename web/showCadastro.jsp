<%@include file="utils/header.jsp" %>
<link rel="stylesheet" href="css/cadastro.css">

<form method="post" action="GerenteServlet" class="form-signin">
<h1 class="mb-4 h-3 fw-normal ">Detalhes</h1> 
<div class="form-floating">   
    <input type="text" class="form-control" id="perfil" placeholder="perfil" value="<c:out value="${cadastro.perfil.descricao}"/>" disabled>
    <label for="perfil">Perfil</label> 
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="nome" placeholder="Nome" value="<c:out value="${cadastro.nome}"/>" disabled>
    <label for="nome">Nome</label>
</div>
    <div class="form-floating">
        <input type="email" class="form-control" id="email" placeholder="Email" value="<c:out value="${cadastro.email}"/>" disabled>
        <label for="email">Email</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" id="cpf" placeholder="CPF" value="<c:out value="${cadastro.cpfFormatado}"/>" disabled>
        <label for="cpf">CPF</label>
    </div>
<div class="form-floating">
    <input type="text" class="form-control" id="rua" placeholder="Rua" value="<c:out value="${cadastro.rua}"/>" disabled>
    <label for="rua">Rua</label>
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="nr" placeholder="Número da rua" value="<c:out value="${cadastro.ruaNumero}"/>" disabled>
    <label for="nr">Número da rua</label>
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="complemento" placeholder="Complemento" value="<c:out value="${cadastro.ruaComplemento}"/>"disabled>
    <label for="complemento">Complemento</label>
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="bairro" placeholder="Bairro" value="<c:out value="${cadastro.bairro}"/>" disabled>
    <label for="bairro">Bairro</label>
</div>
<div class="form-floating">
    <input type="text" class="form-control" id="cep" placeholder="CEP" value="<c:out value="${cadastro.cepFormatado}"/>" disabled>
    <label for="cep">CEP</label>
</div>

<div class="form-floating">
    <input type="text" class="form-control" id="telefone" placeholder="Telefone" value="<c:out value="${cadastro.telefoneFormatado}"/>" disabled>
    <label for="telefone">Telefone</label>
</div> 

<div class="form-floating">
    <input type="text" class="form-control" id="uf" placeholder="Estado" value="<c:out value="${cadastro.cidade.estado.nome}"/>" disabled>
    <label for="uf">Estado</label>
</div>

<div class="form-floating">
    <input type="text" class="form-control" id="cidade" placeholder="Cidade" value="<c:out value="${cadastro.cidade.nome}"/>" disabled>
    <label for="cidade">Cidade</label>
</div>
<input type="submit" value="Voltar" class="w-100 btn btn-lg btn-primary" />
</form>

<%@include file="utils/footer.jsp" %>