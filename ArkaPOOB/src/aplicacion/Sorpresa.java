package aplicacion;

import java.awt.*;
import javax.swing.ImageIcon;


public abstract class Sorpresa implements Elemento{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isChocado;
	private ImageIcon imagen;
	
	public Image getImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/sorpresa_especial.gif"));
		return imagen.getImage();
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
		this.y += y;
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
	public abstract String getTipo();
	public abstract void setTipo(String type);
}
