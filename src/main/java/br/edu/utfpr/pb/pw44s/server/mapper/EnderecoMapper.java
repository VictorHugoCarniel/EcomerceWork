package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.EnderecoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper {
    EnderecoDTO toDto(Endereco endereco);
    Endereco toEntity(EnderecoDTO dto);
}
