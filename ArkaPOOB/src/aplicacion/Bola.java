package aplicacion;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bola implements Elemento{
	private int x;
	private int y;
	private double dx;
	private double dy;
	private static final int TAMX=21;
	private static final int TAMY=21;
	private Plataforma nave;
	private Plataforma nave2;
	private ArkaPOOB ark;
	private int velocidad;
	private int damage;
	private boolean vivo;
	private double angulo;
	private boolean isInAire;
	private boolean isChocado;
	private ImageIcon imagen;
	private int ultimo;
	
	public Bola(int x, int y, Plataforma nave,Plataforma nave2, int velocidad, int damage,int angulo, ArkaPOOB ark) {
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
		this.ultimo = 0;
		
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
				if(this.isChocado(ark.getBloques().get(i).get(j))) {
					return ark.getBloques().get(i).get(j);	
				}
			}
		}
		return null;
	}
	
	public void muevase(double widthT, double heightT){ //tienen la T porque significa tablero.
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
			if(isInNave()) {
				y=(int)heightT-TAMY;
				dy=-dy;
			}
			else setVivo(false);
			//System.out.println(isVivo());
		}
		
		Bloque b = confirmeChoque();
		if(b!=null) {
			//y=b.getY()+b.getHeight()-3;//+TAMY //+b.getHeight())/2
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
		if(getX()>=nave.getX()-15 && getX()<=nave.getX()+nave.getWidth()) {
			setUltimo(0);
			ban = true;
		}if(nave2!=null && getX()>=nave2.getX()-15 && getX()<=nave2.getX()+nave2.getWidth()) {
			ban = true;
			setUltimo(1);
		}
		return ban;
	}
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public void setX(int x) {
		if(x==2)
			this.x -= 20;
		else
			this.x += 20;
	}
	
	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	public void setUltimo(int a) {
		this.ultimo = a;
	}
	
	public int getUltimo() {
		return ultimo;
	}
	
	
	public Plataforma getNave() {
		return nave;
	}

	public void setNave(Plataforma nave) {
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
		return 25;
	}
}
