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
		this.y += y;
	}
	
	public void setTipo(String type) {
		tipo = type;
	}
	
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return borde;
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
	
	public void muevase() {
		setY(1);
	}
	
	public abstract boolean isChocado(Plataforma nave);
	//public String getTipo() {
	//	return tipo;
	//}
	public abstract String getTipo();
	//public abstract void setTipo(String type);
}
