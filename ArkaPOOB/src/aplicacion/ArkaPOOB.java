package aplicacion;

import persistencia.*;
import java.io.*;
import java.util.*;

/**
 * Clase principal del paquete aplicaci�n y del juego en general,
 * es la encargada de almacenar los elementos e interactuar con ellos.
 * @author Jimenez Eduard
 * @author Murillo Carlos
 */

public class ArkaPOOB implements Serializable{
	private ArrayList<ArrayList<Bloque>> bloques;
	private ArrayList< ArrayList<Plataforma>> vidas;
	private ArrayList<Plataforma> naves;
	private Bola bola;
	private Bloque ultimoBloque;
	private int score;
	private int score2;
	private int jugadores;
	private Sorpresa sorpresa;
	private boolean poderActivo;
	private ArkaPoobDAO dao;
	private int nivel;
	
	/**
	 * Crea una instancia del tablero de juego
	 * 
	 */
	public ArkaPOOB(int jugadores) {
		this.jugadores = jugadores;
		naves = new ArrayList<Plataforma>();
		score=0;
		score2=0;
		nivel = 0;
		prepareBloques();
		ultimoBloque = bloques.get(0).get(0);
		dao = new ArkaPoobDAO();
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
			if(getSorpresa() != null) sorpresa.muevase();
			for(int i=0;i<bloques.size();i++) {
				for(int j=0;j<bloques.get(i).size();j++) {
					activeSorpresa();
					isPlataformaActiva();
					if(bloques.get(i).get(j).isChocado(bola)) {
						if(bola.getUltimo().equals(naves.get(0))) score+=bloques.get(i).get(j).getPuntos();
						else score2+=bloques.get(i).get(j).getPuntos();
						adicioneVida(bloques.get(i).get(j));
						reemplazarBloque(bloques.get(i).get(j),i,j);
						//bloques.get(i).remove(j);
						
					}
					if(jugadores  == 2 && naves.get(0).isChocado(naves.get(1))) {
						int ax = naves.get(0).getX(),ax2 = naves.get(1).getX();
						naves.get(0).setX(ax2);
						naves.get(1).setX(ax);
					}
				}
			}
		}
		
	}
	
	
	/**
	 * Metodo encargado de eliminar el bloque dado su posici�n en el arreglo de Bloques.
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
	 * Metodo que determina la creaci�n de una nueva bola si esta fue eliminada. 
	 * @param heights
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
		if(jugadores == 1)
			naves.add(new Plataforma(750/2,480,90,20));
		if(jugadores ==2 ) {
			naves.add(new Plataforma(40,480,90,20));//750/2 - 100
			naves.add(new Plataforma(750-60,480,90,20));//750/2 + 100
		}
	}
	
	public void eliminarJugador(int i) {
		naves.get(i).setVIvo(false);
		naves.set(i,null);
	}
	
	/**
	 * Metodo que adiciona una vida si el bloqueAmarillo es golpeado.
	 * @param bloque
	 */
	private void adicioneVida(Bloque bloque) {
		if(bloque.getTipo().equals("amarillo")) {
			if(bola.getUltimo().equals(naves.get(0))) {
				Plataforma vida = vidas.get(0).get(vidas.get(0).size()-1);
				Plataforma temp = new Plataforma(vida.getX()+70,vida.getY(),vida.getWidth(),vida.getHeight());
				temp.setColor(vida.getColor());
				vidas.get(0).add(temp);
			}else {
				Plataforma vida = vidas.get(1).get(vidas.get(1).size()-1);//
				Plataforma temp = new Plataforma(vida.getX()-10,vida.getY(),vida.getWidth(),vida.getHeight());
				temp.setColor(vida.getColor());
				vidas.get(1).add(temp);
			}
		}
	}
	
	
	/**
	 * Metodo que prepara las vidas del jugador. 
	 */
	public void prepareVidas() {
		vidas = new ArrayList< ArrayList<Plataforma>>();
		Plataforma vida;
		int pos =0;
		ArrayList<Plataforma> v = new ArrayList<Plataforma>();
		for(int i=0;i<3;i++) {
			vida = new Plataforma(30+pos,500,40,15);
			vida.setColor(naves.get(0).getColor());
			v.add(vida);
			pos+=40;
		}
		vidas.add(v);
		if(jugadores == 2) {
			v = new ArrayList<Plataforma>();
			pos = 750;
			for(int i=0;i<3;i++) {
				vida = new Plataforma(pos-30,500,40,15); //aqui podemos sacarle provecho al color que se le pasa
				vida.setColor(naves.get(1).getColor());
				v.add(vida);
				pos-=40;
			}
		vidas.add(v);
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
			if(bola.getUltimo().equals(naves.get(0)) && vidas.get(0).size()>0) {
				if(bola.getUltimo().equals(naves.get(0)) &&vidas.get(0).size()==1) vidas.get(0).add(0,null);
				vidas.get(0).remove(vidas.get(0).size()-1);
				
			}
			else if(bola.getUltimo().equals(naves.get(1)) &&vidas.get(1).size()>0) {
				vidas.get(1).remove(vidas.get(1).size()-1);
				
			}
		}
	}
	
	public int getScore1() {
		return score;
	}
	
	public int getScore2() {
		return score2;
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
	public boolean perdio(int a,Plataforma p) {
		if(jugadores == 1) {
			if(getVidas().get(0).size()==0) return true;
			else return false;
		}else {
			if(getVidas().get(a).size() == 1 && getVidas().get(a).get(0)==null) return true;
			else return false;
		}
	}
	
	
	public ArrayList<ArrayList<Plataforma>> getVidas(){
		return vidas;
	}
	
	
	/**
	 * Metodo que prepara la bola dandole unos atributos iniciales.
	 */
	public void prepareBola(){
		if(jugadores==1)
			bola = new Bola(naves.get(0).getX()+naves.get(0).getWidth()/2-15,naves.get(0).getY()-naves.get(0).getHeight(),naves.get(0),null,45,1,45,this);
		else {
			int numero = (int) (Math.random() * 2);
			if(naves.get(numero) == null) {
				if(numero == 0) numero=1;
				else numero = 0;
			}
			bola = new Bola(naves.get(numero).getX()+naves.get(numero).getWidth()/2-15,naves.get(numero).getY()-naves.get(numero).getHeight(),naves.get(numero),naves.get((numero)==0?1:0),45,1,45,this);
		}
	}
	
	public void prepareBola(Plataforma plat){ 
		int i = naves.indexOf(plat);
		if(jugadores==1) {
			bola = new Bola(naves.get(0).getX()+naves.get(0).getWidth()/2-15,naves.get(0).getY()-naves.get(0).getHeight(),naves.get(0),null,45,1,45,this);
		}
		else {
			bola = new Bola(naves.get(i).getX()+naves.get(i).getWidth()/2-15,naves.get(i).getY()-naves.get(i).getHeight(),naves.get(i),naves.get(i),45,1,45,this);
		}
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
					bloque = new BloqueGris(posX, posY,70,35,this);
				else if(i==1) {
					if(j==5 || j == 7)
						bloque = new BloqueRojo(posX,posY,70,35,this); //poner esto random
					else
						bloque = new BloqueVerde( posX, posY,70,35,this);
				}
				else {
					if(j== 9)
						bloque = new BloqueAmarillo(posX, posY, 70,35,this);
					else if(j==0)
						bloque = new BloqueNegro(posX,posY,70,35,this);
					else if(j == 7)
						bloque = new BloqueAzul(posX,posY,70,35,this);
					else
						bloque = new BloqueRojo( posX, posY,70,35,this); //75 45
				}
				blocks.add(bloque);
				posX += step;
			}
			posY += 38;
			bloques.add(blocks);
		}
		
	}
	
	public void isPlataformaActiva() {
		if(naves.get(0).isPoderActivo()) {
			naves.get(0).hagaTalCosa(this);
		}
	}
	
	public void activeSorpresa() {
		if(getSorpresa()!=null) {
			if(naves.get(0)!= null && sorpresa.isChocado(naves.get(0))){
				setPoder(true);
				//prepareBola();
				sorpresa = null;
				//naves.get(0).setPoderActivado(false);
				
			}else if(jugadores == 2 && naves.get(1)!= null && sorpresa.isChocado(naves.get(1))) {
				setPoder(true);
				//prepareBola();
				sorpresa = null;
				//naves.get(0).setPoderActivado(false);
			}
		}
	}

	
	public void setPoder(boolean a) {
		poderActivo = a;
	}

	public boolean getPoder() {
		return poderActivo;
	}

	public ArrayList<Plataforma> getPlataforma() {
		return naves;
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
	
	public void setSorpresa(Sorpresa a) {
		sorpresa = a;
		
	}
	
	public Sorpresa getSorpresa() {
		return sorpresa;
	}
	
	public int getJugadores() {
		return jugadores;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void guardar(File file) throws ArkaPoobException {
		dao.guardar(this,file);
	}
}