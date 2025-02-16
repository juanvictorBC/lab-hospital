document.getElementById("pacienteForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const paciente = {
        nome: document.getElementById("nome").value,
        genero: document.getElementById("genero").value,
        cpf: document.getElementById("cpf").value.replace(/\D/g, ''), // Remove qualquer não-dígito
        dataNascimento: document.getElementById("nascimento").value
    };

    try {
        // Validação do CPF (após remover caracteres não numéricos)
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
        console.log(result);

        // Limpar os campos do formulário após o cadastro
        document.getElementById("pacienteForm").reset();

    } catch (error) {
        console.error("Erro:", error);
        alert("Falha no cadastro.");
    }
});
