package aplicacion;




public class SorpresaPlataforma extends Sorpresa{
	private String tipo;
	private ArkaPOOB ark;
	private boolean isChocado;
	private int turnos;
	private boolean activo;
	private int nave;
	
	public SorpresaPlataforma(int x, int y,ArkaPOOB ark) { 
		setX(x);
		setY(y);
		setWidth(70);
		setHeight(34);
		setIsChocado(false);
		escojaTipo();
		//setTipo("pegajosa");
		this.ark = ark;
	}
	
	
	private void escojaTipo() {
		double x = Math.random()*3;
		int azar = (int)x;
		System.out.println("Numero escojido al azar nave: "+azar);
		if(azar%2==0) tipo="Laser";
		else tipo = "pegajosa";
		
	}
	
	@Override
	public boolean isChocado(Jugador nave) { //mirar si le damos la nave o con arkgetPlataforma
        isChocado =nave.getBounds().intersects(this.getBounds());
        if(isChocado) {	
        	ark.setPoder(true);
        	activeSorpresa(nave);
        }	
        return isChocado;
    }
	
	private void activeSorpresa(Jugador nave) {
		nave.activeSorpresa(tipo,ark);
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
