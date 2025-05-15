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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color; 
public class Visualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel TableModel;
	public JComboBox comboCliente;
	public JTextField textFieldBuscador;
	public JButton BotonCopiaSeg;
	public JButton ButtonCargar;
	private JTextField txtSesin;
	

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
		setBounds(100, 100, 932, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 98, 814, 173);
		contentPane.add(scrollPane);
		
		TableModel = new DefaultTableModel(new Object[] {"DNI", "nombre", "apellido", "rol", "email", "telefono", "contrasena"}, 0);
		
		table = new JTable(TableModel);
		scrollPane.setViewportView(table);
		
		
		
		JComboBox comboCliente = new JComboBox<Object>();
		comboCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coordinador.getVisualizarEmpleado(TableModel, comboCliente);
			}
		});
		comboCliente.setBounds(369, 304, 105, 29);
		contentPane.add(comboCliente);
		Coordinador.VisualizarEmpleadoComboBox(comboCliente);
		
		
		JLabel lblDNI = new JLabel("Datos:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDNI.setBounds(238, 357, 105, 22);
		contentPane.add(lblDNI);
		
		
		JButton ButtonCargar = new JButton("Cargar Copia de \r\nSeguridad");
		ButtonCargar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonCargar.setBounds(667, 475, 185, 55);
		contentPane.add(ButtonCargar);
		ButtonCargar.setBackground(Color.decode("#FBCF4C"));
		
		JButton BotonCopiaSeg = new JButton("Copia de Seguridad");
		BotonCopiaSeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coordinador.CargarFicherosBinarios(BotonCopiaSeg);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		BotonCopiaSeg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BotonCopiaSeg.setBounds(667, 410, 185, 55);
		contentPane.add(BotonCopiaSeg);
		BotonCopiaSeg.setBackground(Color.decode("#FBCF4C"));
		
		textFieldBuscador = new JTextField();
		textFieldBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Controlador.Coordinador.buscarDatos(TableModel, table, textFieldBuscador);
			}
		});
		textFieldBuscador.setBounds(335, 362, 216, 19);
		contentPane.add(textFieldBuscador);
		textFieldBuscador.setColumns(10);
		
		txtSesin = new JTextField();
		txtSesin.setForeground(new Color(255, 255, 255));
		txtSesin.setFont(new Font("Tahoma", Font.BOLD, 38));
		txtSesin.setHorizontalAlignment(SwingConstants.CENTER);
		txtSesin.setText("Visualizar");
		txtSesin.setBounds(0, 0, 918, 73);
		contentPane.add(txtSesin);
		txtSesin.setColumns(10);
		txtSesin.setBackground(Color.decode("#002069"));
		txtSesin.setEditable(false); // No permite editar el texto
		
	}
}