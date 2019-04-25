package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BloqueGris extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private String tipo;
	private boolean isChocado;
	private final int puntos = 0; //confirmar si deberia tener puntos o no
	private ImageIcon imagen;
	
	public BloqueGris(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		tipo = "gris";
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_gris.png"));
	}
	
	@Override
	public boolean isChocado(Bola bola) {
        //isChocado = bola.getBounds().intersects(this.getBounds());
        return false;
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
