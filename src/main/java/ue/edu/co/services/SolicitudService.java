package ue.edu.co.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ue.edu.co.models.PersonaModel;
import ue.edu.co.models.ProductoModel;

import ue.edu.co.models.SolicitudModel;
import ue.edu.co.repositories.ProductoRepository;
import ue.edu.co.repositories.SolicitudRepository;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository repo;
    
    @Autowired
    ProductoRepository prodRepo;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    EmailService emailService;
    
    public boolean consultarBlackList(String documento) {
        String blackList = "http://localhost:8081/BlackList/web/personas/buscar/" + documento;
        ResponseEntity<String> response = restTemplate.getForEntity(blackList, String.class);
        return Boolean.parseBoolean(response.getBody());
    }
    
    public int consultarDataCredito(String documento) {
        String dataCredito = "http://localhost:8081/DataCredito/web/personas/buscar/" + documento;
        ResponseEntity<String> response = restTemplate.getForEntity(dataCredito, String.class);
        return Integer.parseInt(response.getBody());
    }
    
    public String crearProducto (SolicitudModel solicitud) {
        ProductoModel tarjeta = new ProductoModel();
        PersonaModel persona = solicitud.getPersona();
        solicitud.setEstado("Aprovado");
        tarjeta.setNombre("Tarjeta de Credito");
        tarjeta.setSolicitud(solicitud);
        prodRepo.save(tarjeta);
        repo.save(solicitud);
        emailService.sendSimpleEmail(persona.getEmail(), "Entidad Financiera", "Completada la solicitud de tarjeta de credito");
        return "exito";
    }

    public ArrayList<SolicitudModel> obtnerTodos() {
        return (ArrayList<SolicitudModel>) repo.findAll();
    }

    public SolicitudModel guardar(SolicitudModel tp) {
        return repo.save(tp);
    }

    public Optional<SolicitudModel> obtenerPorId(Long id) {
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