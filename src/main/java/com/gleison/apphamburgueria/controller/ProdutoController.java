package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Produto;
import com.gleison.apphamburgueria.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<?> listar(){

        List<Produto> produtos = produtoRepository.findAll();


       return !produtos.isEmpty() ? ResponseEntity.ok(produtos) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Produto find(@PathVariable Long id){

        Optional<Produto> objProduto =  produtoRepository.findById(id);

        return objProduto.orElse(null);
    }


}
