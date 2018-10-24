package mcCabe;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	public void paintComponent(Graphics g) {
		Nodo n = Dibujar.test();
		dibujar(n);
	}

	private void dibujar(Nodo n) {
		/**
		 * Esto deberia ser recursivo... el tema seria que tenes que conocer con quien
		 * se va a conectar y donde va a estar porque en los de condicion se tiene que
		 * unir la salida de Verdadero con la de Falso y ademas si hay muchos nodos
		 * siguiente dentro de un verdadero o un falso hace que quede desnivelado y es
		 * un bardo hacer que se unan
		 * 
		 */
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Draw Line");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.white);
		frame.setSize(200, 200);

		Graph panel = new Graph();

		frame.add(panel);

		frame.setVisible(true);
	}
}