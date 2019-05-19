package aplicacion;

import javax.swing.ImageIcon;

public class JugadorMimo extends Jugador{
	private ArkaPOOB ark;
	private int x;
	private String tipo; 
	
	public JugadorMimo(int x,int y, int width, int height, ArkaPOOB ark) {
		super( x, y, width, height);
		this.x = x;
		this.ark = ark;
		tipo = "mimo";
		prepareImagen();
	}
	private void prepareImagen() {
		setImagen(new ImageIcon(getClass().getResource("/imagenes/vaus_green.gif")));
	}
	
	public String getTipo() {
		return tipo;
	}
	@Override
	public void moverX(int x) {
	}
}
