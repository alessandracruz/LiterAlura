# LiterAlura - Catálogo de Livros e Autores

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white )
![Spring](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white )
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-336791?style=for-the-badge&logo=postgresql&logoColor=white )
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white )

## 📖 Sobre o Projeto

**LiterAlura** é uma aplicação back-end desenvolvida como parte do Challenge de Back-end do programa ONE (Oracle Next Education) em parceria com a Alura. O objetivo foi construir uma aplicação que consome a API gratuita [Gutendex](https://gutendex.com/ ) para buscar livros e autores, persistindo esses dados em um banco de dados PostgreSQL.

Inicialmente desenvolvido como uma aplicação de console, o projeto foi **evoluído para uma API RESTful**, expondo os dados para que possam ser consumidos por qualquer aplicação front-end.

---

## 🧠 Conceitos-Chave para Estudo

Este projeto foi uma oportunidade de aplicar conceitos fundamentais do desenvolvimento back-end com Java.

### O que é Spring Boot?

O **Spring Boot** é um framework que torna a criação de aplicações Java muito mais rápida e fácil. Ele elimina grande parte da configuração manual que era necessária antigamente. Pense nele como uma "receita de bolo" para aplicações: ele já vem com os ingredientes (dependências) e o modo de preparo (configurações padrão) prontos.

Neste projeto, o Spring Boot ajudou a:
-   **Criar um servidor web** (Tomcat) embutido com pouquíssimas linhas de código.
-   **Gerenciar dependências** com o `pom.xml` (via Maven).
-   **Simplificar a conexão com o banco de dados** através do `application.properties`.
-   **Facilitar a criação de uma API REST** com anotações como `@RestController` e `@GetMapping`.

### O que foi feito neste projeto?

Construí uma aplicação em camadas, uma arquitetura muito comum no mercado:

1.  **`Controller` (Camada da API):** É a porta de entrada da aplicação. Recebe as requisições web (HTTP) e direciona para os serviços corretos.
2.  **`Service` (Camada de Serviço):** Contém a lógica de negócio. Por exemplo, a regra de não salvar um autor duplicado.
3.  **`Repository` (Camada de Acesso a Dados):** É a interface que conversa com o banco de dados, graças ao Spring Data JPA. Ela permite buscar, salvar e deletar dados sem escrever SQL.
4.  **`Model` / `Entity` (Camada de Modelo):** Representa as tabelas do banco de dados como classes Java (`Livro`, `Autor`).
5.  **`DTO` (Data Transfer Object):** São classes "burras" usadas para controlar exatamente quais dados são enviados na resposta da API, evitando recursividade e expondo apenas o necessário.

---

## 🚀 API Endpoints

A aplicação expõe os seguintes endpoints para consulta:

| Método | URL                                       | Descrição                                    |
| :----- | :---------------------------------------- | :------------------------------------------- |
| `GET`  | `/literalura/livros`                      | Lista todos os livros registrados no banco.  |
| `GET`  | `/literalura/autores`                     | Lista todos os autores registrados no banco. |
| `GET`  | `/literalura/autores/vivos/{ano}`         | Lista autores vivos em um determinado ano.   |
| `GET`  | `/literalura/livros/idioma/{sigla_idioma}` | Lista livros em um determinado idioma.       |

**Exemplos de uso:**
- `http://localhost:8080/literalura/livros`
- `http://localhost:8080/literalura/autores/vivos/1810`
- `http://localhost:8080/literalura/livros/idioma/pt`

---

## 🛠️ Tecnologias Utilizadas

-   **Java 17+**
-   **Spring Boot 3**
-   **Spring Data JPA**
-   **PostgreSQL**
-   **Maven**
-   **API Gutendex** (fonte externa de dados )
-   **Jackson** (para conversão de objetos para JSON)

---

## ⚙️ Como Executar

**Pré-requisitos:**
-   Java 17 ou superior
-   Maven 3.8 ou superior
-   PostgreSQL 14 ou superior

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/alessandracruz/LiterAlura.git
    ```

2.  **Configure o Banco de Dados:**
    -   Crie um banco de dados no PostgreSQL chamado `literalura_db`.
    -   Abra o arquivo `src/main/resources/application.properties`.
    -   Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do PostgreSQL.

3.  **Execute a aplicação:**
    -   Navegue até a raiz do projeto.
    -   Execute o seguinte comando Maven para construir o projeto e garantir que tudo está correto:
    ```bash
    mvn clean install
    ```
    -   Depois do `BUILD SUCCESS`, inicie o servidor web:
    ```bash
    mvn spring-boot:run
    ```
    -   A API estará disponível em `http://localhost:8080/literalura`.