package com.gleison.apphamburgueria.repositories;

import com.gleison.apphamburgueria.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
