package com.gleison.apphamburgueria.controller;


import com.gleison.apphamburgueria.domain.Pedido;
import com.gleison.apphamburgueria.repositories.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoRepository pedidoRepository;


    public ResponseEntity<?> listar(){

        List<Pedido> pedidos= pedidoRepository.findAll();

        return !pedidos.isEmpty() ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
    }




}
