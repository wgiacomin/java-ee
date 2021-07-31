<%@include file="utils/header.jsp" %>
<div class="container m-5">
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Data-Hora</th>
                <th scope="col">Status</th>
                <th scope="col">Tipo de Atendimento</th>
                <th scope="col">Produto</th>
                <th class="text-center" scope="col"></th>
            </tr>
        </thead>
        <tbody>        
            <c:forEach var="a" varStatus="i" items="${lista}" >                        
                <tr>
                    <th scope="row">${i.count}</th>
                    <td><fmt:formatDate value="${a.dataHora}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><c:out value="${a.status.descricao}"/></td>
                    <td><c:out value="${a.tipoAtendimento.descricao}"/></td>
                    <td><c:out value="${a.produto.nome}"/></td>
                    <td class="text-center">
                        <a href="AtendimentoServlet?id=<c:out value="${a.id}"/>&action=details" class="btn btn-primary"> 
                            <button type="button" class="btn btn-secondary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="utils/footer.jsp" %>