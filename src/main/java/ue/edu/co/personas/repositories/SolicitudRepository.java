package ue.edu.co.personas.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.personas.models.SolicitudModel;

@Repository
public interface SolicitudRepository extends CrudRepository<SolicitudModel, Long> {
    public abstract ArrayList<SolicitudModel> findByCliente(Long cliente);
}
