package model;

import model.dto.DadosEnderecoResponseDTO;

import java.util.concurrent.CompletableFuture;

public class Aluno {
    private String nome;
    private DadosEndereco endereco;

    public Aluno() {
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    public void adicionarDadosEndereco(DadosEndereco endereco) {
        this.endereco = endereco;
    }

    public DadosEndereco getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
