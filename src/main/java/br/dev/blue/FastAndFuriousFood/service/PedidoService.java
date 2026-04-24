/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.service;

import br.dev.blue.FastAndFuriousFood.model.StatusPedido;
import br.dev.blue.FastAndFuriousFood.model.Pedido;
import br.dev.blue.FastAndFuriousFood.model.Produto;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.dev.blue.FastAndFuriousFood.repository.PedidoRepository;
import br.dev.blue.FastAndFuriousFood.repository.ProdutoRepository;
import java.util.List;

/**
 *
 * @author sesi3dib
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    
      @Autowired
    private ProdutoRepository produtoRepository;
      
      
    public Pedido criarPedido(Pedido pedido) {

        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataCriacao(LocalDateTime.now());

        Long novoNumero = repository.findTopByOrderByNumeroDesc()
    .map(p -> {
        Long num = p.getNumero();
        return (num == null ? 1L : num + 1);
    })
    .orElse(1L);
        
          System.out.println("Numero gerado: " + novoNumero);

        pedido.setNumero(novoNumero);
    
   List<Produto> produtos = pedido.getProdutos().stream()
                .map(p -> produtoRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado")))
                .toList();

        pedido.setProdutos(produtos);

        return repository.save(pedido);
    }
   
     public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {

        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        StatusPedido atual = pedido.getStatus();

        if (!transicaoValida(atual, novoStatus)) {
            throw new RuntimeException("Transição inválida");
        }

        if (novoStatus == StatusPedido.EM_PREPARACAO && pedido.getInicioPreparo() == null) {
            pedido.setInicioPreparo(LocalDateTime.now());
        }

        pedido.setStatus(novoStatus);

        return repository.save(pedido);
    }

    private boolean transicaoValida(StatusPedido atual, StatusPedido novo) {
        return switch (atual) {
            case ABERTO -> novo == StatusPedido.EM_PREPARACAO;
            case EM_PREPARACAO -> novo == StatusPedido.PRONTO;
            case PRONTO -> novo == StatusPedido.ENTREGUE;
            case ENTREGUE -> false;
        };
    }
}