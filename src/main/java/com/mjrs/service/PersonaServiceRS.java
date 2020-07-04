package com.mjrs.service;

import com.mjrs.data.PersonaDao;
import com.mjrs.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * En este caso no se va a crear la capa de Servicio, se va usar directamente en
 * esta clase personaDao, y no "complicar" mas la parte backend.
 *
 * Esta clase, webservice rest, al que se accederá por ruta "personas" y
 * devolverá un datos en formato json, es directame un ejb (por eso el
 * stateless) de tipo jax rs (api rest java) en el que se inyectará PersonaDao
 * mediante CDI (inyección de dependencias)
 * 
 * Para usar este servicio desde otros lugares sin que dea problemas de CORS,
 * luego se creará una clase llamada CorsFilter (en paquete com.mjrs.web), para añadir
 * en el header el "Access-Control-Allow-Origin" con valor "*", y que permita peticiones desde
 * cualquier sitio.
 * A mayores, después, crearemos una clase para activar este servicio web de jax-rs (en lugar de activarlo mediante de configurarlo en el web.xml),
 * que se llamará JAXRSActivator. Tras esto, podremos hacer un clean & build de la aplicación
 * y comprobarlo
 */
@Stateless
@Path("/personas")
public class PersonaServiceRS {

    @Inject
    private PersonaDao personaDao;

    /**
     * Metodo get de personas, que devolverá un json con la lista de personas, sin parametros
     * 
     * @return List personas 
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas() {
        List<Persona> personas = personaDao.encontrarTodasPersonas();
        System.out.println("Personas encontradas: " + personas);
        return personas;
    }

    /**
     * Método get que devolverá un json de una persona en concreto. Se le pasa
     * el parametro id, que será el id de persona que buscamos, la ruta para la
     * petición quedaría algo como /personas/{id} , donde id sería un valor
     * numérico, por ejemplo: /personas/1
     * 
     * @param id
     * @return 
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Persona encontrarPersona(@PathParam("id") int id) {
        // @PathParam("id") es la información para el servicio web, ya que estamos usando jax rs
        Persona persona = personaDao.encontrarPersona(new Persona(id));// encontrarPersona solo acepta Persona como parametro..
        System.out.println("Personas encontradas: " + persona);
        return persona;
    }

    /**
     * Será el post de personas (insert), al que se le pasará un objeto con los datos de
     * la persona a insertar
     *
     * @param persona
     * @return Persona La instancia de persona ya insertada y por tanto con el
     * id_persona
     */
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona) {
        personaDao.insertarPersona(persona);// encontrarPersona solo acepta Persona como parametro..
        System.out.println("Personas agreagada: " + persona);
        return persona;// Como insertarPersona, ademas de persist hace el flush por tanto va a tener id modificado en la instancia persona
    }
    
    /**
     * Será el PUT de personas (update), al que se le pasará el id de la persona a modificar
     * y un objeto con los datos de persona con los que actualizar la persona con el id indicado
     * 
     * @param id
     * @param persona
     * @return 
     */
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response encontrarPersona(@PathParam("id") int id, Persona personaConDatosModificados) {
        Persona persona = new Persona(id);
        if(persona != null){
            personaDao.actualizarPersona(personaConDatosModificados);
            System.out.println("Persona modificada: " + personaConDatosModificados);
            return Response.ok().entity(personaConDatosModificados).build(); // indicando que se ha modificado correctamente la persona con esos datos
        }else{
             return Response.status(Response.Status.NOT_FOUND).build(); // indicando que no se encontró
        }
    }
    
    /**
     * Será el DELETE de personas, al que se le pasará el id de la persona a eliminar
     * 
     * @param id
     * @return 
     */
    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id") int id) {
        personaDao.eliminarPersona(new Persona(id));
        System.out.println("Persona eliminada con id: " + id);
        return Response.ok().build(); // indicando que se ha eliminado correctamente, sin más datos
    }

}
