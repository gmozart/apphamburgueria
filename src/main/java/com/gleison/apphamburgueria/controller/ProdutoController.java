package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Produto;
import com.gleison.apphamburgueria.repositories.ProdutoRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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

        return objProduto.orElseThrow(() -> new ObjectNotFoundException(" Código do Produto não encontrado! Código: "+id+
        ", Tipo: " + Produto.class.getName()));
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto, HttpServletResponse response){

        Produto produtoSalvo = produtoRepository.save(produto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(produtoSalvo.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());

     return ResponseEntity.created(uri).body(produtoSalvo);
    }

}
