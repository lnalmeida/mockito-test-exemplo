package services;

import model.Aluno;
import model.DadosEndereco;
import model.dto.DadosEnderecoResponseDTO;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CadastrarAlunoService {

    private static ApiCorreios apiCorreios;

    public CadastrarAlunoService() {
    }

    public CadastrarAlunoService(ApiCorreios apiCorreios) {
        this.apiCorreios = apiCorreios;
    }

    public Aluno cadastrarAluno(String nome, String cep, String numero, String complemento) throws IOException, InterruptedException, ExecutionException {
        Aluno aluno = new Aluno(nome);
        CompletableFuture<DadosEnderecoResponseDTO> enderecoResponse = apiCorreios.buscarEnderecoPeloCep(cep);
        if(enderecoResponse != null) {
            DadosEnderecoResponseDTO enderecoDto = enderecoResponse.get();

            if(enderecoDto != null) {
                DadosEndereco endereco = new DadosEndereco(
                        enderecoResponse.get().cep(),
                        enderecoResponse.get().street(),
                        numero = numero,
                        complemento = complemento,
                        enderecoResponse.get().neighborhood(),
                        enderecoResponse.get().city(),
                        enderecoResponse.get().state()
                );
                aluno.adicionarDadosEndereco(endereco);
            } else {
                throw new RuntimeException("ERRO: DTO de endereço retornado é nulo");
            }
        } else {
            throw new RuntimeException("Erro: A resposta da API é nula.");
        }
        return aluno;
    }
}
