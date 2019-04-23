package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jugadoresGUI extends JFrame{
	
	private myPanel pantalla;
	private JPanel botones;
	private Container contenedor;
	private Boton Jugador;
	private Boton dosJugadores;
	private Boton jugadorCPU;
	private JLabel panelLogo;
	private JPanel mazo;
	private Boton jugar1;
	private Boton jugar2;
	private Boton jugar3;
	private Boton volver1;
	private Boton volver2;
	private Boton volver3;
	private JTextField textNombre;
	private JComboBox<String> naveColor;
	private String[] colores = {"red","blue","orange","green","purple"};
	
	public jugadoresGUI() {
		super("Jugadores");
		setBackground(Color.BLACK);
		setSize(700,400);
		setLocationRelativeTo(null);
		contenedor = getContentPane();
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		pantalla = new myPanel();
		pantalla.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		pantalla.setLayout(new GridLayout(2,1));
		contenedor.add(pantalla);
		panelLogo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/jugadores.png")));
		pantalla.add(panelLogo);
		//pantalla.setBackground(Color.BLACK);
		botones = new JPanel();
		botones.setOpaque(false);
		//botones.setBackground(Color.BLACK);
		pantalla.add(botones);
		Jugador = new Boton(new ImageIcon(getClass().getResource("/imagenes/1_jugador.png")));
		Jugador.setTransparent();
		dosJugadores = new  
				Boton(new ImageIcon(getClass().getResource("/imagenes/2_jugadores.png")));
		dosJugadores.setTransparent();
		jugadorCPU = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugador_vs_cpu.png")));
		jugadorCPU.setTransparent();
		botones.add(Jugador);
		botones.add(dosJugadores);
		botones.add(jugadorCPU);
		setIconImage(new ImageIcon(getClass().getResource("/imagenes/icon.png")).getImage());
		
		mazo = new JPanel(new CardLayout());
		contenedor.add(mazo);
		mazo.add(pantalla,"Inicio");
		prepareUnJugador();
		prepareDosJugadores();
		prepareJugadorCPU();
	}
	
	private void prepareUnJugador() {
		myPanel panelUnJugador = new myPanel();
		panelUnJugador.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		panelUnJugador.setLayout(new GridLayout(2,1));
		mazo.add(panelUnJugador,"1Jugador");
		
		JPanel unJugadorLogo = new JPanel(new GridBagLayout());
		unJugadorLogo.setOpaque(false);//Los hace transparentes
		panelUnJugador.add(unJugadorLogo);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		unJugadorLogo.add(logo);
		unJugadorLogo.setBackground(Color.BLACK);
		
		JPanel contenido = new JPanel();
		contenido.setOpaque(false);//Los hace transparentes
		contenido.setLayout(null);
		contenido.setBackground(Color.BLACK);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setForeground(Color.BLACK);
		nombre.setBounds(15, 20, 80, 18);
		contenido.add(nombre);
		
		JLabel nave = new JLabel("Color Nave:");
		nave.setForeground(Color.BLACK);
		nave.setBounds(15, 65, 80, 18);
		contenido.add(nave);
		
		jugar1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar1.setBounds(85, 150, 180, 60);
		jugar1.setTransparent();
		contenido.add(jugar1);
		
		volver1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver1.setBounds(300, 150, 200, 60);
		volver1.setTransparent();
		contenido.add(volver1);
		
		textNombre = new JTextField();
		textNombre.setBounds(90, 19, 78, 18);
		contenido.add(textNombre);
		textNombre.setColumns(10);
		
		naveColor = new JComboBox<>(colores);
		naveColor.setBounds(100, 65, 121, 20);
		contenido.add(naveColor);

		panelUnJugador.add(contenido);
	}
	
	private void prepareDosJugadores() {
		myPanel panelDosJugador = new myPanel();
		panelDosJugador.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		panelDosJugador.setLayout(new GridLayout(2,1));
		mazo.add(panelDosJugador,"2Jugador");
		
		JPanel dosJugadorLogo = new JPanel(new GridBagLayout());
		dosJugadorLogo.setOpaque(false);//Los hace transparentes
		panelDosJugador.add(dosJugadorLogo);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		dosJugadorLogo.add(logo);
		dosJugadorLogo.setBackground(Color.BLACK);
		
		JPanel contenido = new JPanel();
		contenido.setOpaque(false);//Los hace transparentes
		contenido.setLayout(null);
		contenido.setBackground(Color.BLACK);
		
		jugar2 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar2.setBounds(85, 150, 180, 60);
		jugar2.setTransparent();
		contenido.add(jugar2);
		
		volver2 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver2.setBounds(300, 150, 200, 60);
		volver2.setTransparent();
		contenido.add(volver2);
		
		panelDosJugador.add(contenido);
	}
	
	private void prepareJugadorCPU() {
		myPanel panelJugadorCPU = new myPanel();
		panelJugadorCPU.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		panelJugadorCPU.setLayout(new GridLayout(2,1));
		mazo.add(panelJugadorCPU,"JugadorCPU");
		
		JPanel dosJugadorLogo = new JPanel(new GridBagLayout());
		dosJugadorLogo.setOpaque(false);//Los hace transparentes
		panelJugadorCPU.add(dosJugadorLogo);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		dosJugadorLogo.add(logo);
		dosJugadorLogo.setBackground(Color.BLACK);
		
		JPanel contenido = new JPanel();
		contenido.setOpaque(false);//Los hace transparentes
		contenido.setLayout(null);
		contenido.setBackground(Color.BLACK);
		
		jugar3 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar3.setBounds(85, 150, 180, 60);
		jugar3.setTransparent();
		contenido.add(jugar3);
		
		volver3 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver3.setBounds(300, 150, 200, 60);
		volver3.setTransparent();
		contenido.add(volver3);
		
		panelJugadorCPU.add(contenido);
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
		
		ActionListener juego3 = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configuracionJugadorCPU();
			}
		};
		jugadorCPU.addActionListener(juego3);
		
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				inicio();
			}
		};
		volver1.addActionListener(l);
		
		ActionListener jugarUno = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jugar(1,false);
			}
		};
		jugar1.addActionListener(jugarUno);
		
		ActionListener jugarDos = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jugar(2,false);
			}
		};
		
		ActionListener jugarDosCPU = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jugar(2,true);
			}
		};
		
		jugar2.addActionListener(jugarDos);
		
		volver2.addActionListener(l);
		
		jugar3.addActionListener(jugarDosCPU);
		
		volver3.addActionListener(l);
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
		setSize(600,500);;
		setLocationRelativeTo(null);
	}
	
	private void configuracionJugadorCPU() {
		setTitle("Configuracion");
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"JugadorCPU");
		setSize(600,500);;
		setLocationRelativeTo(null);
	}
	
	private void inicio() {
		setTitle("Jugadores");
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"Inicio");
		setSize(700,400);
		setLocationRelativeTo(null);
	}
	
	private void jugar(int jugadores,boolean cpu) {
		dispose();
		PantallaDeJuego pdj = null;
		if(jugadores == 1) {
			pdj = new PantallaDeJuego((String)naveColor.getSelectedItem());
		}
		pdj.setVisible(true);
	}
	
}