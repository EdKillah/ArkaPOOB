package presentacion;

import aplicacion.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;


import javax.swing.*;

public class Pintor extends JPanel implements ActionListener, KeyListener, Runnable{
	private ArkaPOOB ark;
	private ArrayList<Integer> keysDown;
	//private String colorNave;
	private Thread hilo;
	private Timer myTimer;
	private TimerTask task;
	private int width;
	private int height;
	private boolean pausa;
	private int jugadores;
	
	public Pintor(int w, int h , int jugadores) {
		this.jugadores = jugadores;
		pausa = false;
		keysDown=new ArrayList<Integer>();
		ark = new ArkaPOOB(jugadores);
		width = w;
		height = h;
		//colorNave = color;
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
				Image img =  new ImageIcon(getClass().getResource("/imagenes/bloque_"+b.getTipo()+".png")).getImage();
				g.drawImage(img, b.getX(), b.getY(),b.getWidth(),b.getHeight(), this);			
			}
		}
		Plataforma nave = ark.getPlataforma().get(0);
		if(nave != null )g.drawImage(nave.getImagen(), nave.getX(), nave.getY(),nave.getWidth(),nave.getHeight(), this);
		if(jugadores == 2)  {
			Plataforma nave2 = ark.getPlataforma().get(1);
			if(nave2 != null ) g.drawImage(nave2.getImagen(), nave2.getX(), nave2.getY(),nave2.getWidth(),nave2.getHeight(), this);
		}
		
		Sorpresa poder = ark.getSorpresa();
		if(poder!= null) {
			g.drawImage(poder.getImagen(), poder.getX(),poder.getY() ,poder.getWidth(),poder.getHeight(), this);
		}
		
		Bola bola = ark.getBola();
		if(bola.isVivo()) {
			Image img =  new ImageIcon(getClass().getResource("/imagenes/pelota.png")).getImage();
			g.drawImage(img, bola.getX(),bola.getY() ,Bola.getTamX(),Bola.getTamY(), this);			//(int)d.getHeight()-125
		}
		for(int i=0;i<ark.getVidas().size();i++) {
			for(int j=0;j<ark.getVidas().get(i).size();j++) {
				Plataforma vida = ark.getVidas().get(i).get(j);
				if(vida != null )g.drawImage(vida.getImagen(), vida.getX(),vida.getY() ,vida.getWidth(),vida.getHeight(), this);		
			}
		}
	}
	
	public void colores(String color1,String color2) {
		ArrayList<Plataforma> naves = ark.getPlataforma();
		if(color1 != null) naves.get(0).setColor(color1);
		if(color2 != null) naves.get(1).setColor(color2);
		ark.prepareVidas();
	}
	
	public void prepareElementos() {
		//ark.getPlataforma().setColor(colorNave);
		for(int i=0;i<ark.getVidas().size();i++) {
			for(Plataforma vida_N: ark.getVidas().get(i)) {
				if(i==1) vida_N.setColor(ark.getPlataforma().get(1).getColor());
				else vida_N.setColor(ark.getPlataforma().get(0).getColor());
			}
		}
	}
	
	public void prepareAcciones() {
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
			if(!keysDown.contains(e.getKeyCode())) {
				keysDown.add(new Integer(e.getKeyCode()));
			}
			moverJugador();

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
			if (ark.getPlataforma().get(0).getX()>15&& !pausa)  
				ark.getPlataforma().get(0).setX(2);
			if(ark.getBola().getX()>ark.getPlataforma().get(0).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
				ark.getBola().setX(2); //esto puede meterse en el if de arriba
		}
		
		if(keysDown.contains(new Integer(KeyEvent.VK_RIGHT))) {
			if (ark.getPlataforma().get(0).getX()<width-ark.getPlataforma().get(0).getWidth()&& !pausa) 
				ark.getPlataforma().get(0).setX(1);
			
			if(ark.getBola().getX()+15<width-ark.getPlataforma().get(0).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
				ark.getBola().setX(1); //ark.getBola().getX()+30 el +30 es el ancho de la bola, ponerlo así esta mal
			
		}
		if(keysDown.contains(new Integer(KeyEvent.VK_SPACE))) {
			if(!ark.getBola().isInAire() && !pausa) {
				ark.getBola().setInAire(true);
				juegue();
			}
		}
		if(jugadores == 2) {
			if(keysDown.contains(new Integer(KeyEvent.VK_A))) {
				if (ark.getPlataforma().get(1).getX()>15&& !pausa)  
					ark.getPlataforma().get(1).setX(2);
				//if(ark.getBola().getX()>ark.getPlataforma().get(1).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
					//ark.getBola().setX(2); //esto puede meterse en el if de arriba
			}
			
			if(keysDown.contains(new Integer(KeyEvent.VK_D))) {
				if (ark.getPlataforma().get(1).getX()<width-ark.getPlataforma().get(1).getWidth()&& !pausa) 
					ark.getPlataforma().get(1).setX(1);
				
				//if(ark.getBola().getX()+15<width-ark.getPlataforma().get(1).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
					//ark.getBola().setX(1); //ark.getBola().getX()+30 el +30 es el ancho de la bola, ponerlo así esta mal
				
			}
			if(keysDown.contains(new Integer(KeyEvent.VK_W))) {
				if(!ark.getBola().isInAire() && !pausa) {
					ark.getBola().setInAire(true);
					juegue();
				}
			}
		}
		this.repaint();
	}
	
	public void juegue() {
		task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Score: "+ark.getScore1());
				System.out.println("Score2: "+ark.getScore2());
				ark.juegue(width, ark.getPlataforma().get(0).getY());
				
				if(ark.gano()) {
					mensaje();
					cancel();
				}
				if(!ark.isVivo()) {
					ark.estatico(height);
					mensaje();
					cancel();
				}
				if(ark.getPoder() && ark.getBola().getY()+ ark.getBola().getTamY() >= ark.getPlataforma().get(0).getY()) {
					ark.estatico(0);
					cancel();
					ark.setPoder(false);
				}
			}
		};
		myTimer.scheduleAtFixedRate(task,0,7);
	}
	
	public void pausar() {
		if(pausa) {
			pausa = false;
			if(ark.getBola().isInAire()) 
				juegue();
				
		}else {
			pausa = true;
			if(ark.getBola().isInAire()) 
				task.cancel();	
		}
		
	}
	
	public int getScore() {
		return ark.getScore1();
	}
	
	private void mensaje() {
		if(jugadores == 1) {
			if(ark.gano()) JOptionPane.showMessageDialog(this, "Ganaste!");
			if(ark.perdio(0,ark.getPlataforma().get(0))) JOptionPane.showMessageDialog(this, "Perdiste!");
		}else {
			if(ark.perdio(0,ark.getPlataforma().get(0))) {
				ark.eliminarJugador(0);	
				
			}if(ark.perdio(1,ark.getPlataforma().get(1))) {
				ark.eliminarJugador(1);	
			}
		}
	}
	
	
	@Override
	public void run() {
		while(true) {
			this.repaint();
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void reiniciar() {
		task.cancel();
		pausa = false;
		keysDown=new ArrayList<Integer>();
		String c,c2=null;
		c = ark.getPlataforma().get(0).getColor();
		if(jugadores == 2) {c2 = ark.getPlataforma().get(1).getColor();}
		ark = new ArkaPOOB(jugadores);
		colores(c,c2);
		hilo= new Thread(this);
		myTimer = new Timer();
		hilo.start();
		prepareElementos();
		prepareAcciones();
		setBackground(Color.BLACK);
	}

}
