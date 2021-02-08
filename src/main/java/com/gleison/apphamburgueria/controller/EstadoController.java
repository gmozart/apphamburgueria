package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Estado;
import com.gleison.apphamburgueria.repositories.EstadoRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @RequestMapping
    public ResponseEntity<?> listar(){

        List<Estado> estados = estadoRepository.findAll();


        return !estados.isEmpty() ? ResponseEntity.ok(estados) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Estado find(@PathVariable Long id){

        Optional<Estado> objEstado = estadoRepository.findById(id);

        
      return objEstado.orElseThrow(() -> new ObjectNotFoundException("Código do Estado não encontrado! Código: "+id+
              ", Tipo: " + Estado.class.getName()));
    }

}
