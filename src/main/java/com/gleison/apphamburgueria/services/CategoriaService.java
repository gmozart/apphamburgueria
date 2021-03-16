package com.gleison.apphamburgueria.services;

import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;

import java.util.Optional;


@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;


    public Optional<Categoria> find(Long id){

        Optional<Categoria> objCategoria = categoriaRepository.findById(id);

        if(objCategoria == null){

            throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id+
                    ", Tipo: " + Categoria.class.getName());

        }

        return objCategoria;
    }

       public  Categoria insert(Categoria objCategoria){
           objCategoria.setId(null);
           return  categoriaRepository.save(objCategoria);
       }

       public Categoria update(Categoria objCategoria){
        find(objCategoria.getId());

        return categoriaRepository.save(objCategoria);
       }


    }



