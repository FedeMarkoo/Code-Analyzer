package mcCabe;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Algo extends JPanel {
	String cod;
	private Graphics2D control;

	public Algo() {

		add(btnNewButton);
		cod = Dibujar.test();
	}

	public Algo(String codigoCompleto) {
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveComponentAsJPEG(a, "McCabe.jpeg");
			}
		});

		cod = codigoCompleto;
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
	private final static JButton btnNewButton = new JButton("Guardar Imagen");
	private Algo a;
	private BufferedImage bufferedImage;

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
		Graphics2D g2;
		bufferedImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
		control = bufferedImage.createGraphics();
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = getSize();
		int anchoMedio = d.width / 2;
		nodo(g2, anchoMedio, 1, Dibujar.resolver(cod), ' ', null);
	}

	public void saveComponentAsJPEG(Component myComponent, String filename) {
		try {
			BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle(this.getSize()));

			// Save as JPEG
			File file = new File(filename);
			ImageIO.write(screencapture, "jpg", file);

			// Save as PNG
			// File file = new File("screencapture.png");
			// ImageIO.write(screencapture, "png", file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void nodo(Graphics2D g2, int posCentral, int altura, Nodo n, char o, Point a) {
		if (n == null || (n.codigo.equals(" ") && n.siguiente == null))
			return;

		g2.setPaint(Color.BLACK);
		RoundRectangle2D.Double nodo = new RoundRectangle2D.Double(posCentral - 15, (altura * 25) - 7, 30, 15, 10, 10);

		if (a != null && isPainted(g2, a.x, a.y, posCentral, (altura * 25) - 7)) {
			g2.drawLine(a.x, a.y, posCentral, (altura * 25) - 7);
			control.drawLine(a.x, a.y, posCentral, (altura * 25) - 7);
		}

		switch (o) {
		case 't':
			g2.setPaint(Color.GREEN);
			g2.fill(nodo);
			control.fill(nodo);
			break;
		case 'f':
			g2.setPaint(Color.RED);
			g2.fill(nodo);
			control.fill(nodo);
			break;
		}
		if (n.getClass() == NodoCondicion.class) {
			g2.setPaint(Color.YELLOW);
			g2.fill(nodo);
			control.fill(nodo);
		}
		g2.setPaint(Color.BLACK);
		g2.draw(nodo);
		control.draw(nodo);
		g2.drawString(n.nivel, posCentral - 7, (altura * 25) + 6);
		control.drawString(n.nivel, posCentral - 7, (altura * 25) + 6);

		if (n.getClass() == Nodo.class) {
			nodo(g2, posCentral, altura + 1, n.siguiente, 's', new Point(posCentral, (altura * 25) + 8));
		} else {
			NodoCondicion n2 = (NodoCondicion) n;
			nodo(g2, posCentral - 40, altura + 1, n2.verdadero, 't', new Point(posCentral, (altura * 25) + 8));
			nodo(g2, posCentral + 40, altura + 1, n2.falso, 'f', new Point(posCentral, (altura * 25) + 8));
			int h = n.hijos;
			if (n.siguiente != null && n.siguiente.siguiente != null) {
				h -= n.siguiente.hijos;
			}
			nodo(g2, posCentral, altura + 1 + h, n2.siguiente, 's', new Point(posCentral, (altura * 25) + 8));
		}

	}

	private boolean isPainted(Graphics2D g2, int x, int y, int xfin, int yfin) {
		return true;
	}

	public static void main(String s[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame f = new JFrame("ShapesDemo2D");
		f.setSize(700, 700);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Algo applet = new Algo();
		f.getContentPane().add("Center", applet);
		f.pack();
		f.setSize(new Dimension(1000, 1000));
		f.setVisible(true);

	}

	public void resolver(String codigoCompleto) {
		JFrame f = new JFrame("ShapesDemo2D");
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(700, 700);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Algo applet = new Algo(codigoCompleto);
		f.getContentPane().add("Center", applet);
		f.pack();
		f.setSize(new Dimension(1000, 1000));
		f.setVisible(true);

		a = this;
	}

}