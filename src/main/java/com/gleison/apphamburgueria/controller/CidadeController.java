package com.gleison.apphamburgueria.controller;

import com.gleison.apphamburgueria.domain.Cidade;
import com.gleison.apphamburgueria.domain.Produto;
import com.gleison.apphamburgueria.repositories.CidadeRepository;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
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

  
}
