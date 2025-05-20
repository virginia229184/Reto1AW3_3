/**
 * paquete BBD, que contiene las clases adminConnect y empleadoConnect
 */
package BBDD;

import java.io.FileWriter;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JComboBox;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

import Modelos.Empleado;
import Modelos.Persona;
import logs.FormatoHTML;

/**
 * clase empleadoConnect que se encarga de la conexion con la base de datos y y
 * realizar las operaciones CRUD y ejecutar las sentencias SQL
 * 
 * tambien gestiona el registro de errores mediante un logger
 * 
 * @author FELIPE
 * @author VIRGINIA
 * @author JON
 * @version 1.0
 */

public class empleadoConnect {

	/**
	 * constructor vacio
	 */

	public empleadoConnect() {

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
		boolean conexion = false;

		Connection connect = null;

		try {
			connect = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("Se ha conectado a la base de datos");
			conexion = true;

		} catch (SQLException e) {
			e.printStackTrace();

			conexion = false;
		}

		return connect;

	}

	/**
	 * obtiene una lista de todos los empleados de la base de datos
	 * 
	 * @return lista de los empledos
	 * @throws SQLException si ocurre un error al realizar la consulta a la base de
	 *                      datos
	 */

	public ArrayList<Empleado> getEmpleado() throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona";
		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Empleado> empleadoList = new ArrayList<Empleado>();

		try {
			while (resultset.next()) {
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

		} catch (SQLException e) {
			System.err.println("Error en getEmpleado");

		}
		con.close();

		return empleadoList;

	}

	/**
	 * Obtiene la lista de los empleado que tienen el mismo nombre que el
	 * seleccionado en el ComboBox
	 * 
	 * @param comboCliente lista desplegable de donde se obtiene el nombre
	 *                     seleccionado
	 * @return lista de los empleados con el nombre seleccionado
	 * @throws SQLException si ocurre un error al realizar la consulta a la base de
	 *                      datos
	 */

	public ArrayList<Empleado> getEmpleadoNombre(JComboBox comboCliente) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM persona where nombre = " + "'" + comboCliente.getSelectedItem().toString()
				+ "'" + ";";

		ResultSet resultset = (ResultSet) st.executeQuery(consulta);
		ArrayList<Empleado> empleadoList = new ArrayList<Empleado>();

		try {
			while (resultset.next()) {
				Empleado empleado2 = new Empleado();
				empleado2.setDNI(resultset.getString("DNI"));
				empleado2.setNombre(resultset.getString("nombre"));
				empleado2.setApellido(resultset.getString("apellido"));
				empleado2.setRol(resultset.getString("rol"));
				empleado2.setEmail(resultset.getString("Email"));
				empleado2.setTelefono(resultset.getString("telefono"));
				empleado2.setContrasena(resultset.getString("contrasena"));
				empleadoList.add(empleado2);

			}

		} catch (SQLException e) {
			System.err.println("Error en getEmpleadoNombre");

		}
		con.close();

		return empleadoList;

	}

	/**
	 * Selecciona los nombres de las personas para ser utilizados en una lista
	 * despegable(ComboBox)
	 * 
	 * @return el nombre de los empleados
	 * @throws SQLException si ocurre un error al realizar la consulta a la base de
	 *                      datos
	 */
	public ArrayList<Empleado> getEmpleadoCombo() throws SQLException {
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

		} catch (Exception e) {
			System.err.println("Error en getEmpleadoCombo" + e.getMessage());

		}
		con.close();
		return empleadoList;

	}

	/**
	 * Inserta un nuevo empleado en la base de datos y registra la operación en un
	 * archivo de texto
	 * 
	 * @param empleado empleado objeto que tiene los datos que se quieren registrar
	 * @throws SQLException si ocurre un error al realizar la consulta a la base de
	 *                      datos
	 * @throws IOException  si ocurre un error al escribir en el archivo de registro
	 */

	// registrar empleados
	public void registrarEmpleado(Empleado empleado) throws SQLException, IOException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "INSERT INTO Persona (Dni, nombre, apellido, rol, email, telefono, contrasena) VALUES('"
				+ empleado.getDNI() + "', '" + empleado.getNombre() + "','" + empleado.getApellido() + "','"
				+ empleado.getRol() + "','" + empleado.getEmail() + "','" + empleado.getTelefono() + "','"
				+ empleado.getContrasena() + "');";
		st.execute(consulta);

		FileWriter fw = new FileWriter("registrar_persona_insert.txt", true);
		fw.write(empleado.getDNI() + ":" + empleado.getNombre() + ":" + empleado.getApellido() + ":" + empleado.getRol()
				+ ":" + empleado.getEmail() + ":" + empleado.getEmail() + ";" + empleado.getTelefono() + ":"
				+ empleado.getContrasena() + "\n");
		fw.close();
		con.close();

	}

	/**
	 * Elimina un empleado de la base de datos usando su DNI y desactiva las
	 * restricciones de claves foráneas para evitar errores
	 * 
	 * @param Dni dni del empleado que se quiere eliminar
	 * @throws SQLException si ocurre un error al realizar la consulta a la base de
	 *                      datos
	 */
//	borrar empleados
	public void borrarEmpleado(String Dni) throws SQLException {
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

	/**
	 * Modifica un empleado ya existenten en la base de datos
	 * 
	 * @param empleado actualiar los datos de un empleado existenten en la base de
	 *                 datos
	 * @throws SQLException si ocurre un error al ejecutar la operación de
	 *                      actualización.
	 */

//	modificar empleado
	public void modificarEmpleado(Empleado empleado) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "UPDATE persona SET nombre = '" + empleado.getNombre() + "', apellido = '"
				+ empleado.getApellido() + "', rol = '" + empleado.getRol() + "', email = '" + empleado.getEmail()
				+ "', telefono = '" + empleado.getTelefono() + "', contrasena = '" + empleado.getContrasena()
				+ "' WHERE dni = '" + empleado.getDNI() + "';";

		st.executeUpdate(consulta);
		con.close();

	}

}