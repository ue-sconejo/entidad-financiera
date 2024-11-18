package ue.edu.co.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ue.edu.co.models.ProductoModel;
import ue.edu.co.models.SolicitudModel;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {
    // Metdo de busqueda por solicitud
    public ArrayList<ProductoModel> findBySolicitud(SolicitudModel solicitud);
}