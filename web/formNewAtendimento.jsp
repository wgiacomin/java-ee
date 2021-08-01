<%@include file="utils/header.jsp" %>
<link rel="stylesheet" href="css/cadastro.css">
<form method="post" action="AtendimentoServlet?action=new" class="form-signin">
    <h1 class="mb-4 h-3 fw-normal">Novo Atendimento</h1>
    <div class="form-floating">
        <input type="text" class="form-control" id="dataHora" name="dataHora" placeholder="Data Hora" value="<fmt:formatDate value="${dataHora}"  pattern="dd/MM/yyyy HH:mm:ss"/>" readonly required>
        <label for="dataHora">Data Hora</label>
    </div>
    <div class="form-floating">
        <select class="form-select" id="produto" placeholder="Produto" name="produto" required>
            <option disabled selected>Selecione</option>
            <c:forEach var="p" items="${produto}">
                <option value="${p.id}"> ${p.nome}</option>
            </c:forEach>
        </select>
        <label for="produto">Produto</label>        
    </div>
    <div class="form-floating">
        <select class="form-select" id="tA" placeholder="Tipo de Atendimento" name="tipoAtendimento" required>
            <option disabled selected>Selecione</option>
            <c:forEach var="tA" items="${tipoAtendimento}">
                <option value="${tA.id}">${tA.descricao}</option>
            </c:forEach>
        </select>
        <label for="tA">Tipo de Atendimento</label>
    </div>
    <div class="form-textarea">
        <textarea class="form-control" id="descricao" placeholder="Descrição" name="descricao" maxlength="2147483647" rows="5"required></textarea>
    </div> 
    <input type="submit" value="Enviar" class="w-100 btn btn-lg btn-outline-success"/>
    <a href="HomeServlet" class="btn w-100 btn btn-lg btn-danger">Cancelar</a>
</form>
<%@include file="utils/footer.jsp" %>