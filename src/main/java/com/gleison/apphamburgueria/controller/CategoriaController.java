package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;
import com.gleison.apphamburgueria.services.CategoriaService;
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
    private CategoriaRepository categoriaRepository; //Será Substituído pelo Service

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<?> listar(){

        List<Categoria> categorias = categoriaRepository.findAll();

      return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Categoria buscar(@PathVariable Long id){
     Optional<Categoria> objCategoria = service.find(id);

     return objCategoria.orElseThrow(()
             -> new ObjectNotFoundException(" Código de Categoria não encontrado! Código: "+id +
             ", Tipo: " + Categoria.class.getName()));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria, HttpServletResponse response){

        categoria = service.insert(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(categoria.getId()).toUri();

      return ResponseEntity.created(uri).body(categoria);
    }

    //RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //public ResponseEntity<Void> update(@RequestBody Categoria objCategoria, @PathVariable Long id){

        // objCategoria.setId(id);
   //}

}
