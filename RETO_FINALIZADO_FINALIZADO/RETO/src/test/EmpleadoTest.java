
/**
 * paquete test, en el se encuentra la clase EmpleadoTest

 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import BBDD.empleadoConnect;
import Modelos.Empleado;
import Modelos.Persona;

/**
 * clase empledaoTest pruebas unitarias para la clase empleado se hacen pruebas
 * de getters, setters, metodo getEmpleado()
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
	 * test para el metodo getEmpleado
	 * @throws SQLException si ocurre un error en la base de datos
	 */
	
	@Test
	 void tesGetEmpleado() throws SQLException {
		empleadoConnect empCon = new empleadoConnect();
		List<Empleado> empleados = empCon.getEmpleado();
		Empleado primerEmpleado = empleados.get(0);
		assertEquals("10111213H", primerEmpleado.getDNI());
		assertEquals("Tom", primerEmpleado.getNombre());
		assertEquals("Hanks", primerEmpleado.getApellido());
		assertEquals("Empleado", primerEmpleado.getRol());
		assertEquals("tomhanks@gmail.com", primerEmpleado.getEmail());
		assertEquals("222222222", primerEmpleado.getTelefono());
		assertEquals("8TomHanks", primerEmpleado.getContrasena());
		System.out.println(empleados.get(0));
	}

	
	/**
	 * test para el metodo setDNI
	 */

	@Test
	 void testSetDNI() {
		String dni = "12345678A";
		Empleado empleado = new Empleado(dni);
		empleado.setDNI(dni);
		assertEquals(dni, "12345678A");

	}

	

	/**
	 * test para el metodo getNombre
	 */
	@Test
	 void testGetNombre() {
		String nombre = "Maria";
		Empleado empleado = new Empleado(nombre);
		empleado.getNombre();
		assertEquals(nombre, "Maria");
	}

}
