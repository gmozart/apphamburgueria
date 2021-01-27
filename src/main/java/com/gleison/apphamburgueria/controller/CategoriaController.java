package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<?> listar(){

        List<Categoria> categorias = categoriaRepository.findAll();

      return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Categoria find(@PathVariable Long id){
     Optional<Categoria> obj = categoriaRepository.findById(id);
     return obj.orElse(null);
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
        .buildAndExpand(categoriaSalva.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());

      return ResponseEntity.created(uri).body(categoriaSalva);
    }


}
