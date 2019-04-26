package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PantallaDeJuego extends JFrame{
	
	private Pintor pint;
	private Container container;
	private Boton pausaBoton;
	private Boton reiniciar;
	private String color;
	private boolean pausa;
	private JMenuBar barraMenu;
	private JMenu menu;	
	private JMenuItem[] items;
	private JFileChooser files;
	
	public PantallaDeJuego(String color) {
		super("Juego");
		setSize(750,660);
		this.color = color;
		pausa =false;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);
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
		
		items = new JMenuItem[3];
		barraMenu = new JMenuBar();
		container.add(barraMenu,BorderLayout.NORTH);
		menu = new JMenu("Opciones");
		barraMenu.add(menu);
		
		items[0] = new JMenuItem("Abrir");
		items[1] = new JMenuItem("Guardar");
		items[2] = new JMenuItem("Salir");
		
		menu.add(items[0]);
		menu.addSeparator();
		menu.add(items[1]);
		menu.addSeparator();
		menu.add(items[2]);
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
		
		items[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrir();
			}
		});
		items[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				guardar();
			}
		});
		items[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				salir();
			}
		});
	}
	private void abrir(){
		files = new JFileChooser();
	}
	
	private void guardar(){
		files = new JFileChooser();
	}
	
	private void salir() {
		int salir = JOptionPane.showConfirmDialog(this, "Realmente Quieres Salir?");
		if(salir == JOptionPane.YES_OPTION) {
			setVisible(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		else {
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}
	public void pausar() {
		if (pausa) pausa = false;
		else pausa = true;
		pint.pausar();
	}
	
	public void reiniciar() {
		pint.reiniciar();
	}
	
}