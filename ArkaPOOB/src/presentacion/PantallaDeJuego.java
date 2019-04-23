package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PantallaDeJuego extends JFrame{
	
	private Pintor pint;
	private Container container;
	private JPanel opciones;
	private int jugadores; 
	private Boton guardar;
	private Boton pausaBoton;
	private Boton reiniciar;
	private String color;
	
	public PantallaDeJuego(String color) {
		super("Juego");
		setSize(750,660);
		this.color = color;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);
		//setLocation(d.width/2,d.height/2);
		setResizable(false);
		prepareElementos();
		prepareAcciones();
	}
	
	public void prepareElementos() {
		pint = new Pintor(747,580,color);
		container = getContentPane();
		container.add(pint, BorderLayout.CENTER); //JUEGO
	}
	
	public void prepareAcciones() {
	}
}