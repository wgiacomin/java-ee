<%@include file="utils/header.jsp" %>
    <div class="container mb-3">
        <p>Atendimentos efetuados:</p>
        <span><c:out value="${total-aberto}"/></span>
    </div>
    <div class="container mb-3">
        <p>Atendimentos em aberto:</p>
        <span><c:out value="${aberto}"/></span>
    </div>
    <div class="container mb-3">
         <p>Porcentagem de atendimentos em aberto:</p>
         <span><c:out value="${(aberto/total)*100}%"/></span>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tipo Atendimento</th>
                <th scope="col">Aberto</th>
                <th scope="col">Total</th>
            </tr>
        </thead>
        <tbody>        
            <c:forEach var="a" varStatus="i" items="${lista}" >                        
                <tr>
                    <th scope="row">${i.count}</th>
                    <td><c:out value="${a.tipo}"/></td>
                    <td><c:out value="${a.aberto}"/></td>
                    <td><c:out value="${a.total}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="utils/footer.jsp" %>