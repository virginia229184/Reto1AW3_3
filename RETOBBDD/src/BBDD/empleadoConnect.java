package BBDD;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

import Modelos.Empleado;

/**
 * Conexion a la base de Empleados
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */

public class empleadoConnect {
	
	/**
	 * Este es lo que conecta la app con la base de datos
	 * @param url: la url que conecta a esa base especifica 
	 * @param username: es el nombre de usuario que tiene
	 * @param password: la contraseña de la base de datos, en este caso, no tiene
	 * @return
	 */
	
	Connection connect() {
		
		String url= "jdbc:mysql://localhost:3306/yelmocines";
		String username = "root";
		String password = "";
		
		Connection connect = null;
		
		try {
			connect = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("Se ha conectado a la base de datos");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
		
	}
	
	
	 /**
	  * La sentencia que genera los datos de toda la tabla de persona
	  * @return
	  * @throws SQLException
	  */
	
	public ArrayList<Empleado> getEmpleado() throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Empleado> empleadoList = new ArrayList<Empleado>();
		
		try {
			while(resultset.next()) {
				Empleado empleado = new Empleado();
				empleado.setDNI(resultset.getString("DNI"));
				empleado.setNombre(resultset.getString("nombre"));
				empleado.setApellido(resultset.getString("apellido"));
				empleado.setRol(resultset.getString("rol"));
				empleado.setEmail(resultset.getString("email"));
				empleado.setTelefono(resultset.getString("telefono"));
				empleado.setContrasena(resultset.getString("contrasena"));
				empleadoList.add(empleado);
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error en getEmpleado");
			
		}
		con.close();
		
		return empleadoList;
		
	}
	
	
	/**
	 * Esta sentencia es utilizada cuando seleccionamos un campo en el comboBox
	 * @param comboCliente
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Empleado> getEmpleadoNombre(JComboBox comboCliente) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona where nombre = " + "'" + comboCliente.getSelectedItem().toString()+ "'" + ";";
				
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Empleado> empleadoList = new ArrayList<Empleado>();
		
		try {
			while(resultset.next()) {
				Empleado empleado2 = new Empleado();
				empleado2.setDNI(resultset.getString("DNI"));
				empleado2.setNombre(resultset.getString("nombre"));
				empleado2.setApellido(resultset.getString("apellido"));
				empleado2.setRol(resultset.getString("rol"));
				empleado2.setEmail(resultset.getString("Email"));
				empleado2.setTelefono(resultset.getString("telefono"));
				empleado2.setContrasena(resultset.getString("contrasena"));	
				empleadoList.add(empleado2 );
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error en getEmpleadoNombre");
			
		}
		con.close();
		
		return empleadoList;
		
	}
	
	
	/**
	 * Esta sentencia es utilizada para el filtro de Nombre
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Empleado> getEmpleadoCombo()throws SQLException{
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT Nombre FROM Persona;";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Empleado> empleadoList = new ArrayList<Empleado>();
		
		try {
			while (resultset.next()) {
				Empleado empleado = new Empleado();
				empleado.setNombre(resultset.getString("Nombre"));
				empleadoList.add(empleado);
			}
			
		}catch (Exception e) {
			System.err.println("Error en getEmpleadoCombo");
			
		}
		con.close();
		return empleadoList;
		
		
	}
	
	/**
	 * Esta sentencia sirve para borrar los empleados usando el Dni
	 * @param Dni
	 * @throws SQLException
	 */
	
	public void borrarEmpleado (String Dni) throws SQLException{
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "DELETE FROM Persona WHERE Dni = " +Dni;
		st.execute(consulta);
		
	}
	
	/**
	 * La sentencia sirve para introducir nuevos Empleados
	 * @param empleado
	 * @throws SQLException
	 */
	public void registrarEmpleado (Empleado empleado) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "INSERT INTO Persona (Dni, nombre, apellido, rol, email, telefono, contraseña) VALUES('" + empleado.getDNI() + "', '"
				+ empleado.getNombre() + "','" + empleado.getApellido() + "','" + empleado.getRol() + "','" + empleado.getEmail() +
				"','" + empleado.getTelefono() + "','" + empleado.getContrasena() + "');'";
		st.execute(consulta);
		
		con.close();
	}
	
	/**
	 * Esta sentencia ayuda a Modificar los empleados
	 * @param empleado
	 * @throws SQLException
	 */
	public void modificarEmpleado (Empleado empleado) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "UPDATE persona SET Dni = Dni -" +empleado.getDNI() + "WHERE Nombre =" +empleado.getNombre() +"'";
		st.execute(consulta);
	}
	
	/**
	 * Esta sentencia se utiliza para Iniciar Sesion como Empleado
	 * @param rol
	 * @throws SQLException
	 */
	public void getSesionEmpleado (String rol) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona where rol = Empleado " +rol+ "';'"; 
		
	}
	
		
	

}
