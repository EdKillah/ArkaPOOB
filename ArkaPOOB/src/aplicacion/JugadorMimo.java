package aplicacion;

import javax.swing.ImageIcon;

public class JugadorMimo extends Jugador{
	private ArkaPOOB ark;
	private int x;
	public JugadorMimo(int x,int y, int width, int height, ArkaPOOB ark) {
		super( x, y, width, height);
		setVidas(3);
		this.x = x;
		this.ark = ark;
		prepareImagen();
	}
	private void prepareImagen() {
		setImagen(new ImageIcon(getClass().getResource("/imagenes/vaus_green.gif")));
	}
	
	@Override
	public void moverX(int x) {
	}
}
