package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dto.DadosEnderecoResponseDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiCorreios {
    public  CompletableFuture<DadosEnderecoResponseDTO> buscarEnderecoPeloCep(String cep) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://brasilapi.com.br/api/cep/v1/".concat(cep)))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        return mapper.readValue(response, DadosEnderecoResponseDTO.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Erro na desserialização do JSON");
                    }
                });
//        return null;
    }
}
