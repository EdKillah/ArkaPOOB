package aplicacion;


import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Bola implements Elemento, Serializable{
	private int x;
	private int y;
	private double dx;
	private double dy;
	private static final int TAMX=21;
	private static final int TAMY=21;
	private Jugador nave;
	private Jugador nave2;
	private ArkaPOOB ark;
	private int velocidad;
	private int damage;
	private boolean vivo;
	private double angulo;
	private boolean isInAire;
	private boolean isChocado;
	private ImageIcon imagen;
	private Jugador ultimo;
	
	public Bola(int x, int y, Jugador nave,Jugador nave2, int velocidad, int damage,int angulo, ArkaPOOB ark) {
		dy = 1;
		dx = 1;
		vivo = true;
		isChocado = false;
		isInAire = false;
		this.ark = ark;
		this.x = x;
		this.y = y; //mirar como poner esto para que sea extensible 
		this.nave = nave;
		this.nave2 = nave2;
		this.velocidad = velocidad;
		this.damage = damage;
		this.angulo = angulo;
		this.ultimo = nave;
		
	}
	
	public void activeSorpresa(String type) {
		
		if(type=="minus")
			moverX("-");
		else
			moverX("+");
	}
	
	
	public void moverX(String type) {
		if(type.equals("-")) {
			dx = 2;
			dy = 2;
		}
		else {
			dx = 0.5;
			dy = 0.5;
		}
		
			
	}
		
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), TAMX, TAMY);
        return borde;
    }   
	
	public boolean isChocado(Bloque bloque) {
        isChocado = bloque.getBounds().intersects(this.getBounds());
        return isChocado;
    }
	
	public void setIsChocado(boolean choque) {
		isChocado = choque;
	}
	
	public Bloque confirmeChoque() { //otra forma de hacerlo es enviarle desde arkaPOOB el bloque con el cual choco y con eso no habria que hacer este
		for(int i=0;i<ark.getBloques().size();i++) {
			for(int j=0;j<ark.getBloques().get(i).size();j++) {
				if(ark.getBloques().get(i).get(j).isVivo() && this.isChocado(ark.getBloques().get(i).get(j))) {
					return ark.getBloques().get(i).get(j);	
				}
			}
		}
		return null;
	}
	
	public void muevase(double widthT, double heightT){ 
		x+=dx;
		y+=dy;
		if(x<=20){
			x=20;
			dx=-dx;
		}
		
		if(x + TAMX>=widthT-10){
			x=(int)widthT-10 - TAMX;
			dx=-dx;
		}
		
		if(y<0){
			y=0;
			dy=-dy;
		}
		
		if(y + TAMY>heightT){
			
			if(nave.isChocado(this)) {
				y=(int)heightT-TAMY;
				dy=-(dy);
				setUltimo(nave);
			}
			else if(nave2 != null && nave2.isChocado(this) &&  nave2.getVidas()>=0) {
				y=(int)heightT-TAMY;
				dy=-(dy);
				setUltimo(nave2);
			}/*
			else if(ark.getMaquina()!=null && ark.getMaquina().isChocado(this)) {
				y=(int)heightT-TAMY;
				dy=-(dy);
			}*/
			
			/*
			if(isInNave()) {
				//System.out.println("EXTA GOLPEANDO LA NAVE");
				y=(int)heightT-TAMY-10;
				dy=-(dy);
				//dy=-dy;
			}
			*/
			else setVivo(false);
		}
		
		Bloque b = confirmeChoque();
		if(b!=null) {
			if(this.getX()<=b.getX() || b.getX()+b.getWidth()<=this.getX()+1) {
				dx=-dx;
			}
			else dy=-dy;
		}
		
	}
	
	
	public boolean isVivo() {
		return vivo;
	}
	
	public void setVivo(boolean vida) { //mirar si se cambia el nombre
		vivo = vida; 
	}
	
	public boolean isInNave() {
		boolean ban = false;
		if(nave.getVidas()>=0  && getX()>=nave.getX()-15 && getX()<=nave.getX()+nave.getWidth()) {
			setUltimo(nave);
			ban = true;
		}else if(nave2 != null && nave2.getVidas()>=0 && getX()>=nave2.getX()-15 && getX()<=nave2.getX()+nave2.getWidth()) {
			ban = true;
			setUltimo(nave2);
		}
		return ban;
	}
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	/*
	public void moverX(int x) {
		if(x==2)
			this.x -= 20;
		else
			this.x += 20;
	}
	*/
	
	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	public void setUltimo(Jugador a) {
		this.ultimo = a;
	}
	
	public Jugador getUltimo() {
		return ultimo;
	}
	
	public Jugador getPlataforma() {
		return ultimo;
	}
	
	public Jugador getNave() {
		return nave;
	}

	public void setNave(Jugador nave) {
		this.nave = nave;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
	
	public boolean isInAire() {
		return isInAire;
	}
	
	public void setInAire(boolean enAire) {
		isInAire = enAire;
	}


	public static int getTamX() {
		return TAMX;
	}

	public static int getTamY() {
		return TAMY;
	}
	
	@Override
	public int getWidth() {
		return TAMX;
	}
	
	@Override
	public String toString(){
		return x+" "+y+ " "+nave+" "+nave2;
	}
}
