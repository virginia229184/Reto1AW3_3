package Modelos;

public class Empleado extends Persona{
	
	private String dni_empleado;
	private String contraseña_empleado;
	
	
	
	public Empleado(String dNI, String nombre, String apellido, String rol, String email, String telefono,
			String contraseña) {
		super(dNI, nombre, apellido, rol, email, telefono, contraseña);
	}



	public Empleado(String dNI, String nombre, String apellido, String rol, String email, String telefono,
			String contraseña, String dni_empleado, String contraseña_empleado) {
		super(dNI, nombre, apellido, rol, email, telefono, contraseña);
		this.dni_empleado = dni_empleado;
		this.contraseña_empleado = contraseña_empleado;
	}



	public String getDni_empleado() {
		return dni_empleado;
	}



	public void setDni_empleado(String dni_empleado) {
		this.dni_empleado = dni_empleado;
	}



	public String getContraseña_empleado() {
		return contraseña_empleado;
	}



	public void setContraseña_empleado(String contraseña_empleado) {
		this.contraseña_empleado = contraseña_empleado;
	}
	
	
	

}
