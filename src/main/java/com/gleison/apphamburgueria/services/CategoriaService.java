package com.gleison.apphamburgueria.services;

import com.gleison.apphamburgueria.domain.Categoria;
import com.gleison.apphamburgueria.dto.CategoriaDTO;
import com.gleison.apphamburgueria.services.exception.DataIntegrityException;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.gleison.apphamburgueria.repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){

        return categoriaRepository.findAll();
    }


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

       public void delete(Long id){
       find(id);

       try {
           categoriaRepository.deleteById(id);
           }
       catch (DataIntegrityViolationException e){

           throw new DataIntegrityException("Não é possível Excluir uma " +
                   "Categoria que possui Produtos");

          }
       }

       public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){

           PageRequest pageRequest = PageRequest.of(page, linesPage, Sort.Direction.valueOf(direction), orderBy);

       return categoriaRepository.findAll(pageRequest);
        }


        public Categoria fromDTO(CategoriaDTO objDTO){

        return new Categoria(objDTO.getId(), objDTO.getNome());
        }
    }



