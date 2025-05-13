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


public class empleadoConnect {
	
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
	
	public void borrarEmpleado (String Dni) throws SQLException{
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "DELETE FROM Persona WHERE Dni = " +Dni;
		
	}
	public void registrarEmpleado (Empleado empleado) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "INSERT INTO Persona (Dni, nombre, apellido, rol, email, telefono, contraseña) VALUES('" + empleado.getDNI() + "', '"
				+ empleado.getNombre() + "','" + empleado.getApellido() + "','" + empleado.getRol() + "','" + empleado.getEmail() +
				"','" + empleado.getTelefono() + "','" + empleado.getContrasena() + "');'";
		st.execute(consulta);
		
		con.close();
	}
	
	public void modificarEmpleado (Empleado empleado) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "UPDATE persona SET Dni = Dni -" +empleado.getDNI() + "WHERE Nombre =" +empleado.getNombre() +"'";
		st.execute(consulta);
	}
	
	public void getSesionEmpleado (String rol) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona where rol = Empleado " +rol+ "';'"; 
		
	}
	
		
	

}
