package br.dev.blue.FastAndFuriousFood.repository;

import br.dev.blue.FastAndFuriousFood.model.StatusPedido;
import br.dev.blue.FastAndFuriousFood.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author sesi3dib
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    public List<Pedido> findByStatus(StatusPedido status);

    
}
