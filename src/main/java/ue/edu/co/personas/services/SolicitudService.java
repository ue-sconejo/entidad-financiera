package ue.edu.co.personas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.personas.models.SolicitudModel;
import ue.edu.co.personas.repositories.SolicitudRepository;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;

    public ArrayList<SolicitudModel> obtnerTodos() {
        return (ArrayList<SolicitudModel>) solicitudRepository.findAll();
    }

    public SolicitudModel guardar(SolicitudModel solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public Optional<SolicitudModel> obtenerPorId(Long id) {
        return solicitudRepository.findById(id);
    }

    public boolean eliminar(Long id) {
        try {
            solicitudRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
