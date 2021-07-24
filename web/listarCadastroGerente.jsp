<%@include file="utils/header.jsp" %>
<div class="container my-2">
    <a href="#" class="btn btn-outline-success">Novo</a>
    <select class="form-select mx-3 d-inline w-auto" id="cadastros" name="cadastros" onchange="getCadastros()">
        <option selected>Selecionar Cadastros</option>
        <option value="1">Gerente e Funcionario</option>
        <option value="3">Gerente</option>
        <option value="2">Funcionario</option>
    </select></div>
<table class="table table-hoverS">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">CPF</th>
            <th scope="col">Email</th>
            <th class="text-center" scope="col">Visualizar</th>
            <th class="text-center"scope="col">Alterar</th>
            <th class="text-center" scope="col">Remover</th>
        </tr>
    </thead>
    <tbody>        
        <c:forEach var="c" varStatus="i" items="${lista}" >                        
            <tr>
                <th scope="row"><c:out value="${i.count}"/></th>
                <td><c:out value="${c.nome}"/></td>
                <td class="cpf"><c:out value="${c.cpf}"/></td>
                <td><c:out value="${c.email}"/></td>
                <td class="text-center">
                    <a href="GerenteServlet?id=${c.id}&action=show">
                        <button type="button" class="btn btn-secondary">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                            </svg>
                        </button>
                    </a>
                </td>
                <td class="text-center">
                    <a href="GerenteServlet?id=${c.id}&action=formUpdate">
                        <button type="button" class="btn btn-outline-primary">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                            </svg>
                        </button>
                    </a>
                </td>
                <td class="text-center">
                    <a href="GerenteServlet?id=${c.id}&action=remove" id="excluir">
                        <button type="button" class="btn btn-outline-danger">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script src="js/gerente.js"></script>
<script type="text/javascript">
    $('#excluir').click(function excluir(e) {
        var id = $("#excluir").attr("href").replace(/[^\d.]/g, '');
        id = parseInt(id);
        if (id !== ${sessionScope.logado.id}) {
             return confirm('Voc� deseja excluir este usu�rio?');
        } else {
            alert('Voc� n�o pode remover-se.');
            e.preventDefault();
            $('#excluir').empty();
            $('#excluir').replaceWith('<span class="text-secondary">Desabilitado</span>');
            return false;
        }
    });

</script>
<%@include file="utils/footer.jsp" %>