package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArkaPOOBGUI extends JFrame{
	private JPanel pantallaInicial;
	private JPanel logo; 
	private JPanel botones;
	private JPanel pBotones;
	private JPanel panelEast; //Mirar si dejamos estos o  no (son los paneles de  al lado de los botones para que no se vean tan grandes
	private JPanel panelWest;
	private JPanel mazo;
	private JButton jugar;
	private JButton salir;
	private JButton instrucciones;
	private JButton volverInstrucciones;
	private Container contenedor;
	
	public ArkaPOOBGUI() {
		super("ArkaPOOB");
		prepareElementos();
		prepareAcciones();
		
	}
	
	public void prepareElementos() {
		prepareElementosVentana();
		preparePantalla();
		prepareLogo();
		prepareBotones();
		pantallaInicial.add(logo);
		pantallaInicial.add(botones);
		mazo = new JPanel(new CardLayout());
		mazo.add(pantallaInicial, "Inicio");
		contenedor.add(mazo);
		prepararInstrucciones();
	}
	
	public void prepareBotones() {
		botones = new JPanel();
		botones.setLayout(new BorderLayout());//new GridLayout(4,1)
		botones.setBackground(Color.BLACK);
		creeBotones();
	}
	
	public void creeBotones() {
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(3,1));
		jugar = new JButton("Jugar");
		jugar.setBounds(new Rectangle(50, 20));
		instrucciones = new JButton("Como jugar");
		instrucciones.setBounds(new Rectangle(30, 20));
		salir = new JButton("Salir");
		salir.setBounds(new Rectangle(50, 20));
		pBotones.add(jugar);
		pBotones.add(instrucciones);
		pBotones.add(salir);
		botones.add(pBotones,BorderLayout.CENTER);
		panelEast = new JPanel();
		panelWest = new JPanel();
		panelEast.setBackground(Color.BLACK);
		panelWest.setBackground(Color.BLACK);
		botones.add(panelEast,BorderLayout.EAST);
		botones.add(panelWest,BorderLayout.WEST);
	}
	
	public void preparePantalla() {
		contenedor = getContentPane();
		pantallaInicial = new JPanel();
		pantallaInicial.setLayout(new GridLayout(2,1));
	}
	
	public void prepareElementosVentana() {
		setSize(550,550);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);	
	}
	
	private void prepareLogo() {
		logo = new JPanel();
		logo.setLayout(new FlowLayout());
		logo.setBackground(Color.BLACK);
		logo.add(new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png"))));		
	}
	
	public void prepareAcciones() {
		ActionListener comenzar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				juegoNuevo();
			}
		};
		jugar.addActionListener(comenzar);
		
		/*ActionListener opcion = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//abrirOpciones();
			}
		};
		opciones.addActionListener(opcion);*/
		
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
		System.out.println("hola");
		JPanel instruccion = new JPanel(new GridLayout(3,1));
		
		mazo.add(instruccion,"Instrucciones");
		JPanel instruImg = new JPanel(new GridBagLayout());
		
		instruccion.add(instruImg);
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ArkFont.png")));
		instruImg.add(logo);
		instruImg.setBackground(Color.BLUE);
		
		JPanel contenido = new JPanel();
		JLabel texto = new JLabel("<html><center>Con el ratón moverás la barra hacia los lados con el<br/>"
				+ "fin de recoger la pelota en su caída y hacerla subir<br/>"
				+ "de nuevo. El objetivo de este juego es romper todos<br/> "
				+ "los ladrillos que se encuentran en la parte superior, <br/>"
				+ "cuando limpies la pantalla pasarás de nivel.</center></html>");
		texto.setForeground(Color.WHITE);
		texto.setFont(new Font(null,Font.ITALIC,25));
		contenido.add(texto);
		contenido.setBackground(Color.BLACK);
		instruccion.add(contenido);
		JPanel boton = new JPanel(new GridLayout(3, 3));
		boton.setBackground(Color.DARK_GRAY);
		
		volverInstrucciones = new JButton("Volver");
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

	public static void main(String[] args) {
		ArkaPOOBGUI arka = new ArkaPOOBGUI();
		arka.setVisible(true);

	}
	

}
