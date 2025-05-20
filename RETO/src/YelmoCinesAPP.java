import java.awt.Menu;
import java.io.IOException;
import Ventana.*;
import java.sql.SQLException;
import BBDD.*;
import Controlador.Coordinador;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import Ventana.EliminarPersona;
import Ventana.IniciarSesion;
import Ventana.ModificarPersona;
import Ventana.Visualizar;
import test.EmpleadoTest;

/**
 * Metodo prinicipal  que inicia la ventana de inicio de sesión
 * 
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public class YelmoCinesAPP {	
	
	
	/**
	  * Método principal que muestra la ventana de inicio de sesión
	  * 
	  * @param args Argumentos de línea de comandos (no se usan)
	  * @throws IOException  Si ocurre un error de entrada/salida
	  * @throws SQLException Si ocurre un error relacionado con la base de datos
	  */
	public static void main(String[] args) throws IOException, SQLException {

		IniciarSesion iniciarSesion = new IniciarSesion();
		EliminarPersona elimPersona = new EliminarPersona();
		Menu menu = new Menu();
		ModificarPersona modPersona = new ModificarPersona();
		RegistrarPersona regPersona = new RegistrarPersona();
		Visualizar visua = new Visualizar();
		XMLForm xmlForm = new XMLForm();
		
		Administrador admin = new Administrador();
		Empleado empleado = new Empleado();
		
		adminConnect admCon = new adminConnect();
		empleadoConnect empCon = new empleadoConnect();
	
		

		iniciarSesion.setVisible(true);
	}

}
