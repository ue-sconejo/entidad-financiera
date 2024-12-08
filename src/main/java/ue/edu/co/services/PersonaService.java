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

    // Remplaza la Info vieja con la nueva y la guarda
    public PersonaModel updateData(PersonaModel oldData, PersonaModel newData) {
        oldData.setNombre(newData.getNombre());
        oldData.setEmail(newData.getEmail());
        oldData.setTipoPersona(newData.getTipoPersona());
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