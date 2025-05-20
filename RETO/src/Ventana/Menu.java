package Ventana;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

/**
  * Clase menu1 donde se muestra el  menú de administrador.
 * Permite al administrador eliminar,
 * modificar, registrar, y visualizar personas, además de realizar una copia de seguridad.
 * 
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal que contiene los componentes de la interfaz 
	 */
	private JPanel contentPane;
	/**
	 * Campo de texto para mostrar  el titulo de la ventana
	 */
	private JTextField txtMenDe;

	/**
	 * Launch the application.
	 */
	
	 /**
     * Método principal que muestra la ventana del menú de administrador.
     * 
     * @param args argumentos de línea de comando (no usados)
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
     * Constructor que crea y configura la ventana del menú principal.
     * 
     */
	public Menu() {
		
		
		//imagen
		setIconImage(
				new ImageIcon("D:/PROG/RETO/bin/Ventana/cineyelmo_logo.jpg").getImage());


	     

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMenDe = new JTextField();
		txtMenDe.setForeground(new Color(255, 255, 255));
		txtMenDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenDe.setText("Menú de Administrador");
		txtMenDe.setBounds(0, 0, 1017, 80);
		contentPane.add(txtMenDe);
		txtMenDe.setColumns(10);
		txtMenDe.setBackground(Color.decode("#002069"));
		txtMenDe.setFont(new Font("Tahoma", Font.BOLD, 38)); 
		txtMenDe.setEditable(false); // No permite editar el texto
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 1007, 638);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnEliminar = new JButton("ELIMINAR");
		panel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			
			/**
             * Acción al pulsar el botón "ELIMINAR"
             * Abre la ventana para eliminar personas.
             * 
             * @param e evento generado al pulsar el botón
             */
			public void actionPerformed(ActionEvent e) {
				EliminarPersona frame = new EliminarPersona();
            	frame.setVisible(true);
			}
		});
		btnEliminar.setBackground(Color.decode("#FBCF4C"));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 21)); 
		
		
		JButton btnModificar = new JButton("MODIFICAR");
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			
			/**
             * Acción al pulsar el botón "MODIFICAR"
             * Abre la ventana para modificar personas
             * 
             * @param e evento generado al pulsar el botón
             */
			public void actionPerformed(ActionEvent e) {
				ModificarPersona frame = new ModificarPersona();
            	frame.setVisible(true);
				
			}
		});
		btnModificar.setBackground(Color.decode("#FBCF4C"));
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 21)); 
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		panel.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			/**
             * Acción al pulsar el botón "REGISTRAR"
             * Abre la ventana para registrar personas
             * 
             * @param e evento generado al pulsar el botón
             */
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona frame = new RegistrarPersona();
            	frame.setVisible(true);
			}
		});
		btnRegistrar.setBackground(Color.decode("#FBCF4C"));
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 21)); 
		
		JButton btnVisualizar = new JButton("VISUALIZAR + Copia de Seguridad");
		panel.add(btnVisualizar);
		btnVisualizar.addActionListener(new ActionListener() {
			/**
             * Acción al pulsar el botón "VISUALIZAR"
             * Abre la ventana para visualizar y realizar la copia de seguridad
             * 
             * @param e evento generado al pulsar el botón
             */
			public void actionPerformed(ActionEvent e) {
				Visualizar frame = new Visualizar();
				frame.setVisible(true);
				
				
			}
		});
		btnVisualizar.setBackground(Color.decode("#FBCF4C"));
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 21)); 

		
		
	}
}
