<meta charset="utf-8"/>
<%@include file="utils/header.jsp" %>    
    <link rel="stylesheet" href="css/cadastro.css">
    <div class="container">
        <form method="post" action="CategoriaProdutoServlet?action=alterarCategoria" class="form-signin">
            <h1 class="mb-4 h-3 fw-normal ">Categoria:</h1> 
            <c:if test="${msg != null}">
                <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                    ${msg}
                </span></br> 
            </c:if>
            <div class="form-floating">
                <input type="text" class="form-control" id="id" placeholder="ID" name="id" value="<c:out value="${categoria.id}"/>" readonly>
                <label for="descricao">ID</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="descricao" placeholder="Descrição" name="descricao" value="<c:out value="${categoria.descricao}"/>  " required>
                <label for="descricao">Descrição</label>
            </div>
            

            <input type="submit" value="Alterar" class="w-100 btn btn-lg btn-primary mt-3" />
        </form>
        <div>
            <br>
        <a href="CategoriaProdutoServlet" class="btn btn-outline-danger">Voltar</a>
        </div>
    </div>
    
<%@include file="utils/footer.jsp" %>
