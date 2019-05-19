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
	private JTextField textNombre1;
	private JTextField textNombre2;
	private JTextField textNombre4;
	private JComboBox<String> naveColor;
	private JComboBox<String> naveColor1;
	private JComboBox<String> naveColor2;
	private JComboBox<String> tipoMaquina;
	private JCheckBox bloqueRosado;
	private JCheckBox bloqueNegro;
	private JCheckBox bloqueAmarillo;
	private JCheckBox bloqueNaranja;
	private JCheckBox bloqueAzul;
	private JCheckBox bloqueRosado2;
	private JCheckBox bloqueNegro2;
	private JCheckBox bloqueAmarillo2;
	private JCheckBox bloqueNaranja2;
	private JCheckBox bloqueAzul2;
	
	private String[] colores = {"red","blue","orange","green","purple"};
	private String[] maquinas = {"destructor","curioso","mimo"};
	
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
	
	
	private void prepareBloquesJugador1(JPanel contenido) {
		
		JLabel bloques = new JLabel("Bloques:");
		bloques.setForeground(Color.BLACK);
		bloques.setBounds(355, 10, 80, 18);
		contenido.add(bloques);
		
		bloqueRosado = new JCheckBox("Rosado");
		bloqueAzul = new JCheckBox("Azul");
		bloqueAmarillo = new JCheckBox("Amarillo");
		bloqueNaranja = new JCheckBox("Naranja");
		bloqueNegro = new JCheckBox("Negro");
		
		bloqueRosado.setBounds(355, 35, 80, 20);
		bloqueAzul.setBounds(355, 55, 80, 20);
		bloqueAmarillo.setBounds(355, 75, 80, 20);
		bloqueNaranja.setBounds(355, 95, 80, 20);
		bloqueNegro.setBounds(355, 115, 80, 20);
		bloqueRosado.setOpaque(false);
		bloqueAzul.setOpaque(false);
		bloqueAmarillo.setOpaque(false);
		bloqueNaranja.setOpaque(false);
		bloqueNegro.setOpaque(false);
		
		contenido.add(bloqueRosado);
		contenido.add(bloqueAzul);
		contenido.add(bloqueAmarillo);
		contenido.add(bloqueNaranja);
		contenido.add(bloqueNegro);
	}
	
	private void prepareBloquesJugador2(JPanel contenido) {
		JLabel bloques = new JLabel("Bloques:");
		bloques.setForeground(Color.BLACK);
		bloques.setBounds(25, 105, 80, 18);
		contenido.add(bloques);
		
		bloqueRosado2 = new JCheckBox("Rosado");
		bloqueAzul2 = new JCheckBox("Azul");
		bloqueAmarillo2 = new JCheckBox("Amarillo");
		bloqueNaranja2 = new JCheckBox("Naranja");
		bloqueNegro2 = new JCheckBox("Negro");
		
		bloqueRosado2.setBounds(105, 105, 80, 20);
		bloqueAzul2.setBounds(185, 105, 80, 20);
		bloqueAmarillo2.setBounds(265, 105, 80, 20);
		bloqueNaranja2.setBounds(345, 105, 80, 20);
		bloqueNegro2.setBounds(425, 105, 80, 20);
		bloqueRosado2.setOpaque(false);
		bloqueAzul2.setOpaque(false);
		bloqueAmarillo2.setOpaque(false);
		bloqueNaranja2.setOpaque(false);
		bloqueNegro2.setOpaque(false);
		
		contenido.add(bloqueRosado2);
		contenido.add(bloqueAzul2);
		contenido.add(bloqueAmarillo2);
		contenido.add(bloqueNaranja2);
		contenido.add(bloqueNegro2);
	}
	
	private void prepareNombreJugador1(JPanel contenido) {
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setForeground(Color.BLACK);
		nombre.setBounds(15, 20, 80, 18);
		contenido.add(nombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(90, 19, 78, 18);
		contenido.add(textNombre);
		textNombre.setColumns(10);
	}
	
	private void prepareColorNaveJugador1(JPanel contenido) {
		
		JLabel nave = new JLabel("Color Nave:");
		nave.setForeground(Color.BLACK);
		nave.setBounds(15, 65, 80, 18);
		contenido.add(nave);
		
		naveColor = new JComboBox<>(colores);
		naveColor.setBounds(100, 65, 121, 20);
		contenido.add(naveColor);
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
		
		prepareNombreJugador1(contenido);
		
		prepareColorNaveJugador1(contenido);

		prepareBloquesJugador1(contenido);
		
		jugar1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar1.setBounds(85, 150, 180, 60);
		jugar1.setTransparent();
		contenido.add(jugar1);
		
		volver1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver1.setBounds(300, 150, 200, 60);
		volver1.setTransparent();
		contenido.add(volver1);
		//prepareBotonJugar(contenido);
		//prepareBotonVolver(contenido);
		
		panelUnJugador.add(contenido);
	}
	
	private void prepareBotonJugar(JPanel contenido) {
		jugar1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar1.setBounds(85, 150, 180, 60);
		jugar1.setTransparent();
		contenido.add(jugar1);
	}
	
	private void prepareBotonVolver(JPanel contenido){
		volver1 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver1.setBounds(300, 150, 200, 60);
		volver1.setTransparent();
		contenido.add(volver1);
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
		
		//prepareNombreJugador1(contenido);
		
		//prepareColorNaveJugador1(contenido);
		
		JLabel nombre = new JLabel("Jugador 1:");
		nombre.setForeground(Color.BLACK);
		nombre.setBounds(15, 20, 80, 18);
		contenido.add(nombre);
		
		textNombre1 = new JTextField();
		textNombre1.setBounds(90, 19, 78, 18);
		contenido.add(textNombre1);
		textNombre1.setColumns(10);
		
		JLabel nave = new JLabel("Color Nave:");
		nave.setForeground(Color.BLACK);
		nave.setBounds(15, 65, 80, 18);
		contenido.add(nave);
		
		naveColor1 = new JComboBox<>(colores);
		naveColor1.setBounds(90, 65, 121, 20);
		contenido.add(naveColor1);
		
		prepareBloquesJugador2(contenido);
		
		JLabel nombre2 = new JLabel("Jugador 2:");
		nombre2.setForeground(Color.BLACK);
		nombre2.setBounds(280, 20, 80, 18);
		contenido.add(nombre2);
		
		
		JLabel nave2 = new JLabel("Color Nave:");
		nave2.setForeground(Color.BLACK);
		nave2.setBounds(280, 65, 80, 18);
		contenido.add(nave2);
		
		
		textNombre2 = new JTextField();
		textNombre2.setBounds(355, 19, 78, 18);
		contenido.add(textNombre2);
		textNombre2.setColumns(10);
		
		naveColor2 = new JComboBox<>(colores);
		naveColor2.setBounds(355, 65, 121, 20);
		contenido.add(naveColor2);
		
		jugar2 = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar2.setBounds(85, 150, 180, 60);
		jugar2.setTransparent();
		contenido.add(jugar2);
		
		volver2 = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volver2.setBounds(300, 150, 200, 60);
		volver2.setTransparent();
		contenido.add(volver2);
		
		//prepareBotonJugar(contenido);
		
		//prepareBotonVolver(contenido);
		
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
		
		JLabel nombre = new JLabel("Jugador:");
		nombre.setForeground(Color.BLACK);
		nombre.setBounds(15, 20, 80, 18);
		contenido.add(nombre);
		
		textNombre4 = new JTextField();
		textNombre4.setBounds(90, 19, 78, 18);
		contenido.add(textNombre4);
		textNombre4.setColumns(10);
		
		JLabel nave = new JLabel("Color Nave:");
		nave.setForeground(Color.BLACK);
		nave.setBounds(15, 65, 80, 18);
		contenido.add(nave);
		
		naveColor1 = new JComboBox<>(colores);
		naveColor1.setBounds(90, 65, 121, 20);
		contenido.add(naveColor1);
		
		prepareBloquesJugador2(contenido);
		
		JLabel nombre2 = new JLabel("Maquina :");
		nombre2.setForeground(Color.BLACK);
		nombre2.setBounds(280, 20, 80, 18);
		contenido.add(nombre2);
		
		tipoMaquina = new JComboBox<>(maquinas);
		tipoMaquina.setBounds(355, 19, 121, 20);
		contenido.add(tipoMaquina);
	
		
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
				jugar(1,true);
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
		if(jugadores == 1 && !cpu) {
			pdj = new PantallaDeJuego(jugadores,textNombre.getText(),(String)naveColor.getSelectedItem(),bloqueRosado.isSelected(), bloqueAzul.isSelected(), bloqueAmarillo.isSelected(), bloqueNaranja.isSelected(), bloqueNegro.isSelected());
		}else if(jugadores == 2 && !cpu) {
			pdj = new PantallaDeJuego(jugadores,false,textNombre1.getText(),textNombre2.getText(),(String)naveColor1.getSelectedItem(),(String)naveColor2.getSelectedItem(),bloqueRosado2.isSelected(), bloqueAzul2.isSelected(), bloqueAmarillo2.isSelected(), bloqueNaranja2.isSelected(), bloqueNegro2.isSelected());
		}else if(jugadores == 1 && cpu) {
			pdj = new PantallaDeJuego(jugadores,true,textNombre4.getText(),null,(String)naveColor1.getSelectedItem(),null,bloqueRosado2.isSelected(), bloqueAzul2.isSelected(), bloqueAmarillo2.isSelected(), bloqueNaranja2.isSelected(), bloqueNegro2.isSelected());
			System.out.println((String)tipoMaquina.getSelectedItem());
			pdj.maquina((String)tipoMaquina.getSelectedItem());
		}
		pdj.setVisible(true);
	}
	
}