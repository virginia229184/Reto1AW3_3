package Controlador;

import Ventana.Visualizar;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import Ventana.Modificar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

	Persona miPersona;
	Visualizar miVisualizador;
	/*personaConnect miPersonconnect;*/
	Empleado miEmpleado;
	Administrador miAdministrador;
	Modificar miModificador;
	empleadoConnect miEmpleadoConnect;

	public Coordinador(Persona paramPersona, Visualizar paramVisualizar,
			Empleado empleadoParam, Administrador adminParam, Modificar paramModificar, empleadoConnect paramEmpleadoConnect) {
		this.miPersona = paramPersona;
		this.miVisualizador = paramVisualizar;
		/*this.miPersonconnect = paramPersonconnect;*/
		this.miEmpleado = empleadoParam;
		this.miAdministrador = adminParam;
		this.miModificador = paramModificar;
		this.miEmpleadoConnect = paramEmpleadoConnect;
	}

	public Coordinador() {

	}
	
	
	public static void VisualizarEmpleadoModi(DefaultTableModel Tablemodel) {
		empleadoConnect empleCon = new empleadoConnect();
		
		
		
		
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
        } catch (Exception ex) {
            ex.printStackTrace();
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
    
    
    public static void buscarDatos(DefaultTableModel tablemodel,JTable tablexml,JTextField textFieldBuscador) {
        DefaultTableModel ob=(DefaultTableModel) tablexml.getModel();
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
