package presentacion;

import aplicacion.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class PantallaDeJuego extends JPanel{
	private ArkaPOOB ark;
	
	public PantallaDeJuego() {
		System.out.println("Aqui entra");
		setBackground(Color.BLACK);
		ark = new ArkaPOOB();
		System.out.println("Aqui también");
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("Parece que aqui no entra");
        ArrayList<ArrayList<Bloque>> bloques= ark.getBloques();
		for(int i=0;i<bloques.size();i++) {
			for(int j=0;j<bloques.get(i).size();j++) {
				g.drawImage(bloques.get(i).get(j).getImagen(), bloques.get(i).get(j).getX(), bloques.get(i).get(j).getY(), null);			
			}
		}
		/*
		for (Bloque e: ark.getBloques()) {
			g.drawImage(e.getImagen(), e.getX(), e.getY(), this);
			
		}
		*/
	}
	
	
}
