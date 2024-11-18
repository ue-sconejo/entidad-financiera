package ue.edu.co.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.models.TipoPersonaModel;

@Repository
public interface TipoPersonaRepository extends CrudRepository<TipoPersonaModel, Long> {
}