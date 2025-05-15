import java.io.IOException;
import java.sql.SQLException;

import Controlador.Coordinador;
import Modelos.Persona;
import Ventana.IniciarSesion;

public class YelmoCinesAPP {
	public static void main(String[] args) throws IOException, SQLException {
		Persona person = new Persona();

		Coordinador coord = new Coordinador();
		IniciarSesion frame = new IniciarSesion();

		frame.setVisible(true);
	}

}
