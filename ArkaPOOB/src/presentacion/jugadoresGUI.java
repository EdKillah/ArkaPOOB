package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jugadoresGUI extends JFrame{
	
	private JPanel pantalla;
	private JPanel botones;
	private Container contenedor;
	private JButton Jugador;
	private JButton dosJugadores;
	private JLabel panelLogo;
	private JPanel mazo;
	private JButton jugar1;
	private JButton jugar2;
	private JButton volver1;
	private JButton volver2;
	private JTextField textNombre;
	private JComboBox<String> naveColor;
	private String[] colores = {"Red","Blue","Orange","Green"};
	
	public jugadoresGUI() {
		super("Jugadores");
		setBackground(Color.BLACK);
		setSize(485,210);
		setLocationRelativeTo(null);
		contenedor = getContentPane();
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		pantalla = new JPanel();
		pantalla.setLayout(new GridLayout(2,1));
		contenedor.add(pantalla);
		panelLogo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/jugadores.png")));
		pantalla.add(panelLogo);
		pantalla.setBackground(Color.BLACK);
		botones = new JPanel();
		botones.setBackground(Color.BLACK);
		pantalla.add(botones);
		Jugador = new JButton("1 jugador");
		dosJugadores = new JButton("2 jugadores");
		botones.add(Jugador);
		botones.add(dosJugadores);
		setIconImage(new ImageIcon(getClass().getResource("/imagenes/icon.png")).getImage());
		
		mazo = new JPanel(new CardLayout());
		contenedor.add(mazo);
		mazo.add(pantalla,"Inicio");
		prepareUnJugador();
		prepareDosJugadores();
	}
	
	private void prepareUnJugador() {
		JPanel panelUnJugador = new JPanel(new GridLayout(2,1));
		mazo.add(panelUnJugador,"1Jugador");
		
		JPanel unJugadorLogo = new JPanel(new GridBagLayout());
		panelUnJugador.add(unJugadorLogo);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		unJugadorLogo.add(logo);
		unJugadorLogo.setBackground(Color.BLACK);
		
		JPanel contenido = new JPanel();
		contenido.setLayout(null);
		contenido.setBackground(Color.BLACK);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setForeground(Color.WHITE);
		nombre.setBounds(15, 40, 78, 14);
		contenido.add(nombre);
		
		JLabel nave = new JLabel("Color Nave:");
		nave.setForeground(Color.WHITE);
		nave.setBounds(15, 85, 78, 14);
		contenido.add(nave);
		
		jugar1 = new JButton("Jugar");
		jugar1.setBounds(160, 150, 89, 23);
		contenido.add(jugar1);
		
		volver1 = new JButton("Volver");
		volver1.setBounds(330, 150, 89, 23);
		contenido.add(volver1);
		
		textNombre = new JTextField();
		textNombre.setBounds(110, 40, 78, 14);
		contenido.add(textNombre);
		textNombre.setColumns(10);
		
		naveColor = new JComboBox<>(colores);
		naveColor.setBounds(100, 85, 121, 17);
		contenido.add(naveColor);

		panelUnJugador.add(contenido);
	}
	
	private void prepareDosJugadores() {
		JPanel panelUnJugador = new JPanel(new GridLayout(2,1));
		mazo.add(panelUnJugador,"2Jugador");
	}
	
	private void prepareAcciones() {
		ActionListener juego = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configuracionUnJugador();
			}
		};
		Jugador.addActionListener(juego);
		
		ActionListener juego1 = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configuracionDosJugador();
			}
		};
		dosJugadores.addActionListener(juego1);
		
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				inicio();
			}
		};
		volver1.addActionListener(l);
		
		ActionListener jugarUno = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jugar(1);
			}
		};
		jugar1.addActionListener(jugarUno);
	}
	
	private void configuracionUnJugador() {
		setTitle("Configuracion");
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"1Jugador");
		setSize(600,500);
		setLocationRelativeTo(null);
	}
	
	private void configuracionDosJugador() {
		setTitle("Configuracion");
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"2Jugador");
		setSize(450,725);
		setLocationRelativeTo(null);
	}
	
	private void inicio() {
		setTitle("Jugadores");
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"Inicio");
		setSize(485,210);
		setLocationRelativeTo(null);
	}
	
	private void jugar(int jugadores) {
		setTitle("Juego");
		PantallaDeJuego pdj = null;
		if(jugadores == 1) {
			System.out.println("Aqui ");
			pdj = new PantallaDeJuego();
			mazo.add(pdj,"juega");
			CardLayout c1 = (CardLayout)(mazo.getLayout());
			c1.show(mazo,"juega");
			pdj.setFocusable(true);
			pdj.setVisible(true);
			
		}
	}
	
}