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
	private boolean pausa;
	
	public PantallaDeJuego(String color) {
		super("Juego");
		setSize(750,660);
		this.color = color;
		pausa =false;
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
		
		JPanel a = new JPanel(); 
		a.setOpaque(true);
		a.setBackground(Color.BLUE);
		
		pausaBoton = new Boton(new ImageIcon(getClass().getResource("/imagenes/Pausa.png")));
		pausaBoton.setTransparent();
		a.add(pausaBoton);
		
		reiniciar = new Boton(new ImageIcon(getClass().getResource("/imagenes/Reiniciar.png")));
		reiniciar.setTransparent();
		a.add(reiniciar);
		
		container.add(a, BorderLayout.SOUTH); //PUNTAJE
	}
	
	public void prepareAcciones() {
		
		ActionListener pausar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (pausa) {
					pausaBoton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Pausa.png")));
				}
				else {
					pausaBoton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Continuar.png")));
				}
				pausar();
			}
		};
		pausaBoton.addActionListener(pausar);
		
		ActionListener e = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		};
		reiniciar.addActionListener(e);
		
	}
	
	public void pausar() {
		if (pausa) pausa = false;
		else pausa = true;
		pint.pausar();
	}
	
	public void reiniciar() {
		System.out.println("aaa");
		pint = new Pintor(747,580,color);
	}
	
}