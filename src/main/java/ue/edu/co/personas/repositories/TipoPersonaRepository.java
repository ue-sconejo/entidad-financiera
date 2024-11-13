package ue.edu.co.personas.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.personas.models.TipoPersonaModel;

@Repository
public interface TipoPersonaRepository extends CrudRepository<TipoPersonaModel, Long> {

}