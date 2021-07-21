$(document).ready(function() {
    getCidades();
    $("#cep").mask("99.999-999");
    $("#cpf").mask("999.999.999-99");
    $("#nr").mask("9999");
    $("#telefone").mask("00000-0000");
    $("#uf").change(function() {
       getCidades();
    });
});