package presentacion;

import javax.swing.*;
import java.awt.*;

public class ArkaPOOBGUI extends JFrame{
	private JPanel pantallaInicial;
	private JPanel logo; 
	private JPanel botones;
	private JPanel pBotones;
	private JPanel panelEast; //Mirar si dejamos estos o  no (son los paneles de  al lado de los botones para que no se vean tan grandes
	private JPanel panelWest;
	private JButton jugar;
	private JButton salir;
	private JButton opciones;
	private JButton instrucciones;
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
		contenedor.add(pantallaInicial);
	}
	
	public void prepareBotones() {
		botones = new JPanel();
		botones.setLayout(new BorderLayout());//new GridLayout(4,1)
		botones.setBackground(Color.BLACK);
		creeBotones();
	}
	
	public void creeBotones() {
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(4,1));
		jugar = new JButton("Jugar");
		jugar.setBounds(new Rectangle(50, 20));
		opciones = new JButton("Opciones");
		jugar.setBounds(new Rectangle(50, 20));
		instrucciones = new JButton("Como jugar");
		jugar.setBounds(new Rectangle(50, 20));
		salir = new JButton("Salir");
		jugar.setBounds(new Rectangle(50, 20));
		pBotones.add(jugar);
		pBotones.add(opciones);
		pBotones.add(instrucciones);
		pBotones.add(salir);
		botones.add(pBotones,BorderLayout.CENTER);
		panelEast = new JPanel();
		panelWest = new JPanel();
		panelEast.setBackground(Color.BLACK);
		panelWest.setBackground(Color.BLACK);
		botones.add(panelEast,BorderLayout.EAST);
		botones.add(panelWest,BorderLayout.WEST);
		//botones.add(jugar);
		//botones.add(opciones);
		//botones.add(instrucciones);
		//botones.add(salir);
	}
	
	public void preparePantalla() {
		contenedor = getContentPane();
		pantallaInicial = new JPanel();
		pantallaInicial.setLayout(new GridLayout(2,1));
	}
	
	public void prepareElementosVentana() {
		setSize(500,500);
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
		
	}
	

	public static void main(String[] args) {
		ArkaPOOBGUI arka = new ArkaPOOBGUI();
		arka.setVisible(true);

	}

}
