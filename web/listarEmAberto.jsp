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
                <th scope="col">Data</th>
                <th scope="col">Tipo Atendimento</th>
                <th scope="col">Descrição</th>
                <th scope="col">Produto</th>
                <th scope="col">ver</th>
            </tr>
        </thead>
        <tbody>        
            <c:forEach var="a" varStatus="i" items="${atendimento}">                        
                <tr>
                    <th scope="row"><c:out value="${a.id}"/></th>
                    <c:if test=""></c:if>
                    <td><fmt:formatDate value="${a.dataHora}" pattern="dd/MM/yyyy 'às' HH:mm"/></td>
                    <td><c:out value="${a.tipoAtendimento.descricao}"/></td>
                    <td><c:out value="${a.descricao}"/></td>
                    <td><c:out value="${a.produto.nome}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>




<%@include file="utils/footer.jsp" %>