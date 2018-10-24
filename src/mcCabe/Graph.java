package mcCabe;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	public void paintComponent(Graphics g) {

		// vertical line
		g.setColor(Color.red);
		g.drawLine(20, 20, 20, 120);

		// horizontal line
		g.setColor(Color.green);
		g.drawLine(20, 20, 120, 20);

		// diagonal line
		g.setColor(Color.blue);
		g.drawLine(20, 20, 120, 120);

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