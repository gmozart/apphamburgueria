package com.gleison.apphamburgueria.repositories;

import com.gleison.apphamburgueria.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
