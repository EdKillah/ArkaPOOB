package aplicacion;

import java.awt.*;
import javax.swing.*;

public class Bloque {
	private int x;
	private int y;
	private int golpes;
	private int puntos;
	private int width;
	private int height;
	private ImageIcon imagen;
	
	
	public Bloque(int x, int y, int width, int height, int puntos) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.puntos = puntos;
		prepareImagen();
	}
	
	public void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_rojo.png"));
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
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
	public void setPuntos(int puntos) {
		this.puntos = puntos;
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
	
	
}
