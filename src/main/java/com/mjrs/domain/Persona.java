package com.mjrs.domain;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name="Persona.encontrarTodasPersonas", query="SELECT p FROM Persona p ORDER BY p.idPersona")
})

public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona")
    private int idPersona;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    public Persona(){}
    public Persona(int idPersona){
        this.idPersona = idPersona;
    }
    public Persona(int idPersona, String nombre){
        this.idPersona = idPersona;
        this.nombre = nombre;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + '}';
    }
}
