package Controlador;

/*import BBDD.personaConnect;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import BBDD.empleadoConnect;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BBDD.adminConnect;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import Ventana.Modificar;
import Ventana.Visualizar;

public class Coordinador implements ActionListener {

	Persona miPersona;
	Visualizar miVisualizador;
	/* personaConnect miPersonconnect; */
	Empleado miEmpleado;
	Administrador miAdministrador;
	Modificar miModificador;
	empleadoConnect miEmpleadoConnect;

	public Coordinador(Persona paramPersona, Visualizar paramVisualizar, Empleado empleadoParam,
			Administrador adminParam, Modificar paramModificar, empleadoConnect paramEmpleadoConnect) {
		this.miPersona = paramPersona;
		this.miVisualizador = paramVisualizar;
		/* this.miPersonconnect = paramPersonconnect; */
		this.miEmpleado = empleadoParam;
		this.miAdministrador = adminParam;
		this.miModificador = paramModificar;
		this.miEmpleadoConnect = paramEmpleadoConnect;
	}

	public Coordinador() {

	}

	public static void GuardarEmpleado(JTextField textFieldDatos) {

		empleadoConnect empleCon = new empleadoConnect();

		String Dni = textFieldDatos.getName();

		Empleado empleado = new Empleado();

		try {
			empleCon.registrarEmpleado(empleado);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void VisualizarEmpleadoComboBox(JComboBox<String> comboCliente) {
		comboCliente.addItem("Nombre");
		comboCliente.addItem("DNI");
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

	public void getModificarPersona(JButton buttonModificar) {
		empleadoConnect empleCon = new empleadoConnect();

		try {

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void tablemodelxml(JTable tablexml) {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Node doc = builder.parse(new File(exploradorArchivosNormal()));
			org.w3c.dom.Element root = ((org.w3c.dom.Document) doc).getDocumentElement();
			NodeList sesionList = root.getElementsByTagName("sesion");
			DefaultTableModel model = (DefaultTableModel) tablexml.getModel();

			for (int i = 0; i < sesionList.getLength(); i++) {
				Node sesion = sesionList.item(i);
				String idSesion = getTextContent(sesion, "IdSesion");
				String hora = getTextContent(sesion, "hora");
				String dia = getTextContent(sesion, "dia");
				String aforo = getTextContent(sesion, "aforo");
				String idPelicula = getTextContent(sesion, "IdPelicula");
				String cif = getTextContent(sesion, "cif");
				model.addRow(new Object[] { idSesion, hora, dia, aforo, idPelicula, cif });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String exploradorArchivosNormal() {
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

	public static String exploradorArchivosXML() {
		String filepath = "";
		JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos XML", "xml");
		selector.setSelectedFile(new File("sesion.xml"));
		selector.setFileFilter(filter);

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
		File archivo = new File("escritura.bin");
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
				escribir.writeObject(empleadoList.get(i));
			}
			// Cerramos los objetos para no consumir recursos.
			escribir.close();
			fos.close();

			System.out.println("se han guardado con exito;");
		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo. " + e.getMessage());
		}
	}

	public static void VisualizarFicheroBinario(JButton btnNewButtonCopia) throws SQLException {
		File archivo = new File("escritura.dat");

		try {
			// Para poder leer utilizaremos un FileInputStream pasandole
			// como referencia el archivo de tipo File.
			FileInputStream fis = new FileInputStream(archivo);

			// Declaramos una variable objeto del tipo ObjectInputStream
			ObjectInputStream leer;

			// Creamos un bucle para leer la información
			// Mientras haya bytes en el archivo.
			while (fis.available() > 0) {
				leer = new ObjectInputStream(fis);

				// En una variable objeto de tipo Persona almacenaremos
				// el objeto leido de tipo Object convertido en un objeto
				// de tipo persona
				Persona personaLeida = (Persona) leer.readObject();

				// Imprimimos el objeto leido en consola
				System.out.println(personaLeida);
			}

		} catch (Exception e) {
			System.out.println("Error al leer el archivo. " + e.getMessage());
		}
	}

	public static void exportadorXML() throws ParserConfigurationException, TransformerException, SQLException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		empleadoConnect Connection = new empleadoConnect();
		Connection con = Connection.connect();

		Statement st = con.createStatement();
		String consulta = "SELECT * FROM sesion";
		ResultSet rs = st.executeQuery(consulta);

		Document document = builder.newDocument();
		Element root = document.createElement("sesiones");
		document.appendChild(root);

		while (rs.next()) {
			Element sesion = document.createElement("sesion");
			root.appendChild(sesion);
			Element idSesion = document.createElement("IdSesion");
			idSesion.appendChild(document.createTextNode(rs.getString("IdSesion")));
			sesion.appendChild(idSesion);
			Element hora = document.createElement("hora");
			hora.appendChild(document.createTextNode(rs.getString("hora")));
			sesion.appendChild(hora);
			Element dia = document.createElement("dia");
			dia.appendChild(document.createTextNode(rs.getString("dia")));
			sesion.appendChild(dia);
			Element aforo = document.createElement("aforo");
			aforo.appendChild(document.createTextNode(rs.getString("aforo")));
			sesion.appendChild(aforo);
			Element cif = document.createElement("cif");
			cif.appendChild(document.createTextNode(rs.getString("cif")));
			sesion.appendChild(cif);
			Element idPelicula = document.createElement("IdPelicula");
			idPelicula.appendChild(document.createTextNode(rs.getString("IdPelicula")));
			sesion.appendChild(idPelicula);

		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(exploradorArchivosXML());
		transformer.transform(source, result);

		System.out.println("Se ha creado el fichero XML.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
