package FrontEnd;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import BackEnd.Analizador;
import BackEnd.Archivo;
import BackEnd.Clase;
import BackEnd.Metodo;
import BackEnd.Packag;
import BackEnd.Proyecto;
import BackEnd.sourceP;
import mcCabe.Texto;

public class Interfaz {

	private JFrame frame;
	private JTextField textField;
	private String codigoG = " ";
	private String nombreMcCabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				javax.swing.ToolTipManager.sharedInstance().setDismissDelay(90000000);
				javax.swing.ToolTipManager.sharedInstance().setInitialDelay(100);
				javax.swing.ToolTipManager.sharedInstance().setReshowDelay(10);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					System.out.println("Error setting native LAF: " + e);
				}
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

		JButton buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.insets = new Insets(0, 0, 0, 5);
		gbc_buscar.gridx = 1;
		gbc_buscar.gridy = 0;
		panel_1.add(buscar, gbc_buscar);

		JButton analizar = new JButton("Analizar");
		GridBagConstraints gbc_analizar = new GridBagConstraints();
		gbc_analizar.gridx = 2;
		gbc_analizar.gridy = 0;
		panel_1.add(analizar, gbc_analizar);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 473, 0, 0 };
		gbl_panel.rowHeights = new int[] { 98, 237, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 238, 239, 259, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0, 16, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JTextPane recomendacion = new JTextPane();
		recomendacion.setEditable(false);
		GridBagConstraints gbc_recomendacion = new GridBagConstraints();
		gbc_recomendacion.insets = new Insets(0, 0, 0, 5);
		gbc_recomendacion.fill = GridBagConstraints.BOTH;
		gbc_recomendacion.gridx = 0;
		gbc_recomendacion.gridy = 2;
		panel.add(recomendacion, gbc_recomendacion);

		JLabel lblProyecto = new JLabel("Proyecto");
		GridBagConstraints gbc_lblProyecto = new GridBagConstraints();
		gbc_lblProyecto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProyecto.gridx = 0;
		gbc_lblProyecto.gridy = 0;
		panel_3.add(lblProyecto, gbc_lblProyecto);

		JLabel lblSourcePackage = new JLabel("Source Package");
		GridBagConstraints gbc_lblSourcePackage = new GridBagConstraints();
		gbc_lblSourcePackage.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourcePackage.gridx = 1;
		gbc_lblSourcePackage.gridy = 0;
		panel_3.add(lblSourcePackage, gbc_lblSourcePackage);

		JLabel lblPackage = new JLabel("Package");
		GridBagConstraints gbc_lblPackage = new GridBagConstraints();
		gbc_lblPackage.insets = new Insets(0, 0, 5, 0);
		gbc_lblPackage.gridx = 2;
		gbc_lblPackage.gridy = 0;
		panel_3.add(lblPackage, gbc_lblPackage);

		JComboBox<Proyecto> proyecto = new JComboBox<Proyecto>();
		GridBagConstraints gbc_proyecto = new GridBagConstraints();
		gbc_proyecto.insets = new Insets(0, 0, 5, 5);
		gbc_proyecto.fill = GridBagConstraints.HORIZONTAL;
		gbc_proyecto.gridx = 0;
		gbc_proyecto.gridy = 1;
		panel_3.add(proyecto, gbc_proyecto);

		JComboBox<sourceP> sourcepackage = new JComboBox<sourceP>();
		GridBagConstraints gbc_sourcepackage = new GridBagConstraints();
		gbc_sourcepackage.insets = new Insets(0, 0, 5, 5);
		gbc_sourcepackage.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourcepackage.gridx = 1;
		gbc_sourcepackage.gridy = 1;
		panel_3.add(sourcepackage, gbc_sourcepackage);

		JComboBox<Packag> paquete = new JComboBox<Packag>();
		GridBagConstraints gbc_paquete = new GridBagConstraints();
		gbc_paquete.insets = new Insets(0, 0, 5, 0);
		gbc_paquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_paquete.gridx = 2;
		gbc_paquete.gridy = 1;
		panel_3.add(paquete, gbc_paquete);

		JLabel lblArchivo = new JLabel("Archivo");
		GridBagConstraints gbc_lblArchivo = new GridBagConstraints();
		gbc_lblArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArchivo.gridx = 0;
		gbc_lblArchivo.gridy = 2;
		panel_3.add(lblArchivo, gbc_lblArchivo);

		JLabel lblClase = new JLabel("Clase");
		GridBagConstraints gbc_lblClase = new GridBagConstraints();
		gbc_lblClase.insets = new Insets(0, 0, 5, 5);
		gbc_lblClase.gridx = 1;
		gbc_lblClase.gridy = 2;
		panel_3.add(lblClase, gbc_lblClase);

		JLabel lblMetodo = new JLabel("Metodo");
		GridBagConstraints gbc_lblMetodo = new GridBagConstraints();
		gbc_lblMetodo.insets = new Insets(0, 0, 5, 0);
		gbc_lblMetodo.gridx = 2;
		gbc_lblMetodo.gridy = 2;
		panel_3.add(lblMetodo, gbc_lblMetodo);

		JComboBox<Archivo> archivos = new JComboBox<Archivo>();
		GridBagConstraints gbc_archivos = new GridBagConstraints();
		gbc_archivos.fill = GridBagConstraints.HORIZONTAL;
		gbc_archivos.insets = new Insets(0, 0, 0, 5);
		gbc_archivos.gridx = 0;
		gbc_archivos.gridy = 3;
		panel_3.add(archivos, gbc_archivos);

		JComboBox<Clase> clases = new JComboBox<>();
		GridBagConstraints gbc_clases = new GridBagConstraints();
		gbc_clases.fill = GridBagConstraints.HORIZONTAL;
		gbc_clases.insets = new Insets(0, 0, 0, 5);
		gbc_clases.gridx = 1;
		gbc_clases.gridy = 3;
		panel_3.add(clases, gbc_clases);

		JComboBox<Metodo> metodos = new JComboBox<>();
		GridBagConstraints gbc_metodos = new GridBagConstraints();
		gbc_metodos.fill = GridBagConstraints.HORIZONTAL;
		gbc_metodos.gridx = 2;
		gbc_metodos.gridy = 3;
		panel_3.add(metodos, gbc_metodos);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);

		JTextPane lineasCod = new JTextPane();
		lineasCod.setEditable(false);
		lineasCod.setFont(new Font("Tahoma", Font.BOLD, 10));
		lineasCod.setBounds(106, 61, 51, 19);
		panel_2.add(lineasCod);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 61, 86, 19);
		panel_2.add(lblCodigo);

		JTextPane fanOutC = new JTextPane();
		fanOutC.setEditable(false);
		fanOutC.setToolTipText("Numero de llamadas del metodo dentro de la clase propia del metodo");
		fanOutC.setFont(new Font("Tahoma", Font.BOLD, 10));
		fanOutC.setBounds(106, 111, 51, 19);
		panel_2.add(fanOutC);

		JLabel lblFanin = new JLabel("Fan Out");
		lblFanin.setBounds(10, 111, 86, 19);
		panel_2.add(lblFanin);

		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(10, 86, 86, 19);
		panel_2.add(lblComentarios);

		JTextPane lineasComentarios = new JTextPane();
		lineasComentarios.setEditable(false);
		lineasComentarios.setFont(new Font("Tahoma", Font.BOLD, 10));
		lineasComentarios.setBounds(106, 86, 40, 19);
		panel_2.add(lineasComentarios);

		JLabel lblFanout = new JLabel("Fan In");
		lblFanout.setBounds(10, 136, 86, 19);
		panel_2.add(lblFanout);

		JTextPane fanIn = new JTextPane();
		fanIn.setEditable(false);
		fanIn.setFont(new Font("Tahoma", Font.BOLD, 10));
		fanIn.setBounds(106, 136, 51, 19);
		panel_2.add(fanIn);

		JLabel lblN = new JLabel("Longitud");
		lblN.setBounds(249, 36, 51, 19);
		panel_2.add(lblN);

		JTextPane halsN = new JTextPane();
		halsN.setEditable(false);
		halsN.setFont(new Font("Tahoma", Font.BOLD, 10));
		halsN.setBounds(299, 36, 51, 19);
		panel_2.add(halsN);

		JLabel lblN_1 = new JLabel("N1");
		lblN_1.setBounds(274, 61, 15, 19);
		panel_2.add(lblN_1);

		JTextPane halsN1 = new JTextPane();
		halsN1.setEditable(false);
		halsN1.setFont(new Font("Tahoma", Font.BOLD, 10));
		halsN1.setBounds(299, 61, 51, 19);
		panel_2.add(halsN1);

		JTextPane fanOutT = new JTextPane();
		fanOutT.setEditable(false);
		fanOutT.setToolTipText("Numero de llamadas de totales del metodo en todos los archivos analizados");
		fanOutT.setFont(new Font("Tahoma", Font.BOLD, 10));
		fanOutT.setBounds(167, 111, 51, 19);
		panel_2.add(fanOutT);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 11, 86, 19);
		panel_2.add(lblTipo);

		JTextPane tipo = new JTextPane();
		tipo.setEditable(false);
		tipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		tipo.setBounds(106, 11, 112, 19);
		panel_2.add(tipo);

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

		JTextPane halsN2 = new JTextPane();
		halsN2.setEditable(false);
		halsN2.setFont(new Font("Tahoma", Font.BOLD, 10));
		halsN2.setBounds(299, 86, 51, 19);
		panel_2.add(halsN2);

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

		JLabel lblesfuerzo = new JLabel("Esfuerzo");
		lblesfuerzo.setBounds(274, 186, 76, 19);
		panel_2.add(lblesfuerzo);

		JTextPane textPane_17 = new JTextPane();
		textPane_17.setEditable(false);
		textPane_17.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_17.setBounds(356, 211, 86, 19);
		panel_2.add(textPane_17);
		JLabel lblTmpEntendimiento = new JLabel("Tmp Entendimiento");
		lblTmpEntendimiento.setBounds(274, 211, 76, 19);
		panel_2.add(lblTmpEntendimiento);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		JScrollPane j = new JScrollPane();
		panel.add(j, gbc_textArea);
		JTextPane cc = new JTextPane();
		cc.setEditable(false);
		cc.setFont(new Font("Tahoma", Font.BOLD, 10));
		cc.setBounds(106, 36, 51, 19);
		panel_2.add(cc);

		JLabel lblNewLabel = new JLabel("CC");
		lblNewLabel.setBounds(10, 36, 86, 19);
		panel_2.add(lblNewLabel);
		tooltip(lblNewLabel, "Complejidad Cliclomatica del programa");

		JTextPane textPane_18 = new JTextPane();
		textPane_18.setEditable(false);
		textPane_18.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane_18.setBounds(156, 86, 62, 19);
		panel_2.add(textPane_18);

		JEditorPane textArea = new JEditorPane();
		textArea.setContentType("text/html");
		textArea.setEditable(false);
		j.setViewportView(textArea);

		JButton graficarBtn = new JButton("Graficar");

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.isControlDown() && arg0.getKeyCode() == 86) {
					new Thread() {
						public void run() {
							try {
								textField.setText(((String) Toolkit.getDefaultToolkit().getSystemClipboard()
										.getData(DataFlavor.stringFlavor)).replace("\"", ""));
								analizar(proyecto);
							} catch (Exception e) {
							}
						}
					}.start();
				}
			}
		});

		analizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread() {
					public void run() {
						analizar(proyecto);
					}
				}.start();
			}
		});

		proyecto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sourcepackage.removeAllItems();
				if (proyecto.getComponentCount() == 0 || proyecto.getSelectedItem() == null)
					return;
				for (sourceP sp : ((Proyecto) proyecto.getSelectedItem()).get()) {
					sourcepackage.addItem(sp);
				}
			}
		});

		sourcepackage.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				paquete.removeAllItems();
				if (sourcepackage.getComponentCount() == 0 || sourcepackage.getSelectedItem() == null)
					return;
				for (Packag pk : ((sourceP) sourcepackage.getSelectedItem()).get()) {
					paquete.addItem(pk);
				}
			}
		});
		paquete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				archivos.removeAllItems();
				if (paquete.getComponentCount() == 0 || paquete.getSelectedItem() == null)
					return;
				for (Archivo a : ((Packag) paquete.getSelectedItem()).get()) {
					archivos.addItem(a);
				}
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
				cc.setForeground(Color.BLACK);
				fanIn.setForeground(Color.BLACK);
				textPane_14.setForeground(Color.BLACK);

				if (metodos.getComponentCount() == 0 || metodos.getSelectedItem() == null) {
					tipo.setText("");
					cc.setText("");
					lineasCod.setText("");
					lineasComentarios.setText("");
					fanOutC.setText("");
					fanOutT.setText("");
					fanIn.setText("");
					halsN.setText("");
					textPane_11.setText("");
					halsN1.setText("");
					halsN2.setText("");
					textPane_10.setText("");
					textPane_12.setText("");
					textPane_14.setText("");
					textPane_13.setText("");
					textPane_15.setText("");
					textPane_16.setText("");
					textPane_17.setText("");
					textPane_18.setText("");
					textArea.setText("");
					recomendacion.setText("");

					cc.setBackground(Color.WHITE);
					fanOutC.setBackground(Color.WHITE);
					fanOutT.setBackground(Color.WHITE);
					fanIn.setBackground(Color.WHITE);
					textPane_14.setBackground(Color.WHITE);
					return;
				}
				Metodo metodo = (Metodo) metodos.getSelectedItem();
				if (metodo.cc < 6)
					cc.setBackground(Color.GREEN);
				else if (metodo.cc < 9)
					cc.setBackground(Color.YELLOW);
				else {
					cc.setForeground(Color.WHITE);
					cc.setBackground(Color.RED);
				}
				int fanOut_C = metodo.fanIn[0];
				if (fanOut_C < 3)
					fanOutC.setBackground(Color.YELLOW);
				else
					fanOutC.setBackground(Color.GREEN);
				int fanOut_T = metodo.fanIn[1];
				if (fanOut_T < 3)
					fanOutT.setBackground(Color.YELLOW);
				else
					fanOutT.setBackground(Color.GREEN);

				if (metodo.fanOut < 5)
					fanIn.setBackground(Color.GREEN);
				else if (metodo.fanOut > 12) {
					fanIn.setForeground(Color.WHITE);
					fanIn.setBackground(Color.RED);
				} else
					fanIn.setBackground(Color.YELLOW);

				double v = metodo.halstead.V();
				if (v < 200)
					textPane_14.setBackground(Color.GREEN);
				else if (v < 450)
					textPane_14.setBackground(Color.YELLOW);
				else {
					textPane_14.setForeground(Color.WHITE);
					textPane_14.setBackground(Color.RED);
				}
				cc.setText("" + metodo.cc);
				lineasCod.setText(metodo.lineasCodigo + "");
				fanOutC.setText(fanOut_C + "");
				lineasComentarios.setText(metodo.lineasComentadas + "");
				fanIn.setText(metodo.fanOut + "");
				halsN.setText(metodo.halstead.N() + "");
				halsN1.setText(String.format("%,.0f", metodo.halstead.N1));
				halsN2.setText(String.format("%,.0f", metodo.halstead.N2));
				fanOutT.setText((metodo.tipo.contains("Private") ? "-" : fanOut_T) + "");
				tipo.setText(metodo.tipo);
				textPane_10.setText(String.format("%,.0f", metodo.halstead.n1));
				textPane_11.setText(metodo.halstead.n() + "");
				textPane_12.setText(String.format("%,.0f", metodo.halstead.n2));
				textPane_13.setText(String.format("%,.03f", metodo.halstead.D()));
				textPane_14.setText(String.format("%,.03f", v));
				textPane_15.setText(String.format("%,.03f", metodo.halstead.L()));
				textPane_16.setText(String.format("%,.03f", metodo.halstead.E()));
				textPane_17.setText(String.format("%,.03f", metodo.halstead.T()));
				setN(lblN_1, lblN_4, metodo);
				double porcentaje = 100 * (double) metodo.lineasComentadas;
				int lineasTotal = metodo.lineasCodigo + metodo.lineasComentadas;
				porcentaje /= lineasTotal;
				textPane_18.setText(String.format("%.03f", porcentaje) + "%");
				setCodigo(textArea, metodo);
				codigoG = metodo.codigoCompleto;
				nombreMcCabe = ((Archivo) archivos.getSelectedItem()) + " - " + ((Clase) clases.getSelectedItem())
						+ " - " + metodo;
				recomendacion.setText(metodo.recomendacion);

			}

			private void setCodigo(JEditorPane textArea, Metodo metodo) {
				Pattern p = Pattern.compile("" + "(\\W)(else\\s+if|if|while|do|else|switch|for|case|try"
						+ "|\\|\\||\\&amp;\\&amp;"
						+ "|(?!r)(?!e)(?!t)(?!u)(?!r)(?!n)(?! )[\\w\\<\\>\\+\\-\\=\\*\\\\\\&\\|\\(\\) ]+\\?[^\\?:;]+:[^:;\\?]+;)(\\W)");
				String codigo = p
						.matcher(metodo.codigoCompleto.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;"))
						.replaceAll("$1<span style=\"color:#FF0000\";>$2</span>$3");
				codigo = codigo.replace("\t", "&#32&#32&#32&#32 ");
				codigo = codigo.replaceAll("(?:\\/\\/[^\"].+)(?<!\n)", "<span style=\"color:#03AF06\";>$0</span>")
						.replace("\n", "<br>");
				codigo = codigo.replaceAll("\\/\\*.*\\*\\/", "<span style=\"color:#AFCFE9\";>$0</span>");
				// .replaceAll("/|/, replacement); prueba
				/**
				 * LA IDEA ERA QUE RESALTE LOS OPERADORES Y LOS OPERANDOS... PERO FALLA FEO
				 * for(String a : metodo.halstead.goperandos()) { codigo=codigo.replace(a,
				 * "<span style=\"color:#00FF00\";>"+a+"</span>"); } for (String a :
				 * metodo.halstead.goperadores()) { codigo=codigo.replace(a, "<span
				 * style=\"color:0000FF\";>"+a+"</span>"); }
				 */
				textArea.setText(codigo);

			}
		});

		graficarBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					String codigoCompleto = codigoG;
					codigoCompleto = codigoCompleto.substring(codigoCompleto.indexOf("{") + 1,
							codigoCompleto.lastIndexOf("}"));
					new Texto(codigoCompleto, "Code Analizer - Grafico de McCabe: " + nombreMcCabe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				try {
					textField.setText(f.getSelectedFile().getAbsolutePath());
					new Thread() {
						public void run() {
							analizar(proyecto);
						}
					}.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		tooltip(lblCodigo, "Lineas de codigo escritas");
		tooltip(lblComentarios, "Lineas de comentarios escritas");
		tooltip(lblDificultad,
				"DIFICULTAD\r\n" + "Para definir la dificultad D del programa, se usa la fórmula siguiente:\r\n"
						+ "\r\n" + "dificultad D = (n1 * N2) / (n2 *2)\r\n" + "");
		tooltip(lblesfuerzo,
				"El esfuerzo es otra medida estudiada por Halstead que ofrece una medida del trabajo requerido para desarrollar un programa. \r\n"
						+ "\r\n"
						+ "Desde el punto de vista del mantenimiento, el esfuerzo se puede interpretar como\r\n"
						+ "una medida del trabajo requerido para comprender un software ya desarrollado.Esfuerzo E = D * V ó V / L\r\n"
						+ "\r\n"
						+ "Donde el volumen V es multiplicado por la medida de dificultad D con la que se hizo el programa. \r\n"
						+ "\r\n"
						+ "Atendiendo a varios estudios empíricos, el esfuerzo, E, es incluso una medida mejor de la entendibilidad (comprensión) que N.");
		tooltip(lblFanin,
				"Cantidad de veces que se invoca el metodo evaluado.\nEs recomendable un Fan Out lo mas alto posible ya que indica que reutilizacion del codigo");
		tooltip(lblFanout,
				"Cantidad de metodos que utiliza el metodo evaluado.\nEs recomendable un Fan In de 0 en su defecto uno muy bajo");
		tooltip(lblN_3, " número de operadores únicos que aparecen en un programa");
		tooltip(lblN_5, "número de operandos únicos que aparecen en un programa");
		tooltip(lblN, "longitud N = N1 + N2\r\n" + "\r\n"
				+ "N es una simple medida del tamaño de un programa. Cuanto más grande sea el tamaño de N, mayor será la dificultad para comprender el programa y mayor el esfuerzo para mantenerlo. \r\n"
				+ "\r\n"
				+ "N es una medida alternativa al simple conteo de líneas de código. Aunque es casi igual de fácil de calcular, N es más sensible a la complejidad que el contar el número de líneas porque N no asume que todas las instrucciones son igual de fácil o de difícil de entender.");
		tooltip(lblN_2, "Tamaño del Vocabulario del programa: n = n1 + n2");
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

		graficarBtn.setBounds(5, 198, 91, 23);
		panel_2.add(graficarBtn);

	}

	private void setN(JLabel lblN_1, JLabel lblN_4, Metodo metodo) {
		tooltip(lblN_1, "número total de ocurrencias de operadores\n" + metodo.halstead.operadores());
		tooltip(lblN_4, "número total de ocurrencias de operandos\n" + metodo.halstead.operandos());
	}

	private void tooltip(JLabel l, String text) {
		text = text.replace("\n", "<br>");
		l.setToolTipText("<html><p width=\"340\">" + text + "</p></html>");
	}

	@SuppressWarnings("deprecation")
	public void analizar(JComboBox<Proyecto> proyecto) {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(250);
						frame.setTitle("Code Analyzer   Analizando");
						sleep(250);
						frame.setTitle("Code Analyzer   Analizando.");
						sleep(250);
						frame.setTitle("Code Analyzer   Analizando..");
						sleep(250);
						frame.setTitle("Code Analyzer   Analizando...");
					} catch (Exception e) {
					}
				}
			}
		};
		thread.start();

		Analizador a = new Analizador(textField.getText());
		proyecto.removeAllItems();
		thread.stop();
		frame.setTitle("Code Analyzer");
		for (Proyecto p : a.proyectos.get()) {
			proyecto.addItem(p);
		}
		
	}
}
