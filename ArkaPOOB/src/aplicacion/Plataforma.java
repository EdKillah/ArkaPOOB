package aplicacion;

import java.awt.Image;

import javax.swing.*;

public class Plataforma {
	private int x;
	private int vidas;
	private int balas;
	private String color;
	private int width;
	private int height;
	private ImageIcon imagen;
	
	public Plataforma(int x, String color, int width, int height) {
		this.x = x;
		this.color = color;
		this.width = width;
		this.height = height;
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/Arkanoid_Vaus.gif"));
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

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getBalas() {
		return balas;
	}

	public void setBalas(int balas) {
		this.balas = balas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImagen() {
		return imagen.getImage();
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	
	
}