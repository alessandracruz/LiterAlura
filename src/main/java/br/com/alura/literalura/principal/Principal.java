package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.GutendexResponseDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO_BASE = "https://gutendex.com/books/?search=";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n*** Escolha uma das opções abaixo ***
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro que você deseja buscar:");
        var nomeLivro = leitura.nextLine();

        // 1. CHAMADA À API
        var json = consumoApi.obterDados(ENDERECO_BASE + nomeLivro.replace(" ", "+"));

        // 2. CONVERSÃO E EXTRAÇÃO DOS DADOS
        // Converte a resposta geral da API
        GutendexResponseDTO dadosGerais = conversor.obterDados(json, GutendexResponseDTO.class);

        // Pega o primeiro livro da lista de resultados (se houver)
        Optional<LivroDTO> livroDTOOpcional = dadosGerais.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst();

        // 3. PROCESSAMENTO DOS DADOS
        if (livroDTOOpcional.isPresent()) {
            LivroDTO livroDTO = livroDTOOpcional.get();

            // Pega os dados do autor do livro
            AutorDTO autorDTO = livroDTO.autores().get(0); // Pega o primeiro autor da lista

            // 4. VERIFICAÇÃO NO BANCO DE DADOS E PERSISTÊNCIA
            // Verifica se o autor já existe no nosso banco
            Optional<Autor> autorOpcional = autorRepository.findByNome(autorDTO.nome());

            Autor autor;
            if (autorOpcional.isPresent()) {
                autor = autorOpcional.get();
                System.out.println("Autor já existente no banco de dados.");
            } else {
                // Se não existe, cria um novo e salva
                autor = new Autor(autorDTO.nome(), autorDTO.anoNascimento(), autorDTO.anoFalecimento());
                autorRepository.save(autor);
                System.out.println("Novo autor salvo no banco de dados.");
            }

            // Cria o objeto Livro com o autor (novo ou existente)
            Livro livro = new Livro(livroDTO.titulo(), livroDTO.idiomas().get(0), livroDTO.numeroDownloads(), autor);

            try {
                livroRepository.save(livro);
                System.out.println("Livro salvo com sucesso!");
                System.out.println(livro); // Mostra os dados do livro salvo usando o toString() formatado
            } catch (Exception e) {
                // O @Column(unique=true) no título vai gerar uma exceção se tentarmos salvar um livro repetido.
                // Aqui capturamos essa exceção e informamos o usuário.
                System.out.println("Erro ao salvar o livro: O livro '" + livro.getTitulo() + "' já existe no banco de dados.");
            }

        } else {
            System.out.println("Livro não encontrado com esse título.");
        }
    }

    private void listarLivrosRegistrados() {
        // Busca todos os livros do nosso banco de dados através do repositório
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco de dados.");
        } else {
            System.out.println("\n----- LIVROS REGISTRADOS -----");
            // Itera sobre a lista de livros e imprime cada um usando o método toString()
            livros.forEach(System.out::println);
            System.out.println("----------------------------\n");
        }
    }

    private void listarAutoresRegistrados() {
        // Busca todos os autores do banco
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado no banco de dados.");
        } else {
            System.out.println("\n----- AUTORES REGISTRADOS -----");
            // Para cada autor, imprime seus dados e a lista de livros
            autores.forEach(autor -> {
                System.out.println("Autor: " + autor.getNome());
                System.out.println("  Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println("  Ano de Falecimento: " + (autor.getAnoFalecimento() > 0 ? autor.getAnoFalecimento() : "N/A"));
                System.out.println("  Livros: " + autor.getLivros().stream()
                        .map(Livro::getTitulo)
                        .collect(Collectors.joining(", ")));
                System.out.println("-------------------------------");
            });
        }
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("Digite o ano para pesquisar os autores vivos:");
        try {
            int ano = leitura.nextInt();
            leitura.nextLine(); // Limpa o buffer

            // Chama o método customizado do repositório
            List<Autor> autoresVivos = autorRepository.findAutoresVivosEmAno(ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado para o ano de " + ano + ".");
            } else {
                System.out.println("\n----- AUTORES VIVOS EM " + ano + " -----");
                autoresVivos.forEach(autor -> {
                    System.out.println("Autor: " + autor.getNome());
                    System.out.println("  (Nascimento: " + autor.getAnoNascimento() + " - Falecimento: " + (autor.getAnoFalecimento() > 0 ? autor.getAnoFalecimento() : "Presente") + ")");
                });
                System.out.println("----------------------------------\n");
            }
        } catch (Exception e) {
            System.out.println("Ano inválido. Por favor, digite um número.");
            leitura.nextLine(); // Limpa o buffer em caso de erro
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma para a busca (ex: pt, en, es, fr):");
        var idioma = leitura.nextLine().toLowerCase();

        // Chama o método gerado automaticamente pelo Spring
        List<Livro> livrosPorIdioma = livroRepository.findByIdioma(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'.");
        } else {
            System.out.println("\n----- LIVROS NO IDIOMA '" + idioma + "' -----");
            livrosPorIdioma.forEach(System.out::println);
            System.out.println("----------------------------------\n");
        }
    }
}

