package aplicacion;

import java.awt.*;
import javax.swing.*;

public class Bloque implements Elemento{
	private int x;
	private int y;
	private int golpes;
	private int puntos;
	private int width;
	private int height;
	private boolean isChocado;
	private ImageIcon imagen;
	
	
	public Bloque(int x, int y, int width, int height, int puntos) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.puntos = puntos;
		isChocado = false;
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_rojo.png"));
	}
	
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
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
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


	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), 75, 45);
        return borde;
    }   
	
	public boolean isChocado(Bola bola) {
        isChocado = bola.getBounds().intersects(this.getBounds());
        return isChocado;
    }
	
	@Override
	public Image getImagen() {
		return imagen.getImage();
	}
	
	
}
