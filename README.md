personas-backend-java
=====================

Previamente se ha configurado en un servidor GlashFish un Pool de conexiones a una tabla llamada personas (con los campos idPersona, nombre, apellido, email y telefono) de una BBDD MySql, y con el se creo un JDBC Resources llamado jdbc/PersonaDb, que es el utilizado en el persistence.xml. 

En realidad es un backend de pruebas, con apenas seguridad, usando JAX-RS, EJB y con una conexión JTA, y donde se permite peticiones desde cualquier lugar, es decir, con la cabecera "Access-Control-Allow-Origin" a "*" .

En mi caso particular era para hacer pruebas con clientes en Angular y ReactJS.