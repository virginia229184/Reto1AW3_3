package Controlador;

import Ventana.Visualizar;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import BBDD.personaConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Coordinador implements ActionListener {

	Persona miPersona;
	Visualizar miVisualizador;
	personaConnect miPersonconnect;
	Empleado miEmpleado;
	Administrador miAdministrador;

	public Coordinador(Persona paramPersona, Visualizar paramVisualizar, personaConnect paramPersonconnect,
			Empleado empleadoParam, Administrador adminParam) {
		this.miPersona = paramPersona;
		this.miVisualizador = paramVisualizar;
		this.miPersonconnect = paramPersonconnect;
		this.miEmpleado = empleadoParam;
		this.miAdministrador = adminParam;
	}

	public Coordinador() {

	}

	public static void GuardarPersona(JTextField textFieldDatos) {

		personaConnect perCon = new personaConnect();

		String Dni = textFieldDatos.getName();

		Persona person = new Persona();

		try {
			perCon.registrarPersona(person);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void VisualizarPersonasComboBox(JComboBox<String> comboCliente) {

		personaConnect perCon = new personaConnect();

		try {

			comboCliente.addItem("Todos");
			for (int i = 0; i < perCon.getPersonaCombo().size(); i++) {
				comboCliente.addItem(perCon.getPersonaCombo().get(i).getNombre());
			}

			System.out.println("Personas visualizadas exitosamente");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void getVisualizarPersonas(DefaultTableModel TableModel, JComboBox comboCliente) {

		personaConnect perCon = new personaConnect();

		try {
			TableModel.setRowCount(0);

			if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("Todos")) {
				ArrayList<Persona> personaList = perCon.getPersona();
				for (Persona persona : personaList) {
					TableModel.addRow(new Object[] { persona.getDNI(), persona.getNombre(), persona.getApellido(),
							persona.getRol(), persona.getEmail(), persona.getTelefono(), persona.getContrasena() });

					System.out.println("Personas introducidas correctamente");
				}

			}

			else {
				ArrayList<Persona> personaLists = perCon.getPersonaNombre(comboCliente);
				for (Persona persona : personaLists) {
					TableModel.addRow(new Object[] { persona.getDNI(), persona.getNombre(), persona.getApellido(),
							persona.getRol(), persona.getEmail(), persona.getTelefono(), persona.getContrasena() });
				}

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
