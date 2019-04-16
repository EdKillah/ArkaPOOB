package aplicacion;

import java.util.*;

public class ArkaPOOB {
	private ArrayList<ArrayList<Bloque>> bloques; 
	private Plataforma nave;
	
	public ArkaPOOB() {
		prepareBloques();
		prepareNave();
	}
	
	
	public void prepareNave() {
		nave = new Plataforma(300,"blue",120,40);
	}
	
	public void prepareBloques() {
		
		Bloque bloque;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=0;
		for(int i=0;i<3;i++) {
			int posX=1;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<20;j++) {
				if(i<1) {
					bloque = new Bloque( j*posX+j*75, (i*posY+i*45)+35,75,45,1);
					blocks.add(bloque);
				}
				else if(i<=2) {
					bloque = new Bloque( j*posX+j*75, (i*posY+i*45)+35,75,45,1);
					blocks.add(bloque);
				}
				else  {
					bloque = new Bloque( j*posX+j*75, (i*posY+i*45)+35,75,45,1);
					blocks.add(bloque);
				}
			}
			bloques.add(blocks);
		}
		
	}


	public Plataforma getPlataforma() {
		return nave;
	}

	public ArrayList<ArrayList<Bloque>> getBloques() {
		return bloques;
	}




	public void setBloques(ArrayList<ArrayList<Bloque>> bloques) {
		this.bloques = bloques;
	}
}