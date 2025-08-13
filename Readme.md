# LiterAlura - Catálogo de Livros

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white )
![Spring](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white )
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-336791?style=for-the-badge&logo=postgresql&logoColor=white )
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white )

## 📖 Sobre o Projeto

**LiterAlura** é uma aplicação de catálogo de livros desenvolvida como parte do Challenge de Back-end do programa ONE (Oracle Next Education) em parceria com a Alura. O objetivo principal é construir uma aplicação de console que consome a API gratuita [Gutendex](https://gutendex.com/ ) para buscar livros, e persistir esses dados em um banco de dados PostgreSQL.

Este projeto demonstra habilidades em Java, Spring Boot, JPA/Hibernate e consumo de APIs, seguindo as melhores práticas de desenvolvimento.

## 🚀 Funcionalidades Implementadas

A aplicação oferece um menu interativo no console com as seguintes opções:

1.  **Buscar livro pelo título:** Consulta a API Gutendex, busca um livro pelo título e o salva no banco de dados local, incluindo os dados do autor.
2.  **Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar autores registrados:** Mostra todos os autores salvos e os títulos de seus respectivos livros.
4.  **Listar autores vivos em um determinado ano:** Pesquisa e exibe autores que estavam vivos em um ano específico.
5.  **Listar livros em um determinado idioma:** Filtra e mostra os livros disponíveis em um idioma específico (ex: pt, en, es, fr).

## 🛠️ Tecnologias Utilizadas

*   **Java 17+:** Linguagem principal do projeto.
*   **Spring Boot 3:** Framework para criação da aplicação, gerenciamento de dependências e configuração.
*   **Spring Data JPA:** Para persistência de dados de forma simplificada.
*   **PostgreSQL:** Banco de dados relacional para armazenar os livros e autores.
*   **Maven:** Ferramenta para gerenciamento de dependências e build do projeto.
*   **API Gutendex:** Fonte externa de dados sobre os livros.
*   **Jackson:** Biblioteca para conversão de JSON para objetos Java (DTOs).

## ⚙️ Como Executar o Projeto

**Pré-requisitos:**
*   Java 17 ou superior
*   Maven 3.8 ou superior
*   PostgreSQL 14 ou superior

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/LiterAlura.git
    ```

2.  **Configure o Banco de Dados:**
   *   Crie um banco de dados no PostgreSQL chamado `literalura_db`.
   *   Abra o arquivo `src/main/resources/application.properties`.
   *   Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do PostgreSQL.

3.  **Execute a aplicação:**
   *   Navegue até a raiz do projeto.
   *   Execute o seguinte comando Maven:
    ```bash
    mvn spring-boot:run
    ```
   *   A aplicação iniciará e o menu interativo será exibido no console.

💻 Autora

Alessandra Cruz
- LinkedIn: https://www.linkedin.com/in/alessandraccruz/
- GitHub: https://github.com/alessandracruz/