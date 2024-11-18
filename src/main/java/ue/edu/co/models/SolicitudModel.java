package ue.edu.co.models;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitud")
public class SolicitudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @ManyToOne
    private PersonaModel persona;

    @OneToMany(mappedBy="solicitud")
    private Set<ProductoModel> productos;

    private Date fechaCreacion;

    public void setPersona(PersonaModel p) {
        this.persona = p;
    }

    public PersonaModel getPersona() {
        return persona;
    }

    public Set<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoModel> productos) {
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fecha) {
        this.fechaCreacion = fecha;
    }
}