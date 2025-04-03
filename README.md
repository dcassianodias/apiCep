# API de Busca de CEP

Esta é uma API desenvolvida em Java para busca de CEP, utilizando uma API externa mockada. Os logs das requisições são armazenados em um banco de dados MySQL.

## Desenho de Solução

![image](https://github.com/user-attachments/assets/036b2bec-924d-4dc7-99e5-6795d77dcf8b)


## Tecnologias Utilizadas

- **Java 11** ou superior
- **Spring Boot**
- **Banco de Dados MySQL**
- **Flyway** para versionamento do banco de dados
- **Mockoon** para mock de APIs externas

## Requisitos

- Java 11 ou superior instalado
- MySQL instalado e configurado
- Mockoon instalado para simular a API externa

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/apiCep.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd apiCep
   ```
3. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `api_cep`
   - Atualize as configurações do banco no `application.properties`

4. Execute as migrações do banco de dados:
   ```bash
   mvn flyway:migrate
   ```

## Executando a API

1. Inicie o Mockoon com o arquivo de mock da API externa.
2. Compile e inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A API estará rodando em `http://localhost:8080`.

## Endpoints

### Buscar informações de um CEP
- **GET** `/api/cep/{cep}`
- **Parâmetros**: `cep` (string) - CEP a ser buscado
- **Resposta**:
  ```json
  {
    "cep": "01001-000",
    "logradouro": "Praça da Sé",
    "bairro": "Sé",
    "cidade": "São Paulo",
    "estado": "SP"
  }
  ```

## Logs de Atendimento
Os logs de chamadas são armazenados automaticamente no banco de dados MySQL.

## Contribuição

Sinta-se à vontade para abrir uma issue ou enviar um pull request caso queira contribuir.

