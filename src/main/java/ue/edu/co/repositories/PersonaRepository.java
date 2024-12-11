package ue.edu.co.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.models.PersonaModel;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Long> {
}