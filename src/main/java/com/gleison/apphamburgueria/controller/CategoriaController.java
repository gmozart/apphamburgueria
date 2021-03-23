package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.dto.CategoriaDTO;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;
import com.gleison.apphamburgueria.services.CategoriaService;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository; //Será Substituído pelo Service

    @Autowired
    private CategoriaService service;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){

        List<Categoria> ListaCategorias = service.findAll();
        List<CategoriaDTO> listDTO = ListaCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
      return !listDTO.isEmpty() ? ResponseEntity.ok(listDTO) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Categoria buscar(@PathVariable Long id){
     Optional<Categoria> objCategoria = service.find(id);

     return objCategoria.orElseThrow(()
             -> new ObjectNotFoundException(" Código de Categoria não encontrado! Código: "+id +
             ", Tipo: " + Categoria.class.getName()));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaDTO objDTO, HttpServletResponse response){

        Categoria objCategoria = service.fromDTO(objDTO);

        objCategoria = service.insert(objCategoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(objCategoria.getId()).toUri();

      return ResponseEntity.created(uri).body(objCategoria);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Long id){
         Categoria objCategoria = service.fromDTO(objDTO);
         objCategoria.setId(id);
         objCategoria = service.update(objCategoria);

         return ResponseEntity.noContent().build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public  ResponseEntity<Void> delete(@PathVariable Long id){

       service.delete(id);

       return ResponseEntity.noContent().build();
   }


    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
             @RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
             @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
             @RequestParam(value ="direction", defaultValue = "ASC") String direction
            ){

        Page<Categoria> list = service.findPage(page, linesPage, orderBy, direction);
        Page<CategoriaDTO> listDTO = list.map(objCategoria -> new CategoriaDTO(objCategoria));
        return ResponseEntity.ok().body(listDTO);
    }

}
