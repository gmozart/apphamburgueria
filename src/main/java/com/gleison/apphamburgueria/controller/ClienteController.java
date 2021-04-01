package com.gleison.apphamburgueria.controller;

import com.gleison.apphamburgueria.domain.Cliente;
import com.gleison.apphamburgueria.dto.ClienteDTO;
import com.gleison.apphamburgueria.services.ClienteService;
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

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ClienteService service;


    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){

        List<Cliente> ListaCategorias = service.findAll();
        List<ClienteDTO> listDTO = ListaCategorias.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return !listDTO.isEmpty() ? ResponseEntity.ok(listDTO) : ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Cliente buscar(@PathVariable Long id){
        Optional<Cliente> objCliente = service.find(id);

        return objCliente.orElseThrow(()
                -> new ObjectNotFoundException(" Código de Cliente não encontrado! Código: "+id +
                ", Tipo: " + Cliente.class.getName()));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteDTO objDTO, HttpServletResponse response){

        Cliente objCliente = service.fromDTO(objDTO);

        objCliente = service.insert(objCliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(objCliente.getId()).toUri();

        return ResponseEntity.created(uri).body(objCliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Long id){
        Cliente objCliente = service.fromDTO(objDTO);
        objCliente.setId(id);
        objCliente = service.update(objCliente);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<Void> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value ="direction", defaultValue = "ASC") String direction
    ){

        Page<Cliente> list = service.findPage(page, linesPage, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(objCategoria -> new ClienteDTO(objCategoria));
        return ResponseEntity.ok().body(listDTO);
    }



}
