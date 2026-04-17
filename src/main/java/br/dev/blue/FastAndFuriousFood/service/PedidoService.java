/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.service;

import br.dev.blue.FastAndFuriousFood.model.StatusPedido;
import br.dev.blue.FastAndFuriousFood.model.Pedido;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.dev.blue.FastAndFuriousFood.repository.PedidoRepository;

/**
 *
 * @author sesi3dib
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataCriacao(LocalDateTime.now());
        
        return repository.save(pedido);
        
        
        
    }

    public Pedido atualizarStatus(Long id, StatusPedido status) {
       Optional<Pedido> optional = (Optional<Pedido>) repository.findById(id);

        if (optional.isEmpty()) {
    throw new RuntimeException("Pedido não encontrado");
        }

        Pedido pedido = optional.get();

        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido já finalizado");
        }

        pedido.setStatus(status);
        return repository.save(pedido);
    }
}
