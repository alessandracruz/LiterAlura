package br.com.alura.literalura.dto;

import java.util.List;

public record AutorResponseDTO(
        Long id,
        String nome,
        Integer anoNascimento,
        Integer anoFalecimento,
        List<String> titulosLivros // Apenas uma lista de Strings com os títulos
) {
}

