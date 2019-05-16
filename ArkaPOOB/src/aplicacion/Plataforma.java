package aplicacion;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;


/**
 * Clase que crea la plataforma del juego la cual es la que representa al jugador.
 * @author Jimenez Eduard- Murillo Carlos.
 *
 */
public class Plataforma implements Elemento,Serializable{
	private int x;
	private int y;
	private int vidas;
	private int balas;
	private String color;
	private int width;
	private int height;
	private ImageIcon imagen;
	private boolean poderActivo;
	private boolean vivo;
	private int contador;
	
	
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
		this.vivo = true;
		contador = 0;
		poderActivo = false;
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
		this.x = x;
	}
	
	public void moverX(int x) {
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

	
	public Image getImagen() {
		return imagen.getImage();
	}
	
	public void setVIvo(boolean a) {
		this.vivo = a;
		
	}
	
	public boolean isVivo() {
		return vivo;
		
	}
	
	public void setPoderActivo(boolean poder) {
		poderActivo = poder;
	}
	
	public boolean isPoderActivo() {
		return poderActivo;
	}
	
	
	public void hagaTalCosa(ArkaPOOB ark) {
		if(contador>3) {
			setPoderActivo(false);
		}
		else {
			contador++;
			ark.prepareBola();
		}
	}
	
	
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return borde;
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
	
	
	
	public void activeSorpresa(String tipo,ArkaPOOB ark) {
		if(tipo.equals("Pegajosa")) {
			if(ark.getPoder() && ark.getBola().getY()+ ark.getBola().getTamY() + height >= getY()-1) {
				this.setPoderActivo(true);
				//ark.prepareBola(); //this
				//ark.setPoder(false);
			}
		}
	}
	
	public boolean isChocado(Bola bola) {
        boolean isChocado = bola.getBounds().intersects(this.getBounds());
        return isChocado;
    }
	
	public boolean isChocado(Plataforma plat) {
		if(plat.getX() <= this.getX() && plat.getX()+plat.getWidth()+1>=this.getX()) {
			this.setX(this.getX()+2);
			return true;
		}
		if(this.getX() <= plat.getX() && this.getX()+this.getWidth()+1>=plat.getX()) {
			plat.setX(plat.getX()+2);
			return true;
		}
        return false;
    }


}