## Em desenvolvimento...

# Lab Hospital
Este projeto lab, está sendo desenvolvido para gerenciar internações hospitalares, permitindo o acompanhamento de pacientes, médicos e status das internações de forma simples e eficiente.

## Tecnologias Utilizadas

- Java com Spring Boot: Framework para desenvolvimento da API REST.
- JPA/Hibernate: Para interação com o banco de dados.
- PostgreSQL: Para manipulação do banco de dados SQL.
- Enumeração: Utilização de enum para representar o status da internação.

## Funcionalidades

- Cadastro de Pacientes e Médicos.
- Acompanhamento do Status das Internações (Usando enum).
- Gerenciamento de internações de pacientes, incluindo a associação a médicos.
- API RESTful para interagir com o sistema.


## Como Rodar o Projeto
1. Clone o repositório:
  
```bash
git clone https://github.com/juanvictorBC/lab-hospital.git
```

2. Importe o projeto no seu IDE: Se você estiver utilizando o IntelliJ IDEA, Eclipse, ou outra IDE Java, basta importar o projeto como um projeto Maven.

3. Configuração do banco de dados:

- Configure as credenciais do banco de dados no arquivo `application.properties` ou `application.yml` para garantir a comunicação com o banco de dados desejado.

5. Acesse a API: O servidor será iniciado em http://localhost:8080.

6. Endpoints da API (GET, POST, PUT, DELETE)
- /pacientes
- /pacientes/id
- /medicos
- /medicos/id
- /internacoes/
- /internacoes/id
- /leitos
- leitos/id

# Como Contribuir

1. Faça um Fork do repositório.
2. Crie uma branch para a sua feature (git checkout -b feature/nome-da-feature).
3. Commit suas alterações (git commit -am 'Adiciona nova feature').
4. Push para a branch (git push origin feature/nome-da-feature).
5. Crie um Pull Request.

# Licença
Este projeto está licenciado sob a MIT License - consulte o arquivo LICENSE para mais detalhes.

