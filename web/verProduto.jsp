<%-- 
    Document   : verProduto
    Created on : Jul 26, 2021, 9:59:14 PM
    Author     : filipefreitas
--%>
<meta charset="utf-8"/>
<%@include file="utils/header.jsp" %>
    
    <div class="container">
        <form method="post" class="form-signin">
            <h1 class="mb-4 h-3 fw-normal ">Produto:</h1> 
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
                <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="<c:out value="${produto.nome}"/>  "readonly>
                <label for="descricao">Nome</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" id="descricao" placeholder="Descri??o" name="descricao" required><c:out value="${produto.descricao}"/>  </textarea>
                <label for="descricao">Descri??o</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="peso" placeholder="Peso" name="peso" value="<c:out value="${produto.peso}"/>  " readonly>
                <label for="descricao">Peso</label>
            </div>
            <div class="form-floating">
                <select class="form-control" id="categoria" placeholder="Categoria" name="categoria" readonly>
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

        </form>
        <div>
            <br>
        <a href="CategoriaProdutoServlet" class="btn btn-outline-danger">Voltar</a>
        </div>
    </div>
    
<%@include file="utils/footer.jsp" %>

