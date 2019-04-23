package presentacion;

import aplicacion.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class Pintor extends JPanel implements ActionListener, KeyListener, Runnable{
	private ArkaPOOB ark;
	//private Dimension d;
	private ArrayList<Integer> keysDown;
	private String colorNave;
	private Thread hilo;
	private Timer myTimer;
	private TimerTask task;
	private int width;
	private int height;
	
	public Pintor(int w, int h , String color) {
		keysDown=new ArrayList<Integer>();
		ark = new ArkaPOOB();
		width = w;
		height = h;
		//d = Toolkit.getDefaultToolkit().getScreenSize();
		colorNave = color;
		hilo= new Thread(this);
		myTimer = new Timer();
		hilo.start();
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
		g.drawImage(nave.getImagen(), nave.getX(), nave.getY(),nave.getWidth(),nave.getHeight(), this);			
		Bola bola = ark.getBola();
		if(bola.isVivo())
			g.drawImage(bola.getImagen(), bola.getX(),bola.getY() ,Bola.getTamX(),Bola.getTamY(), this);			//(int)d.getHeight()-125
		for(int i=0;i<ark.getVidas().size();i++) {
			Plataforma vida = ark.getVidas().get(i);
			g.drawImage(vida.getImagen(), vida.getX(),vida.getY() ,vida.getWidth(),vida.getHeight(), this);			
		}
	}
	
	public void prepareElementos() {
		ark.getPlataforma().setColor(colorNave);
		for(Plataforma vida_N: ark.getVidas()) {
			vida_N.setColor(ark.getPlataforma().getColor());
		}
	}
	
	public void prepareAcciones() {
		setFocusable(true);
		addKeyListener(this);
	}
	public void moverBola() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
			if(!keysDown.contains(e.getKeyCode())) {
				keysDown.add(new Integer(e.getKeyCode()));
			}
			moverJugador();
			moverBola();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.remove(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void moverJugador() {
		if(keysDown.contains(new Integer(KeyEvent.VK_LEFT))) {
			
			if (ark.getPlataforma().getX()>15)  
				ark.getPlataforma().setX(2);
			if(ark.getBola().getX()>ark.getPlataforma().getWidth()/2 && !ark.getBola().isInAire())
				ark.getBola().setX(2); //esto puede meterse en el if de arriba
		}
		
		if(keysDown.contains(new Integer(KeyEvent.VK_RIGHT))) {
			if (ark.getPlataforma().getX()<width-ark.getPlataforma().getWidth()) 
				ark.getPlataforma().setX(1);
			
			if(ark.getBola().getX()+15<width-ark.getPlataforma().getWidth()/2 && !ark.getBola().isInAire())
				ark.getBola().setX(1); //ark.getBola().getX()+30 el +30 es el ancho de la bola, ponerlo así esta mal
			
		}
		if(keysDown.contains(new Integer(KeyEvent.VK_SPACE))) {
			if(!ark.getBola().isInAire()) {
				ark.getBola().setInAire(true);
				juegue();
			}
		}
		this.repaint();
	}
	
	public void juegue() {
		task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Score: "+ark.getScore());
				//numero++;
				ark.juegue(width, ark.getPlataforma().getY());
				if(ark.gano()) {
					mensaje();
					cancel();
				}
				if(!ark.isVivo()) {
					ark.estatico(height);
					mensaje();
					cancel();
				}
			}
		};
		myTimer.scheduleAtFixedRate(task,0,7);
	}
	
	private void mensaje() {
		if(ark.gano()) JOptionPane.showMessageDialog(this, "Ganaste!");
		if(ark.perdio()) JOptionPane.showMessageDialog(this, "Perdiste!");
	}
	
	
	@Override
	public void run() {
		while(true) {
			
			this.repaint();
			/*
			try {
				//hilo.sleep(4);
				//this.repaint();
				if(!ark.getBola().isVivo()) {
					//System.out.println("Cuantas veces entra en bolaMuerta: "+numero);
					//numero++;
					//hilo.stop();
					//task.wait();
					//hilo.wait();
					//myTimer.cancel();
					//myTimer = new Timer();
				}
				this.repaint();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}*/
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
