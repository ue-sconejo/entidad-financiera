package ue.edu.co.controllers;

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
import org.springframework.web.bind.annotation.RestController;
import ue.edu.co.models.PersonaModel;

import ue.edu.co.models.SolicitudModel;
import ue.edu.co.services.SolicitudService;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {
    @Autowired
    SolicitudService service;

    // Create
    @PostMapping()
    public SolicitudModel gaurdarSolicitud(@RequestBody SolicitudModel tp) {
        return this.service.guardar(tp);
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
    @PutMapping(value = "/actualizar")
    public String actualziarSolicitud(@RequestBody SolicitudModel solicitud) {
        // Obtiene la data actual
        Optional<SolicitudModel> actualizaInfo = this.service.obtenerPorId(solicitud.getId());
        // Compara si ya esta registrada la data
        if (actualizaInfo.isPresent()) {
            service.guardar(solicitud);
            return "Info Actualizada en: " + solicitud.getId();
        } else {
            service.guardar(solicitud);
            return "Info creada con exito";
        }
    }

    @GetMapping(path="procesar")
    public String consultaApi(@RequestBody SolicitudModel solicitud) {
        PersonaModel persona = solicitud.getPersona();
        String documento = persona.getDocumento();
        if(service.consultarDataCredito(documento) >= 350 && !service.consultarBlackList(documento)) {
            return service.crearProducto(solicitud);
        } else {
            return "Rechazado";
        }
    }
}