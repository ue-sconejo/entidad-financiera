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

    public ProductoModel guardar(ProductoModel p) {
        return repo.save(p);
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return repo.findById(id);
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