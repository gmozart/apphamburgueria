package com.gleison.apphamburgueria.repositories;

import com.gleison.apphamburgueria.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
}
