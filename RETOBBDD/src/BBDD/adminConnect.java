package BBDD;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;

import Modelos.Administrador;
import Modelos.Empleado;

/**
 * Conexion con la base de datos Administrador
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */

public class adminConnect {
	
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
	 * Esto sentencia es utilizada para Iniciar Sesion como administrador
	 * @param rol
	 * @throws SQLException
	 */
	public void getSesionAdmin (String rol) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona where rol = Admin " +rol+ "';'"; 
		
	}
	
	/**
	 * Esta sentencia utilizada para los datos de Administrador
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Administrador> getAdmin() throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Administrador> adminList = new ArrayList<Administrador>();
		
		try {
			while(resultset.next()) {
				Administrador admin = new Administrador();
					admin.setDNI(resultset.getString("DNI"));
					admin.setNombre(resultset.getString("nombre"));
					admin.setApellido(resultset.getString("apellido"));
					admin.setRol(resultset.getString("rol"));
					admin.setEmail(resultset.getString("email"));
					admin.setTelefono(resultset.getString("telefono"));
					admin.setContrasena(resultset.getString("contrasena"));
				adminList.add(admin);
				
			}
			
		}catch(SQLException e) {
			System.err.println("Error en getEmpleado");
			
		}
		con.close();
		
		return adminList;
		
	}


}
