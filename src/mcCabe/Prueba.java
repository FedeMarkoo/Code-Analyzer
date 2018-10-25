package mcCabe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Prueba {

	public JFrame frame;
	private Nodo nodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba window = new Prueba(Dibujar.test());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param nodo
	 */
	public Prueba(Nodo nodo) {
		initialize();
		this.nodo = nodo;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				frame.getContentPane().removeAll();
				test();
			}
		});
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 950, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		test();
	}

	private void test() {
		this.frame.setBackground(Color.WHITE);
		nodo(this.frame.getWidth() / 2, 3, nodo, ' ');
	}

	private void nodo(int posCentral, int altura, Nodo n, char o) {
		if (n == null || (n.codigo.equals(" ") && n.siguiente == null))
			return;

		Label panel = new Label(n.nivel);
		panel.setAlignment(Label.CENTER);
		panel.setBounds(posCentral - 15, (altura * 25) - 7, 30, 15);
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel);
		switch (o) {
		case 't':
			panel.setBackground(Color.GREEN);
			panel.setForeground(Color.BLACK);
//			g2.setPaint(Color.GREEN);
//			g2.fill(nodo);
			break;
		case 'f':
			panel.setBackground(Color.RED);
			panel.setForeground(Color.BLACK);
//			g2.setPaint(Color.RED);
//			g2.fill(nodo);
			break;
		}
		if (n.getClass() == NodoCondicion.class) {
			panel.setBackground(Color.YELLOW);
			panel.setForeground(Color.BLACK);
//			g2.setPaint(Color.YELLOW);
//			g2.fill(nodo);
		}
//		g2.setPaint(Color.BLACK);
//		g2.draw(nodo);
//		g2.drawString(n.nivel, posCentral - 7, (altura * 25) + 6);

		if (n.getClass() == Nodo.class) {
			nodo(posCentral, altura + 1, n.siguiente, 's');
		} else {
			NodoCondicion n2 = (NodoCondicion) n;
			nodo(posCentral - 40, altura + 1, n2.verdadero, 't');
			nodo(posCentral + 40, altura + 1, n2.falso, 'f');
			int h = n.hijos;
			if (n.siguiente != null && n.siguiente.siguiente != null) {
				h -= n.siguiente.hijos;
			}
			nodo(posCentral, altura + 1 + h, n2.siguiente, 's');
		}
	}
}
