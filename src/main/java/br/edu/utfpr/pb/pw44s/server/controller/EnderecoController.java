package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.EnderecoDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.EnderecoMapper;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.service.AuthService;
import br.edu.utfpr.pb.pw44s.server.service.IEnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    private final IEnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;
    private final AuthService authService;

    public EnderecoController(IEnderecoService enderecoService, EnderecoMapper enderecoMapper, AuthService authService) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAllForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Endereco> enderecos = this.enderecoService.findByUsuarioUsername(username);
        return ResponseEntity.ok(enderecos.stream().map(enderecoMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = (User) authService.loadUserByUsername(username);
        
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco.setUsuario(usuario);
        
        Endereco saved = this.enderecoService.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoMapper.toDto(saved));
    }
}
