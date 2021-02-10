package com.gleison.apphamburgueria.controller;

import com.gleison.apphamburgueria.domain.Cliente;
import com.gleison.apphamburgueria.repositories.ClienteRepository;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<?> listar(){

        List<Cliente> clientes = clienteRepository.findAll();

        return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Cliente find(@PathVariable Long id){
        Optional<Cliente> objCliente = clienteRepository.findById(id);
        return objCliente.orElseThrow(()
                -> new ObjectNotFoundException(" Código do Cliente não encontrado! Código: "+id +
                ", Tipo: " + Cliente.class.getName()));
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente, HttpServletResponse response){
        Cliente clienteSalva = clienteRepository.save(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteSalva.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());

        return ResponseEntity.created(uri).body(clienteSalva);
    }



}
