package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ItemPedidoMapper.class, EnderecoMapper.class})
public interface PedidoMapper {

    @Mapping(source = "endereco.id", target = "enderecoId")
    PedidoDTO toDto(Pedido pedido);

    @Mapping(target = "itens", ignore = true)
    Pedido toEntity(PedidoDTO dto);
}
