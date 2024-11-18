package ue.edu.co.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.models.SolicitudModel;

@Repository
public interface SolicitudRepository extends CrudRepository<SolicitudModel, Long> {
}