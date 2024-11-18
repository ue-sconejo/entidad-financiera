package ue.edu.co.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.models.ProductoModel;
import ue.edu.co.models.SolicitudModel;
import ue.edu.co.repositories.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository repo;

    public ArrayList<ProductoModel> obtenerTodos() {
        return (ArrayList<ProductoModel>) repo.findAll();
    }

    public ArrayList<ProductoModel> obtenerTodosPorSolicitud(SolicitudModel s) {
        return (ArrayList<ProductoModel>) repo.findBySolicitud(s);
    }

    public ProductoModel guardar(ProductoModel persona) {
        return repo.save(persona);
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // Remplaza la Info vieja con la nueva y la guarda
    public ProductoModel updateData(ProductoModel oldData, ProductoModel newData) {
        oldData.setNombre(newData.getNombre());
        oldData.setSolicitud(newData.getSolicitud());
        this.guardar(oldData);
        return oldData;
    }

    public boolean eliminar(Long id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}