package Controlador;

import Ventana.Visualizar;
import logs.FormatoHTML;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import BBDD.adminConnect;
import BBDD.empleadoConnect;
import BBDD.personaConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Coordinador implements ActionListener {

	private static final Logger sesion = Logger.getLogger("sesionLogger");
	private static final Logger errores = Logger.getLogger("erroresLogger");
	private static final LogManager logManager = LogManager.getLogManager();
	static {
		try {
			LogManager.getLogManager().reset();
			//fichero para cada sesion
			String nombreSesion = "sesion_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".html";
	        FileHandler fileHandler = new FileHandler(nombreSesion, true);
			fileHandler.setFormatter(new FormatoHTML());
			sesion.addHandler(fileHandler);
			sesion.setLevel(Level.ALL);

//			fichero global para errores
			FileHandler fileHandler1 = new FileHandler("errores.html", true);
			fileHandler1.setFormatter(new FormatoHTML());
			errores.addHandler(fileHandler1);
			errores.setLevel(Level.ALL);

		} catch (IOException e) {
			System.err.println("Error al configurar los logs");
		}
	}

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

	public static void GuardarPersona(JTextField textFieldDNI, JTextField textFieldNombre, JTextField textFieldApellido,
			JTextField textFieldRol, JTextField textFieldMail, JTextField textFieldTelefono,
			JTextField textFieldContrasena) throws IOException {

		personaConnect perCon = new personaConnect();

		String dni = textFieldDNI.getText();
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		String rol = textFieldRol.getText();
		String mail = textFieldMail.getText();
		String telefono = textFieldTelefono.getText();
		String contrasena = textFieldContrasena.getText();

		Persona per = new Persona(dni, nombre, apellido, rol, mail, telefono, contrasena);

		try {
			perCon.registrarPersona(per);
			JOptionPane.showMessageDialog(null, "Has insertado con éxito los datos");
			sesion.log(Level.INFO, "Persona registrada con éxito. DNI: " + dni);


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en guardarPersona");
			JOptionPane.showMessageDialog(null, "No se han podido insertar los datos");
			errores.log(Level.SEVERE, "Error al registrar persona");

		}

	}

	public static void EliminarPersona(JTextField textFieldDNI) {

		personaConnect perCon = new personaConnect();

		String dni = textFieldDNI.getText();
		boolean borrado = false;

		try {
			for (int i = 0; i < perCon.getPersona().size(); i++) {
				if (dni.equalsIgnoreCase(perCon.getPersona().get(i).getDNI())) {
					perCon.borrarPersona(dni);
					borrado = true;
					JOptionPane.showMessageDialog(null, "Has eliminado con éxito los datos");
					sesion.log(Level.INFO, "Persona eliminada con éxito");

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en eliminarPersona");
			JOptionPane.showMessageDialog(null, "No se han eliminado con éxito los datos");
			errores.log(Level.WARNING, "Error al eliminar persona");

		}
		if (borrado == false) {
			System.out.println("El dni no existe");
			JOptionPane.showMessageDialog(null, "No se han podido eliminar los datos");
			errores.log(Level.WARNING, "Error al eliminar persona. DNI no encontrado");

		}

	}

	public static void IniciarSesion(JTextField textFieldDNI, JTextField textFieldContrasena) {

		personaConnect perCon = new personaConnect();

		String dni = textFieldDNI.getText();
		String contrasena = textFieldContrasena.getText();
		boolean iniciarSesion = false;

		try {
			for (int i = 0; i < perCon.getPersona().size(); i++) {
				if (dni.equalsIgnoreCase(perCon.getPersona().get(i).getDNI())
						&& contrasena.equals(perCon.getPersona().get(i).getContrasena())
						&& perCon.getPersona().get(i).getRol().equalsIgnoreCase("empleado")) {
					Ventana.XMLForm frame = new Ventana.XMLForm();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "El empleado ha iniciado sesión con éxito");
					System.out.println("esto es el empleado");
					iniciarSesion = true;
					sesion.log(Level.INFO, "Inicio de sesión correcto. DNI: " + dni);


				} else {
					if (dni.equalsIgnoreCase(perCon.getPersona().get(i).getDNI())
							&& contrasena.equals(perCon.getPersona().get(i).getContrasena())
							&& perCon.getPersona().get(i).getRol().equalsIgnoreCase("administrador")) {
						Ventana.Menu1 frame = new Ventana.Menu1();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null, "El administrador ha iniciado sesión con éxito");
						System.out.println("esto es el administrador");
						iniciarSesion = true;
						sesion.log(Level.INFO, "Inicio de sesión correcto");
						sesion.log(Level.INFO, "Inicio de sesión correcto. DNI: " + dni);


					}

				}

			}
			if (iniciarSesion == false) {
				JOptionPane.showMessageDialog(null, "Has introducido mal un dato(DNI o contraseña)");
				errores.log(Level.WARNING, "Error en IniciarSesion. DNI: "+ dni);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en IniciarSesion");
			JOptionPane.showMessageDialog(null, "Has introducido mal un dato(DNI o contraseña)");
			errores.log(Level.SEVERE, "Error en IniciarSesion");

		}

	}

	public static void modificarPersona(JTextField textFieldDNI, JTextField textFieldNombre,
			JTextField textFieldApellido, JTextField textFieldRol, JTextField textFieldEmail,
			JTextField textFieldTelefono, JTextField textFieldContrasena) {
		personaConnect perCon = new personaConnect();
		boolean resultado = false;
		String dni = textFieldDNI.getText();
		try {
			for (int i = 0; i < perCon.getPersona().size(); i++) {
				if (dni.equalsIgnoreCase(perCon.getPersona().get(i).getDNI())) {
					String nombre = textFieldNombre.getText();
					String apellido = textFieldApellido.getText();
					String rol = textFieldRol.getText();
					String email = textFieldEmail.getText();
					String telefono = textFieldTelefono.getText();
					String contrasena = textFieldContrasena.getText();

					Persona persona = new Persona(dni, nombre, apellido, rol, email, telefono, contrasena);

					perCon.modificarPersona(persona);
					resultado = true;

					JOptionPane.showMessageDialog(null, "Has modificado con éxito los datos");
					sesion.log(Level.INFO, "Persona modificada con éxito");

				}
			}
		} catch (SQLException e5) {
			e5.printStackTrace();
			System.out.println("Error en modificarPersona");
			JOptionPane.showMessageDialog(null, "No se ha podido modificar los datos");
			errores.log(Level.SEVERE, "Error al modificar persona");

		}

		if (resultado == false) {
			System.out.println("El dni no existe");
			JOptionPane.showMessageDialog(null, "No se ha podido modificar los datos");
			errores.log(Level.SEVERE, "Error al modificar persona");

		}

	}

	public static void VisualizarPersonasComboBox(JComboBox<String> comboCliente) {

		personaConnect perCon = new personaConnect();

		try {
			comboCliente.removeAllItems();
			for (int i = 0; i < perCon.getPersonaCombo().size(); i++) {
				comboCliente.addItem(perCon.getPersonaCombo().get(i).getNombre());
			}

			System.out.println("Personas visualizadas exitosamente");
			sesion.log(Level.INFO, "Personas visualizadas correctamente en ComboBox");

		} catch (SQLException e) {
			e.printStackTrace();
			errores.log(Level.SEVERE, "Error al visualizar personas en ComboBox");

		}

	}

	public static void getVisualizarPersonas(DefaultTableModel TableModel, JComboBox comboPersona) {

		personaConnect perCon = new personaConnect();

		try {
			TableModel.setRowCount(0);
			if (comboPersona.getSelectedItem().toString().equalsIgnoreCase("Total")) {
				ArrayList<Persona> personaList = perCon.getPersona();
				for (Persona persona : personaList) {
					TableModel.addRow(new Object[] { persona.getDNI(), persona.getNombre(), persona.getApellido(),
							persona.getTelefono(), persona.getEmail(), persona.getRol(), persona.getContrasena() });

				}

				System.out.println("Personas introducidas correctamente");
				sesion.log(Level.INFO, "Personas visualizadas correctamente en la tabla");

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			errores.log(Level.SEVERE, "Error al visualizar personas en tabla.");

		}

	}

	public static void tablemodelxml(JTable tablexml) {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Node doc = builder.parse(new File(exploradorArchivos()));
			org.w3c.dom.Element root = ((org.w3c.dom.Document) doc).getDocumentElement();
			NodeList sesionList = root.getElementsByTagName("sesion");
			DefaultTableModel model = (DefaultTableModel) tablexml.getModel();
			for (int i = 0; i < sesionList.getLength(); i++) {
				Node sesion = sesionList.item(i);
				String idSesion = getTextContent(sesion, "idSesion");
				String hora = getTextContent(sesion, "hora");
				String dia = getTextContent(sesion, "dia");
				String aforo = getTextContent(sesion, "aforo");
				String cif = getTextContent(sesion, "cif");
				String idPelicula = getTextContent(sesion, "idPelicula");
				model.addRow(new Object[] { idSesion, hora, dia, aforo, cif, idPelicula });

			}
			sesion.log(Level.INFO, "Datos del XML cargados correctamente en la tabla");
		} catch (Exception ex) {
			ex.printStackTrace();
			errores.log(Level.SEVERE, "Error al cargar datos XML en tabla.");

		}
	}

	public static String exploradorArchivos() {
		String filepath = "";
		JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(new File("."));
		int result = selector.showOpenDialog(selector);
		if (result == JFileChooser.APPROVE_OPTION) {

			File selectedFile = selector.getSelectedFile();

			filepath = selectedFile.getAbsolutePath();
		}
		return filepath;
	}

	private static String getTextContent(Node record, String tagName) {
		NodeList list = ((org.w3c.dom.Element) record).getElementsByTagName(tagName);
		if (list.getLength() > 0) {
			return list.item(0).getTextContent().trim();
		}
		return "";
	}

	public static void buscarDatos(DefaultTableModel tablemodel, JTable tablexml, JTextField textFieldBuscador) {
		DefaultTableModel ob = (DefaultTableModel) tablexml.getModel();
		TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
		tablexml.setRowSorter(obj);
		obj.setRowFilter(RowFilter.regexFilter(textFieldBuscador.getText()));
	}

	public static void CargarFicherosBinarios(JButton BotonCopiaSeg) throws SQLException {
		File archivo = new File("escritura.dat");
		adminConnect admConnect = new adminConnect();
		empleadoConnect empConnect = new empleadoConnect();
		ArrayList<Empleado> empleadoList = empConnect.getEmpleado();
		ArrayList<Administrador> adminList = admConnect.getAdmin();

		try {
			// Para poder escribir utilizaremos un FileOutputStream pasandole
			// como referencia el archivo de tipo File.
			FileOutputStream fos = new FileOutputStream(archivo);

			// Y crearemos también una instancia del tipo ObjectOutputStream
			// al que le pasaremos por parámetro
			// el objeto de tipo FileOutputStream
			ObjectOutputStream escribir = new ObjectOutputStream(fos);

			// Escribimos los objetos en el archivo.
			for (int i = 0; i < adminList.size(); i++) {

				escribir.writeObject(adminList.get(i));
			}

			for (int i = 0; i < empleadoList.size(); i++) {
				escribir.writeObject(empleadoList.get(i).toString());
			}
			// Cerramos los objetos para no consumir recursos.
			escribir.close();
			fos.close();

			System.out.println("se han guardado con exito;");
		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo. " + e.getMessage());
		}
	}

	public static void getVisualizarEmpleado(DefaultTableModel TableModel, JComboBox comboCliente) {

		empleadoConnect empleCon = new empleadoConnect();

		try {
			TableModel.setRowCount(0);

			if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("DNI")) {
				ArrayList<Empleado> empleadoList = empleCon.getEmpleado();
				for (Empleado empleado : empleadoList) {
					TableModel.addRow(new Object[] { empleado.getDNI() });

					System.out.println("Personas introducidas correctamente");
				}

			}

			else {
				if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
					ArrayList<Empleado> empleadoLists = empleCon.getEmpleado();
					for (Empleado empleado : empleadoLists) {
						TableModel.addRow(new Object[] { empleado.getDNI(), empleado.getNombre(),
								empleado.getApellido(), empleado.getRol(), empleado.getEmail(), empleado.getTelefono(),
								empleado.getContrasena() });
					}
				}

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void VisualizarEmpleadoComboBox(JComboBox<String> comboCliente) {

		comboCliente.addItem("Nombre");
		comboCliente.addItem("DNI");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}