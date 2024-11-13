package ue.edu.co.personas.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ue.edu.co.personas.models.SolicitudModel;
import ue.edu.co.personas.services.SolicitudService;

@RestController
@RequestMapping("/solicitud")

public class SolicitudController {
    @Autowired
    SolicitudService service;

    // Create
    @PostMapping()
    public SolicitudModel guardaPersona(@RequestBody SolicitudModel solicitud) {
        return this.service.guardar(solicitud);
    }

    // Read
    @GetMapping()
    public ArrayList<SolicitudModel> obtenerSolicitudes() {
        return service.obtnerTodos();
    }

    @GetMapping(path = "/{id}")
    public Optional<SolicitudModel> optenerPorId(@PathVariable("id") Long id) {
        return this.service.obtenerPorId(id);
    }

    // @GetMapping("/query")
    // public ArrayList<SolicitudModel> obtenerPorCliente(@RequestParam("cliente")
    // Long cliente){
    // return this.service.obtenerPorCliente(cliente);
    // }

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
    @PutMapping(value = "/actualizar/{id}")
    public String actualizarPersona(@PathVariable("id") Long id, @RequestBody SolicitudModel solicitud) {
        Optional<SolicitudModel> actualizarData = this.service.obtenerPorId(id);
        if (actualizarData.isPresent()) {
            SolicitudModel existeData = actualizarData.get();
            getPersona(existeData, solicitud);
            return "Persona Actualizada: " + id;
        } else {
            SolicitudModel nuevaData = new SolicitudModel();
            getPersona(nuevaData, solicitud);
            return "Persona creada";
        }
    }

    private SolicitudModel getPersona(SolicitudModel oldData, SolicitudModel newData) {
        oldData.setNombre(newData.getNombre());
        oldData.setTipo(newData.getTipo());
        oldData.setCliente(newData.getCliente());
        service.guardar(oldData);
        return oldData;
    }
}