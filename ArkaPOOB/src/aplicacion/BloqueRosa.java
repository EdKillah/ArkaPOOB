package aplicacion;

import java.awt.Image;

import javax.swing.*;

public class BloqueRosa extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private String tipo;
	private boolean isChocado;
	private final int puntos = 500;
	private ImageIcon imagen;
	
	public BloqueRosa(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		tipo = "rosa";
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_rosa.png"));
	}
	
	@Override
	public boolean isChocado(Bola bola) {
        isChocado = bola.getBounds().intersects(this.getBounds());
        return isChocado;
    }
	
	@Override
	public Image getImagen() {
		return imagen.getImage();
	}
	
	@Override
	public int getPuntos() {
		return puntos;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public void setTipo(String type) {
		tipo = type;
	}
}


