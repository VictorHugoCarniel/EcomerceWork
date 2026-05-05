package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.dto.ItemPedidoDTO;
import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.model.ItemPedido;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.EnderecoRepository;
import br.edu.utfpr.pb.pw44s.server.repository.PedidoRepository;
import br.edu.utfpr.pb.pw44s.server.repository.ProductRepository;
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;
import br.edu.utfpr.pb.pw44s.server.service.IPedidoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, Long> implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final EnderecoRepository enderecoRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, EnderecoRepository enderecoRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.enderecoRepository = enderecoRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<Pedido, Long> getRepository() {
        return this.pedidoRepository;
    }

    @Override
    public List<Pedido> findByUsuarioUsername(String username) {
        return this.pedidoRepository.findByUsuarioUsername(username);
    }

    @Override
    public Pedido finalizarCompra(PedidoDTO pedidoDTO, String username) {
        User usuario = userRepository.findUserByUsername(username);
        Endereco endereco = enderecoRepository.findById(pedidoDTO.getEnderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEndereco(endereco);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("FINALIZADO");
        pedido.setFormaPagamento(pedidoDTO.getFormaPagamento());
        
        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoDTO itemDto : pedidoDTO.getItens()) {
            Product product = productRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
            ItemPedido item = new ItemPedido();
            item.setProduto(product);
            item.setQuantidade(itemDto.getQuantidade());
            item.setValor(product.getPrice());
            item.setPedido(pedido);

            itens.add(item);
            
            BigDecimal subtotal = product.getPrice().multiply(new BigDecimal(itemDto.getQuantidade()));
            total = total.add(subtotal);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }
}
