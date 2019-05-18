package aplicacion;

import java.awt.*;
import java.io.Serializable;


/**
 * clase abstracta Bloque es la cual los demás bloques heredan de ella. 
*/
public abstract class Bloque implements Elemento, Serializable{
	private int x;
	private int y;
	private int golpes;
	private String tipo;
	private final int puntos = 100;
	private int width;
	private int height;
	private boolean isChocado;
	private boolean vivo;
	
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
	
	public void setVivo(boolean a) {
		this.vivo = a;
	}
	
	public boolean isVivo() {
		return vivo;
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
	public abstract String getTipo();
	public abstract void setTipo(String type);
	
	
	
}
