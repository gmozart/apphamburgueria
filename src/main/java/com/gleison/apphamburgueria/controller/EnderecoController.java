package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Cidade;
import com.gleison.apphamburgueria.domain.Endereco;
import com.gleison.apphamburgueria.domain.Produto;
import com.gleison.apphamburgueria.repositories.EnderecoRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @RequestMapping
    public ResponseEntity<?> listar(){

        List<Endereco> enderecos = enderecoRepository.findAll();

        return !enderecos.isEmpty() ? ResponseEntity.ok(enderecos) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Endereco find(@PathVariable Long id){

        Optional<Endereco> objEndereco = enderecoRepository.findById(id);

        return objEndereco.orElseThrow(() -> new ObjectNotFoundException("Código de Endereço não encontrado! Código: "+id+
                ", Tipo: " + Produto.class.getName()));
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco, HttpServletResponse response){

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(enderecoSalvo.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());

        return ResponseEntity.created(uri).body(enderecoSalvo);
    }



}
