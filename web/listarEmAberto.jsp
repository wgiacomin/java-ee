<%@include file="utils/header.jsp" %>

<div class="input-group mx-3 d-inline-flex w-auto">
    <label class="input-group-text" for="atendimentos">Atendimentos</label>
    <select class="form-select" id="atendimentos">
        <option value="1" selected >Somente Abertos</option>
        <option value="2">Todos</option>
    </select>
</div>

<br><br>
<div class="container">
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
            <c:forEach var="a" varStatus="i" items="${lista}">                        
                <tr>
                    <th scope="row"><c:out value="${i.count}"/></th>
                    <td><c:out value="${a.tipo}"/></td>
                    <td><c:out value="${a.aberto}"/></td>
                    <td><c:out value="${a.total}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>




<%@include file="utils/footer.jsp" %>