package unitTests;

import model.Aluno;
import model.DadosEndereco;
import model.dto.DadosEnderecoResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import services.ApiCorreios;
import services.CadastrarAlunoService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
public class CadastrarAlunoTest {

    @Mock
    private ApiCorreios apiCorreios = new ApiCorreios();

    @InjectMocks
    private CadastrarAlunoService cadastrarAlunoService;

    @Test
    void validarDadosAluno() throws IOException, InterruptedException, ExecutionException {
        //arrange
        DadosEnderecoResponseDTO fakeEndereco = new DadosEnderecoResponseDTO("25535482", "Rua Maria Gonzaga", "Vila Norma", "São João de Meriti", "RJ", null);
        String nome = "João";
        String cep = "25535482";
        String numero = "353";
        String complemento = "casa 1";
        //mock
        Mockito.when(apiCorreios.buscarEnderecoPeloCep(cep)).thenReturn( CompletableFuture.completedFuture(fakeEndereco));
        //act
        CompletableFuture<DadosEnderecoResponseDTO> responseEndereco = apiCorreios.buscarEnderecoPeloCep(cep);
        DadosEnderecoResponseDTO result = responseEndereco.join();
        Aluno aluno = cadastrarAlunoService.cadastrarAluno(nome, cep, numero, complemento);
        //assert
        assertNotNull(responseEndereco);
        assertEquals("João", aluno.getNome());
        assertEquals(cep, result.cep());
        assertEquals("Rua Maria Gonzaga", result.street());
        assertEquals(numero, aluno.getEndereco().getNumero());
        assertEquals(complemento, aluno.getEndereco().getComplemento());
        assertEquals("Vila Norma", result.neighborhood());
        assertEquals("São João de Meriti", result.city());
        assertEquals("RJ", result.state());
    }

}
