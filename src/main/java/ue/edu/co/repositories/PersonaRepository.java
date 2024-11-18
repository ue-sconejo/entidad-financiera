package ue.edu.co.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.models.PersonaModel;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Long> {
    // Metodo de busqueda por prioridad
    public abstract ArrayList<PersonaModel> findByPrioridad(Integer prioridad);
}