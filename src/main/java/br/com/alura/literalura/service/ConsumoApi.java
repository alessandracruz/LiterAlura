package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obterDados(String endereco ) {
        // Cria um cliente HTTP com configurações padrão
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma requisição para o endereço fornecido
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        // Envia a requisição e obtém a resposta
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // Lida com possíveis erros de conexão ou interrupção
            throw new RuntimeException(e);
        }

        // Retorna o corpo da resposta (o JSON como texto)
        String json = response.body();
        return json;
    }
}

