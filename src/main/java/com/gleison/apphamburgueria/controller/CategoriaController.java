package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){

      return categoriaRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Categoria find(@PathVariable Long id){
     Optional<Categoria> obj = categoriaRepository.findById(id);
     return obj.orElse(null);

    }


}
