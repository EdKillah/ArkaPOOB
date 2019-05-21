package aplicacion;


public class JugadorCurioso extends Jugador{
	private ArkaPOOB ark;
	private int x;
	private String tipo;
	private String color;
	
	public JugadorCurioso(int x,int y, int width, int height, ArkaPOOB ark) {
		super( x, y, width, height);
		this.x = x;
		this.ark = ark;
		tipo = "curioso";
		this.color = "green";
	}
	
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public void moverX(int i) {
		if(ark.getSorpresa()!=null && ark.getSorpresa().getX() != getX() ) {
			if(ark.getSorpresa().getX() > getX()) {
				super.moverX(1);
			}else if(ark.getSorpresa().getX() > getX()) {
				super.moverX(2);
			}else {
				;
			}
		}else if(ark.getSorpresa()!=null && ark.getSorpresa().getX() == getX() ) {
			;
		}else {
			if(i == 3)
				setX(ark.getBola().getX()-getWidth()/2+10);
		}
		
	}
	
	@Override
	public String getColor() {
		return color;
	}
	
	@Override	
	public void isChocado(Jugador plat) {
		if(plat.getVidas() > 0 && plat.getX() <= this.getX() && plat.getX()+plat.getWidth()+1>=this.getX()) {
			this.setX(this.getX());
			int ax = this.getX(),ax2 = plat.getX();
			if(ark.getDireccion()==1)
				plat.setX(ax+80);
			else
				plat.setX(ax-85);
		}
		else if(plat.getVidas() > 0 && this.getX() <= plat.getX() && this.getX()+this.getWidth()+1>=plat.getX()) {
			plat.setX(plat.getX());
			int ax = this.getX(),ax2 = plat.getX();
			if(ark.getDireccion()==1)
				plat.setX(ax+80);
			else
				plat.setX(ax-85);
		}
	}
}
