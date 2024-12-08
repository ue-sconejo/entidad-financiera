package ue.edu.co.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.models.TipoPersonaModel;
import ue.edu.co.repositories.TipoPersonaRepository;

@Service
public class TipoPersonaService {
    @Autowired
    TipoPersonaRepository repo;

    public ArrayList<TipoPersonaModel> obtenerTodos() {
        return (ArrayList<TipoPersonaModel>) repo.findAll();
    }

    public TipoPersonaModel guardar(TipoPersonaModel tp) {
        return repo.save(tp);
    }

    public Optional<TipoPersonaModel> obtenerPorId(Long id) {
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