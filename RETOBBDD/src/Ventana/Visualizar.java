package Ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controlador.Coordinador; 

public class Visualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel TableModel;
	public JComboBox comboCliente;
	private JTextField textFieldDatos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizar frame = new Visualizar();
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
	public Visualizar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 814, 173);
		contentPane.add(scrollPane);
		
		TableModel = new DefaultTableModel(new Object[] {"DNI", "nombre", "apellido", "telefono", "email", "rol", "Contraseña"}, 0);
		
		table = new JTable(TableModel);
		scrollPane.setViewportView(table);
		/*Controlador.Coordinador.getVisualizarPersonas(TableModel);*/
		
		JComboBox comboCliente = new JComboBox<Object>();
		comboCliente.setBounds(366, 210, 105, 29);
		contentPane.add(comboCliente);
		Coordinador.VisualizarPersonasComboBox(comboCliente);
		
	
		
		JLabel lblDNI = new JLabel("Datos:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDNI.setBounds(239, 273, 105, 22);
		contentPane.add(lblDNI);
		
		
		JButton btnNewButton = new JButton("Cargar Copia de \r\nSeguridad");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(639, 338, 185, 55);
		contentPane.add(btnNewButton);
		
		JButton BotonCopiaSeg = new JButton("Copia de Seguridad");
		BotonCopiaSeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BotonCopiaSeg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		BotonCopiaSeg.setBounds(639, 273, 185, 55);
		contentPane.add(BotonCopiaSeg);
		
		textFieldDatos = new JTextField();
		textFieldDatos.setBounds(317, 278, 216, 19);
		contentPane.add(textFieldDatos);
		textFieldDatos.setColumns(10);
	}
}
