package com.gleison.apphamburgueria.services;

import com.gleison.apphamburgueria.domain.Cliente;
import com.gleison.apphamburgueria.dto.ClienteDTO;
import com.gleison.apphamburgueria.repositories.ClienteRepository;
import com.gleison.apphamburgueria.services.exception.DataIntegrityException;
import com.gleison.apphamburgueria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }


    public Optional<Cliente> find(Long id){

        Optional<Cliente> objCliente = clienteRepository.findById(id);
        if(objCliente == null){

            throw new ObjectNotFoundException("Objeto não encontrado! Id: "+id+
                    ", Tipo: " + Cliente.class.getName());

        }

        return objCliente;
    }

    public  Cliente insert(Cliente objCliente){
        objCliente.setId(null);
        return  clienteRepository.save(objCliente);
    }

    public Cliente update(Cliente obj){

      Cliente objCliente;
    

      return clienteRepository.save(objCliente);
    }

    private void updateData(ObjCliente, obj){




    }


    public void delete(Long id){
        find(id);

        try {
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){

            throw new DataIntegrityException("Não é possível Excluir uma " +
                    "Categoria que possui Produtos");

        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPage, Sort.Direction.valueOf(direction), orderBy);

        return clienteRepository.findAll(pageRequest);
    }


    public Cliente fromDTO(ClienteDTO objDTO){

      return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

}
