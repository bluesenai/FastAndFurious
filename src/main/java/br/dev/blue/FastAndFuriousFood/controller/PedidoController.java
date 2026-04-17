/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.controller;

import br.dev.blue.FastAndFuriousFood.model.StatusPedido;
import br.dev.blue.FastAndFuriousFood.model.Pedido;
import br.dev.blue.FastAndFuriousFood.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.dev.blue.FastAndFuriousFood.repository.PedidoRepository;

/**
 *
 * @author sesi3dib
 */
@RestController
@RequestMapping("/fastfurious/pedido")
public class PedidoController {
  @Autowired
    private PedidoService service;

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return service.criarPedido(pedido);
    }

    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id,
                                  @RequestParam StatusPedido status) {
        return service.atualizarStatus(id, status);
    }

    @GetMapping("/status/{status}")
    public List<Pedido> porStatus(@PathVariable StatusPedido status) {
        return repository.findByStatus(status);
    }
}