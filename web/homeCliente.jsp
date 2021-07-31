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
                                Detalhes
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@include file="utils/footer.jsp" %>