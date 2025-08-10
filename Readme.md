# 📖 LiterAlura - Catálogo de Livros

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge )

## 📄 Descrição do Projeto

O **LiterAlura** é uma aplicação de catálogo de livros interativa, desenvolvida como parte do Challenge de Back-end do programa Oracle Next Education (ONE). A aplicação permite aos usuários buscar livros através da API pública [Gutendex](https://gutendex.com/ ), registrar esses livros e seus autores em um banco de dados local, e realizar diversas consultas sobre os dados armazenados.

Este projeto foi construído para ser executado via console (terminal) e demonstra a integração de uma aplicação Java com APIs externas e a persistência de dados em um banco de dados relacional.

## ⚙️ Funcionalidades

A aplicação oferece um menu interativo com as seguintes opções:

1.  **Buscar livro pelo título:** Consulta a API Gutendex, exibe os resultados e salva o livro e seu autor no banco de dados.
2.  **Listar livros registrados:** Exibe todos os livros salvos no banco de dados.
3.  **Listar autores registrados:** Mostra todos os autores salvos, junto com seus dados biográficos e os livros de sua autoria registrados no sistema.
4.  **Listar autores vivos em um determinado ano:** Permite ao usuário inserir um ano e exibe os autores que estavam vivos naquele período.
5.  **Listar livros em um determinado idioma:** Apresenta os livros registrados em um idioma específico (ex: português, inglês, etc.).

## 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias e bibliotecas:

- **Java 21:** Linguagem de programação principal.
- **Spring Boot 3:** Framework para criação da aplicação, gerenciamento de dependências e configuração.
- **Spring Data JPA:** Para persistência de dados e comunicação com o banco de dados.
- **PostgreSQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados.
- **Maven:** Ferramenta de gerenciamento de projetos e dependências.
- **API Gutendex:** API pública para consulta de dados de livros.
- **Jackson:** Biblioteca para conversão de dados entre objetos Java e JSON.

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

### Pré-requisitos

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21 ) ou superior instalado.
- [Apache Maven](https://maven.apache.org/download.cgi ) instalado.
- [PostgreSQL](https://www.postgresql.org/download/ ) instalado e em execução.
- Um cliente Git instalado.

### Passos

1. **Clone o repositório:**
   ```
   git clone https://github.com/seu-usuario/LiterAlura.git
   cd LiterAlura
   ```

2. **Configure o banco de dados:**

    1. Crie um banco de dados no PostgreSQL com o nome literalura_db.
    2. Abra o arquivo src/main/resources/application.properties.
    3. Altere as propriedades spring.datasource.username e spring.datasource.password com suas credenciais do PostgreSQL.

3. **Execute a aplicação:**

    1. Utilize o Maven para compilar e executar o projeto:
    ```
    mvn spring-boot:run
    ```
    2. A aplicação iniciará no seu terminal e exibirá o menu interativo.

💻 Autora

Alessandra Cruz
- LinkedIn: https://www.linkedin.com/in/alessandraccruz/
- GitHub: https://github.com/alessandracruz/