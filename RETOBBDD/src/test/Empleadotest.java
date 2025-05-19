package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelos.Empleado;
import Modelos.Persona;

class EmpleadoTest {

	@Test
	void testGetDNI() {
		String dni = "12345678A";
		Empleado empleado = new Empleado();
		empleado.getDNI();
		assertEquals(dni, "12345678A");
	}
	
	@Test
	void testSetDNI() {
		String dni = "12345678A";
		Empleado empleado = new Empleado();
		empleado.setDNI(dni);
		assertEquals(dni, "12345678A");

	}
	
	@Test
	void testGetNombre() {
		String nombre = "Maria";
		Empleado empleado = new Empleado();
		empleado.getNombre();
		assertEquals(nombre, "Maria");
	}

}
