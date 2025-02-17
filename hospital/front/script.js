document.addEventListener("DOMContentLoaded", async () => {
    // Função para carregar pacientes e preencher a tabela
    async function carregarPacientes() {
        try {
            const response = await fetch("http://localhost:8080/pacientes");
            if (!response.ok) {
                throw new Error("Erro ao buscar pacientes.");
            }

            const pacientes = await response.json();

            const tabelaBody = document.querySelector("#pessoasTable tbody");
            tabelaBody.innerHTML = ""; // Limpar a tabela

            pacientes.forEach(paciente => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${paciente.id}</td>
                    <td>${paciente.nome}</td>
                    <td>${paciente.genero}</td>
                    <td>
                        <button class="editar" onclick="editarPaciente(${paciente.id})">Editar</button>
                        <button class="deletar" onclick="deletarPaciente(${paciente.id})">Deletar</button>
                    </td>
                `;

                tabelaBody.appendChild(row);
            });
        } catch (error) {
            console.error("Erro ao carregar pacientes:", error);
            alert("Falha ao carregar pacientes.");
        }
    }

    // Carregar pacientes ao carregar a página
    carregarPacientes();

    // Função para editar paciente
    window.editarPaciente = function(id) {
        document.getElementById("editarPacienteBox").style.display = "block";
        
        fetch(`http://localhost:8080/pacientes/${id}`)
            .then(response => response.json())
            .then(paciente => {
                document.getElementById("editarNome").value = paciente.nome;
                document.getElementById("editarGenero").value = paciente.genero;
                document.getElementById("editarCpf").value = paciente.cpf;
                document.getElementById("editarNascimento").value = paciente.dataNascimento;

                document.getElementById("editarForm").onsubmit = async function (event) {
                    event.preventDefault();

                    const pacienteAtualizado = {
                        nome: document.getElementById("editarNome").value,
                        genero: document.getElementById("editarGenero").value,
                        cpf: document.getElementById("editarCpf").value.replace(/\D/g, ''),
                        dataNascimento: document.getElementById("editarNascimento").value
                    };

                    try {
                        const response = await fetch(`http://localhost:8080/pacientes/${id}`, {
                            method: "PUT",
                            headers: { "Content-Type": "application/json" },
                            body: JSON.stringify(pacienteAtualizado)
                        });

                        if (!response.ok) {
                            throw new Error("Erro ao editar paciente.");
                        }

                        alert("Paciente atualizado com sucesso!");
                        carregarPacientes();
                        document.getElementById("editarPacienteBox").style.display = "none";
                    } catch (error) {
                        console.error("Erro ao editar paciente:", error);
                        alert("Falha ao editar paciente.");
                    }
                };
            })
            .catch(error => {
                console.error("Erro ao buscar paciente:", error);
                alert("Falha ao carregar dados do paciente.");
            });
    };

    // Função para deletar paciente
    window.deletarPaciente = async function (id) {
        if (confirm("Você tem certeza que deseja deletar este paciente?")) {
            try {
                const response = await fetch(`http://localhost:8080/pacientes/${id}`, {
                    method: "DELETE"
                });

                if (!response.ok) {
                    throw new Error("Erro ao deletar paciente.");
                }

                alert("Paciente deletado com sucesso!");
                carregarPacientes();
            } catch (error) {
                console.error("Erro ao deletar paciente:", error);
                alert("Falha ao deletar paciente.");
            }
        }
    };

    // Cadastro de paciente
    document.getElementById("pacienteForm").addEventListener("submit", async function (event) {
        event.preventDefault();

        const paciente = {
            nome: document.getElementById("nome").value,
            genero: document.getElementById("genero").value,
            cpf: document.getElementById("cpf").value.replace(/\D/g, ''),
            dataNascimento: document.getElementById("nascimento").value
        };

        try {
            if (paciente.cpf.length !== 11) {
                alert("Por favor, insira um CPF válido com 11 dígitos.");
                return;
            }

            const response = await fetch("http://localhost:8080/pacientes", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(paciente)
            });

            if (!response.ok) {
                throw new Error("Erro ao cadastrar paciente.");
            }

            const result = await response.json();
            alert("Paciente cadastrado com sucesso! ID: " + result.id);

            // Limpar os campos do formulário após o cadastro
            document.getElementById("pacienteForm").reset();
        } catch (error) {
            console.error("Erro ao cadastrar paciente:", error);
            alert("Falha ao cadastrar paciente.");
        }
    });

    // Cancelar edição
    document.getElementById("cancelarEdicao").addEventListener("click", () => {
        document.getElementById("editarPacienteBox").style.display = "none";
    });
});
