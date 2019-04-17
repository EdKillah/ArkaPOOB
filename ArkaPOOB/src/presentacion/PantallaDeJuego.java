package presentacion;

import aplicacion.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

public class PantallaDeJuego extends JPanel implements ActionListener, KeyListener{
	private ArkaPOOB ark;
	private Dimension d;
	private ArrayList<Integer> keysDown;
	
	public PantallaDeJuego() {
		setLayout(null);
		keysDown=new ArrayList<Integer>();
		ark = new ArkaPOOB();
		d = Toolkit.getDefaultToolkit().getScreenSize();
		//this.repaint();
		prepareElementos();
		prepareAcciones();
		setBackground(Color.BLACK);

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        ArrayList<ArrayList<Bloque>> bloques= ark.getBloques();
        Bloque b;
		for(int i=0;i<bloques.size();i++) {
			for(int j=0;j<bloques.get(i).size();j++) {
				b = bloques.get(i).get(j);
				g.drawImage(b.getImagen(), b.getX(), b.getY(),b.getWidth(),b.getHeight(), this);			
			}
		}
		Plataforma nave = ark.getPlataforma();
		g.drawImage(nave.getImagen(), nave.getX(), (int)d.getHeight()-100,nave.getWidth(),nave.getHeight(), this);			
		
	}
	
	public void prepareElementos() {
		
	}
	
	public void prepareAcciones() {
		System.out.println("entra en prepareAcciones");
		//setFocusable(true);
		addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

			System.out.println("Entra en key");
			if(!keysDown.contains(e.getKeyCode())) {
				keysDown.add(new Integer(e.getKeyCode()));
			}
			
			moverJugador();

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Entra en typed");

	}





	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Entra en released");
		keysDown.remove(new Integer(e.getKeyCode()));
		/*
		if(ark.getBalas().size()==1 && new Integer(e.getKeyCode())==32) {
	
			keysDown.remove(new Integer(e.getKeyCode()));
		}
		else {
		
			keysDown.remove(new Integer(e.getKeyCode()));
		}
		*/
	}
	
	public void moverJugador() {
		
		System.out.println("entra en moverJugador");
		if(keysDown.contains(new Integer(KeyEvent.VK_LEFT))) {
			System.out.println("CONTIENE");
			if (ark.getPlataforma().getX()>0) { 
				ark.getPlataforma().setX(2);
			}
		}
			
		if(keysDown.contains(new Integer(KeyEvent.VK_RIGHT))) {
			if (ark.getPlataforma().getX()<d.getWidth()-100) { 
				ark.getPlataforma().setX(1);
			}
		}
		this.repaint();
		/*
		if(keysDown.contains(new Integer(KeyEvent.VK_SPACE))) 
		{
			logica.disparo();
			logica.getBalaJ().play();
		}
		*/
		
		/*
		if(keysDown.contains(new Integer(KeyEvent.VK_X))) {
			logica.disparoEspecial();
			logica.getBalaJ().play();
		}
		
		if(keysDown.contains(new Integer(KeyEvent.VK_P))) 
		{
			if(pausa) {
				hilo.resume();
				pausa=false;
			}
			else {
				hilo.suspend();
				pausa=true;
			}
		}
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
