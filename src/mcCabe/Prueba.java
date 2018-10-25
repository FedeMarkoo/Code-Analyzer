package mcCabe;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Prueba {

	public JFrame frame;
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
		Algo algo = new Algo(" ");
		algo.setVisible(true);
		frame.add(algo);
		frame.setVisible(true);
	}
}
