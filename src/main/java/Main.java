import model.Aluno;
import model.DadosEndereco;
import model.dto.DadosEnderecoResponseDTO;
import services.ApiCorreios;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import services.ApiCorreios;
import services.CadastrarAlunoService;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        try {
            ApiCorreios api = new ApiCorreios();
            CadastrarAlunoService service = new CadastrarAlunoService(api);
            String cep = "25535482";
            Aluno aluno = service.cadastrarAluno("Luiz", cep, "353", "N/C");
            System.out.println("Aluno Cadastrado");
            System.out.println(String.format("Nome: %s", aluno.getNome()));
            System.out.println("Endereco: ");
            System.out.println(String.format("Cep: %s", aluno.getEndereco().getCep()));
            System.out.println(String.format("Logradouro: %s", aluno.getEndereco().getLogradouro()));
            System.out.println(String.format("Numero: %s", aluno.getEndereco().getNumero()));
            System.out.println(String.format("Complemento: %s", aluno.getEndereco().getComplemento()));
            System.out.println(String.format("Bairro: %s", aluno.getEndereco().getBairro()));
            System.out.println(String.format("Cidade: %s", aluno.getEndereco().getCidade()));
            System.out.println(String.format("UF: %s", aluno.getEndereco().getUf()));
        } catch (IOException | InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
        }

    }
}
