package ue.edu.co.personas.controllers;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ue.edu.co.personas.models.PersonaModel;
import ue.edu.co.personas.services.PersonaService;

@RestController
@RequestMapping("/persona")

public class PersonaController {
    @Autowired
    PersonaService personaService;

    // Create
    @PostMapping()
    public PersonaModel guardaPersona(@RequestBody PersonaModel persona){
        return this.personaService.guardarPersona(persona);
    }
    // Read
    @GetMapping()
    public ArrayList<PersonaModel> obtenerPersonas(){
        return personaService.obtenerPersona();
    }

    @GetMapping(  path = "/{id}" )
    public Optional<PersonaModel> optenerPorId(@PathVariable("id") Long id){
        return this.personaService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<PersonaModel> obtenerPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.personaService.obtenerPorPrioridad(prioridad);
    }

    // Delete
    @DeleteMapping( path = "/{id}" )
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.personaService.eliminarPersona(id);
        if(ok){
            return "Persona eliminada id:" + id;
        }else{
            return "No se pudo eliminar a la persona con el id: " + id;
        }
    }

    // Update
    @PutMapping( value = "/actualizar/{id}" )
    public String actualizarPersona(@PathVariable("id") Long id, @RequestBody PersonaModel persona){
        Optional<PersonaModel> actualizaPersona = this.personaService.obtenerPorId(id);
        if(actualizaPersona.isPresent()){
            PersonaModel existePersona = actualizaPersona.get();
            getPersona(existePersona, persona);
            return "Persona Actualizada: " + id;
        }else{
            PersonaModel nuevaPersona = new PersonaModel();
            getPersona(nuevaPersona, persona);
            return "Persona creada";
        }
        
    }

    private PersonaModel getPersona(PersonaModel oldPersona, PersonaModel newPersona){
        oldPersona.setNombre(newPersona.getNombre());
        oldPersona.setEmail(newPersona.getEmail());
        oldPersona.setPrioridad(newPersona.getPrioridad());
        personaService.guardarPersona(oldPersona);
        return oldPersona;
    }
}

