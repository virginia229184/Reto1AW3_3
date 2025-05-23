
/**
 * Paquete encargado de coordinar la lógica de la aplicación,

 * gestionando las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 */
package Controlador;

import Ventana.EliminarPersona;
import Ventana.Menu;
import Ventana.ModificarPersona;
import Ventana.RegistrarPersona;
import Ventana.Visualizar;
import Ventana.XMLForm;
import logs.FormatoHTML;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import BBDD.adminConnect;
//import BBDD.adminConnect;
import BBDD.empleadoConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JPasswordField;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

/**
 * Clase coordinador, controla lo relacionado con los modelos y las ventanas,
 * gestiona las operaciones CRUD, inicio de sesión, manejo de logs, carga de xml
 * y manejo de ficheros binarios
 * 
 * @author FELIPE
 * @author VIRGINIA
 * @author JON
 * @version 1.0
 */

public class Coordinador implements ActionListener {

	private static final Logger sesion = Logger.getLogger("sesionLogger");
	private static final Logger errores = Logger.getLogger("erroresLogger");
	private static final LogManager logManager = LogManager.getLogManager();

	static {

		try {

			LogManager.getLogManager().reset();
			// fichero para cada sesion
		
			String nombreSesion = "sesion_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
			FileHandler fileHandler = new FileHandler(nombreSesion, true);
			fileHandler.setFormatter(new FormatoHTML());
			sesion.addHandler(fileHandler);
			sesion.setLevel(Level.ALL);

//	fichero global para errores
			FileHandler fileHandler1 = new FileHandler("errores.html", true);
			fileHandler1.setFormatter(new FormatoHTML());
			errores.addHandler(fileHandler1);
			errores.setLevel(Level.ALL);
		} catch (IOException e) {
			System.err.println("Error al configurar los logs");
		}
	}

	private Ventana.IniciarSesion iniciarSesion;
	private Menu menu;
	private EliminarPersona elimPersona;
	private ModificarPersona modPersona;
	private RegistrarPersona regPersona;
	private Visualizar visualizar;
	private XMLForm xmlForm;

	private Administrador admin;
	private Empleado emp;

	private adminConnect admCon;
	private empleadoConnect empCon;

	/**
	 * constructor con parametros que inicializa los modelos y la vista
	 * 
	 * @param paramVisualizar Objeto de la clase Visualizar
	 * @param empleadoParam   Objeto del modelo Empleado
	 * @param adminParam      Objeto del modelo Administrador
	 * 
	 */
	
	/**
	 * constructor con parametros que inicializa los modelos y la vista
	 * @param paramIniciarSesion Objeto de la clase IniciarSesion
	 * @param paramMenu Objeto de la clase Menu
	 * @param paramEliminarPersona Objeto de la clase EliminarPersona
	 * @param paramModificarPersona Objeto de la clase ModificarPersona
	 * @param paramRegistrarPersona Objeto de la clase RegistrarPersona
	 * @param paramVisualizar Objeto de la clase Visualizar
	 * @param paramXMLForm Objeto de la clase XMLForm
	 * @param paramEmpleado Objeto de la clase Empleado
	 * @param paramAdmin Objeto de la clase Admin
	 * @param paramAdmCon Objeto de la clase AdmCon
	 * @param paramEmpCon Objeto de la clase EmpCon
	 */

	public Coordinador(Ventana.IniciarSesion paramIniciarSesion, Menu paramMenu, EliminarPersona paramEliminarPersona,
			ModificarPersona paramModificarPersona, RegistrarPersona paramRegistrarPersona, Visualizar paramVisualizar, XMLForm paramXMLForm,
			Empleado paramEmpleado, Administrador paramAdmin,adminConnect paramAdmCon, empleadoConnect paramEmpCon) {
		this.iniciarSesion = paramIniciarSesion;
		this.menu = paramMenu;
		this.elimPersona = paramEliminarPersona;
		this.modPersona = paramModificarPersona;
		this.regPersona = paramRegistrarPersona;
		this.visualizar = paramVisualizar;
		this.xmlForm = paramXMLForm;
		this.admin = paramAdmin;
		this.emp = paramEmpleado;
		this.admCon = paramAdmCon;
		this.empCon = paramEmpCon;
	}

	/**
	 * constructor vacio
	 */
	public Coordinador() {

	}

//	INSERTAR PERSONA

	/**
	 * metodo que registra un nuevo empleado en la base de datos
	 * 
	 * @param textFieldDNI      campo de texto del DNI
	 * @param textFieldNombre   campo de texto del nombre
	 * @param textFieldApellido campo de texto del apellido
	 * @param textFieldRol      campo de texto del rol
	 * @param textFieldMail     campo de texto del mail
	 * @param textFieldTelefono campo de texto del telefono
	 * @param passwordField     campo de texto del de la contrasena
	 * @throws IOException si ocurre un error al escribir en el archivo de registro
	 */
	public static void GuardarPersona(JTextField textFieldDNI, JTextField textFieldNombre, JTextField textFieldApellido,

			JTextField textFieldRol, JTextField textFieldMail, JTextField textFieldTelefono,

			JPasswordField passwordField) throws IOException {

		empleadoConnect empCon = new empleadoConnect();
		String dni = textFieldDNI.getText();
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		String rol = textFieldRol.getText();
		String mail = textFieldMail.getText();
		String telefono = textFieldTelefono.getText();
		String contrasena = String.valueOf(passwordField.getPassword());
		Empleado empleado = new Empleado(dni, nombre, apellido, rol, mail, telefono, contrasena);

		try {
			empCon.registrarEmpleado(empleado);
			JOptionPane.showMessageDialog(null, "Has insertado con éxito los datos");
			sesion.log(Level.INFO, "Persona registrada con éxito. DNI: " + dni);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en guardarPersona");
			JOptionPane.showMessageDialog(null, "No se han podido insertar los datos");
			errores.log(Level.SEVERE, "Error al registrar persona. El DNI ya existe");

		}

	}

	/**
	 * 
	 * 
	 * Elimina un empleado de la base de datos teniendo en cuenta el DNI
	 * 
	 * @param textFieldDNI campo de texto del DNI
	 * 
	 */

	// ELIMINAR PERSONA

	public static void EliminarEmpleado(JTextField textFieldDNI) {
		empleadoConnect empCon = new empleadoConnect();
		String dni = textFieldDNI.getText();
		boolean borrado = false;

		try {
			for (int i = 0; i < empCon.getEmpleado().size(); i++) {
				if (dni.equals(empCon.getEmpleado().get(i).getDNI())) {
					empCon.borrarEmpleado(dni);
					borrado = true;
					JOptionPane.showMessageDialog(null, "Has eliminado con éxito los datos");
					sesion.log(Level.INFO, "Persona eliminada con éxito. DNI: " + dni);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en eliminarPersona");
			JOptionPane.showMessageDialog(null, "No se han eliminado con éxito los datos");
			errores.log(Level.SEVERE, "Error al eliminar persona");

		}

		if (borrado == false) {
			System.out.println("El dni no existe");
			JOptionPane.showMessageDialog(null, "No se han podido eliminar los datos");
			errores.log(Level.SEVERE, "Error al eliminar persona. DNI no encontrado");

		}

	}

	/**
	 * Iniciar sesion validadndo el dni, la contraseña y el tipo de usuario
	 * 
	 * @param textFieldDNI  campo de texto del DNI
	 * @param passwordField campo de texto de la contraseña
	 */
	
	
//	INICIAR SESION

	public static void IniciarSesion(JTextField textFieldDNI, JPasswordField passwordField) {

		empleadoConnect empCon = new empleadoConnect();
		String dni = textFieldDNI.getText();
		String contrasena = String.valueOf(passwordField.getPassword());

		boolean iniciarSesion = false;

		try {

			for (int i = 0; i < empCon.getEmpleado().size(); i++) {
				if (dni.equalsIgnoreCase(empCon.getEmpleado().get(i).getDNI())
						&& contrasena.equals(empCon.getEmpleado().get(i).getContrasena())
						&& empCon.getEmpleado().get(i).getRol().equalsIgnoreCase("empleado")) {
					Ventana.XMLForm frame = new Ventana.XMLForm();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "El empleado ha iniciado sesión con éxito");
					System.out.println("esto es el empleado");
					iniciarSesion = true;
					sesion.log(Level.INFO, "Inicio de sesión correcto. DNI: " + dni);
				} else {
					if (dni.equalsIgnoreCase(empCon.getEmpleado().get(i).getDNI())
							&& contrasena.equals(empCon.getEmpleado().get(i).getContrasena())
							&& empCon.getEmpleado().get(i).getRol().equalsIgnoreCase("administrador")) {
						Ventana.Menu frame = new Ventana.Menu();
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null, "El administrador ha iniciado sesión con éxito");
						System.out.println("esto es el administrador");
						iniciarSesion = true;

						sesion.log(Level.INFO, "Inicio de sesión correcto. DNI: " + dni);
					}
				}
			}
			if (iniciarSesion == false) {
				JOptionPane.showMessageDialog(null, "Has introducido mal un dato(DNI o contraseña)");
				errores.log(Level.SEVERE, "Error en Iniciar Sesion");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en IniciarSesion");
			errores.log(Level.SEVERE, "Error en Iniciar Sesion");
		}
	}

	/**
	 * Modificar los datos de un empleado de la base de datos
	 * 
	 * @param textFieldDNI        campo de texto del DNI
	 * @param textFieldNombre     campo de texto del nombre
	 * @param textFieldApellido   campo de texto del apellido
	 * @param textFieldRol        campo de texto del rol
	 * @param textFieldEmail      campo de texto del email
	 * @param textFieldTelefono   campo de texto del telefono
	 * @param textFieldContrasena campo de texto del contrasena
	 */
	

//	MODIFICAR PERSONA 

	public static void modificarPersona(JTextField textFieldDNI, JTextField textFieldNombre,
			JTextField textFieldApellido, JTextField textFieldRol, JTextField textFieldEmail,
			JTextField textFieldTelefono, JPasswordField textFieldContrasena) {

		empleadoConnect empCon = new empleadoConnect();
		boolean resultado = false;
		String dni = textFieldDNI.getText();
		try {

			for (int i = 0; i < empCon.getEmpleado().size(); i++) {

				if (dni.equalsIgnoreCase(empCon.getEmpleado().get(i).getDNI())) {
					String nombre = textFieldNombre.getText();
					String apellido = textFieldApellido.getText();
					String rol = textFieldRol.getText();
					String email = textFieldEmail.getText();
					String telefono = textFieldTelefono.getText();
					String contrasena = String.valueOf(textFieldContrasena.getPassword());
					Empleado empleado = new Empleado(dni, nombre, apellido, rol, email, telefono, contrasena);
					empCon.modificarEmpleado(empleado);
					System.out.println(empleado);
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
			errores.log(Level.SEVERE, "Error al modificar persona. No se ha encontrado el DNI");
		}
	}

	/**
	 * Carga los datos de un archivo XML seleccionado en una tabla.
	 * 
	 * @param tablexml la tabla donde se cargará la información del archivo XML
	 */

//	ventana XMLForm

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
				String idSesion = getTextContent(sesion, "IdSesion");
				String hora = getTextContent(sesion, "hora");
				String dia = getTextContent(sesion, "dia");
				String aforo = getTextContent(sesion, "aforo");
				String cif = getTextContent(sesion, "cif");
				String idPelicula = getTextContent(sesion, "IdPelicula");
				model.addRow(new Object[] { idSesion, hora, dia, aforo, cif, idPelicula });
			}
			sesion.log(Level.INFO, "Datos del XML cargados correctamente en la tabla");
		} catch (Exception ex) {
			ex.printStackTrace();
			errores.log(Level.SEVERE, "Error al cargar datos XML en tabla.");
		}
	}

	/**
	 * Recupera el contenido de un nodo XML dado el nombre de la etiqueta.
	 * 
	 * @param record  nodo XML a consultar
	 * @param tagName nombre de la etiqueta
	 * @return el contenido del nodo como String
	 */

//	VENTANA XMLFORM

	private static String getTextContent(Node record, String tagName) {
		NodeList list = ((org.w3c.dom.Element) record).getElementsByTagName(tagName);
		if (list.getLength() > 0) {
			return list.item(0).getTextContent().trim();
		}
		return "";
	}

	/**
	 * Abre un explorador de archivos y devuelve la ruta del archivo seleccionado
	 * 
	 * @return ruta absoluta del archivo seleccionado
	 */

//	VENTANA XMLFORM
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

	/**
	 * Busca datos en una tabla según el texto introducido
	 * 
	 * @param tablemodel        el modelo de datos de la tabla
	 * @param table             el modelo de datos de la tabla a filtrar
	 * @param textFieldBuscador campo de texto para buscar
	 */

//	ventana visualizar
	public static void buscarDatos(DefaultTableModel tablemodel, JTable table, JTextField textFieldBuscador) {
		DefaultTableModel ob = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
		table.setRowSorter(obj);
		obj.setRowFilter(RowFilter.regexFilter(textFieldBuscador.getText()));
		sesion.log(Level.INFO, "Búsqueda en la tabla realizada");

	}

	/**
	 * Carga la lista de empleados en un archivo binario.
	 * 
	 * @param BotonCopiaSeg Botón que ejecuta la acción
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */

//	ventana visualizar

	public static void CargarFicherosBinarios(JButton BotonCopiaSeg) throws SQLException {
		File archivo = new File("escritura.dat");
		empleadoConnect empConnect = new empleadoConnect();
		ArrayList<Empleado> empleadoList = empConnect.getEmpleado();
		try {

			// Para poder escribir utilizaremos un FileOutputStream pasandole
			// como referencia el archivo de tipo File.
			FileOutputStream fos = new FileOutputStream(archivo);
			// Y crearemos también una instancia del tipo ObjectOutputStream
			// al que le pasaremos por parámetro
			// el objeto de tipo FileOutputStream
			ObjectOutputStream escribir = new ObjectOutputStream(fos);
			// Escribimos los objetos en el archivo.
			for (int i = 0; i < empleadoList.size(); i++) {
				escribir.writeObject(empleadoList);
			}
			// Cerramos los objetos para no consumir recursos.
			escribir.close();
			fos.close();
			System.out.println("se han guardado con exito;");
			sesion.log(Level.INFO, "Datos guardados correctamente en el fichero binario");
		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo. " + e.getMessage());
			errores.log(Level.SEVERE, "Error datos guardados correctamente en el fichero binario");
		}
	}

	/**
	 * Llena la tabla con datos de empleados según la selección del comboBox.
	 * 
	 * @param TableModel   modelo de la tabla
	 * @param comboCliente comboBox con opciones de datos a mostrar
	 */
//	ventana visualizar

	public static void getVisualizarEmpleado(DefaultTableModel TableModel, JComboBox comboCliente) {
		empleadoConnect empleCon = new empleadoConnect();

		try {
			TableModel.setRowCount(0);

			if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("Datos Empleados")) {
				ArrayList<Empleado> empleadoLists = empleCon.getEmpleado();
				for (Empleado empleado : empleadoLists) {
					TableModel.addRow(new Object[] { empleado.getDNI(), empleado.getNombre(), empleado.getApellido(),
							empleado.getRol(), empleado.getEmail(), empleado.getTelefono(), empleado.getContrasena() });

				}
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/**
	 * Añade opciones al comboBox
	 * 
	 * @param comboCliente ComboBox donde se agregan las opciones
	 */

//	ventana visualizar

	public static void VisualizarEmpleadoComboBox(JComboBox<String> comboCliente) {
		comboCliente.addItem("Copia de Seguridad");
		comboCliente.addItem("Datos Empleados");

	}

	/**
	 * Exporta las sesiones almacenadas en la base de datos a un archivo XML.
	 * 
	 * @throws ParserConfigurationException si hay un error en la configuración del parser
	 * @throws TransformerException         si ocurre un error al transformar el DOM en XML                               
	 * @throws SQLException                 si ocurre un error al acceder a la base de datos
	 *                                      
	 */

//	ventana xmlform boton generaar sesion
	// exportador xml
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
		sesion.log(Level.INFO, "Sesiones exportadas correctamente");
	}

	
	public static void importarXMLLL() throws SQLException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
       
            adminConnect adminCon = new adminConnect();
            Connection con = adminCon.connect();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(exploradorArchivos()));
            NodeList nodeList = document.getElementsByTagName("sesion_reservada");
            for (int i = 0; i < nodeList.getLength(); i = i + 2) {
                Node node = nodeList.item(i);
                String sesionElegida = node.getTextContent();
                String[] datos = sesionElegida.split("\n");

                int id_sesion = Integer.valueOf(datos[1].replaceAll("\\s", ""));
                String dni = datos[2].replaceAll("\\s", "");
                

                Statement st = con.createStatement();
                String consulta = "INSERT INTO sesion_reservada (id_sesion, DNI) VALUES ('" + id_sesion + "', '" + dni
                        + "');";
                st.executeUpdate(consulta);
                System.out.println("se ha importado con exito el xml");
                try {
                    FileWriter writer = new FileWriter(
                            "src\\consulta.txt");
                    writer.write(consulta);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error al generar el fichero .txt");
                    e.printStackTrace();
                 
                }
            }
        }
	/**
	 * Abre un explorador de archivos para seleccionar un archivo con extensión XML.
	 * 
	 * @return la ruta absoluta del archivo XML seleccionado. Si no se selecciona
	 *         ningún archivo, se devuleve una cadena vacía
	 */

//	ventana xmlform boton generar sesion

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

	/**
	 * Lee y muestra el contenido de un archivo binario. El archivo debe estar
	 * serializado como ArrayList de Empleados.
	 * 
	 * @param btnNewButtonCopia botón relacionado (no utilizado en el método
	 *                          actualmente)
	 * @param TableModel        tabla donde se cargan los datos
	 * @throws SQLException si ocurre un error relacionado con la base de datos
	 */

//	ventana visualizar cargar copia no se si esta acabado

	public static void VisualizarFicheroBinario(JButton btnNewButtonCopia, DefaultTableModel TableModel)
			throws SQLException {
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
				ArrayList<Empleado> Empleado = (ArrayList<Empleado>) leer.readObject();

				// Imprimimos el objeto leido en consola

				TableModel.setRowCount(0);
				for (int i = 0; i < Empleado.size(); i++) {
					TableModel.addRow(new Object[] { Empleado.get(i).getDNI(), Empleado.get(i).getNombre(),
							Empleado.get(i).getApellido(), Empleado.get(i).getRol(), Empleado.get(i).getEmail(),
							Empleado.get(i).getTelefono(), Empleado.get(i).getContrasena() });
				}

				sesion.log(Level.INFO, "Fichero binario visualizado correctamente");
			}
		} catch (Exception e) {
			System.out.println("Se han visualizado correctamente los datos");
			errores.log(Level.SEVERE, "Error al visualizar el fichero binario ");

		}

	}

	@Override

	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

	}

}