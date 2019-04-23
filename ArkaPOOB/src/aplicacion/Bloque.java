package aplicacion;

import java.awt.*;
import javax.swing.*;

public abstract class Bloque implements Elemento{
	private int x;
	private int y;
	private int golpes;
	private final int puntos = 100;
	private int width;
	private int height;
	private boolean isChocado;
	private ImageIcon imagen;
	
	/*
	public Bloque(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isChocado = false;
		//prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_rojo.png"));
	}
	*/
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	public int getGolpes() {
		return golpes;
	}
	
	public void setGolpes(int golpes) {
		this.golpes = golpes;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setIsChocado(boolean isChocado) {
		this.isChocado = isChocado;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), 70, 35);
        return borde;
    }   
	
	public abstract boolean isChocado(Bola bola);
	
	
	
	
}
