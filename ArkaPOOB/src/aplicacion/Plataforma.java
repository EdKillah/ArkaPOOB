package aplicacion;

import java.awt.Image;

import javax.swing.*;

/**
 * Clase que crea la plataforma del juego la cual es la que representa al jugador.
 * @author Jimenez Eduard- Murillo Carlos.
 *
 */
public class Plataforma implements Elemento{
	private int x;
	private int y;
	private int vidas;
	private int balas;
	private String color;
	private int width;
	private int height;
	private ImageIcon imagen;
	
	
	/**
	 * Crea la instancia de la plataforma.
	 * @param x la posicion en el eje x.
	 * @param y la posicion en el eje y.
	 * @param color el color que tendrá la plataforma. 
	 * @param width el ancho de la plataforma. 
	 * @param height el alto de la plataforma.
	 */
	public Plataforma(int x,int y, int width, int height) {
		this.x = x-30;
		this.y = y;
		this.color = "blue";
		this.width = width;
		this.height = height;
		prepareImagen();
	}
	
	/**
	 * Metodo que prepara la imagen de la plataforma dado su color. 
	 */
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/vaus_"+color+".gif"));
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		if(x==2)
			this.x -= 20;
		else
			this.x += 20;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getBalas() {
		return balas;
	}

	public void setBalas(int balas) {
		this.balas = balas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		actualizeImagen();
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

	@Override
	public Image getImagen() {
		return imagen.getImage();
	}
	
	/**
	 * Metodo que actualiza la imagen de la plataforma dependiendo del color que se le asigno.
	 */
	private void actualizeImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/vaus_"+color+".gif"));
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
}