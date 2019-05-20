package aplicacion;

public class JugadorMimo extends Jugador{
	private ArkaPOOB ark;
	private String tipo;
	private String color;
	
	public JugadorMimo(int x,int y, int width, int height, ArkaPOOB ark) {
		super( x, y, width, height);
		this.ark = ark;
		tipo = "mimo";
		this.color = "green";
	}
	
	public String getTipo() {
		return tipo;
	}
	@Override
	public void moverX(int i) {
	}
	@Override
	public String getColor() {
		return color;
	}
	
	@Override	
	public void isChocado(Jugador plat) {
		if(plat.getVidas() > 0 && plat.getX() <= this.getX() && plat.getX()+plat.getWidth()+1>=this.getX()) {
			//System.out.println("W");
			this.setX(this.getX());
			int ax = this.getX(),ax2 = plat.getX();
			//this.setX(ax2-2);
			if(ark.getDireccion()==1)
				plat.setX(ax+80);
			else
				plat.setX(ax-85);
		}
		else if(plat.getVidas() > 0 && this.getX() <= plat.getX() && this.getX()+this.getWidth()+1>=plat.getX()) {
			plat.setX(plat.getX());
			int ax = this.getX(),ax2 = plat.getX();
			//this.setX(ax2+2);
			if(ark.getDireccion()==1)
				plat.setX(ax+80);
			else
				plat.setX(ax-85);
		}
	}
}
