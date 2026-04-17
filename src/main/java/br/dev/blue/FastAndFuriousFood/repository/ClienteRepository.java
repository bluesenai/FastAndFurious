/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.repository;

import br.dev.blue.FastAndFuriousFood.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dib
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
