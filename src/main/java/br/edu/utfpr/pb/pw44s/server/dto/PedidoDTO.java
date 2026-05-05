package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.edu.utfpr.pb.pw44s.server.model.enums.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private String status;

    @NotNull
    private FormaPagamento formaPagamento;

    @NotNull
    private Long enderecoId; 
    
    private EnderecoDTO endereco;

    @NotNull
    private List<ItemPedidoDTO> itens;
}
