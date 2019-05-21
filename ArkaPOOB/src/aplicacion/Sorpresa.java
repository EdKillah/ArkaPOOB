package aplicacion;

import java.awt.*;
import java.io.Serializable;



public abstract class Sorpresa implements Elemento,Serializable{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isChocado;
	private String tipo;
	
	/**
	 * Metodo que obtiene la posicion en x
	 * @return x
	 */
	@Override
	public int getX() {
		return x;
	}
	
	/**
	 * Metodo que actualiza la posicion en x
	 * @param x
	 */
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Metodo que obtiene la posicion en y
	 * @return y
	 */
	@Override
	public int getY() {
		return y;
	}
	
	/**
	 * Metodo que actualiza la posicion en y
	 * @param y
	 */
	@Override
	public void setY(int y) {
		this.y += y;
	}
	
	/**
	 * Metodo que actualiza el tipo de sopresa
	 * @param type
	 */
	public void setTipo(String type) {
		tipo = type;
	}
	
	/**
	 * Metodo que obtiene la figura del objeto
	 * @return rectangle
	 */
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return borde;
    }   
	
	/**
	 * Metodo que obtiene el ancho del la sorpresa
	 * @return width
	 */
	@Override
	public int getWidth() {
		return width;
	}
	
	/**
	 * Metodo que actualiza el ancho del la sorpresa
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Metodo que obtiene el alto del la sorpresa
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Metodo que actualiza si es chocado o no
	 * @param isChocado
	 */
	public void setIsChocado(boolean isChocado) {
		this.isChocado = isChocado;
	}
	
	/**
	 * Metodo que actualiza el alto de la sorpresa
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Metodo que mueve la sorpresa
	 */
	public void muevase() {
		setY(1);
	}
	
	public abstract boolean isChocado(Jugador nave);
	public abstract String getTipo();
}
