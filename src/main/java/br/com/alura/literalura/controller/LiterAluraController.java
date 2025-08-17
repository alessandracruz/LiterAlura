package br.com.alura.literalura.controller;

import br.com.alura.literalura.dto.AutorResponseDTO;
import br.com.alura.literalura.dto.LivroResponseDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/literalura")
public class LiterAluraController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    // Endpoint para listar todos os livros
    @GetMapping("/livros")
    public List<LivroResponseDTO> listarLivros() {
        return livroRepository.findAll().stream()
                .map(this::converterLivroParaDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para listar todos os autores
    @GetMapping("/autores")
    public List<AutorResponseDTO> listarAutores() {
        return autorRepository.findAll().stream()
                .map(this::converterAutorParaDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para listar autores vivos em um determinado ano
    @GetMapping("/autores/vivos/{ano}")
    public List<AutorResponseDTO> listarAutoresVivos(@PathVariable int ano) {
        return autorRepository.findAutoresVivosEmAno(ano).stream()
                .map(this::converterAutorParaDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para listar livros por idioma
    @GetMapping("/livros/idioma/{idioma}")
    public List<LivroResponseDTO> listarLivrosPorIdioma(@PathVariable String idioma) {
        return livroRepository.findByIdioma(idioma).stream()
                .map(this::converterLivroParaDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODOS PRIVADOS DE CONVERSÃO ---

    private LivroResponseDTO converterLivroParaDTO(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIdioma(),
                livro.getNumeroDownloads(),
                (livro.getAutor() != null) ? livro.getAutor().getNome() : "Autor desconhecido"
        );
    }

    private AutorResponseDTO converterAutorParaDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getAnoNascimento(),
                autor.getAnoFalecimento(),
                autor.getLivros().stream()
                        .map(Livro::getTitulo)
                        .collect(Collectors.toList())
        );
    }
}


