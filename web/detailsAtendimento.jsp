<%@include file="utils/header.jsp" %>

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-2">
        <h1 class="display-5 fw-bold">
            Resolver Atendimento:
        </h1><br>
        <div class="container fs-4">
            <small><small>&emsp;&emsp;&emsp;&emsp;&nbsp;Cliente:&nbsp;&nbsp;</small></small> <c:out value="${cadastro.nome}"/><br>
            <small><small>Data de abertura:&nbsp;&nbsp;</small></small> <fmt:formatDate value="${atendimento.dataHora}" pattern="dd/MM/yyyy 'às' HH:mm"/><br>
            <small><small>&emsp;&emsp;&emsp;&emsp;Produto:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.produto.nome}"/><br>
            <small><small>&emsp;&emsp;&emsp;&nbsp;Descrição:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.descricao}"/><br><br>
        </div>
        <br><br>

        <c:choose>
            <c:when test="${sessionScope.logado.perfil.id == 1}">
                <div class="container fs-4">
                <small><small>&emsp;&emsp;&emsp;&nbsp;Solução:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.solucao}"/><br>
                <small><small>&emsp;&emsp;&emsp;&nbsp;Status:&nbsp;&nbsp;</small></small> <c:out value="${atendimento.status.descricao}"/><br><br>
                </div>
                    
                <div class=" gap-2 d-md-flex justify-content-md-end">
                    <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#remove_prod_${c.id}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                </svg>
                            </button>
                            <div class="modal fade" id="remove_prod_${c.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Atenção!</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body" style="text-align: left;">
                                            Tem certeza que deseja apagar o atendimento?
                                        </div>
                                        <div class="modal-footer">
                                            <a href="AtendimentoServlet?id=<c:out value="${atendimento.id}"/>&action=remove" class="btn btn-primary">Sim</a>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <a href="${sessionScope.logado.perfil.id == 1 ? "HomeServlet" : "AtendimentoServlet" }" class="btn btn-secondary">Voltar</a>
                </div>
            </c:when>
            <c:when test="${sessionScope.logado.perfil.id == 2}">
                <form action="AtendimentoServlet?action=solve" method="post">
                    <input value="<c:out value="${atendimento.id}"/>" hidden name="atendimento_id">
                    <p class="col-md-8 fs-4">
                        Solução:
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