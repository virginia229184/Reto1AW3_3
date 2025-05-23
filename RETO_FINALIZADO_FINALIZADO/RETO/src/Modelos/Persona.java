/**
 * paquete Modelos, que contiene las clases Administrador, Empleado, Persona
 */
package Modelos;

import java.io.Serializable;

/**
 * clase abstracta de persona, implementa serializable para poder guardar objetos en archivos
 * tiene datos como dni, nombre, apellido, rol, email, telefono, contrasena
 * 
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public abstract class Persona implements Serializable{
    
	
	/**
	 * dni de la persona
	 */
    String Dni;
    
    /**
     * nombre de la persona
     */
    String nombre;
    /**
     * apellido de la persona
     */
    String apellido; 
    /**
     * rol de la persona
     */
    String rol;
    /**
     * email de la persona
     */
    String email;
    /**
     * email de la persona
     */
    String telefono;
    /**
     * telefono de la persona
     */
    
    /**
     * contrasena de la persona
     */
    String contrasena;
    
   /**
    * constructor con todos los parametros
    * @param Dni dni de la persona
    * @param nombre nombre de la persona
    * @param apellido apellido de la persona
    * @param rol rol de la persona
    * @param email email de la persona
    * @param telefono telefono de la persona
    * @param contrasena contrasena de la persona
    */

    public Persona(String Dni, String nombre, String apellido, String rol, String email, String telefono,
            String contrasena) {
        this.Dni = Dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    /**
     * constructor vacio
     */
    public Persona() {

    }

    /**
     * devuelve el dni de la persona
     * @return dni
     */
    public String getDNI() {
        return Dni;
    }

    /**
     * asigna un nuevo valor al dni
     * @param Dni nuevo dni
     */
    public void setDNI(String Dni) {
      this.Dni = Dni;
    }

    /**
     * devuelve el nombre de la persona
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * asigna un nuevo valor al nombre
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve el apellido de la persona
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * asigna un nuevo valor al apellido
     * @param apellido nuevo apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * devuelve el rol de la persona
     * @return rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * asigna un nuevo valor al rol
     * @param rol nuevo rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * devuelve el enail de la persona
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * asigna un nuevo valor al email
     * @param email nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * devuelve el telefono de la persona
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * asigna un nuevo valor al telefono
     * @param telefono nuevo telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * devuelve la contranse de la persona
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * asigna un nuevo valor al contrasena
     * @param contrasena nueva contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    /**
     * metodo toString que devuelveen forma de texto todos los atributos de la persona
     */
    @Override
    public String toString() {
        return "Persona [DNI=" + Dni + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", email="
                + email + ", telefono=" + telefono + ", contraseña=" + contrasena + "]";
    }

}