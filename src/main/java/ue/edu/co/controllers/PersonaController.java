package ue.edu.co.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ue.edu.co.models.PersonaModel;
import ue.edu.co.services.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    
    @Autowired
    private PersonaService service;
    
    // Create
    @PostMapping()
    public PersonaModel guardaPersona(@RequestBody PersonaModel persona) {
        return this.service.guardar(persona);
    }

    // Read
    @GetMapping()
    public ArrayList<PersonaModel> obtenerPersonas() {
        return service.obtenerTodos();
    }

    @GetMapping(path = "/{id}")
    public Optional<PersonaModel> optenerPorId(@PathVariable("id") Long id) {
        return this.service.obtenerPorId(id);
    }

    // Delete
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.service.eliminar(id);
        if (ok) {
            return "Persona eliminada id:" + id;
        } else {
            return "No se pudo eliminar a la persona con el id: " + id;
        }
    }

    // Update
    @PutMapping(value = "/actualizar")
    public String actualizarPersona(@RequestBody PersonaModel persona) {
        // Obtiene la data actual
        Optional<PersonaModel> actualizaPersona = this.service.obtenerPorId(persona.getId());
        // Compara si ya esta registrada la data
        if (actualizaPersona.isPresent()) {
            service.guardar(persona);
            return "Informacion Actualizada de la persona: " + persona.getNombre();
        } else {
            service.guardar(persona);
            return "Persona creada";
        }
    }
    
}