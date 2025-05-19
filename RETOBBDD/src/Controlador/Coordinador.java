package Controlador;

import Ventana.Visualizar;
import logs.FormatoHTML;
import Modelos.Administrador;
import Modelos.Empleado;
import Modelos.Persona;
import BBDD.adminConnect;
import BBDD.empleadoConnect;
/*import BBDD.personaConnect;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

/**
 * El Coordinador, el encargado de llevar a cabo todo el programa
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class Coordinador implements ActionListener {
	
	
	
	
	private static final Logger sesion = Logger.getLogger("sesionLogger");
	private static final Logger errores = Logger.getLogger("erroresLogger");
	private static final LogManager logManager = LogManager.getLogManager();
	
	/**
	 * Esto servira para poder utilizar los loggers a traves del programa
	 */
	
	static {
		try {
			LogManager.getLogManager().reset();
			//fichero para cada sesion
			String nombreSesion = "sesion_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".html";
	        FileHandler fileHandler = new FileHandler(nombreSesion, true);
			fileHandler.setFormatter(new FormatoHTML());
			sesion.addHandler(fileHandler);
			sesion.setLevel(Level.ALL);

			//fichero global para errores
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
	/*personaConnect miPersonconnect;*/
	Empleado miEmpleado;
	Administrador miAdministrador;
	
	empleadoConnect miEmpleadoConnect;
	
	/**
	 * Aqui pasas todas las clases al Coordinador 
	 * @param paramPersona
	 * @param paramVisualizar
	 * @param empleadoParam
	 * @param adminParam
	 * @param paramEmpleadoConnect
	 */

	public Coordinador(Persona paramPersona, Visualizar paramVisualizar,
			Empleado empleadoParam, Administrador adminParam, empleadoConnect paramEmpleadoConnect) {
		this.miPersona = paramPersona;
		this.miVisualizador = paramVisualizar;
		/*this.miPersonconnect = paramPersonconnect;*/
		this.miEmpleado = empleadoParam;
		this.miAdministrador = adminParam;
		
		this.miEmpleadoConnect = paramEmpleadoConnect;
		
	}

	//Coordinador vacio
	public Coordinador() {

	}
	
	
	
	/**
	 * Esto hace la accion de guardar el Empleado 
	 * @param textFieldDatos
	 */
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
	
	/**
	 * Tiene la funcion de ejecutar la ventana de Inicio de Sesion
	 * Puede iniciar sesion tanto como empleado y administrador
	 * @param textFieldDNI
	 * @param textFieldContrasena
	 */
	public static void IniciarSesion(JTextField textFieldDNI, JTextField textFieldContrasena) {

		empleadoConnect empleCon = new empleadoConnect();
		
		adminConnect adminCon = new adminConnect();
		
		String dni = textFieldDNI.getText();
		String contrasena = textFieldContrasena.getText();
		boolean iniciarSesion = false;

		try {
			for (int i = 0; i < empleCon.getEmpleado().size(); i++) {
				if (dni.equalsIgnoreCase(empleCon.getEmpleado().get(i).getDNI())
						&& contrasena.equals(empleCon.getEmpleado().get(i).getContrasena())
						&& empleCon.getEmpleado().get(i).getRol().equalsIgnoreCase("empleado")) {
					Ventana.XMLForm frame = new Ventana.XMLForm();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "El empleado ha iniciado sesión con éxito");
					System.out.println("esto es el empleado");
					iniciarSesion = true;
					sesion.log(Level.INFO, "Inicio de sesión correcto. DNI: " + dni);


				} else {
					if (dni.equalsIgnoreCase(adminCon.getAdmin().get(i).getDNI())
							&& contrasena.equals(adminCon.getAdmin().get(i).getContrasena())
							&& adminCon.getAdmin().get(i).getRol().equalsIgnoreCase("administrador")) {
						
						Ventana.MenuAdmin frame = new Ventana.MenuAdmin();
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
	
	/**
	 * Tiene la funcion de Eliminar el Empleado usando el Dni
	 * @param textFieldDNI
	 */
	public static void EliminarEmpleado(JTextField textFieldDNI) {

		empleadoConnect empleCon = new empleadoConnect();

		String dni = textFieldDNI.getText();
		boolean borrado = false;

		try {
			for (int i = 0; i < empleCon.getEmpleado().size(); i++) {
				if (dni.equalsIgnoreCase(empleCon.getEmpleado().get(i).getDNI())) {
					empleCon.borrarEmpleado(dni);
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

	/**
	 * Se crean los filtros para el ComboBox
	 * @param comboCliente
	 */
	public static void VisualizarEmpleadoComboBox(JComboBox<String> comboCliente) {

		
		comboCliente.addItem("Nombre");
		comboCliente.addItem("DNI");
		
	}

	/**
	 * Se le da la funcion al comboBox, para que los filtros carguen los datos
	 * @param TableModel
	 * @param comboCliente
	 */
	public static void getVisualizarEmpleado(DefaultTableModel TableModel, JComboBox comboCliente) {

		empleadoConnect empleCon = new empleadoConnect();

		try {
			TableModel.setRowCount(0);

			if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("DNI")) {
				ArrayList<Empleado> empleadoList = empleCon.getEmpleado();
				for (Empleado empleado : empleadoList) {
					TableModel.addRow(new Object[] { empleado.getDNI()});

					System.out.println("Personas introducidas correctamente");
				}

			}

			else {
				if (comboCliente.getSelectedItem().toString().equalsIgnoreCase("Nombre")) {
					ArrayList<Empleado> empleadoLists = empleCon.getEmpleado();
					for (Empleado empleado : empleadoLists) {
						TableModel.addRow(new Object[] { empleado.getDNI(), empleado.getNombre(), empleado.getApellido(),
								empleado.getRol(), empleado.getEmail(), empleado.getTelefono(), empleado.getContrasena() });
					}	
				}
				

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	/**
	 * Esta Funcion es utilizado en la ventana de modificar
	 * @param textFieldDNI
	 * @param textFieldNombre
	 * @param textFieldApellido
	 * @param textFieldRol
	 * @param textFieldEmail
	 * @param textFieldTelefono
	 * @param textFieldContrasena
	 */
	public static void modificarEmpleado(JTextField textFieldDNI, JTextField textFieldNombre,
			JTextField textFieldApellido, JTextField textFieldRol, JTextField textFieldEmail,
			JTextField textFieldTelefono, JTextField textFieldContrasena) {
		
		empleadoConnect empleCon = new empleadoConnect();
		boolean resultado = false;
		String dni = textFieldDNI.getText();
		try {
			for (int i = 0; i < empleCon.getEmpleado().size(); i++) {
				if (dni.equalsIgnoreCase(empleCon.getEmpleado().get(i).getDNI())) {
					String nombre = textFieldNombre.getText();
					String apellido = textFieldApellido.getText();
					String rol = textFieldRol.getText();
					String email = textFieldEmail.getText();
					String telefono = textFieldTelefono.getText();
					String contrasena = textFieldContrasena.getText();

					Empleado empleado = new Empleado();

					empleCon.modificarEmpleado(empleado);
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
	
	/**
	 * Este lleva la funcion de la ventana Guardar
	 * @param textFieldDNI
	 * @param textFieldNombre
	 * @param textFieldApellido
	 * @param textFieldRol
	 * @param textFieldMail
	 * @param textFieldTelefono
	 * @param textFieldContrasena
	 * @throws IOException
	 */
	public static void GuardarEmpleado(JTextField textFieldDNI, JTextField textFieldNombre, JTextField textFieldApellido,
			JTextField textFieldRol, JTextField textFieldMail, JTextField textFieldTelefono,
			JTextField textFieldContrasena) throws IOException {

		empleadoConnect empleCon = new empleadoConnect();

		String dni = textFieldDNI.getText();
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		String rol = textFieldRol.getText();
		String mail = textFieldMail.getText();
		String telefono = textFieldTelefono.getText();
		String contrasena = textFieldContrasena.getText();

		Empleado empleado = new Empleado();

		try {
			empleCon.registrarEmpleado(empleado);
			JOptionPane.showMessageDialog(null, "Has insertado con éxito los datos");
			sesion.log(Level.INFO, "Persona registrada con éxito. DNI: " + dni);


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en guardarPersona");
			JOptionPane.showMessageDialog(null, "No se han podido insertar los datos");
			errores.log(Level.SEVERE, "Error al registrar persona");

		}

	}
	
	
	/**
	 * Esta tiene la funcion de cargar el XML en la ventana de reserva
	 * @param tablexml
	 */
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
                String idPelicula = getTextContent(sesion, "id_pelicula");
                String cif = getTextContent(sesion, "CIF");
                model.addRow(new Object[] { idSesion, hora, dia, aforo, cif, idPelicula });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	/**
	 * Esta funcion sirve para que busque los archivos 
	 * @return
	 */
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
    
    /**
     * Esto le da la funcionalidad al buscador de Ventanas
     * @param record
     * @param tagName
     * @return
     */
    
    public static void buscarDatos(DefaultTableModel tablemodel,JTable tablexml,JTextField textFieldBuscador) {
        DefaultTableModel ob=(DefaultTableModel) tablexml.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tablexml.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textFieldBuscador.getText()));
    }
	
    
    /**
     * Esto es un boton con la funcion de cargar un fichero binario en un txt
     * @param BotonCopiaSeg
     * @throws SQLException
     */
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
            for(int i = 0; i < adminList.size();i++) {
      
            escribir.writeObject(adminList.get(i));
            }
            
            for(int i = 0; i < empleadoList.size(); i++) {
            	escribir.writeObject(empleadoList.get(i).toString());
            }
            // Cerramos los objetos para no consumir recursos.
            escribir.close();
            fos.close();
             
            System.out.println("se han guardado con exito;");
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo. "
                    + e.getMessage());   
        }
    }

   	
    

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
