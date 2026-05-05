package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Endereco;

import java.util.List;

public interface IEnderecoService extends ICrudService<Endereco, Long> {
    List<Endereco> findByUsuarioUsername(String username);
}
