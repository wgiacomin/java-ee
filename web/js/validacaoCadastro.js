$(document).ready(function() {
    getCidades();
    $("#cep").mask("99.999-999");
    $("#cpf").mask("999.999.999-99");
    $("#nr").mask("9999");
    $("#telefone").mask("(00)0 0000-0000");
    $("#uf").change(function() {
       getCidades();
    });
});



function getCidades(alterar = - 1) {
    var idEstado = $("#uf").val();
    if (idEstado) {
        var url = "CadastroAJAXServlet";
    
        $.ajax({
            url: url,
           data: {
              idEstado: idEstado
            },
            dataType: 'json',
            success: function (data) {
                $("#cidade").empty();
                $.each(data, function (i, obj) {
                  if (obj.id == alterar) {
                    $("#cidade").append('<option value=' + obj.id + ' selected>' + obj.nome + '</option>');
                  } else {
                    $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
                  }
                });
            },
            error: function (request, textStatus, errorThrown) {
                alert(request.status + ', Error: ' + request.statusText);
            }
        });
    }
}