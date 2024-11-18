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

import ue.edu.co.models.ProductoModel;
import ue.edu.co.models.SolicitudModel;
import ue.edu.co.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService service;

    // Create
    @PostMapping()
    public ProductoModel guardarProducto(@RequestBody ProductoModel p) {
        return this.service.guardar(p);
    }

    // Read
    @GetMapping()
    public ArrayList<ProductoModel> obtenerProductos() {
        return service.obtenerTodos();
    }

    @GetMapping(path = "/getBySolicitud")
    public ArrayList<ProductoModel> obtenerTodosPorServicio(@RequestBody SolicitudModel solicitud) {
        return service.obtenerTodosPorSolicitud(solicitud);
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductoModel> optenerPorId(@PathVariable("id") Long id) {
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
    public String actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductoModel newData) {
        // Obtiene la data actual
        Optional<ProductoModel> actualizaInfo = this.service.obtenerPorId(id);
        // Compara si ya esta registrada la data
        if (actualizaInfo.isPresent()) {
            ProductoModel getData = actualizaInfo.get();
            service.updateData(getData, newData);
            return "Info Actualizada: " + id;
        } else {
            ProductoModel createNew = new ProductoModel();
            service.updateData(createNew, newData);
            return "Info creada";
        }
    }
}