package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.PedidoMapper;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import br.edu.utfpr.pb.pw44s.server.service.IPedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    private final IPedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(IPedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAllForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Pedido> pedidos = this.pedidoService.findByUsuarioUsername(username);
        return ResponseEntity.ok(pedidos.stream().map(pedidoMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> finalizarCompra(@RequestBody @Valid PedidoDTO pedidoDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Pedido saved = this.pedidoService.finalizarCompra(pedidoDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMapper.toDto(saved));
    }
}
