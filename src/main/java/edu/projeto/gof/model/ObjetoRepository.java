package edu.projeto.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetoRepository extends CrudRepository<Objeto, Long> {

}