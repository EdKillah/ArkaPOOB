package aplicacion;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Clase BloqueAmarillo que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueAzul extends Bloque{
	private int x;
	private int y;
	private int width;
	private int height;
	private String tipo;
	private boolean isChocado;
	private final int puntos = 300;
	private ArkaPOOB ark;
	
	/**
	 * Constructor de la clase, que instancia la misma. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BloqueAzul(int x, int y, int width, int height, ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		tipo = "azul";
		this.ark = ark;
	}
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 */
	@Override
	public boolean isChocado(Bola bola) {
        isChocado = bola.getBounds().intersects(this.getBounds());
        creeSorpresa();
        return isChocado;
    }
	
	private void creeSorpresa() {
		if(isChocado) {
			Sorpresa poder = new SorpresaPlataforma(getX(),getY(),ark);
			ark.setSorpresa(poder);
		}
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