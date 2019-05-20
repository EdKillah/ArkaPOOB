package aplicacion;

import persistencia.*;

import java.awt.Image;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;

/**
 * Clase principal del paquete aplicación y del juego en general,
 * es la encargada de almacenar los elementos e interactuar con ellos.
 * @author Jimenez Eduard
 * @author Murillo Carlos
 */

public class ArkaPOOB implements Serializable{
	private ArrayList<ArrayList<Bloque>> bloques;
	private ArrayList<Jugador> naves;
	private Bola bola;
	private Bloque ultimoBloque;
	private int jugadores;
	private Sorpresa sorpresa;
	private boolean poderActivo;
	private ArkaPoobDAO dao;
	private int nivel;
	private String colorNave;
	private boolean bloqueRosa;
	private boolean bloqueAzul;
	private boolean bloqueAmarillo;
	private boolean bloqueNaranja;
	private boolean bloqueNegro;
	private Jugador maquina;
	private int direccion;
	
	
	/**
	 * Crea una instancia del tablero de juego
	 * 
	 */
	public ArkaPOOB(int jugadores,boolean rosa, boolean azul, boolean amarillo, boolean naranja, boolean negro) {
		this.jugadores = jugadores;
		naves = new ArrayList<Jugador>();
		nivel = 1;
		prepareColorBloques(rosa, azul, amarillo, naranja,negro);
		prepareBloques(rosa,azul,amarillo,naranja,negro);
		ultimoBloque = bloques.get(0).get(0);
		dao = new ArkaPoobDAO();
		prepareNave();	
		prepareBola();
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
					//isJugadorActiva();
					if(bloques.get(i).get(j).isVivo() && bloques.get(i).get(j).isChocado(bola)) {
						bola.getUltimo().setScore(bloques.get(i).get(j).getPuntos());
						if(!bloques.get(i).get(j).getTipo().equals("negro"))ultimoBloque = bloques.get(i).get(j);
						
					}
					if(jugadores  == 2) naves.get(0).isChocado(naves.get(1));
					if(maquina != null) {
						maquina.moverX(getDireccion());
						maquina.isChocado(naves.get(0));
					}
					
					
				}
			}
				
		}
		
	}
	
	public void setDireccion(int i) {
		direccion = i;
	}
	
	public int getDireccion() {
		return direccion;
	}
	
	public Bloque getUltimoBloque() {
		return ultimoBloque;
	}
	
	public void setUltimoBloque(Bloque a) {
		ultimoBloque = a;
	}
	
	/**
	 * Metodo que determina la creación de una nueva bola si esta fue eliminada. 
	 * @param heights
	 */
	public void estatico(double height) {
		borrarVidas((int)height);
		if(naves.get(0).getVidas()>0)
			prepareBola();
			bola.setInAire(false);
	}
	
	/**
	 * Metodo que prepara la Jugador del juego dandole unos atributos iniciales.
	 */
	private void prepareNave() {
		if(jugadores == 1)
			naves.add(new Jugador(750/2-40,480,90,20));
		if(jugadores ==2 ) {
			naves.add(new Jugador(750-60,480,90,20));
			naves.add(new Jugador(40,480,90,20));
		}
	}
	
	
	/**
	 * Metodo que elimina un jugador dependiendo de la cantidad de vidas que posea. 
	 * @param i
	 */
	public void eliminarJugador(int i) {
		naves.get(i).setVIvo(false);
		naves.set(i,null);
	}
	
	/**
	 * Metodo que adiciona una vida si el bloqueAmarillo es golpeado.
	 * @param bloque
	 */
	public void adicioneVida() {
		if(bola.getUltimo().equals(naves.get(0))) 
			naves.get(0).setVidas(1);
		else 
			naves.get(1).setVidas(1);
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
			if(bola.getUltimo().equals(naves.get(0)) && naves.get(0).getVidas()>0) {
				//if(bola.getUltimo().equals(naves.get(0)) &&vidas.get(0).size()==1) vidas.get(0).add(0,null);
				naves.get(0).setVidas(-1);	
			}else if(maquina != null && maquina.getVidas()>0){
				maquina.setVidas(-1);
			}
			else if(jugadores == 2 && bola.getUltimo().equals(naves.get(1)) &&naves.get(1).getVidas()>0) {
				naves.get(1).setVidas(-1);
			}
		}
	}
	
	/**
	 * Metodo que determina si el jugador gano, cumpliendose las condiciones necesarias. 
	 * Estas son que se elimine un bloque rosa o que se eliminen todos los bloques(que se pueden eliminar)
	 * @return true si gano, false dlc.
	 */
	public boolean gano() {
		int bloq=0;
		for(int i=0;i<bloques.size();i++) {
			for(int j=0;j<bloques.get(i).size();j++) {
				if(!bloques.get(i).get(j).getTipo().equals("gris") && bloques.get(i).get(j).isVivo())
					bloq++;
			}
		}
		if(bloq == 0 && nivel >= 6) { 
			return true;
		}
		else return false;
	}
	
	/**
	 * Metodo que determina si el jugador gano, cumpliendose las condiciones necesarias. 
	 * Estas son que se elimine un bloque rosa o que se eliminen todos los bloques(que se pueden eliminar)
	 * @return true si gano, false dlc.
	 */
	public boolean avanzaNivel() {
		int bloq=0;
		for(int i=0;i<bloques.size();i++) {
			for(int j=0;j<bloques.get(i).size();j++) {
				if(!bloques.get(i).get(j).getTipo().equals("gris") && bloques.get(i).get(j).isVivo())
					bloq++;
			}
		}
		if(bloq == 0 || ultimoBloque.getTipo().equals("rosa")) return true; //|| ultimoBloque.getTipo().equals("rosa")
		else return false;
	}
	
	/**
	 * Metodo que determina si el jugador perdio.
	 * El jugador pierde si esta no cuenta con vidas suficientes (mayor a 0).
	 * @return
	 */
	public boolean perdio(Jugador p) {
		if(p.getVidas()<=0) return true;
		else return false;
	}	
	
	/**
	 * Metodo que prepara la bola dandole unos atributos iniciales.
	 */
	public void prepareBola(){
		if(jugadores==1 && naves.get(0).getVidas() > 0) {
			if(maquina!=null)bola = new Bola(maquina.getX()+maquina.getWidth()/2-15,maquina.getY()-maquina.getHeight(),maquina,naves.get(0),45,1,45,this);
			else bola = new Bola(naves.get(0).getX()+naves.get(0).getWidth()/2-15,naves.get(0).getY()-naves.get(0).getHeight(),naves.get(0),null,45,1,45,this);
		}
		else if(jugadores == 2){
			int numero = (int) (Math.random() * 2);
			if(naves.get(numero).getVidas() <= 0) {
				if(numero == 0) numero=1;
				else numero = 0;
			}
			bola = new Bola(naves.get(numero).getX()+naves.get(numero).getWidth()/2-15,naves.get(numero).getY()-naves.get(numero).getHeight(),naves.get(numero),naves.get((numero)==0?1:0),45,1,45,this);
		}
	}
	
	public void prepareBola(Jugador plat){ 
		int i = naves.indexOf(plat);
		if(jugadores==1) {
			bola = new Bola(naves.get(0).getX()+naves.get(0).getWidth()/2-15,naves.get(0).getY()-naves.get(0).getHeight(),naves.get(0),null,45,1,45,this);
		}
		else {
			bola = new Bola(naves.get(i).getX()+naves.get(i).getWidth()/2-15,naves.get(i).getY()-naves.get(i).getHeight(),naves.get(i),naves.get(i),45,1,45,this);
		}
	}
	
	
	/**
	 * Metodo que retorna si los bloques rosa,azul,amarillo,naranja,negro estan activados o no.
	 * @return
	 */
	public boolean[] getColores(){
		boolean[] colores = {bloqueRosa,bloqueAzul,bloqueAmarillo,bloqueNaranja,bloqueNegro};
		return colores;
	}
	
	
	
	private void prepareColorBloques(boolean rosa, boolean azul, boolean amarillo, boolean naranja, boolean negro) {
		bloqueRosa = rosa;
		bloqueAzul = azul;
		bloqueAmarillo = amarillo;
		bloqueNaranja = naranja;
		bloqueNegro = negro;
		
	}
	
	
	private Bloque alisteBloques(int posicionAux,int contador,int posX,int posY,Bloque bloque) {
		String[] mComunes = {"Rojo","Verde","Naranja","Gris"};
		String[] pComunes = {"Rosa","Azul","Amarillo","Negro"};
		if(contador%2==0) {
			if(mComunes[posicionAux].equals("Rojo"))
				bloque = new BloqueRojo(posX,posY,70,35,this);
			else if(mComunes[posicionAux].equals("Verde")) {
				bloque = new BloqueVerde(posX,posY,70,35,this);
			}
			else if(mComunes[posicionAux].equals("Naranja") && bloqueNaranja)
				bloque = new BloqueNaranja(posX,posY,70,35,this);
			else 
				bloque = new BloqueVerde(posX,posY,70,35,this);
		}
		else {
			int pocoComunes = (int) (Math.random() * 40);
			if(pocoComunes%2==0) {
				if(pComunes[posicionAux].equals("Azul") && bloqueAzul)
					bloque = new BloqueAzul(posX,posY,70,35,this);
				else if(pComunes[posicionAux].equals("Amarillo") && bloqueAmarillo)
					bloque = new BloqueAmarillo(posX,posY,70,35,this);
				else if(pComunes[posicionAux].equals("Rosa") && bloqueRosa && pocoComunes%8==0)
					bloque = new BloqueAmarillo(posX,posY,70,35,this);
				else if(pComunes[posicionAux].equals("Negro") && bloqueNegro)
					bloque = new BloqueNegro(posX,posY,70,35,this);
				else
					bloque = new BloqueRojo(posX,posY,70,35,this);
			}
			else
				bloque = new BloqueVerde(posX,posY,70,35,this);
		}
		return bloque;
	}
	
	private int compruebePosicionArreglo(int posicionAux) {
		if(posicionAux>=4)
			posicionAux=0;
		return posicionAux;
	}
	
	private void prepareNivelUno() {
		Bloque bloque=null;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=30,posX=0;
		int step = 70;
		int posicionAux=0;
		int contador = 0;
		for(int i=0;i<3;i++) {
			posX=20;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<10;j++) {
				posicionAux = compruebePosicionArreglo(posicionAux);
				if(i==0) 
					bloque = new BloqueGris(posX, posY,70,35,this);
				else if(i==1) {
					//bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
					bloque = new BloqueRosa(posX, posY, 70, 35, this);
				}
				else 
					bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
				if(bloque!=null)
					blocks.add(bloque);
				posX += step;
				posicionAux++;
				contador++;
			}
			posY += 38;
			bloques.add(blocks);
		}
	}
	
	private void prepareNivelDos() {
		Bloque bloque=null;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=30,posX=0;int step = 70;int posicionAux=0, contador = 0;
		for(int i=0;i<4;i++) {
			posX=20;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<10;j++) {
				posicionAux = compruebePosicionArreglo(posicionAux);
				if(i==1 && (j==2 || j==4 || j == 7) || i==3 && (j==1 || j==3 || j==8)) bloque = new BloqueGris(posX,posY,70,35,this);
				else if(i==0) bloque = new BloqueRojo(posX, posY,70,35,this);
				else if(i==1) bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
				else if(i==3 && j==9) bloque = new BloqueRosa(posX,posY,70,35,this);
				else 
					bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
				if(bloque!=null)  
					blocks.add(bloque);
				posX += step;
			}
			posY += 38;
			contador++;
			posicionAux++;
			bloques.add(blocks);
		}
		ultimoBloque = bloques.get(0).get(0);
		//System.out.println("NIVEL 2: "+nivel);
	}
	
	private void prepareNivelCuatro() {
		Bloque bloque=null;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=30,posX=0;int step = 70; int posicionAux=0;int contador = 0; int i=0; int x=1;
		while(i<9) {	
			posX=20;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<x;j++) {
				posicionAux = compruebePosicionArreglo(posicionAux);
				if(i==8) bloque = new BloqueGris(posX,posY,70,35,this);
				else if(j==0) bloque = new BloqueVerde(posX,posY,70,35,this);
				else if(j==1) bloque = new BloqueRojo(posX,posY,70,35,this);
				else if(j==2 && bloqueNaranja) bloque = new BloqueNaranja(posX,posY,70,35,this);
				else if(j==3 && bloqueAmarillo) {
					if(contador%2==0)bloque = new BloqueAmarillo(posX,posY,70,35,this);
					else bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
				}
				else if(j==4 && bloqueAzul) {
						if(contador%2!=0)bloque = new BloqueAzul(posX,posY,70,35,this);
						else bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);		
				}
				else if(i==5) bloque = new BloqueVerde(posX,posY,70,35,this);
				else bloque = alisteBloques(posicionAux,contador,posX,posY,bloque);
				if(bloque!=null)
					blocks.add(bloque);
				posX += step;
				posicionAux++;
				contador++;
			}
			x++;i++;posY += 38;
			bloques.add(blocks);
		}
		bloque = new BloqueRosa(110,30,70,35,this);
		bloques.get(0).add(bloque);
		ultimoBloque = bloques.get(0).get(0);
	}
	
	private void prepareNivelTres() {
		//System.out.println("NIVEL 3: "+nivel);
		Bloque bloque=null;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=30,posX=10;int step = 70; int posicionAux=0;int contador = 0; int i=0; int x=1;
		for(int j=0;j<5;j++) {
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int k=0;k<6;k++) {
				posicionAux = compruebePosicionArreglo(posicionAux);
				if(k%2==0)posX+=step;
				bloque = alisteBloques(posicionAux,contador,posX,posY,bloque); //new BloqueRojo(posX,posY,70,35,this);
				blocks.add(bloque);
				posX+=step;
				posicionAux++;
				contador++;
			}
			posY+=38;
			posX = 10;
			bloques.add(blocks);
		}
		ultimoBloque = bloques.get(0).get(0);
	}
	
	private void prepareNivelCinco() {
		System.out.println("NIVEL 5: "+nivel);
	}
	
	/**
	 * Metodo que prepara los bloques iniciales del juego, distribuyendolos de una manera especifica.
	 */
	public void prepareBloques(boolean rosa, boolean azul, boolean amarillo, boolean naranja, boolean negro) {
		if(nivel == 1) {prepareNivelUno(); nivel++;}		
		else if(nivel == 2) {prepareNivelDos();nivel++;}
		else if(nivel == 3) {prepareNivelTres();nivel++;}
		else if(nivel == 4) {prepareNivelCuatro();nivel++;}
		else if(nivel == 5) {prepareNivelCinco();nivel++;}
	}
	
	/*
	public void prepareBloques() {
		prepareBloques(bloqueRosa,bloqueAzul,bloqueAmarillo,bloqueNaranja,bloqueNegro);
	}
	*/
	
	/*
	public void isJugadorActiva() {
		if(naves.get(0).isPoderActivo()) {
			naves.get(0).hagaTalCosa(this);
		}
	}
	*/
	
	public void activeSorpresa() {
		if(getSorpresa()!=null) {
			if(naves.get(0)!= null && sorpresa.isChocado(naves.get(0))){
				//setPoder(true);
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
	
	
	public boolean existeBloque(Bloque bloque) {
		boolean band = true; 
		for(int i=0;i<getBloques().size();i++) {
			for(int j=0;j<getBloques().get(i).size();j++) {
				Bloque b = getBloques().get(i).get(j);
		    	if(b.getX()== bloque.getX() && b.getX()+b.getWidth()== bloque.getX()+bloque.getWidth()) {
		    		if(b.getY()!= bloque.getY()-bloque.getHeight() && bloque.getY()-b.getY()<40 && !bloque.equals(b)) {
		    			band = false;
		    		}
		    	}
		    }
		}
		return band;
	}

	public void maquina(String tipo) {
		if(tipo.equals("destructor")) maquina = new JugadorDestructor(750/2 + 80,480,90,20,this);
		//else if(tipo.equals("curioso")) maquina = tipo;
		else if(tipo.equals("mimo")) maquina = new JugadorMimo(750/2 + 60,480,90,20,this);
		prepareBola();
	}
	
	/**
	 * Obtiene una lista de los elementos vivos del tablero
	 * @return La lista de los elementos vivos del tablero
	 */
	public ArrayList<Elemento> getElementos(){
		ArrayList<Elemento> elemns = new ArrayList<>();

		for(int i=0;i<bloques.size();i++) {
			for(int j=0;j<bloques.get(i).size();j++) {
				elemns.add(bloques.get(i).get(j));
				//if(bloques.get(i).get(j).isVivo() && bloques.get(i).get(j).isChocado(bola)) {}
			}
		}
		for (Jugador a: naves) {
			if (a.getVidas()>0) elemns.add(a);
		}
		if(maquina!= null) {
			elemns.add(maquina);
		}
		elemns.add(bola);

		return elemns;
	}
	
	public void addBloque(int x,int y,int width,int height,boolean vivo,String bloque) throws ArkaPoobException{
		int i=0;
		Bloque a = null;
		if(bloques.get(0).size() == 10) i=1;
		else if(bloques.get(1).size() == 10) i=2;
		else if(bloques.get(2).size() == 10) i=3;
		else if(bloques.get(4).size() == 10) i=5;
		else if(bloques.get(5).size() == 10) throw new ArkaPoobException("No se puede agregar mas bloques"); 
		
		if(bloque.equals("BloqueRojo")) {
			a = new BloqueRojo(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueAmarillo")) {
			a = new BloqueAmarillo(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueAzul")) {
			a = new BloqueAzul(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueGris")) {
			a = new BloqueGris(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueNaranja")) {
			a = new BloqueNaranja(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueNegro")) {
			a = new BloqueNegro(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueRosa")) {
			a = new BloqueRosa(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
		if(bloque.equals("BloqueVerde")) {
			a = new BloqueVerde(x,y,width,height,this);
			a.setVivo(vivo);
			bloques.get(i).add(a);
		}
	}
	
	public void addJugador(int x,int y,int width,int height,int score,String color,int vidas,String nombre,String jugador) throws ArkaPoobException{
		Jugador a = null;
		if(jugador.equals("Jugador")) {
			a = new Jugador(x,y,width,height);
			a.setScor(score);
			a.setVida(vidas);
			a.setColor(color);
			a.setNombre(nombre);
			naves.add(a);
		}
		if(jugador.equals("JugadorDestructor")) {
			a = new JugadorDestructor(x,y,width,height,this);
			a.setVida(vidas);
			a.setScor(score);
			a.setColor(color);
			maquina = a;
		}
		if(jugador.equals("JugadorMimo")) {
			a = new JugadorMimo(x,y,width,height,this);
			a.setVida(vidas);
			a.setScor(score);
			a.setColor(color);
			maquina = a;
		}
		//if(jugador.equals("JugadorCurioso")) naves.add(new JugadorCurioso(x,y,width,height,this));
	}
	
	public void addBola(int x, int y) throws ArkaPoobException{
		if(jugadores==1 && naves.get(0).getVidas() > 0) {
			if(maquina!=null)bola = new Bola(maquina.getX()+maquina.getWidth()/2-15,maquina.getY()-maquina.getHeight(),maquina,naves.get(0),45,1,45,this);
			else bola = new Bola(naves.get(0).getX()+naves.get(0).getWidth()/2-15,naves.get(0).getY()-naves.get(0).getHeight(),naves.get(0),null,45,1,45,this);
		}
		else if(jugadores == 2){
			int numero = (int) (Math.random() * 2);
			if(naves.get(numero).getVidas() <= 0) {
				if(numero == 0) numero=1;
				else numero = 0;
			}
			bola = new Bola(naves.get(numero).getX()+naves.get(numero).getWidth()/2-15,naves.get(numero).getY()-naves.get(numero).getHeight(),naves.get(numero),naves.get((numero)==0?1:0),45,1,45,this);
		}
	}
	
	public Jugador getMaquina() {
		return maquina;	
	}
	
	public void setPoder(boolean a) {
		poderActivo = a;
	}

	public boolean getPoder() {
		return poderActivo;
	}
	
	public String getColorNave() {
		return colorNave;
	}

	public ArrayList<Jugador> getJugador() {
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
		this.bloques.add(new ArrayList<Bloque>());
		this.bloques.add(new ArrayList<Bloque>());
		this.bloques.add(new ArrayList<Bloque>());
		this.bloques.add(new ArrayList<Bloque>());
		this.bloques.add(new ArrayList<Bloque>());
		this.bloques.add(new ArrayList<Bloque>());
	}
	
	public void setNaves(ArrayList<Jugador> a) {
		this.naves = a;
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
	
	public void setNivel(int a) {
		this.nivel = a;
		prepareBloques(bloqueRosa,bloqueAzul,bloqueAmarillo,bloqueNaranja,bloqueNegro);
	}
	
	public void guardar(File file) throws ArkaPoobException {
		dao.guardar(this,file);
	}
	
	/**
	 * Exporta el estado del juego
	 * @param file La ruta del archivo para exportar
	 */
	public void exportarJuego(File file) throws ArkaPoobException {
		dao.exportar(this, file);
	}
}