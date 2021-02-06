package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
     Optional<Categoria> objCategoria = categoriaRepository.findById(id);
     return objCategoria.orElseThrow(()
             -> new ObjectNotFoundException(" Código de Categoria não encontrado! Código: "+id +
                ", Tipo: " + Categoria.class.getName()));
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(categoriaSalva.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());

      return ResponseEntity.created(uri).body(categoriaSalva);
    }


}