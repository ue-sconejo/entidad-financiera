package ue.edu.co.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.models.SolicitudModel;
import ue.edu.co.repositories.SolicitudRepository;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository repo;

    public ArrayList<SolicitudModel> obtnerTodos() {
        return (ArrayList<SolicitudModel>) repo.findAll();
    }

    public SolicitudModel guardar(SolicitudModel tp) {
        return repo.save(tp);
    }

    public Optional<SolicitudModel> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // Remplaza la Info vieja con la nueva y la guarda
    public SolicitudModel updateData(SolicitudModel oldData, SolicitudModel newData) {
        oldData.setFechaCreacion(newData.getFechaCreacion());
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