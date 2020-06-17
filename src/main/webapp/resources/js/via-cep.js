function cep() {

    var cep = document.getElementById("frmCadastro:tabView:cep").value;

    $(document)
        .ready(
            function () {

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $('frmCadastro:tabView:rua').val("");
                    $('frmCadastro:tabView:cep').val("");
                    $('frmCadastro:tabView:cidade').val("");
                    $('frmCadastro:tabView:uf').val("");

                }

                // Nova variável "cep" somente com dígitos.
                // var cep = $(this).val().replace(/\D/g, '');

                // Verifica se campo cep possui valor informado.
                if (cep != "") {

                    // Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    // Valida o formato do CEP.
                    if (validacep.test(cep)) {

                        // Preenche os campos com "..." enquanto
                        // consulta webservice.
                        var lograd = document
                            .getElementById('frmCadastro:tabView:rua');
                        lograd.value = ("...");

                        var bair = document
                            .getElementById('frmCadastro:tabView:bairro');
                        bair.value = ("...");

                        var loca = document
                            .getElementById('frmCadastro:tabView:cidade');

                        loca.value = ("...");

                        var u = document
                            .getElementById('frmCadastro:tabView:uf');

                        u.value = ("...");

                        // Consulta o webservice viacep.com.br/
                        $
                            .getJSON(
                                "https://viacep.com.br/ws/" +
                                cep +
                                "/json/?callback=?",
                                function (dados) {

                                    if (!("erro" in dados)) {

                                        var lograd = document
                                            .getElementById('frmCadastro:tabView:rua');
                                        lograd.value = dados.logradouro;

                                        var bair = document
                                            .getElementById('frmCadastro:tabView:bairro');
                                        bair.value = dados.bairro;

                                        var loca = document
                                            .getElementById('frmCadastro:tabView:cidade');

                                        loca.value = dados.localidade

                                        var u = document
                                            .getElementById('frmCadastro:tabView:uf');

                                        u.value = dados.uf;

                                    } // end if.
                                    else {
                                        // CEP pesquisado não
                                        // foi encontrado.
                                        limpa_formulário_cep();
                                        alert("CEP não encontrado.");
                                    }
                                });
                    } // end if.
                    else {
                        // cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } // end if.
                else {
                    // cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
}
