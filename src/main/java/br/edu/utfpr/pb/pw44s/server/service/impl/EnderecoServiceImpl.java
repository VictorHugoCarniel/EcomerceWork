package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.repository.EnderecoRepository;
import br.edu.utfpr.pb.pw44s.server.service.IEnderecoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl extends CrudServiceImpl<Endereco, Long> implements IEnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    protected JpaRepository<Endereco, Long> getRepository() {
        return this.enderecoRepository;
    }

    @Override
    public List<Endereco> findByUsuarioUsername(String username) {
        return this.enderecoRepository.findByUsuarioUsername(username);
    }
}
