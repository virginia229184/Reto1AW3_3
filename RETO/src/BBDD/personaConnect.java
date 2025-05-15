package BBDD;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelos.Persona;

public class personaConnect {

	Connection connect() {

		String url = "jdbc:mysql://localhost:3306/yelmocines";
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
			while (resultset.next()) {
				Persona persona = new Persona();
				persona.setDNI(resultset.getString("DNI"));
				persona.setNombre(resultset.getString("Nombre"));
				persona.setApellido(resultset.getString("Apellido"));
				persona.setRol(resultset.getString("Rol"));
				persona.setEmail(resultset.getString("Email"));
				persona.setTelefono(resultset.getString("Telefono"));
				persona.setContrasena(resultset.getString("Contrasena"));
				personaList.add(persona);

			}

		} catch (SQLException e) {
			System.err.println("Error en getPersona");

		}
		con.close();

		return personaList;

	}

	public ArrayList<Persona> getPersonaCombo() throws SQLException {
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

		} catch (Exception e) {
			System.err.println("Error en getPersonaCombo");

		}
		con.close();
		return personaList;

	}

	public void borrarPersona(String Dni) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SET FOREIGN_KEY_CHECKS = 0;";
		String consulta1 = "DELETE FROM Persona WHERE Dni = '" + Dni + "';";
		String consulta2 = "SET FOREIGN_KEY_CHECKS = 1;";

		st.execute(consulta);
		st.execute(consulta1);
		st.execute(consulta2);
		con.close();

	}

	public void registrarPersona(Persona persona) throws SQLException, IOException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "INSERT INTO Persona (Dni, nombre, apellido, rol, email, telefono, contrasena) VALUES('"
				+ persona.getDNI() + "', '" + persona.getNombre() + "','" + persona.getApellido() + "','"
				+ persona.getRol() + "','" + persona.getEmail() + "','" + persona.getTelefono() + "','"
				+ persona.getContrasena() + "');";
		st.execute(consulta);
		

		FileWriter fw = new FileWriter("registrar_persona_insert.txt", true);
		fw.write(persona.getDNI()+":"+persona.getNombre()+":"+persona.getApellido()+":"+persona.getRol()+":"+persona.getEmail()+":" +persona.getEmail()+";"+persona.getTelefono()+":"+ persona.getContrasena()+"\n");
		fw.close();
		con.close();
		
		
	}

	public void modificarPersona(Persona persona) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "UPDATE persona SET nombre = '" + persona.getNombre() + "', apellido = '"
				+ persona.getApellido() + "', rol = '" + persona.getRol() + "', email = '" + persona.getEmail()
				+ "', telefono = '" + persona.getTelefono() + "', contrasena = '" + persona.getContrasena()
				+ "' WHERE dni = '" + persona.getDNI() + "';";

		st.executeUpdate(consulta);
	}
	

}