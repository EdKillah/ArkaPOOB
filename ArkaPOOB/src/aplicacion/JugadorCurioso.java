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
	public void imitar(int x) {
		if(ark.getSorpresa()!=null && ark.getSorpresa().getX() != getX() ) {
			if(ark.getSorpresa().getX() > getX()) {
				super.moverX(1);
			}else if(ark.getSorpresa().getX() > getX()) {
				super.moverX(2);
			}else {
				;
			}
		}else {
			super.imitar(x);
		}
		
	}
	
	@Override
	public void moverX(int i) {
	}
	
	@Override
	public String getColor() {
		return color;
	}
}
