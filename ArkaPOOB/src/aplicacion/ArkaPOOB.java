package aplicacion;

import java.util.*;

/**
 * Clase principal del paquete aplicación y del juego en general,
 * es la encargada de almacenar los elementos e interactuar con ellos.
 * @author Jimenez Eduard- Murillo Carlos
 *
 */

public class ArkaPOOB {
	private ArrayList<ArrayList<Bloque>> bloques;
	private ArrayList<Plataforma> vidas;
	private Plataforma nave;
	private Bola bola;
	private Bloque ultimoBloque;
	private int score;
	
	
	/**
	 * Crea una instancia del tablero de juego
	 * 
	 */
	public ArkaPOOB() {
		vidas = new ArrayList<Plataforma>();
		score=0;
		prepareBloques();
		ultimoBloque = bloques.get(0).get(0);
		prepareNave();
		prepareBola();
		prepareVidas();
	}
	
	
	/**
	 * Metodo encargado de activar el movimiento de la bola. 
	 * @param width
	 * @param height
	 */
	
	public void juegue(double width, double height) {
		if(bola.isVivo()) {
			bola.muevase(width,height);
			for(int i=0;i<bloques.size();i++) {
				for(int j=0;j<bloques.get(i).size();j++) {
					if(bloques.get(i).get(j).isChocado(bola)) {
						score+=bloques.get(i).get(j).getPuntos();
						adicioneVida(bloques.get(i).get(j));
						reemplazarBloque(bloques.get(i).get(j),i,j);
						
						//bloques.get(i).remove(j);
					}
				}
			}
		}
		
	}
	
	
	/**
	 * Metodo encargado de eliminar el bloque dado su posición en el arreglo de Bloques.
	 * @param i
	 * @param j
	 */
	public void eliminarBloque(int i,int j) {
		bloques.get(i).remove(j);
	}
	
	
	/**
	 * Metodo que reemplaza el bloque negro por el ultimo bloque chocado.
	 * @param bloque
	 * @param i
	 * @param j
	 */
	public void reemplazarBloque(Bloque bloque, int i, int j) {
		if(bloque.getTipo().equals("negro")) {
			ultimoBloque.setX(bloque.getX());
			ultimoBloque.setY(bloque.getY());
			//ultimoBloque.setTipo(bloque.getTipo());
			bloques.get(i).set(j,ultimoBloque);
			ultimoBloque = bloques.get(i).get(j);
			//eliminarBloque(i,j);
		}
		else {
			ultimoBloque = bloques.get(i).get(j);
			eliminarBloque(i,j);
		}
	}
	
	/**
	 * Metodo que determina la creación de una nueva bola si esta fue eliminada. 
	 * @param height
	 */
	public void estatico(double height) {
		borrarVidas((int)height);
		if(vidas.size()>0)
			prepareBola();
			bola.setInAire(false);
	}
	
	
	/**
	 * Metodo que prepara la plataforma del juego dandole unos atributos iniciales.
	 */
	public void prepareNave() {
		nave = new Plataforma(750/2,480,"red",90,20);
	}
	
	/**
	 * Metodo que adiciona una vida si el bloqueAmarillo es golpeado.
	 * @param bloque
	 */
	private void adicioneVida(Bloque bloque) {
		if(bloque.getTipo().equals("amarillo")) {
			Plataforma vida = vidas.get(vidas.size()-1);
			vidas.add(new Plataforma(vida.getX()+70,vida.getY(),vida.getColor(),vida.getWidth(),vida.getHeight()));
		}
	}
	
	
	/**
	 * Metodo que prepara las vidas del jugador. 
	 */
	public void prepareVidas() {
		Plataforma vida;
		int pos =0;
		for(int i=0;i<3;i++) {
			vida = new Plataforma(30+pos,500,nave.getColor(),40,15); //aqui podemos sacarle provecho al color que se le pasa
			vidas.add(vida);
			pos+=40;
		}
	}
	
	
	public boolean isVivo() {
		return bola.isVivo();
	}
	
	/**
	 * Metodo encargado de eliminar las vidas del arreglo de vidas dependiendo de si se cumplen las condiciones dadas.
	 * @param height
	 */
	public void borrarVidas(int height) {
		if (bola.getY()<=height) {
			if(vidas.size()>0)
				vidas.remove(vidas.size()-1);
		}
	}
	
	public int getScore() {
		return score;
	}
	
	/**
	 * Metodo que determina si el jugador gano, cumpliendose las condiciones necesarias. 
	 * Estas son que se elimine un bloque rosa o que se eliminen todos los bloques(que se pueden eliminar)
	 * @return true si gano, false dlc.
	 */
	public boolean gano() {
		int bloq=0;
		for(int i=0;i<bloques.size();i++) 
			if(bloques.get(i).size()>0)
				bloq++;
		if(bloq == 0 || ultimoBloque.getTipo().equals("rosa")) return true;
		else return false;
	}
	
	/**
	 * Metodo que determina si el jugador perdio.
	 * El jugador pierde si esta no cuenta con vidas suficientes (mayor a 0).
	 * @return
	 */
	public boolean perdio() {
		if(getVidas().size()==0) return true;
		return false;
	}
	
	
	public ArrayList<Plataforma> getVidas(){
		return vidas;
	}
	
	
	/**
	 * Metodo que prepara la bola dandole unos atributos iniciales.
	 */
	public void prepareBola(){
		bola = new Bola(nave.getX()+nave.getWidth()/2-15,nave.getY()-nave.getHeight(),nave,45,1,45,this);
	}
	
	
	/**
	 * Metodo que prepara los bloques iniciales del juego, distribuyendolos de una manera especifica.
	 */
	public void prepareBloques() {
		Bloque bloque;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=30,posX=0;
		int step = 70;
		for(int i=0;i<3;i++) {
			posX=20;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<10;j++) {
				if(i==0) 
					bloque = new BloqueGris(posX, posY,70,35);
				else if(i==1) {
					if(j==5 || j == 7)
						bloque = new BloqueRosa(posX,posY,70,35); //poner esto random
					else
						bloque = new BloqueVerde( posX, posY,70,35);
				}
				else {
					if(j== 9 )
						bloque = new BloqueAmarillo(posX, posY, 70,35);
					else if(j==0|| j == 7)
						bloque = new BloqueNegro(posX,posY,70,35);
					else
						bloque = new BloqueRojo( posX, posY,70,35); //75 45
				}
				blocks.add(bloque);
				posX += step;
			}
			posY += 38;
			bloques.add(blocks);
		}
		
	}


	public Plataforma getPlataforma() {
		return nave;
	}
	
	public Bola getBola() {
		return bola;
	}

	public ArrayList<ArrayList<Bloque>> getBloques() {
		return bloques;
	}
	
	public void setBloques(ArrayList<ArrayList<Bloque>> bloques) {
		this.bloques = bloques;
	}
}