package aplicacion;

import java.util.*;

public class ArkaPOOB {
	private ArrayList<ArrayList<Bloque>> bloques;
	private ArrayList<Plataforma> vidas;
	private Plataforma nave;
	private Bola bola;
	
	public ArkaPOOB() {
		vidas = new ArrayList<Plataforma>();
		prepareBloques();
		prepareNave();
		prepareBola();
		prepareVidas();
	}
	
	public void juegue(double width, double height) {
		if(bola.isVivo()) {
			bola.muevase(width,height);
			for(int i=0;i<bloques.size();i++) {
				for(int j=0;j<bloques.get(i).size();j++) {
					if(bola.isChocado(bloques.get(i).get(j)))	
						bloques.get(i).remove(j);
				}
			}
		}
		
	}
	
	public void estatico(double height) {
		borrarVidas((int)height);
		if(vidas.size()>0)
			prepareBola();
			bola.setInAire(false);
	}
	
	
	public void prepareNave() {
		nave = new Plataforma(748/2,530,"red",90,20);
	}
	
	public void prepareVidas() {
		Plataforma vida;
		int pos =0;
		for(int i=0;i<3;i++) {
			vida = new Plataforma(30+pos,550,nave.getColor(),40,15); //aqui podemos sacarle provecho al color que se le pasa
			vidas.add(vida);
			pos+=40;
		}
	}
	
	public boolean isVivo() {
		return bola.isVivo();
	}
	
	public void borrarVidas(int height) {
		if (bola.getY()<=height) {
			if(vidas.size()>0)
				vidas.remove(vidas.size()-1);
		}
	}
	
	public boolean gano() {
		int bloq=0;
		for(int i=0;i<bloques.size();i++) 
			if(bloques.get(i).size()>0)
				bloq++;
		if(bloq == 0) return true;
		else return false;
	}
	
	public boolean perdio() {
		if(getVidas().size()==0) return true;
		return false;
	}
	
	public ArrayList<Plataforma> getVidas(){
		return vidas;
	}
	
	public void prepareBola(){
		bola = new Bola(nave.getX()+nave.getWidth()/2-15,nave.getY()-nave.getHeight(),nave,45,1,45,this);
	}
	
	public void prepareBloques() {
		
		Bloque bloque;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=70,posX=0;
		int step = 70;
		for(int i=0;i<3;i++) {
			posX=20;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<10;j++) {
				bloque = new Bloque( posX, posY,70,35,1); //75 45
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