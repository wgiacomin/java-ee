<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <%@include file="utils/bootstrap.jsp"%>
    </head>
    <body class="text-center d-flex flex-column h-100">                
        <div class="align-self-center container-ms mt-5 w-50">
            <h2 class="alert alert-danger mt-3 d-sm-inline-flex">${pageContext.exception.message}</h2> </br>
            <small class=" w-75 text-muted">${pageContext.out.flush()}</small>
            <small class=" w-50 text-muted">${pageContext.exception.printStackTrace(pageContext.response.writer)}</small></br>
            <a class="btn btn-primary d-sm-inline-flex m-5" href="${page}"> Voltar</a>
        </div>        
    </body>
</html>
