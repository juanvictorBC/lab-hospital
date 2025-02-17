document.addEventListener("DOMContentLoaded", function() {
    // Função para buscar as opções dos leitos e médicos
    function fetchOptions(endpoint, elementId, extraField) {
        fetch("http://localhost:8080" + endpoint)
            .then(response => response.json())
            .then(data => {
                console.log("Dados recebidos de", endpoint, data); // Logar os dados recebidos

                const select = document.getElementById(elementId);
                select.innerHTML = ""; // Limpar as opções existentes

                data.forEach(item => {
                    let option = document.createElement("option");
                    option.value = item.id;

                    if (extraField) {
                        // Exibindo informações adicionais para o leito (como número e setor)
                        option.textContent = item.numero + " - " + item.setor;
                    } else {
                        // Para médicos, vamos mostrar o nome e especialidade
                        option.textContent = item.nome + " - " + item.especialidade; // Atribuindo nome e especialidade do médico
                    }

                    select.appendChild(option);
                });
            })
            .catch(error => console.error("Erro ao carregar opções:", error));
    }

    // Buscar os leitos e médicos
    fetchOptions("/leitos", "leito", "setor"); // Buscando leitos
    fetchOptions("/medicos", "medico"); // Buscando médicos sem campo extra

    // Buscar pacientes
    document.getElementById("buscar-paciente").addEventListener("click", function() {
        const nomePaciente = document.getElementById("paciente").value.trim();
        
        if (nomePaciente) {
            fetch(`http://localhost:8080/pacientes?nome=${nomePaciente}`)
                .then(response => response.json())
                .then(pacientes => {
                    if (pacientes.length > 0) {
                        const paciente = pacientes[0]; // Pega o primeiro paciente encontrado
                        console.log("Paciente encontrado:", paciente); // Logando o paciente encontrado
                        
                        // Preencher os campos com os dados do paciente
                        document.getElementById("paciente").value = paciente.nome;
                        document.getElementById("genero").value = paciente.genero;
                        document.getElementById("cpf").value = paciente.cpf;
                        document.getElementById("dataNascimento").value = paciente.dataNascimento;

                        // Verificar status da internação e alocar leito se necessário
                        const leitoSelect = document.getElementById("leito");

                        if (paciente.internacoes && paciente.internacoes.length > 0) {
                            const internacao = paciente.internacoes[0]; // Considerando a primeira internação
                            
                            if (internacao.status === "internado") {
                                console.log("Paciente internado. Buscando leito disponível...");
                                
                                fetch("http://localhost:8080/leitos?disponivel=true")
                                    .then(response => response.json())
                                    .then(leitos => {
                                        console.log("Leitos disponíveis:", leitos); // Logando os leitos disponíveis
                                        if (leitos.length > 0) {
                                            const leitoDisponivel = leitos[0];
                                            leitoSelect.value = leitoDisponivel.id; // Preenche o leito com o ID do leito disponível
                                            console.log(`Leito alocado: ${leitoDisponivel.numero} - ${leitoDisponivel.setor}`);
                                        } else {
                                            alert("Não há leitos disponíveis.");
                                        }
                                    })
                                    .catch(error => console.error("Erro ao buscar leitos disponíveis:", error));
                            }
                        }

                        // Se o paciente está em tratamento, não aloca leito
                        if (paciente.status === "em tratamento") {
                            console.log("Paciente em tratamento. Leito não alocado.");
                        }
                    } else {
                        alert("Paciente não encontrado.");
                    }
                })
                .catch(error => console.error("Erro ao buscar pacientes:", error));
        } else {
            alert("Digite o nome do paciente para buscar.");
        }
    });

    // Formulário de internação
    document.getElementById("internacao-form").addEventListener("submit", function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        fetch("http://localhost:8080/internacoes", { // URL para registrar a internação
            method: "POST",
            body: JSON.stringify(Object.fromEntries(formData)),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then(data => alert("Internação realizada com sucesso!"))
        .catch(error => console.error("Erro ao registrar internação:", error));
    });
});
