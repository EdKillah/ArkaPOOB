package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BloqueRojo extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isChocado;
	private final int puntos = 100;
	private ImageIcon imagen;
	
	public BloqueRojo(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		prepareImagen();
	}
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_rojo.png"));
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
	
}
