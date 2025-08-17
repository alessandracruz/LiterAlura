package br.com.alura.literalura.dto;

// Usamos 'record' para uma classe de dados imutável e concisa
public record LivroResponseDTO(
        Long id,
        String titulo,
        String idioma,
        Double numeroDownloads,
        String nomeAutor
) {
}

