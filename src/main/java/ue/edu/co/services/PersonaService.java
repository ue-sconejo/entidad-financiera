package ue.edu.co.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.models.PersonaModel;
import ue.edu.co.repositories.PersonaRepository;

@Service
public class PersonaService {
    
    @Autowired
    PersonaRepository repo;
    
    public ArrayList<PersonaModel> obtenerTodos() {
        return (ArrayList<PersonaModel>) repo.findAll();
    }

    public PersonaModel guardar(PersonaModel persona) {
        return repo.save(persona);
    }

    public Optional<PersonaModel> obtenerPorId(Long id) {
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