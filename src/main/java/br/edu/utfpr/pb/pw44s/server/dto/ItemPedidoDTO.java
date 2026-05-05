package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoDTO {

    private Long id;

    @NotNull
    private Integer quantidade;

    private BigDecimal valor; 

    @NotNull
    private Long produtoId;
    
    private ProductDTO produto;
}
