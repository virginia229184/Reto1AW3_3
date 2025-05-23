
/**
 * paquete Modelos, que contiene las clases Administrador, Empleado, Persona
 */
package Modelos;


/**
 * Clase empleado que hereda de persona
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public class Empleado extends Persona{
	
	/**
	 * constructor con un solo parametro
	 * @param Dni dni del empleado
	 */
	
	public Empleado(String Dni) {
		this.Dni=Dni;
	}

	/**
	 * constuctor con todos los parametros
	 * @param Dni dni del empleado 
	 * @param nombre nombre del empleado
	 * @param apellido apellido del empleado
	 * @param rol rol del empleado
	 * @param email  email del empleado
	 * @param telefono telefono del empleado
	 * @param contrasena contrasena del empleado
	 */
	public Empleado(String Dni, String nombre, String apellido, String rol, String email, String telefono,
			String contrasena) {
		// TODO Auto-generated constructor stub
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
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	
	
}
