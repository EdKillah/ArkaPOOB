package aplicacion;

import java.awt.*;
import java.io.Serializable;
import javax.swing.*;


/**
 * Clase que crea la plataforma del juego la cual es la que representa al jugador.
 * @author Jimenez Eduard- Murillo Carlos.
 *
 */
public class Jugador implements Elemento,Serializable{
	private int x;
	private int y;
	private int vidas;
	private int balas;
	private String color;
	private int width;
	private int height;
	private boolean poderActivo;
	private boolean vivo;
	private int contador;
	private int score;
	private String tipo;
	private String nombre;
	
	
	/**
	 * Crea la instancia de la plataforma.
	 * @param x la posicion en el eje x.
	 * @param y la posicion en el eje y.
	 * @param color el color que tendr� la plataforma. 
	 * @param width el ancho de la plataforma. 
	 * @param height el alto de la plataforma.
	 */
	public Jugador(int x,int y, int width, int height) {
		this.x = x-30;
		this.y = y;
		color = "blue";
		this.width = width;
		this.height = height;
		this.vivo = true;
		contador = 0;
		poderActivo = false;
		score = 0;
		vidas  = 3;
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
	
	/**
	 * Metodo que mueve el jugador
	 * @param x
	 */
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
	
	/**
	 * Metodo que obtiene las vidas del jugador
	 * @return vidas
	 */
	public int getVidas() {
		return vidas;
	}

	/**
	 * Metodo que actualiza las vidas
	 * @param vidas
	 */
	public void setVidas(int vidas) {
		this.vidas += vidas;
	}
	
	/**
	 * Metodoq que asigna el numero de vidas
	 * @param vidas
	 */
	public void setVida(int vidas) {
		this.vidas = vidas;
	}

	/**
	 * Metodo que obtiene las balas
	 * @return balas
	 */
	public int getBalas() {
		return balas;
	}

	/**
	 * Metodo que actualiza las balas
	 * @param balas
	 */
	public void setBalas(int balas) {
		this.balas = balas;
	}
	
	/**
	 * Metodo que obtiene el color del jugador
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Metodo que actualiza el color del jugador
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
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
	
	public void setVIvo(boolean a) {
		this.vivo = a;
		
	}
	
	public boolean isVivo() {
		return vivo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String a) {
		this.tipo = a;
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
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int a) {
		score += a;
	}
	
	public void setScor(int a) {
		score = a;
	}
	
	public void setNombre(String a) {
		this.nombre =a;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo que calcula el area de este objeto
	 * @return rectangle
	 */
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return borde;
    }  
	
	/**
	 * Metodo que activa una sorpresa
	 * @param tipo
	 * @param ark
	 */
	public void activeSorpresa(String tipo,ArkaPOOB ark) {
		if(tipo.equals("pegajosa")) {
			if(ark.getPoder() && ark.getBola().getY()+ Bola.getTamY() + height >= getY()-1) {
				contador=0;
				this.setPoderActivo(true);
			}
		}
		else if(tipo.equals("chica")) {
			setWidth(getWidth()-30);
		}
		else if(tipo.equals("grande")) {
			setWidth(getWidth()+30);
		}
	}
	
	/**
	 * Metodo que mira si es chocado por la bola
	 * @param bola
	 * @return true si se choca , false dlc.
	 */
	public boolean isChocado(Bola bola) {
        boolean isChocado = bola.getBounds().intersects(this.getBounds());
        if(isChocado && isPoderActivo()) {
        	if(contador<3) {
        		bola.setY(getY());
        		bola.setInAire(false);
        		contador++;
        	}
        }
        return isChocado;
    }

	/**
	 * Metodo que mira si se choca con otro jugador
	 * @param plat
	 */
	public void isChocado(Jugador plat) {
		if(plat.getVidas() > 0 && plat.getX() <= this.getX() && plat.getX()+plat.getWidth()+1>=this.getX()) {
			this.setX(this.getX());
			int ax = this.getX(),ax2 = plat.getX();
			this.setX(ax2-2);
			plat.setX(ax+2);
		}
		else if(plat.getVidas() > 0 && this.getX() <= plat.getX() && this.getX()+this.getWidth()+1>=plat.getX()) {
			plat.setX(plat.getX());
			int ax = this.getX(),ax2 = plat.getX();
			this.setX(ax2+2);
			plat.setX(ax-2);
		}
	}
	
	@Override
	public String toString(){
		return x+" "+y+ " "+width+" "+height+" "+score+" "+color+" "+vidas+ " "+ nombre;
	}
}