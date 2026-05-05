package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.ItemPedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface ItemPedidoMapper {
    ItemPedidoDTO toDto(ItemPedido itemPedido);
    ItemPedido toEntity(ItemPedidoDTO dto);
}
