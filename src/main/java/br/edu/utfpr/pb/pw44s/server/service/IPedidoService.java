package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;

import java.util.List;

public interface IPedidoService extends ICrudService<Pedido, Long> {
    List<Pedido> findByUsuarioUsername(String username);
    Pedido finalizarCompra(PedidoDTO pedidoDTO, String username);
}
