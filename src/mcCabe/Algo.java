package mcCabe;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Algo extends JApplet {
	public Algo() {
	}

	final static int maxCharHeight = 15;
	final static int minFontSize = 6;

	final static Color bg = Color.white;
	final static Color fg = Color.black;
	final static Color red = Color.red;
	final static Color white = Color.white;

	final static BasicStroke stroke = new BasicStroke(2.0f);
	final static BasicStroke wideStroke = new BasicStroke(8.0f);

	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
			0.0f);
	Dimension totalSize;
	FontMetrics fontMetrics;

	public void init() {
		// Initialize drawing colors
		setBackground(bg);
		setForeground(fg);
	}

	FontMetrics pickFont(Graphics2D g2, String longString, int xSpace) {
		boolean fontFits = false;
		Font font = g2.getFont();
		FontMetrics fontMetrics = g2.getFontMetrics();
		int size = font.getSize();
		String name = font.getName();
		int style = font.getStyle();

		while (!fontFits) {
			if ((fontMetrics.getHeight() <= maxCharHeight) && (fontMetrics.stringWidth(longString) <= xSpace)) {
				fontFits = true;
			} else {
				if (size <= minFontSize) {
					fontFits = true;
				} else {
					g2.setFont(font = new Font(name, style, --size));
					fontMetrics = g2.getFontMetrics();
				}
			}
		}

		return fontMetrics;
	}

	public void paint(Graphics g) {
		if (!getSize().equals(new Dimension(700, 700))) {
			// setSize(700, 700);
			// return;
		}
		g.clearRect(0, 0, 9999, 9999);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = getSize();
		int anchoMedio = d.width / 2;
		nodo(g2, anchoMedio, 1, Dibujar.test(), ' ');
	}

	private void nodo(Graphics2D g2, int posCentral, int altura, Nodo n, char o) {
		if (n == null || (n.codigo.equals(" ") && n.siguiente == null))
			return;

		g2.setPaint(Color.BLACK);
		RoundRectangle2D.Double nodo = new RoundRectangle2D.Double(posCentral - 15, (altura * 25) - 7, 30, 15, 10, 10);
		switch (o) {
		case 't':
			g2.setPaint(Color.GREEN);
			g2.fill(nodo);
			break;
		case 'f':
			g2.setPaint(Color.RED);
			g2.fill(nodo);
			break;
		}
		if (n.getClass() == NodoCondicion.class) {
			g2.setPaint(Color.YELLOW);
			g2.fill(nodo);
		}
		g2.setPaint(Color.BLACK);
		g2.draw(nodo);
		g2.drawString(n.nivel, posCentral - 7, (altura * 25) + 6);

		if (n.getClass() == Nodo.class) {
			nodo(g2, posCentral, altura + 1, n.siguiente, 's');
		} else {
			NodoCondicion n2 = (NodoCondicion) n;
			nodo(g2, posCentral - 40, altura + 1, n2.verdadero, 't');
			nodo(g2, posCentral + 40, altura + 1, n2.falso, 'f');
			int h = n.hijos;
			if (n.siguiente != null && n.siguiente.siguiente != null) {
				h -= n.siguiente.hijos;
			}
			nodo(g2, posCentral, altura + 1 + h, n2.siguiente, 's');
		}
	}

	public static void main(String s[]) {
		JFrame f = new JFrame("ShapesDemo2D");
		f.setSize(700, 700);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JApplet applet = new Algo();
		f.getContentPane().add("Center", applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(1000, 1000));
		f.setVisible(true);
	}

}