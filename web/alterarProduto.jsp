<meta charset="utf-8"/>
<%@include file="utils/header.jsp" %>    
    <link rel="stylesheet" href="css/cadastro.css">
    <div class="container">
        <form method="post" action="CategoriaProdutoServlet?action=alterarProduto" class="form-signin">
            <h1 class="mb-4 h-3 fw-normal ">Entre novo produto:</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    ${msg}
                </span></br> 
            </c:if>
              <div class="form-floating">
                  <input type="text" class="form-control" id="id" placeholder="ID" name="id" value="<c:out value="${produto.id}"/>  " readonly>
                <label for="id">ID</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="<c:out value="${produto.nome}"/>  "required>
                <label for="descricao">Nome</label>
            </div>
            <div class="form-textarea">
                <textarea class="form-control" id="descricao" placeholder="Descrição" name="descricao" rows="5" required><c:out value="${produto.descricao}"/>  </textarea>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="peso" placeholder="Peso" name="peso" value="<c:out value="${produto.peso}"/>  " required>
                <label for="descricao">Peso</label>
            </div>
            <div class="form-floating">
                <select class="form-select" id="categoria" placeholder="Categoria" name="categoria" required>
                    <c:forEach var="cat" items="${categoria}">
                        <option value="${cat.id}" 
                        <c:if test="${cat.id == produto.produtoCategoria.id}">
                                        selected
                        </c:if>
                        ><c:out value="${cat.descricao}"/>  </option>
                    </c:forEach>
                </select>
                <label for="categoria">Categoria</label>
            </div>

            <input type="submit" value="Alterar" class="w-100 btn btn-lg btn-primary mt-3" />
            <a href="CategoriaProdutoServlet" class="w-100 btn btn-lg btn-outline-danger mt-3">Voltar</a>
        </form>
    </div>
    
    
<%@include file="utils/footer.jsp" %>
