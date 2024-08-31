package model.dto;

public record DadosEnderecoResponseDTO(
        String cep,
        String street,
        String neighborhood,
        String city,
        String state,
        String service
) {
}
