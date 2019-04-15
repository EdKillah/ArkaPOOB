package aplicacion;

import java.util.*;

public class ArkaPOOB {
	private ArrayList<ArrayList<Bloque>> bloques; 
	
	
	public ArkaPOOB() {
		System.out.println("Entra en aplicacion");
		prepareBloques();
		
	}
	
	
	
	public void prepareBloques() {
		
		Bloque bloque;
		bloques = new ArrayList<ArrayList<Bloque>>();
		int posY=0;
		for(int i=0;i<3;i++) {
			int posX=1;
			ArrayList<Bloque> blocks = new ArrayList<Bloque>();
			for(int j=0;j<1;j++) {
				if(i<1) {
					bloque = new Bloque( j*posX+j*40, (i*posY+i*45)+35,20,40,1);
					blocks.add(bloque);
				}
				else if(i<=2) {
					bloque = new Bloque( j*posX+j*40, (i*posY+i*45)+35,20,40,1);
					blocks.add(bloque);
				}
				else  {
					System.out.println("DON'T ENTRY");
					bloque = new Bloque( j*posX+j*40, (i*posY+i*45)+35,20,40,1);
					blocks.add(bloque);
				}
			}
			bloques.add(blocks);
		}
		System.out.println("Me prepara los bloques");
		
	}




	public ArrayList<ArrayList<Bloque>> getBloques() {
		System.out.println("Me da los bloques");
		return bloques;
	}




	public void setBloques(ArrayList<ArrayList<Bloque>> bloques) {
		this.bloques = bloques;
	}
}
