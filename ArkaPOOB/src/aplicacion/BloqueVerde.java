package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BloqueVerde extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isChocado;
	private final int puntos = 200;
	private ImageIcon imagen;
	private int con; 
	
	
	public BloqueVerde(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		con=0;
		prepareImagen();
	}
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_amarillo.png"));
	}
	
	@Override
	public boolean isChocado(Bola bola) {
	    isChocado = bola.getBounds().intersects(this.getBounds());
	    if(isChocado) con++;
	    
	    if(con>1) return isChocado;
	    else return false;
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
