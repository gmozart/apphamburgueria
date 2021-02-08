package com.gleison.apphamburgueria.controller;

import com.gleison.apphamburgueria.domain.Cidade;
import com.gleison.apphamburgueria.domain.Produto;
import com.gleison.apphamburgueria.repositories.CidadeRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/cidades")
@RestController
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;

  @RequestMapping
  public ResponseEntity<?> listar(){

      List<Cidade> cidades = cidadeRepository.findAll();

     return !cidades.isEmpty() ? ResponseEntity.ok(cidades) : ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Cidade find(@PathVariable Long id){

      Optional<Cidade> objCidade = cidadeRepository.findById(id);

      return objCidade.orElseThrow(() -> new ObjectNotFoundException("Código da Cidade não encontrado! Código: "+id+
              ", Tipo: " + Produto.class.getName()));
  }

  @PostMapping
  public ResponseEntity<Cidade> criarCidade(@RequestBody Cidade cidade, HttpServletResponse response){

      Cidade cidadeSalva = cidadeRepository.save(cidade);

      URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
              .buildAndExpand(cidadeSalva.getId()).toUri();

      return ResponseEntity.created(uri).body(cidadeSalva);
  }


}
