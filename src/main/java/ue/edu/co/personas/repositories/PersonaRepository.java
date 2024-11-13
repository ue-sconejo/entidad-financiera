package ue.edu.co.personas.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.personas.models.PersonaModel;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Long> {
    public abstract ArrayList<PersonaModel> findByPrioridad(Integer prioridad);
}
