package BBDD;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;

import Modelos.Administrador;
import Modelos.Empleado;
import logs.FormatoHTML;

/**
 * clase adminConnect que se encarga de la conexion con la base de datos y
 * ejecutar las sentencias de Mysql
 * 
 * @author FELIPE
 * @author VIRGINIA
 * @author JON
 * @version 1.0
 
 */

public class adminConnect {
	
	/**
	 * constructor vacio
	 */
	public adminConnect() {
		
	}

	

	/**
	 * Establece la conexion con la base de datos
	 * 
	 * @return conexion con la base de datos
	 */

	public Connection connect() {

		String url = "jdbc:mysql://localhost:3306/yelmocines";
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
	 * Obtiene los atributos de un usuario con rol de administrador
	 * @param rol El rol del usuario a buscar
	 * @throws SQLException si ocurre un error en la base de datos
	 */
	public void getSesionAdmin(String rol) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona where rol = Admin " + rol + "';'";

	}

	/**
	 * obtiene una lista de todos los administradores de la base de datos
	 * 
	 * @return lista de los empledos
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */

	public ArrayList<Administrador> getAdmin() throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Administrador> adminList = new ArrayList<Administrador>();

		try {
			while (resultset.next()) {
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

		} catch (SQLException e) {
			System.err.println("Error en getEmpleado");

		}
		con.close();

		return adminList;

	}
}