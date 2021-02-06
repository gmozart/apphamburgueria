package com.gleison.apphamburgueria.controller;

import com.gleison.apphamburgueria.domain.Cidade;
import com.gleison.apphamburgueria.repositories.CidadeRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;

  public ResponseEntity<?> listar(){

      List<Cidade> cidades = cidadeRepository.findAll();

     return !cidades.isEmpty() ? ResponseEntity.ok(cidades) : ResponseEntity.noContent().build();
  }

}
