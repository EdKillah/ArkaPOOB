package presentacion;

import aplicacion.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

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
		setSize(750,728);
		this.color = color;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2-290,d.height/2-324);
		setResizable(false);
		prepareElementos();
		prepareAcciones();
	}
	
	public void prepareElementos() {
		pint = new Pintor(color);
		container = getContentPane();
		container.add(pint, BorderLayout.CENTER); //JUEGO
		
	}
	
	public void prepareAcciones() {
	}
}