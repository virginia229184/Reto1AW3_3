
/**
 * paquete Ventana, que con tiene las clases
 * EliminarPersona,IniciarSesion, Menu1, ModificarPersona, RegistrarPersona, Visualizar, XMLForm
 */
package Ventana;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Clase eliminarPersona, consiste en una ventana para eliminar una persona utilizando su DNI
 * tiene botones para ejecutar la eliminación y salir del formulario.
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */

public class EliminarPersona extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal de la ventana
	 */
	private JPanel contentPane;
	/**
	 * Campo para ingresar DNI 
	 */

	private JTextField textFieldDNI;
	/**
	 * Campo para registrar texto
	 */
	private JTextField txtRegistrar;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Método para mostrar la ventana de eliminación.
	 * @param args  argumentos de línea de comandos (no usados)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarPersona frame = new EliminarPersona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	/**
	 * Constructor que crea y configura la ventana para eliminar personas
     * 
	 */
	public EliminarPersona() {
		// imagen
		//imagen
				setIconImage(
						new ImageIcon("D:/PROG/RETO/bin/Ventana/cineyelmo_logo.jpg").getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(319, 272, 71, 40);
		contentPane.add(lblNewLabel);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(386, 281, 124, 27);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JButton btnRegistrar = new JButton("Eliminar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.addActionListener(new ActionListener() {
			
			
			
			
			 /**
             * Acción que se ejecuta al pulsar el botón "Eliminar".
             * Llama al método para eliminar el empleado usando el DNI introducido
             * 
             * @param e evento de acción
             */
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.EliminarEmpleado(textFieldDNI);
				
			}
		});
		btnRegistrar.setBounds(640, 436, 117, 40);
		contentPane.add(btnRegistrar);
		btnRegistrar.setBackground(Color.decode("#FBCF4C"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			
			 /**
             * Acción que se ejecuta al pulsar el botón "Salir"
             * Cierra esta ventana y abre el menú principal
             * 
             * @param e evento de acción
             */
			public void actionPerformed(ActionEvent e) {
				Menu frame = new Menu();
				frame.setVisible(true);
				
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(640, 486, 117, 40);
		contentPane.add(btnSalir);
		btnSalir.setBackground(Color.decode("#FBCF4C"));
		
		txtRegistrar = new JTextField();
		txtRegistrar.setForeground(new Color(255, 255, 255));
		txtRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrar.setText("Eliminar");
		txtRegistrar.setBounds(0, 0, 767, 70);
		contentPane.add(txtRegistrar);
		txtRegistrar.setColumns(10);
		txtRegistrar.setBackground(Color.decode("#002069"));
		txtRegistrar.setFont(new Font("Tahoma", Font.BOLD, 38)); 
		txtRegistrar.setEditable(false); // No permite editar el texto
		
	}
}
