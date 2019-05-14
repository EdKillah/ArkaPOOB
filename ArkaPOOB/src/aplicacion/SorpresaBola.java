package aplicacion;

public class SorpresaBola extends Sorpresa{
	private String tipo;
	private ArkaPOOB ark;
	private boolean isChocado;
	private int turnos;
	private boolean activo;
	private int nave;
	
	public SorpresaBola(int x, int y,ArkaPOOB ark) { 
		setX(x);
		setY(y);
		setWidth(35);
		setHeight(35);
		setIsChocado(false);
		escojaTipo();
		this.ark = ark;
	}
	
	@Override
	public boolean isChocado(Plataforma nave) { //mirar si le damos la nave o con arkgetPlataforma
        isChocado =nave.getBounds().intersects(this.getBounds());
        if(isChocado) {	
        	ark.setPoder(true);
        	activeSorpresa(nave);
        }	
        return isChocado;
    }
	
	private void escojaTipo() {
		double x = Math.random()*3;
		int azar = (int)x;
		System.out.println("Numero escojido al azar: "+azar);
		if(azar%2==0) tipo="minus";
		else tipo = "plus";
		
	}
	
	private void activeSorpresa(Plataforma nave) {
		ark.getBola().activeSorpresa(tipo);
	}
	
	public void setNave(int i) {
		nave = i;
	}
	
	public int getNave() {
		return nave;
	}
	
	
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public void setTipo(String type) {
		tipo = type;
	}
}
