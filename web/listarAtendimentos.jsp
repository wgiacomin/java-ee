<%@include file="utils/header.jsp" %>

<div class="input-group mx-3 d-inline-flex w-auto">
    <label class="input-group-text" for="atendimentos">Atendimentos</label>
    <select class="form-select" id="atendimentos">
        <option value="only_open" <c:if test="${page == 'only_open'}">selected</c:if>>Somente Abertos</option>
        <option value="all" <c:if test="${page == 'all'}">selected</c:if> >Todos</option>
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
                </tr>
            </thead>
            <tbody>        
            <c:forEach var="a" varStatus="i" items="${atendimento}">     
                <tr  class="
                     <c:choose>
                         <c:when test="${a.clr == 2}">table-danger</c:when>
                         <c:when test="${a.clr == 1}">table-warning</c:when>
                     </c:choose>
                     "
                     <c:if test="${a.clr != -1}">
                         style="cursor:pointer;"
                         onclick="window.location = 'AtendimentoServlet?atendimento_id=<c:out value="${a.id}"/>&action=details&page=<c:out value="${page}"/>'"
                     </c:if>
                     >
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
<script>
    $('#atendimentos').on('change', function (e) {
        var optionSelected = $("option:selected", this)
        var valueSelected = this.value
        $(location).attr('href', 'ListagemServlet?action=' + valueSelected)
    });
</script>
