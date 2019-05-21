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
	
	/**
	 * Metodo que obtiene los golpes
	 * @return
	 */
	public int getGolpes() {
		return golpes;
	}
	
	/**
	 * Metodo que actualiza los bloques
	 * @param golpes
	 */
	public void setGolpes(int golpes) {
		this.golpes = golpes;
	}
	
	/**
	 * Metodo que actualiza los puntos
	 * @return puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Metodo que actualiza si esta vivo el bloque
	 * @param a
	 */
	public void setVivo(boolean a) {
		this.vivo = a;
	}
	
	/**
	 * Metodo que revisa si esta vivo el bloque
	 * @return vivo
	 */
	public boolean isVivo() {
		return vivo;
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	/**
	 * Metodo que actualiza el ancho del bloque
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Metodo que obtiene la altura del bloque
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Metodo que actualiza si es chocado
	 * @param isChocado
	 */
	public void setIsChocado(boolean isChocado) {
		this.isChocado = isChocado;
	}
	
	/**
	 * Metodo que actualiza la altura del bloque
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/** 
	 * Metodo que obtiene el area del bloque
	 * @return borde
	 */
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), 70, 35);
        return borde;
    }   

	public abstract boolean isChocado(Bola bola);
	public abstract String getTipo();
	public abstract void setTipo(String type);
	
	
	
}
