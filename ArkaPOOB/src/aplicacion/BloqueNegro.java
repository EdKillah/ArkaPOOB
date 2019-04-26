package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Clase BloqueNegro que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueNegro extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private String tipo;
	private boolean isChocado;
	private final int puntos = 600;
	private ImageIcon imagen;
	
	
	/**
	 * Constructor de la clase, que instancia la misma. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BloqueNegro(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		tipo = "negro";
		prepareImagen();
	}
	
	
	/**
	 * Metodo encargado de preparar la imagen del bloque dependiendo de su tipo.
	 */
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/bloque_negro.png"));
	}
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 */
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
