/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.controller;

import br.dev.blue.FastAndFuriousFood.DTO.ProdutoDTO;
import br.dev.blue.FastAndFuriousFood.model.Categoria;
import br.dev.blue.FastAndFuriousFood.model.Produto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.dev.blue.FastAndFuriousFood.repository.ProdutoRepository;

/**
 *
 * @author sesi3dib
 */
@RestController
@RequestMapping("/fastfurious/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Produto criar(@Valid @RequestBody ProdutoDTO dto) {
        Produto p = new Produto();
        p.setNome(dto.nome);
        p.setCategoria(Categoria.valueOf(dto.categoria.toUpperCase()));
        p.setPreco(dto.preco);
        return repository.save(p);
    }
}
