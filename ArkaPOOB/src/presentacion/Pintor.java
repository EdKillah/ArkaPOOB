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
import java.io.*;


import javax.swing.*;

public class Pintor extends myPanel implements ActionListener, KeyListener, Runnable{
	//private myPanel myPanel;	
	private ArkaPOOB ark;
	private ArrayList<Integer> keysDown;
	private String colorNave;
	private Thread hilo;
	private Timer myTimer;
	private TimerTask task;
	private int width;
	private int height;
	private boolean pausa;
	private int jugadores;
	private boolean usaRosa;
	private boolean usaAzul;
	private boolean usaAmarillo;
	private boolean usaNaranja;
	private boolean usaNegro;
	
	public Pintor(int w, int h , int jugadores,String colorNave, boolean rosa, boolean azul, boolean amarillo, boolean naranja, boolean negro) {
		this.jugadores = jugadores;
		pausa = false;
		keysDown=new ArrayList<Integer>();
		usaRosa = rosa;
		usaAzul = azul;
		usaAmarillo = amarillo;
		usaNaranja = naranja;
		usaNegro = negro;
		this.colorNave = colorNave;
		ark = new ArkaPOOB(jugadores,colorNave,rosa,azul,amarillo,naranja,negro);
		width = w;
		height = h;
		hilo= new Thread(this);
		myTimer = new Timer();
		hilo.start();
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
		if(nave.getVidas() >0) g.drawImage(nave.getImagen(), nave.getX(), nave.getY(),nave.getWidth(),nave.getHeight(), this);
		if(jugadores == 2)  {
			Plataforma nave2 = ark.getPlataforma().get(1);
			if(nave2.getVidas() >0) g.drawImage(nave2.getImagen(), nave2.getX(), nave2.getY(),nave2.getWidth(),nave2.getHeight(), this);
		}
		
	   // g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.RED);
		g.drawString(" score: " + ark.getPlataforma().get(0).getScore() , width/2-100, height-70); //ark.encuentreTanque(1).getName()+
		if (ark.getJugadores() == 2) {
			g.drawString(" score2: " + ark.getPlataforma().get(1).getScore(), width/2+100, height-70); //ark.encuentreTanque(2).getName()
		}
		
		Sorpresa poder = ark.getSorpresa();
		if(poder!= null) {
			Image img = new ImageIcon(getClass().getResource("/imagenes/sorpresa_"+poder.getTipo()+".gif")).getImage();
			g.drawImage(img, poder.getX(),poder.getY() ,poder.getWidth(),poder.getHeight(), this);
		}
		
		Bola bola = ark.getBola();
		if(bola.isVivo()) {
			Image img =  new ImageIcon(getClass().getResource("/imagenes/pelota.png")).getImage();
			g.drawImage(img, bola.getX(),bola.getY() ,Bola.getTamX(),Bola.getTamY(), this);			//(int)d.getHeight()-125
		}
		int pos =750;
		for(int j=0;j<ark.getPlataforma().get(0).getVidas();j++) {
			Plataforma vida = new Plataforma(pos-30,500,40,15);
			vida.setColor(ark.getPlataforma().get(0).getColor());
			if(vida != null )g.drawImage(vida.getImagen(), vida.getX(),vida.getY() ,vida.getWidth(),vida.getHeight(), this);
			pos-=40;
		}
		
		if(jugadores == 2) {
			pos = 0;
			for(int j=0;j<ark.getPlataforma().get(1).getVidas();j++) {
				Plataforma vida = new Plataforma(30+pos,500,40,15);
				vida.setColor(ark.getPlataforma().get(1).getColor());
				if(vida != null )g.drawImage(vida.getImagen(), vida.getX(),vida.getY() ,vida.getWidth(),vida.getHeight(), this);
				pos+=40;
			}
		}
	}
	
	public void colores(String color1,String color2) {
		ArrayList<Plataforma> naves = ark.getPlataforma();
		if(color1 != null) naves.get(0).setColor(color1);
		if(color2 != null) naves.get(1).setColor(color2);
		//ark.prepareVidas();
	}
	
	
	public void prepareBloques(boolean rosa, boolean azul, boolean amarillo, boolean naranja, boolean negro) {
		usaRosa = rosa;
		usaAzul = azul;
		usaAmarillo = amarillo;
		usaNaranja = naranja;
		usaNegro = negro;
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
			if(ark.getBola().getX()>ark.getPlataforma().get(0).getWidth()/2 && !ark.getBola().isInAire()&& !pausa) {
				if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(0))) {
					ark.getBola().setX(ark.getPlataforma().get(0).getX()+ark.getPlataforma().get(0).getWidth()/2-15);
				}
			}
			if(jugadores == 2 && ark.getBola().getX()>ark.getPlataforma().get(1).getWidth()/2 && !ark.getBola().isInAire()&& !pausa) {
				if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(1))) {
					ark.getBola().setX(ark.getPlataforma().get(1).getX()+ark.getPlataforma().get(1).getWidth()/2-15);
				}
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
		if(ark.getPlataforma().get(0)!=null) {
			if(keysDown.contains(new Integer(KeyEvent.VK_LEFT))) {
				if (ark.getPlataforma().get(0).getX()>15&& !pausa) {  
					ark.getPlataforma().get(0).moverX(2);
				}
				if(ark.getBola().getX()>ark.getPlataforma().get(0).getWidth()/2 && !ark.getBola().isInAire()&& !pausa) {
					if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(0))) {
						ark.getBola().setX(ark.getPlataforma().get(0).getX()+ark.getPlataforma().get(0).getWidth()/2-15);
					}
				}
			}
			
			if(keysDown.contains(new Integer(KeyEvent.VK_RIGHT))) {
				if (ark.getPlataforma().get(0).getX()<width-ark.getPlataforma().get(0).getWidth()&& !pausa) {
					ark.getPlataforma().get(0).moverX(1);
				}
				
				if(ark.getBola().getX()+15<width-ark.getPlataforma().get(0).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
					if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(0))) {
						ark.getBola().setX(ark.getPlataforma().get(0).getX()+ark.getPlataforma().get(0).getWidth()/2-15);
					}
				
			}
			if(keysDown.contains(new Integer(KeyEvent.VK_SPACE))) {
				if(!ark.getBola().isInAire() && !pausa) {
					ark.getBola().setInAire(true);
					juegue();
				}
			}
		}
		if(jugadores == 2) {
			if(keysDown.contains(new Integer(KeyEvent.VK_A))) {
				if (ark.getPlataforma().get(1).getX()>15&& !pausa)  {
					ark.getPlataforma().get(1).moverX(2);
					ark.getPlataforma().get(0).isChocado(ark.getPlataforma().get(1));
				}
				if(ark.getBola().getX()>ark.getPlataforma().get(1).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
					if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(1))) {
						ark.getBola().setX(ark.getPlataforma().get(1).getX()+ark.getPlataforma().get(1).getWidth()/2-15);
					}
			}
			
			if(keysDown.contains(new Integer(KeyEvent.VK_D))) {
				if (ark.getPlataforma().get(1).getX()<width-ark.getPlataforma().get(1).getWidth()&& !pausa) {
					ark.getPlataforma().get(1).moverX(1);
					ark.getPlataforma().get(0).isChocado(ark.getPlataforma().get(1));
				}
				
				if(ark.getBola().getX()+15<width-ark.getPlataforma().get(1).getWidth()/2 && !ark.getBola().isInAire()&& !pausa)
					if(ark.getBola().getUltimo().equals(ark.getPlataforma().get(1))) {
						ark.getBola().setX(ark.getPlataforma().get(1).getX()+ark.getPlataforma().get(1).getWidth()/2-15);
					}
				
			}
			if(keysDown.contains(new Integer(KeyEvent.VK_SPACE))) {
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
				//System.out.println("Score: "+ark.getPlataforma().get(0).getVidas());
				//System.out.println("Score2: "+ark.getPlataforma().get(1).getVidas());
				ark.juegue(width, ark.getPlataforma().get(( ark.getPlataforma().get(0)!=null?0:1)).getY());
				
				if(ark.gano()) {
					mensaje();
					cancel();
				}
				if(!ark.isVivo()) {
					ark.estatico(height);
					mensaje();
					ark.prepareBola();
					cancel();
				}
				if(!ark.getBola().isInAire() && ark.getBola().isInNave()) {
					cancel();
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
	
	private void mensaje() {
		if(jugadores == 1) {
			if(ark.gano()) JOptionPane.showMessageDialog(this, "Ganaste!");
			if(ark.perdio(ark.getPlataforma().get(0))) { 
				JOptionPane.showMessageDialog(this, "GAME OVER!");
				pausa = true;
			}
		}else {
			if(ark.getPlataforma().get(0).getVidas() <= 0 && ark.getPlataforma().get(1).getVidas() <= 0) {
				String ganador = "";
				if(ark.getPlataforma().get(0).getScore() > ark.getPlataforma().get(1).getScore())
					ganador = "Gana jugador Jugador 1";
				else if(ark.getPlataforma().get(0).getScore() < ark.getPlataforma().get(1).getScore())
					ganador = "Gana jugador Jugador 2";
				else ganador = "Empate";
				JOptionPane.showMessageDialog(this, ganador);
				pausa = true;
				
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
		ark = new ArkaPOOB(jugadores,colorNave,usaRosa, usaAzul, usaAmarillo, usaNaranja, usaNegro);
		//colores(c,c2);
		hilo= new Thread(this);
		myTimer = new Timer();
		hilo.start();
		prepareAcciones();
		setBackground(Color.BLACK);
	}
	
	public void setJuego(ArkaPOOB ar) {
		pausa = false;
		keysDown=new ArrayList<Integer>();
		ark = ar;
		jugadores = ark.getJugadores();
		hilo= new Thread(this);
		myTimer = new Timer();
		hilo.start();
	}
	
	public void guardar(File file) throws ArkaPoobException {
		task.cancel();
		hilo= new Thread(this);
		ark.guardar(file);
	}

}
