package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bola {
	private int x;
	private int y;
	private Plataforma nave;
	private int velocidad;
	private int damage;
	private double angulo;
	private boolean isInAire;
	private ImageIcon imagen;

	
	public Bola(int x, int y, Plataforma nave, int velocidad, int damage,int angulo) {
		this.x = x;
		this.y = y;
		this.nave = nave;
		this.velocidad = velocidad;
		this.damage = damage;
		this.angulo = angulo;
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/pelota.png"));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x==2)
			this.x -= 20;
		else
			this.x += 20;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
	
	public Image getImagen() {
		return imagen.getImage();
	}
}
