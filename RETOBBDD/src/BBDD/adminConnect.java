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

public class adminConnect {
	
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

	public void getSesionAdmin (String rol) throws SQLException {
		Connection con = connect();
		Statement st = con.createStatement();
		String consulta = "SELECT * FROM Persona where rol = Admin " +rol+ "';'"; 
		
	}
	
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
