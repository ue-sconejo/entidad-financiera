package ue.edu.co.personas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.personas.models.TipoPersonaModel;
import ue.edu.co.personas.repositories.TipoPersonaRepository;

@Service
public class TipoPersonaService {
    @Autowired
    TipoPersonaRepository tipoPersonaRepository;

    public ArrayList<TipoPersonaModel> obtnerTodos() {
        return (ArrayList<TipoPersonaModel>) tipoPersonaRepository.findAll();
    }

    public TipoPersonaModel guardar(TipoPersonaModel tp) {
        return tipoPersonaRepository.save(tp);
    }

    public Optional<TipoPersonaModel> obtenerPorId(Long id) {
        return tipoPersonaRepository.findById(id);
    }

    public boolean eliminar(Long id) {
        try {
            tipoPersonaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
