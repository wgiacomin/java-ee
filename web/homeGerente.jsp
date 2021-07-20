<%@include file="utils/header.jsp" %>
<div class="container m-5">

    <div class="container mb-2">
        <p>Atendimentos efetuados:</p>
        <span>${total-aberto}</span>
    </div>
    <div class="container mb-2">
        <p>Atendimentos em aberto:</p>
        <span>${aberto}</span>
    </div>
    <div class="container mb-2"
        <p>Porcentagem de atendimentos em aberto</p>
        <span>${(aberto/total)*100}%</span>
    </div>

    <c:forEach var="a" varStatus="i" items="${show}">                        
        <div class="container">
            <p>${a.tipo}</p>
            <span>${a.aberto}/${a.total}</span>
        </div>
    </c:forEach>
</div>
<%@include file="utils/footer.jsp" %>