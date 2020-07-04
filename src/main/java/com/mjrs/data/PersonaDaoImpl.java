package com.mjrs.data;
/**
 * EJB que implementa PersonaDao, que usa la clase de dominio ("entidad") Persona
 * Para lo cual se configuró la unidad de persistencia, PersonaPU, en el persistence.xml  con una conexion jta, a un pool de conexiones en 
 * glasshfish, que anteriormente hemos configurado contra la tabla de personas de una base de datos mysql
 */
import com.mjrs.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonaDaoImpl implements PersonaDao{
    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;
    
    @Override
    public List<Persona> encontrarTodasPersonas() {
         //En la clase persona definimos el NamedQuery Persona.encontrarTodasPersonas
         //Para que devuelva todas las personas
         return em.createNamedQuery("Persona.encontrarTodasPersonas").getResultList();
    }

    @Override
    public Persona encontrarPersona(Persona persona) {
         return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public void insertarPersona(Persona persona) {
         em.persist(persona);
         em.flush();//se inserta en la bbdd y se actualiza la instancia persona con el id_persona
    }

    @Override
    public void actualizarPersona(Persona persona) {
         em.merge(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
         em.remove(em.merge(persona));
    }
    
}
