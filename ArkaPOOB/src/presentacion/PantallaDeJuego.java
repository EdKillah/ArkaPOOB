package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import aplicacion.*;

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
	private int jugadores; 
	
	public PantallaDeJuego(int jugadores) {
		super("Juego");
		setSize(750,660);
		this.jugadores=jugadores;
		//this.color = color;
		pausa =false;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);
		setResizable(false);
		prepareElementos();
		prepareAcciones();
	}
	
	public PantallaDeJuego(int jugadores,String color,String color2) {
		this(jugadores);
		pint.colores(color,color2);
		
	}
	
	public PantallaDeJuego(int jugadores,String color) {
		this(jugadores);
		pint.colores(color,null);
		
	}
	
	public void prepareElementos() {
		files = new JFileChooser();
		pint = new Pintor(747,580,jugadores);
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
		
		items = new JMenuItem[2];
		barraMenu = new JMenuBar();
		container.add(barraMenu,BorderLayout.NORTH);
		menu = new JMenu("Opciones");
		barraMenu.add(menu);
		
		items[0] = new JMenuItem("Guardar");
		items[1] = new JMenuItem("Salir");
		
		menu.add(items[0]);
		menu.addSeparator();
		menu.add(items[1]);
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
				guardar();
			}
		});
		items[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				salir();
			}
		});
	}
	
	private void guardar(){
		files.setFileFilter(new FileNameExtensionFilter("DAT (.dat)","dat"));
		int valid = files.showSaveDialog(this);
		
		File filePath = files.getSelectedFile();
		
		if (filePath != null && !filePath.getName().endsWith(".dat")) {
			
			String fileName = redoExtension(filePath.getName());
			filePath = new File(filePath.getParentFile(),fileName+ ".dat");
		}
		
		try{
			if (valid == JFileChooser.APPROVE_OPTION) pint.guardar(filePath);
			files.resetChoosableFileFilters();
		}catch(ArkaPoobException e){	
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	
	private String redoExtension(String fileName) {
		int dotIndex = fileName.indexOf('.');
		StringBuilder sb = new StringBuilder();
		if (dotIndex >= 0) {
			for (int i = 0 ; i < dotIndex;i++) {
				sb.append(fileName.charAt(i));
			}
		}
		return !sb.toString().equals("") ? sb.toString() : fileName;
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
	
	public void crearJuego(ArkaPOOB ar) {
		pint.setJuego(ar);
	}
	
	
	
}