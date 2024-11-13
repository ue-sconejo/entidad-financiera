package ue.edu.co.personas.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ue.edu.co.personas.models.TipoPersonaModel;
import ue.edu.co.personas.services.TipoPersonaService;

@RestController
@RequestMapping("/tipo-persona")

public class TipoPersonaController {
    @Autowired
    TipoPersonaService service;

    // Create
    @PostMapping()
    public TipoPersonaModel guardaPersona(@RequestBody TipoPersonaModel persona) {
        return this.service.guardar(persona);
    }

    // Read
    @GetMapping()
    public ArrayList<TipoPersonaModel> obtenerPersonas() {
        return service.obtnerTodos();
    }

    @GetMapping(path = "/{id}")
    public Optional<TipoPersonaModel> optenerPorId(@PathVariable("id") Long id) {
        return this.service.obtenerPorId(id);
    }

    // Delete
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.service.eliminar(id);
        if (ok) {
            return "Eliminado el id: " + id;
        } else {
            return "No se pudo eliminar con el id: " + id;
        }
    }

    // Update
    @PutMapping(value = "/actualizar/{id}")
    public String actualizarPersona(@PathVariable("id") Long id, @RequestBody TipoPersonaModel newData) {
        Optional<TipoPersonaModel> actualizaInfo = this.service.obtenerPorId(id);
        if (actualizaInfo.isPresent()) {
            TipoPersonaModel getData = actualizaInfo.get();
            updateData(getData, newData);
            return "Info Actualizada: " + id;
        } else {
            TipoPersonaModel createNew = new TipoPersonaModel();
            updateData(createNew, newData);
            return "Info creada";
        }
    }

    private TipoPersonaModel updateData(TipoPersonaModel oldData, TipoPersonaModel newData) {
        oldData.setNombre(newData.getNombre());
        service.guardar(oldData);
        return oldData;
    }
}