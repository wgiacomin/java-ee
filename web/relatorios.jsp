<%@include file="utils/header.jsp" %>

<div class="container">
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <div class="col">
            <div class="card h-100">
                <div class="card-header">Funcionários</div>
                <div class="card-body text-dark">
                    <p class="card-text">
                        Relatório contendo todos os funcionários cadastrados no sistema.
                    </p>
                    <a href="RelatoriosServlet?action=1" class="btn btn-primary">Gerar</a>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
                <div class="card-header">Produtos com Mais Reclamações</div>
                <div class="card-body text-dark">
                    <p class="card-text">
                        Relatório contendo os 3 produtos com mais reclamações.
                    </p>
                    <a href="RelatoriosServlet?action=2" class="btn btn-primary">Gerar</a>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
                <div class="card-header">Atendimentos por Data</div>
                <div class="card-body text-dark">
                    <p class="card-text">
                        Relatório contendo todos os atendimentos por data.
                    </p>
                    <form action="RelatoriosServlet?action=3" method="post">
                        <div class="form-floating">
                            <input type="date" class="form-control" required name="inicio">
                            <label for="inicio">Data de início:</label>
                        </div><br>
                        <div class="form-floating">
                            <input type="date" class="form-control" required name="fim">
                            <label for="fim">Data de fim</label>
                        </div><br>
                        <button class="btn btn-primary">Gerar</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">Reclamações por Status</div>
                <div class="card-body text-dark">
                    <p class="card-text">
                        Relatório contendo todas as reclamações.
                    </p>
                    <form action="RelatoriosServlet?action=4" method="post">
                        <div>
                            <select class="form-select" placeholder="Status" name="status" aria-label="Default select" id="status" required>
                                <option style="display:none"></option>
                                <c:forEach var="stat" items="${applicationScope.status}">
                                    <option value="<c:out value="${stat.id}"/>">
                                        <c:out value="${stat.descricao}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div><br>
                        <button class="btn btn-primary">Gerar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="utils/footer.jsp" %>