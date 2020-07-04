package com.mjrs.web;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
/**
 * Clase para superar la politica de CORS desde el cliente, es decir, una 
 * clase que introduciremos como filtro de respuesta, para añadir en los headers
 * la posibilidad de recibir peticiones de todos los sitios ("Access-Control-Allow-Origin", "*"), 
 * no teniendo problemas  de CORS desde el lado cliente.
 */

@Provider
public class CorsFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String,Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*"); // Permitirá peticiones desde cualquier sitio
        headers.add("Access-Control-Allow-Credentials", "true");//Permitirá contraseña y usuario, por si en un futuro se desea integrar..
        headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Aceept, Authorization");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD"); //Tipos de peticiones que permitiremos
    }
    
}
