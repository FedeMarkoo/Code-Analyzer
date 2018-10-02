package FrontEnd;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import BackEnd.Analizador;
import BackEnd.Archivo;
import BackEnd.Clase;
import BackEnd.Metodo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

public class Interfaz {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Code Analyzer");
		frame.setBounds(100, 100, 725, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 26, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 529, 73, 59, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Analizar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 234, 213, 0, 0 };
		gbl_panel.rowHeights = new int[] { 36, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JComboBox<Archivo> archivos = new JComboBox<Archivo>();
		GridBagConstraints gbc_archivos = new GridBagConstraints();
		gbc_archivos.insets = new Insets(0, 0, 5, 5);
		gbc_archivos.fill = GridBagConstraints.BOTH;
		gbc_archivos.gridx = 0;
		gbc_archivos.gridy = 0;
		panel.add(archivos, gbc_archivos);

		JComboBox<Clase> clases = new JComboBox<>();
		GridBagConstraints gbc_clases = new GridBagConstraints();
		gbc_clases.insets = new Insets(0, 0, 5, 5);
		gbc_clases.fill = GridBagConstraints.BOTH;
		gbc_clases.gridx = 1;
		gbc_clases.gridy = 0;
		panel.add(clases, gbc_clases);

		JComboBox<Metodo> metodos = new JComboBox<>();
		GridBagConstraints gbc_metodos = new GridBagConstraints();
		gbc_metodos.insets = new Insets(0, 0, 5, 0);
		gbc_metodos.fill = GridBagConstraints.BOTH;
		gbc_metodos.gridx = 2;
		gbc_metodos.gridy = 0;
		panel.add(metodos, gbc_metodos);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane.setBounds(106, 36, 51, 19);
		panel_2.add(textPane);

		JLabel lblNewLabel = new JLabel("CC");
		lblNewLabel.setBounds(10, 36, 86, 19);
		panel_2.add(lblNewLabel);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_1.setBounds(106, 61, 51, 19);
		panel_2.add(textPane_1);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 61, 86, 19);
		panel_2.add(lblCodigo);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setToolTipText("FanIn dentro de la clase");
		textPane_2.setEditable(false);
		textPane_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_2.setBounds(106, 111, 51, 19);
		panel_2.add(textPane_2);

		JLabel lblFanin = new JLabel("FanIn");
		lblFanin.setBounds(10, 111, 86, 19);
		panel_2.add(lblFanin);

		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(10, 86, 86, 19);
		panel_2.add(lblComentarios);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_3.setBounds(106, 86, 51, 19);
		panel_2.add(textPane_3);

		JLabel lblFanout = new JLabel("FanOut");
		lblFanout.setBounds(10, 136, 86, 19);
		panel_2.add(lblFanout);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_4.setBounds(106, 136, 51, 19);
		panel_2.add(textPane_4);

		JLabel lblN = new JLabel("N");
		lblN.setBounds(274, 36, 15, 19);
		panel_2.add(lblN);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_5.setBounds(299, 36, 51, 19);
		panel_2.add(textPane_5);

		JLabel lblN_1 = new JLabel("N1");
		lblN_1.setBounds(274, 61, 15, 19);
		panel_2.add(lblN_1);

		JTextPane textPane_6 = new JTextPane();
		textPane_6.setEditable(false);
		textPane_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_6.setBounds(299, 61, 51, 19);
		panel_2.add(textPane_6);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setToolTipText("FanIn en todos los archivos analizados");
		textPane_8.setEditable(false);
		textPane_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_8.setBounds(167, 111, 51, 19);
		panel_2.add(textPane_8);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 11, 86, 19);
		panel_2.add(lblTipo);

		JTextPane textPane_9 = new JTextPane();
		textPane_9.setEditable(false);
		textPane_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_9.setBounds(106, 11, 112, 19);
		panel_2.add(textPane_9);

		JLabel lblNewLabel_1 = new JLabel("Halstead");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(274, 11, 147, 19);
		panel_2.add(lblNewLabel_1);

		JTextPane textPane_10 = new JTextPane();
		textPane_10.setEditable(false);
		textPane_10.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_10.setBounds(391, 61, 51, 19);
		panel_2.add(textPane_10);

		JLabel lblN_3 = new JLabel("n1");
		lblN_3.setBounds(366, 61, 15, 19);
		panel_2.add(lblN_3);

		JLabel lblN_2 = new JLabel("n");
		lblN_2.setBounds(366, 36, 15, 19);
		panel_2.add(lblN_2);

		JTextPane textPane_11 = new JTextPane();
		textPane_11.setEditable(false);
		textPane_11.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_11.setBounds(391, 36, 51, 19);
		panel_2.add(textPane_11);

		JLabel lblN_4 = new JLabel("N2");
		lblN_4.setBounds(274, 86, 15, 19);
		panel_2.add(lblN_4);

		JTextPane textPane_7 = new JTextPane();
		textPane_7.setEditable(false);
		textPane_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_7.setBounds(299, 86, 51, 19);
		panel_2.add(textPane_7);

		JTextPane textPane_12 = new JTextPane();
		textPane_12.setEditable(false);
		textPane_12.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_12.setBounds(391, 86, 51, 19);
		panel_2.add(textPane_12);

		JLabel lblN_5 = new JLabel("n2");
		lblN_5.setBounds(366, 86, 15, 19);
		panel_2.add(lblN_5);

		JTextPane textPane_14 = new JTextPane();
		textPane_14.setEditable(false);
		textPane_14.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_14.setBounds(356, 111, 86, 19);
		panel_2.add(textPane_14);

		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(274, 111, 76, 19);
		panel_2.add(lblVolumen);

		JTextPane textPane_13 = new JTextPane();
		textPane_13.setEditable(false);
		textPane_13.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_13.setBounds(356, 136, 86, 19);
		panel_2.add(textPane_13);

		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setBounds(274, 136, 76, 19);
		panel_2.add(lblDificultad);

		JTextPane textPane_15 = new JTextPane();
		textPane_15.setEditable(false);
		textPane_15.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_15.setBounds(356, 161, 86, 19);
		panel_2.add(textPane_15);

		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setBounds(274, 161, 76, 19);
		panel_2.add(lblNivel);

		JTextPane textPane_16 = new JTextPane();
		textPane_16.setEditable(false);
		textPane_16.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_16.setBounds(356, 186, 86, 19);
		panel_2.add(textPane_16);

		JLabel lblEsferzo = new JLabel("Esferzo");
		lblEsferzo.setBounds(274, 186, 76, 19);
		panel_2.add(lblEsferzo);

		JTextPane textPane_17 = new JTextPane();
		textPane_17.setEditable(false);
		textPane_17.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_17.setBounds(356, 211, 86, 19);
		panel_2.add(textPane_17);
		JLabel lblTmpEntendimiento = new JLabel("Tmp Entendimiento");
		lblTmpEntendimiento.setBounds(274, 211, 76, 19);
		panel_2.add(lblTmpEntendimiento);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		JScrollPane j = new JScrollPane();
		panel.add(j, gbc_textArea);

		JTextArea textArea = new JTextArea();
		j.setViewportView(textArea);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread() {
					public void run() {
						Analizador a = new Analizador(textField.getText());
						archivos.removeAllItems();
						for (Archivo archivo : a.archivos) {
							archivos.addItem(archivo);
						}
					}
				}.start();

			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				textField.setText(f.getSelectedFile().getAbsolutePath());
				new Thread() {
					public void run() {
						Analizador a = new Analizador(textField.getText());
						archivos.removeAllItems();
						for (Archivo archivo : a.archivos) {
							archivos.addItem(archivo);
						}
					}
				}.start();
			}
		});

		archivos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				clases.removeAllItems();
				if (archivos.getComponentCount() == 0 || archivos.getSelectedItem() == null)
					return;
				for (Clase clase : ((Archivo) archivos.getSelectedItem()).clases) {
					clases.addItem(clase);
				}
			}
		});
		clases.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				metodos.removeAllItems();
				if (clases.getComponentCount() == 0 || clases.getSelectedItem() == null)
					return;
				for (Metodo metodo : ((Clase) clases.getSelectedItem()).metodo) {
					metodos.addItem(metodo);
				}
			}
		});

		metodos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (metodos.getComponentCount() == 0 || metodos.getSelectedItem() == null) {
					textPane_9.setText("");
					textPane.setText("");
					textPane_1.setText("");
					textPane_3.setText("");
					textPane_2.setText("");
					textPane_8.setText("");
					textPane_4.setText("");
					textPane_5.setText("");
					textPane_11.setText("");
					textPane_6.setText("");
					textPane_7.setText("");
					textPane_10.setText("");
					textPane_12.setText("");
					textPane_14.setText("");
					textPane_13.setText("");
					textPane_15.setText("");
					textPane_16.setText("");
					textPane_17.setText("");
					textArea.setText("");

					textPane.setBackground(Color.WHITE);
					textPane_2.setBackground(Color.WHITE);
					textPane_8.setBackground(Color.WHITE);
					textPane_4.setBackground(Color.WHITE);
					textPane_14.setBackground(Color.WHITE);
					return;
				}
				Metodo metodo = (Metodo) metodos.getSelectedItem();
				if (metodo.nivelAlerta < 6)
					textPane.setBackground(Color.GREEN);
				else if (metodo.nivelAlerta < 12)
					textPane.setBackground(Color.YELLOW);
				else
					textPane.setBackground(Color.RED);
				int fanInC = metodo.fanIn[0];
				if (fanInC < 3)
					textPane_2.setBackground(Color.YELLOW);
				else if (fanInC > 5)
					textPane_2.setBackground(Color.GREEN);
				int fanInT = metodo.fanIn[1];
				if (fanInT < 3)
					textPane_8.setBackground(Color.YELLOW);
				else if (fanInT > 5)
					textPane_8.setBackground(Color.GREEN);
				if (metodo.fanOut < 5)
					textPane_4.setBackground(Color.GREEN);
				else if (metodo.fanOut > 12)
					textPane_4.setBackground(Color.RED);
				else
					textPane_4.setBackground(Color.YELLOW);

				double v = metodo.halstead.V();
				if (v < 200)
					textPane_14.setBackground(Color.GREEN);
				else if (v < 450)
					textPane_14.setBackground(Color.YELLOW);
				else
					textPane_14.setBackground(Color.RED);

				textPane.setText(metodo.cc + "");
				textPane_1.setText(metodo.lineasCodigo + "");
				textPane_2.setText(fanInC + "");
				textPane_3.setText(metodo.lineasComentadas + "");
				textPane_4.setText(metodo.fanOut + "");
				textPane_5.setText(metodo.halstead.N() + "");
				textPane_6.setText(String.format("%-6.0f", metodo.halstead.N1));
				textPane_7.setText(String.format("%-6.0f", metodo.halstead.N2));
				textPane_8.setText(fanInT + "");
				textPane_9.setText(metodo.tipo);
				textPane_10.setText(String.format("%-6.0f", metodo.halstead.n1));
				textPane_11.setText(metodo.halstead.n() + "");
				textPane_12.setText(String.format("%-6.0f", metodo.halstead.n2));
				textPane_13.setText(String.format("%-6.3f", metodo.halstead.D()));
				textPane_14.setText(String.format("%-6.3f", v));
				textPane_15.setText(String.format("%-6.3f", metodo.halstead.L()));
				textPane_16.setText(String.format("%-6.3f", metodo.halstead.E()));
				textPane_17.setText(String.format("%-6.3f", metodo.halstead.T()));
				textArea.setText(metodo.codigoCompleto.replace("\t", "     "));
			}
		});

		tooltip(lblCodigo, "Lineas de codigo escritas");
		tooltip(lblComentarios, "Lineas de comentarios escritas");
		tooltip(lblDificultad,
				"DIFICULTAD\r\n" + "Para definir la dificultad D del programa, se usa la fórmula siguiente:\r\n"
						+ "\r\n" + "dificultad D = (n1 * N2) / (n2 *2)\r\n" + "");
		tooltip(lblEsferzo,
				"El esfuerzo es otra medida estudiada por Halstead que ofrece una medida del trabajo requerido para desarrollar un programa. \r\n"
						+ "\r\n"
						+ "Desde el punto de vista del mantenimiento, el esfuerzo se puede interpretar como\r\n"
						+ "una medida del trabajo requerido para comprender un software ya desarrollado.Esfuerzo E = D * V ó V / L\r\n"
						+ "\r\n"
						+ "Donde el volumen V es multiplicado por la medida de dificultad D con la que se hizo el programa. \r\n"
						+ "\r\n"
						+ "Atendiendo a varios estudios empíricos, el esfuerzo, E, es incluso una medida mejor de la entendibilidad (comprensión) que N.");
		tooltip(lblFanin,
				"Cantidad de veces que se invoca el metodo evaluado.\nEs recomendable un FanIn lo mas alto posible ya que indica que reutilizacion del codigo");
		tooltip(lblFanout,
				"Cantidad de metodos que utiliza el metodo evaluado.\nEs recomendable un FanOut de 0 en su defecto uno muy bajo");
		tooltip(lblN, "longitud N = N1 + N2\r\n" + "\r\n"
				+ "N es una simple medida del tamaño de un programa. Cuanto más grande sea el tamaño de N, mayor será la dificultad para comprender el programa y mayor el esfuerzo para mantenerlo. \r\n"
				+ "\r\n"
				+ "N es una medida alternativa al simple conteo de líneas de código. Aunque es casi igual de fácil de calcular, N es más sensible a la complejidad que el contar el número de líneas porque N no asume que todas las instrucciones son igual de fácil o de difícil de entender.");
		tooltip(lblN_1, "número total de ocurrencias de operadores");
		tooltip(lblN_3, " número de operadores únicos que aparecen en un programa");
		tooltip(lblN_5, "número de operandos únicos que aparecen en un programa");
		tooltip(lblN_4, "número total de ocurrencias de operandos");
		tooltip(lblN_2, "Tamaño del Vocabulario del programa: n = n1 + n2");
		tooltip(lblNewLabel, "Complejidad Cliclomatica del programa");
		tooltip(lblNewLabel_1,
				"Operandos: Pueden ser los identificadores que no sean palabras reservadas, las constantes numéricas, los identificadores de tipos (bool, string, char, int, long, etc), los caracteres y strings constantes.\r\n"
						+ "Operadores: Que pueden ser todas las palabras reservadas (if, do, while, class, etc), los calificadores (como const, static) las palabras reservadas, y los operadores en expresiones (+, -, <>, ==, !=, <=, >>, etc).");
		tooltip(lblNivel, "Nivel de Programa: L = 1/D");
		tooltip(lblTipo, "Indica el nivel de visibilidad y si es statica o no");
		tooltip(lblTmpEntendimiento,
				"Tiempo de Entendimiento: T = E/18 (18 es el numero que Halstead encontró experimentalmente para expresar esta magnitud en segundos)");
		tooltip(lblVolumen, "VOLUMEN\r\n"
				+ "La medida de longitud, N, es usada en otra estimación de tamaño de Halstead llamada volumen, V,\r\n"
				+ "\r\n"
				+ "Mientras que la longitud es una simple cuenta (o estimación) del total de operadores y operandos, el volumen da un peso extra al número de operadores y operandos únicos. \r\n"
				+ "\r\n"
				+ "Esta medida de volumen se puede interpretar como el número de \"comparaciones mentales\" necesarias para escribir un programa de longitud N. Esta interpretación sugiere que la mente humana sigue un proceso de búsqueda binaria para seleccionar un token de un vocabulario de tamaño n.\r\n"
				+ "\r\n"
				+ "Por ejemplo, si dos programas tienen la misma longitud N pero uno tiene mayor número de operadores y operandos únicos, que naturalmente lo hacen más difícil de entender y mantener, este tendrá un mayor volumen. \r\n"
				+ "\r\n" + "La fórmula es la siguiente:\r\n" + "\r\n" + "volumen V = N x log2 (n)");
	}

	private void tooltip(JLabel l, String text) {
		text = text.replace("\n", "<br>");
		l.setToolTipText("<html><p width=\"340\">" + text + "</p></html>");
	}
}
