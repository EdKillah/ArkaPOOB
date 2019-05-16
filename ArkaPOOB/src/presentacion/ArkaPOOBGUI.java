package presentacion;

import javax.swing.*;
import javax.swing.JFileChooser;
import aplicacion.ArkaPoobException;
import aplicacion.ArkaPOOB;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencia.*;

public class ArkaPOOBGUI extends JFrame{
	private myPanel pantallaInicial;
	private JPanel logo; 
	private JPanel botones;
	private JPanel pBotones;
	private JPanel panelEast; //Mirar si dejamos estos o  no (son los paneles de  al lado de los botones para que no se vean tan grandes
	private JPanel panelWest;
	private JPanel mazo;
	private Boton jugar;
	private Boton salir;
	private Boton instrucciones;
	private Boton volverInstrucciones;
	private Container contenedor;
	private JMenuBar barraMenu;
	private JMenu menu;	
	private JMenuItem[] items;
	private JFileChooser file;
	
	public ArkaPOOBGUI() {
		super("ArkaPOOB");
		prepareElementos();
		prepareAcciones();
	}
	
	
	public void prepareElementos() {
		file = new JFileChooser();
		prepareElementosVentana();
		preparePantalla();
		prepareLogo();
		prepareBotones();
		pantallaInicial.add(logo);
		pantallaInicial.add(botones);
		mazo = new JPanel(new CardLayout());
		mazo.add(pantallaInicial, "Inicio");
		contenedor.add(mazo);
		
		items = new JMenuItem[2];
		barraMenu = new JMenuBar();
		contenedor.add(barraMenu,BorderLayout.NORTH);
		menu = new JMenu("Opciones");
		barraMenu.add(menu);
		
		items[0] = new JMenuItem("Abrir");
		items[1] = new JMenuItem("Exportar");
		
		menu.add(items[0]);
		menu.addSeparator();
		menu.add(items[1]);
		
		prepararInstrucciones();
	}
	
	public void prepareBotones() {
		botones = new JPanel();
		botones.setOpaque(false);//Los hace transparentes
		botones.setLayout(new BorderLayout());//new GridLayout(4,1)
		botones.setBackground(Color.BLACK);
		creeBotones();
	}
	
	public void creeBotones() {
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(3,1));
		pBotones.setOpaque(false);
		jugar = new Boton(new ImageIcon(getClass().getResource("/imagenes/jugar2.png")));
		jugar.setTransparent();
		instrucciones = new Boton(new ImageIcon(getClass().getResource("/imagenes/como_jugar.png")));
		instrucciones.setTransparent();
		salir = new Boton(new ImageIcon(getClass().getResource("/imagenes/salir.png")));
		salir.setTransparent();
		pBotones.add(jugar);
		pBotones.add(instrucciones);
		pBotones.add(salir);
		botones.add(pBotones,BorderLayout.CENTER);
		panelEast = new JPanel();
		panelWest = new JPanel();
		panelEast.setBackground(Color.BLACK);
		panelEast.setOpaque(false);
		panelWest.setBackground(Color.BLACK);
		panelWest.setOpaque(false);
		botones.add(panelEast,BorderLayout.EAST);
		botones.add(panelWest,BorderLayout.WEST);
	}
	
	public void preparePantalla() {
		contenedor = getContentPane();
		pantallaInicial = new myPanel();
		pantallaInicial.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		pantallaInicial.setLayout(new GridLayout(2,1));
	}
	
	public void prepareElementosVentana() {
		setSize(550,550);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);	
	}
	
	private void prepareLogo() {
		logo = new JPanel();
		logo.setOpaque(false);
		logo.setLayout(new FlowLayout());
		logo.setBackground(Color.BLACK);
		logo.add(new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png"))));		
	}
	
	public void prepareAcciones() {
		items[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrir();
			}
		});
		
		items[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrir();
			}
		});
		
		ActionListener comenzar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				juegoNuevo();
			}
		};
		jugar.addActionListener(comenzar);
		
		ActionListener comoJugar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AbrirInstrucciones();
			}
		};
		instrucciones.addActionListener(comoJugar);
		
		ActionListener exit = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cerrar();
			}
		};
		salir.addActionListener(exit);
	}
	
	
	public void juegoNuevo(){
		jugadoresGUI a = new jugadoresGUI();
		a.setVisible(true);
	}
	
	public void cerrar() {
		System.exit(0);
	}
	
	private void inicio() {
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"Inicio");
		setSize(550,550);
	}
	
	private void prepararInstrucciones() {
		myPanel instruccion = new myPanel();
		instruccion.setLayout(new GridLayout(3,1));
		instruccion.setBackground(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
		mazo.add(instruccion,"Instrucciones");
		JPanel instruImg = new JPanel(new GridBagLayout());
		instruImg.setOpaque(false);
		instruccion.add(instruImg);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		instruImg.add(logo);
		instruImg.setBackground(Color.BLUE);
		
		JPanel contenido = new JPanel();
		contenido.setOpaque(false);
		JLabel texto = new JLabel("<html><center>Con el ratón moverás la barra hacia los lados con el<br/>"
				+ "fin de recoger la pelota en su caída y hacerla subir<br/>"
				+ "de nuevo. El objetivo de este juego es romper todos<br/> "
				+ "los ladrillos que se encuentran en la parte superior, <br/>"
				+ "cuando limpies la pantalla pasarás de nivel.</center></html>");
		texto.setForeground(Color.BLUE);
		texto.setFont(new Font(null,Font.ITALIC,25));
		contenido.add(texto);
		contenido.setBackground(Color.BLACK);
		instruccion.add(contenido);
		JPanel boton = new JPanel(new GridLayout(3, 3));
		boton.setOpaque(false);
		boton.setBackground(Color.DARK_GRAY);
		
		volverInstrucciones = new Boton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		volverInstrucciones.setTransparent();
		boton.add(new JLabel());boton.add(new JLabel());boton.add(new JLabel());
		boton.add(new JLabel());boton.add(volverInstrucciones);boton.add(new JLabel());
		boton.add(new JLabel());boton.add(new JLabel());boton.add(new JLabel());
		
		instruccion.add(boton);
		//instruccion.setBackground(Color.BLACK);
		prepareAccionesInstrucciones();
	}
	
	private void prepareAccionesInstrucciones() {
		volverInstrucciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inicio();
				
			}
		});
	}
	
	public void AbrirInstrucciones(){
		CardLayout c1 = (CardLayout)(mazo.getLayout());
		c1.show(mazo,"Instrucciones");
		setSize(650,550);
	}
	
	public void abrir(){
		file.setFileFilter(new FileNameExtensionFilter("DAT (.dat)","dat"));
		int val = file.showOpenDialog(this);
		if(val == JFileChooser.APPROVE_OPTION){
			ArkaPoobDAO dao = new ArkaPoobDAO();
			ArkaPOOB juego = null;
			try{
				juego = dao.abrir(file.getSelectedFile());
				PantallaDeJuego pj = new PantallaDeJuego(juego.getJugadores(),juego.getColorNave(),juego.getColores()[0],juego.getColores()[1],juego.getColores()[2],juego.getColores()[3],juego.getColores()[4]);
				pj.crearJuego(juego);
				pj.setVisible(true);	
			}catch(ArkaPoobException e){
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		ArkaPOOBGUI arka = new ArkaPOOBGUI();
		arka.setVisible(true);

	}
	

}
