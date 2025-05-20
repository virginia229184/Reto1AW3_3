
/**
 * paquete test, en el se encuentra la clase EmpleadoTest
 */
package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Modelos.Empleado;
import Modelos.Persona;


/**
 * clase empledaoTest
 * pruebas unitarias para la clase empleado
 * se hacen pruebas de getters, setters, toString
 * 
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */

public class EmpleadoTest {
	
	/**
	 * constructor vacio
	 */
	public EmpleadoTest() {
		
	}
	
	/**
	 * prueba del método getDNI()
	 */

	@Test
	void testGetDNI() {
		String dni = "12345678A";
		Empleado empleado = new Empleado(dni);
		empleado.getDNI();
		assertEquals(dni, "12345678A");
	}
	
	/**
	 * prueba del método setDNI()
	 */
	
	@Test
	void testSetDNI() {
		String dni = "12345678A";
		Empleado empleado = new Empleado(dni);
		empleado.setDNI(dni);
		assertEquals(dni, "12345678A");

	}
	
	/**
	 * prueba del metodo getNombre
	 */
	
	@Test
	void testGetNombre() {
		String nombre = "Maria";
		Empleado empleado = new Empleado(nombre);
		empleado.getNombre();
		assertEquals(nombre, "Maria");
	}
	
	/**
	 * prueba del método toString
	 */
	@Test
	void testToString() {
		String dni = "12345678A";
		String nombre = "Javier";
		String apellido = "Milei";
		String email = "javiermilei@gmail.com";
		String rol = "Administrador";
		String telefono = "999999999";
		String contrasena="1JavierMil";
		
		Empleado empleado = new Empleado(dni,nombre,apellido,email,rol,telefono,contrasena);
		String resultado = empleado.toString();
		assertEquals(resultado, empleado.toString() );
	}

}
