<%@include file="utils/header.jsp" %>

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-2">
        <h1 class="display-5 fw-bold">
            Resolver Atendimento:
        </h1><br>
        <div class="container fs-4">
            <small><small>&emsp;&emsp;&emsp;&emsp;&nbsp;Cliente:&nbsp;&nbsp;</small></small> <c:out value="${cadastro.nome}"/><br>
            <small><small>Data de abertura:&nbsp;&nbsp;</small></small> <fmt:formatDate value="${atendimento.dataHora}" pattern="dd/MM/yyyy '�s' HH:mm"/><br>
            <small><small>&emsp;&emsp;&emsp;&emsp;Produto:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.produto.nome}"/><br>
            <small><small>&emsp;&emsp;&emsp;&nbsp;Descri��o:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.descricao}"/><br><br>
        </div>
        <br><br>

        <c:choose>
            <c:when test="${sessionScope.logado.perfil.id == 2}">
                <form action="AtendimentoServlet?action=solve" method="post">
                    <input value="<c:out value="${atendimento.id}"/>" hidden name="atendimento_id">
                    <p class="col-md-8 fs-4">
                        Solu��o:
                    </p>
                    <div class="form-outline">
                        <textarea class="form-control" id="solucao" name="solucao" rows="4" placeholder="Digite aqui..." minlength="10" required></textarea>
                    </div><br>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <a href="ListagemServlet?action=<c:out value="${page}"/>" class="btn btn-secondary">Voltar</a>
                        <button class="btn btn-success me-md-2">Solucionar</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <a href="ListagemServlet?action=<c:out value="${page}"/>" class="btn btn-secondary">Voltar</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>




<%@include file="utils/footer.jsp" %>