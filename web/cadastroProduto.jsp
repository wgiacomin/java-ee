<%-- 
    Document   : cadastroProduto
    Created on : Jul 26, 2021, 7:01:01 PM
    Author     : filipefreitas
--%>
    
<meta charset="utf-8"/>
<%@include file="utils/header.jsp" %>

    
    
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
                <label for="descricao">Peso (g)</label>
            </div>
            <div class="form-floating">
                <select class="form-control" id="categoria" placeholder="Categoria" name="categoria">
                    <c:forEach var="cat" items="${categoria}">
                        <option value="${cat.id}"><c:out value="${cat.descricao}"/>  </option>
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
    
    
<%@include file="utils/footer.jsp" %>



