<%@include file="utils/header.jsp" %>
<div class="container m-5">
    <c:set var="total" value="0"/> 
    <c:set var="aberto" value="0"/>

    <c:forEach var="a" varStatus="i" items="${show}">                        
        <c:set var="total" value="${total+a.total}"/> 
        <c:set var="aberto" value="${aberto+a.aberto}"/> 
    </c:forEach>

    <div class="container">
        <p>Atendimentos efetuados:</p>
        <input value="${total-aberto}">
    </div>
    <div class="container">
        <p>Atendimentos em aberto:</p>
        <input value="${aberto}">
        <input value="${aberto/total*100}">
    </div>

    <c:forEach var="a" varStatus="i" items="${show}">                        
        <div class="container">
            <p>${a.tipo}</p>
            <input value="${a.aberto}/${a.total}">
        </div>
    </c:forEach>
    <%@include file="utils/footer.jsp" %>