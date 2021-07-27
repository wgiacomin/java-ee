<%-- 
    Document   : alterarCategoria
    Created on : Jul 26, 2021, 8:55:28 PM
    Author     : filipefreitas
--%>

    
<meta charset="utf-8"/>
<%@include file="utils/header.jsp" %>

    
    
    <div class="container">
        <form method="post" action="CategoriaProdutoServlet?action=alterarCategoria" class="form-signin">
            <h1 class="mb-4 h-3 fw-normal ">Categoria:</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    ${msg}
                </span></br> 
            </c:if>
            <div class="form-floating">
                <input type="text" class="form-control" id="id" placeholder="ID" name="id" value="${categoria.id}" readonly>
                <label for="descricao">ID</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="descricao" placeholder="Descri��o" name="descricao" value="${categoria.descricao}" required>
                <label for="descricao">Descri��o</label>
            </div>
            

            <input type="submit" value="Alterar" class="w-100 btn btn-lg btn-primary mt-3" />
        </form>
        <div>
            <br>
        <a href="CategoriaProdutoServlet" class="btn btn-outline-danger">Voltar</a>
        </div>
    </div>
    
<%@include file="utils/footer.jsp" %>
