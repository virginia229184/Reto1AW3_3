package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Controlador.Coordinador;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import Modelos.Persona;

/*public class personaConnect {

	Connection connect() {
		
		String url= "jdbc:mysql://localhost:3306/yelmocines";
		String username = "root";
		String password = "";
		
		Connection connect = null;
		
		try {
			connect = DriverManager.getConnection(url, username, password);
			System.out.println("Se ha conectado a la base de datos");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
		
	}
	
	public ArrayList<Persona> getPersona() throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Persona> personaList = new ArrayList<Persona>();
		
		try {
			while(resultset.next()) {
				Persona persona = new Persona();
				persona.setDNI(resultset.getString("DNI"));
				persona.setNombre(resultset.getString("nombre"));
				persona.setApellido(resultset.getString("apellido"));
				persona.setRol(resultset.getString("rol"));
				persona.setEmail(resultset.getString("email"));
				persona.setTelefono(resultset.getString("telefono"));
				persona.setContrasena(resultset.getString("contrasena"));
				personaList.add(persona);
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error en getPersona");
			
		}
		con.close();
		
		return personaList;
		
	}
	
	public ArrayList<Persona> getPersonaNombre(JComboBox comboCliente) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona where nombre = " + "'" + comboCliente.getSelectedItem().toString()+ "'" + ";";
				
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Persona> personaList = new ArrayList<Persona>();
		
		try {
			while(resultset.next()) {
				Persona persona2 = new Persona();
				persona2.setDNI(resultset.getString("DNI"));
				persona2.setNombre(resultset.getString("nombre"));
				persona2.setApellido(resultset.getString("apellido"));
				persona2 .setRol(resultset.getString("rol"));
				persona2.setEmail(resultset.getString("Email"));
				persona2.setTelefono(resultset.getString("telefono"));
				persona2.setContrasena(resultset.getString("contrasena"));	
				personaList.add(persona2);
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error en getPersona");
			
		}
		con.close();
		
		return personaList;
		
	}
	
	public ArrayList<Persona> getPersonaCombo()throws SQLException{
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT Nombre FROM Persona;";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Persona> personaList = new ArrayList<Persona>();
		
		try {
			while (resultset.next()) {
				Persona persona = new Persona();
				persona.setNombre(resultset.getString("Nombre"));
				personaList.add(persona);
			}
			
		}catch (Exception e) {
			System.err.println("Error en getPersonaCombo");
			
		}
		con.close();
		return personaList;
		
		
	}
	
	public void borrarPersona (String Dni) throws SQLException{
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "DELETE FROM Persona WHERE Dni = " +Dni;
		
	}
	public void registrarPersona (Persona persona) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "INSERT INTO Persona (Dni, nombre, apellido, rol, email, telefono, contraseña) VALUES('" + persona.getDNI() + "', '"
				+ persona.getNombre() + "','" + persona.getApellido() + "','" + persona.getRol() + "','" + persona.getEmail() +
				"','" + persona.getTelefono() + "','" + persona.getContrasena() + "');'";
		st.execute(consulta);
		
		con.close();
	}
	
	public void modificarPersona (Persona persona) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "UPDATE persona SET Dni = Dni -" +persona.getDNI() + "WHERE Nombre =" + "'";
	}
	

	
	
}*/
