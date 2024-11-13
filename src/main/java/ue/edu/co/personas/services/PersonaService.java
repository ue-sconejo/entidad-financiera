package ue.edu.co.personas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ue.edu.co.personas.models.PersonaModel;
import ue.edu.co.personas.repositories.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public ArrayList<PersonaModel> obtenerPersona() {
        return (ArrayList<PersonaModel>) personaRepository.findAll();
    }

    public PersonaModel guardarPersona(PersonaModel persona) {
        return personaRepository.save(persona);
    }

    public Optional<PersonaModel> obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }

    public ArrayList<PersonaModel> obtenerPorPrioridad(Integer prioridad) {
        return personaRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarPersona(Long id) {
        try {
            personaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
