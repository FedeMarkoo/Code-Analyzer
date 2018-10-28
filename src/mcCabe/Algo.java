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
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);

		add(btnNewButton);

		JLabel lblNewLabel = new JLabel("                                                     ");
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel);

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
		n = Dibujar.resolver(cod);
		texto = recorrerTexto(n);
	}

	private String recorrerTexto(Nodo n) {
		if (n == null)
			return "";
		String ret = n.nivel + "_&#09;" + n.codigo.trim().replaceAll("\n\\s*", "\n&#09;") + "\n\n";
		if (n.getClass() == Nodo.class)
			return ret + recorrerTexto(n.siguiente);

		NodoCondicion n2 = (NodoCondicion) n;
		return ret + recorrerTexto(n2.verdadero) + recorrerTexto(n2.falso) + recorrerTexto(n2.siguiente);
	}

	private final static JButton btnNewButton = new JButton("Guardar Imagen");
	private Algo a;
	private BufferedImage bufferedImage;
	public String texto;
	private Nodo n;

	public void paint(Graphics g) {

		setBackground(Color.WHITE);
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
		nodo(g2, anchoMedio, 1, n, ' ', null);

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

	private Point nodo(Graphics2D g2, int posCentral, int altura, Nodo n, char o, Point a) {
		if (n == null || (n.codigo.equals(" ") && n.siguiente == null))
			return new Point(0, 0);

		g2.setPaint(Color.BLACK);
		RoundRectangle2D.Double nodo = new RoundRectangle2D.Double(posCentral - 15, (altura * 25) - 7, 30, 15, 10, 10);

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
			Point p = new Point(posCentral, (altura * 25) + 8);
			Point p2 = nodo(g2, posCentral, altura + 1, n.siguiente, 's', p);
			if (n.siguiente != null) {
				g2.drawLine(posCentral, (altura * 25) + 8, posCentral, (altura * 25) + 18);
				control.drawLine(posCentral, (altura * 25) + 8, posCentral, (altura * 25) + 18);
			}
//			return p;
			return new Point(p.x, (int) Math.max(p2.getY(), p.getY()));

		} else {
			Point pVerda, pFalso, pMayor, pSig;
			NodoCondicion n2 = (NodoCondicion) n;
//Por Verdadero
			pVerda = nodo(g2, posCentral - 40 * (n2.cantV == 0 ? 1 : n2.cantV), altura + 1, n2.verdadero, 't',
					new Point(posCentral, (altura * 25) + 8));
			if (pVerda.getY() != 0) {
				g2.drawLine(posCentral, (altura * 25) + 8, posCentral - 40 * (n2.cantV == 0 ? 1 : n2.cantV),
						(altura * 25) + 18);
			}
//Por Falso
			pFalso = nodo(g2, posCentral + 40 * (n2.cantF == 0 ? 1 : n2.cantF), altura + 1, n2.falso, 'f',
					new Point(posCentral, (altura * 25) + 8));
			if (pFalso.getY() != 0) {
				g2.drawLine(posCentral, (altura * 25) + 8, posCentral + 40 * (n2.cantF == 0 ? 1 : n2.cantF),
						(altura * 25) + 18);
			}

			pMayor = new Point(posCentral, (int) Math.max(pFalso.getY(), pVerda.getY()));

			pSig = nodo(g2, posCentral, (int) ((pMayor.getY() / 25) + 1), n2.siguiente, 's',
					new Point(posCentral, (altura * 25) + 8));
			// if (n2.siguiente != null) {
			if (pVerda.getY() > 0 && pSig.getY() > 0)
				g2.drawLine(posCentral, (int) (pMayor.getY() + 10), (int) pVerda.getX(), (int) pVerda.getY());
			if (pFalso.getY() > 0 && pSig.getY() > 0)
				g2.drawLine(posCentral, (int) (pMayor.getY() + 10), (int) pFalso.getX(), (int) pFalso.getY());
			// g2.drawLine(posCentral, (altura * 25) + 8, posCentral, (altura * 25) + 18);
			// }
//			return  new Point(posCentral, (int) Math.max(pFalso.getY(), pVerdadero.getY()));
			return new Point(posCentral, (int) Math.max(pMayor.getY(), pSig.getY()));
		}
	}

	public JPanel resolver(String codigoCompleto) {
		Algo applet = new Algo(codigoCompleto);
		a = this;
		return applet;
	}

}