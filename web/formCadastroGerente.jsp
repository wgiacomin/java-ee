<%@include file="utils/header.jsp" %>
<script src="js/validacaoCadastro.js"></script>
<link rel="stylesheet" href="css/cadastro.css">
<c:choose>
    <c:when test="${requestScope.action == 'formUpdate' and cadastro.id == sessionScope.logado.id}">
        <form method="post" action="GerenteServlet?action=alterar&id=${cadastro.id}&perfil=${cadastro.perfil.id}" class="form-signin">
        </c:when>
        <c:when test="${requestScope.action == 'formUpdate'}">
            <form method="post" action="GerenteServlet?action=alterar&id=${cadastro.id}" class="form-signin">
            </c:when>
            <c:otherwise>
                <form method="post" action="GerenteServlet?action=novo" class="form-signin">
                </c:otherwise>
            </c:choose>

            <h1 class="mb-4 h-3 fw-normal ">${requestScope.action == 'formNew'? 'Novo Funcionário/Gerente':'Alterar'}</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    <c:out value="${msg}"/>
                </span></br> 
            </c:if>
            <div class="form-floating">
                <c:choose>
                    <c:when test="${cadastro.id == sessionScope.logado.id}">            
                        <input type="text" class="form-control" id="perfil" placeholder="perfil" value="<c:out value="${cadastro.perfil.descricao}"/>" disabled>
                        <label for="perfil">Perfil</label>
                    </c:when>
                    <c:otherwise>
                        <select class="form-select" id="perfil" name="perfil" placeholder="Perfil">
                            <option class="text-muted" selected>Selecione</option>
                            <option value="2" ${cadastro.perfil.id == 2 ? 'selected':''}>Funcionário</option>
                            <option value="3" ${cadastro.perfil.id == 3 ? 'selected':''}>Gerente</option>
                        </select>
                        <label for="perfil">Perfil</label>
                    </c:otherwise>
                </c:choose>    
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="<c:out value="${cadastro.nome}"/>" required>
                <label for="nome">Nome</label>
            </div>
            <c:if test="${requestScope.action == 'formNew'}">
                <div class="form-floating">
                    <input type="email" class="form-control" id="email" placeholder="Email" name="email" value="<c:out value="${cadastro.email}"/>" required>
                    <label for="email">Email</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="cpf" placeholder="CPF" name="cpf" value="<c:out value="${cadastro.cpfFormatado}"/>" required>
                    <label for="cpf">CPF</label>
                </div>
            </c:if>
            <div class="form-floating">
                <input type="text" class="form-control" id="rua" placeholder="Rua" name="rua" value="<c:out value="${cadastro.rua}"/>" required>
                <label for="rua">Rua</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="nr" placeholder="Número da rua" name="nr" value="<c:out value="${cadastro.ruaNumero}"/>" required>
                <label for="nr">Número da rua</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="complemento" placeholder="Complemento" name="complemento" value="<c:out value="${cadastro.ruaComplemento}"/>">
                <label for="complemento">Complemento</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="bairro" placeholder="Bairro" name="bairro" value="<c:out value="${cadastro.bairro}"/>" required>
                <label for="bairro">Bairro</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="cep" placeholder="CEP" name="cep" value="<c:out value="${cadastro.cepFormatado}"/>" required>
                <label for="cep">CEP</label>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" id="telefone" placeholder="Telefone" name="telefone" value="<c:out value="${cadastro.telefoneFormatado}"/>" required>
                <label for="telefone">Telefone</label>
            </div> 

            <div class="form-floating">
                <select class="form-select" id="uf" placeholder="Estado" name="uf">
                    <c:forEach var="estado" items="${estados}">
                        <option value="<c:out value="${estado.id}"/>"
                                <c:if test="${estado.id == cadastro.cidade.estado.id}">
                                    selected
                                </c:if>
                                ><c:out value="${estado.nome}"/></option>
                    </c:forEach>
                </select>
                <label for="uf">Estado</label>
            </div>

            <div class="form-floating">
                <select class="form-select" id="cidade" placeholder="Cidade" name="cidade">        
                    <c:if test="${requestScope.action == 'formUpdate'}">
                        <option value="${cadastro.cidade.id}">${cadastro.cidade.nome}</option>
                    </c:if>
                </select>
                <label for="cidade">Cidade</label>
            </div>

            <input type="submit" value="${requestScope.action == 'formUpdate' ? 'Alterar' : 'Cadastrar'}" class="w-100 btn btn-lg btn-outline-success" />
            <a href="GerenteServlet?action=listar" class="btn w-100 btn btn-lg btn-danger">Cancelar</a>
        </form>

        <%@include file="utils/footer.jsp" %>