package ue.edu.co.personas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitud")
public class SolicitudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre_s;
    private String tipo_s;
    private Long cliente_s;

    public void setNombre(String nombre){
        this.nombre_s = nombre;
    }

    public String getNombre(){
        return nombre_s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long cliente) {
        this.cliente_s = cliente;
    }

    public Long getCliente() {
        return cliente_s;
    }

    public void setCliente(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo_s;
    }

    public void setTipo(String tipo) {
        this.tipo_s = tipo;
    }

}