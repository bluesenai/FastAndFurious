/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.blue.FastAndFuriousFood.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author sesi3dib
 */
public class ProdutoDTO {

    @NotBlank
    public String nome;

    @NotBlank
    public String categoria;

    @Positive
    public double preco;
}
