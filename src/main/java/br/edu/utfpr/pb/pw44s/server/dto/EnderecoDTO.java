package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class    EnderecoDTO {

    private Long id;

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String cep;
    
}
