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
		double x = Math.random()*10;
		int azar = (int)x;
		
		if(azar%3==0) setTipo("grande");
		else if(azar%4==0) setTipo("chica");
		else if(azar%2==0) setTipo("especial");
		else setTipo("pegajosa");
		
	}
	
	/**
	 * Metodo que  mira si es chocado con una nave
	 * @param isChocado
	 */
	@Override
	public boolean isChocado(Jugador nave) { //mirar si le damos la nave o con arkgetPlataforma
        isChocado =nave.getBounds().intersects(this.getBounds());
        if(isChocado) {	
        	ark.setPoder(true);
        	activeSorpresa(nave);
        }	
        return isChocado;
    }
	
	/**
	 * Metodo que activa la sorpresa
	 * @param nave
	 */
	private void activeSorpresa(Jugador nave) {
		nave.activeSorpresa(tipo,ark);
	}
	
	/**
	 * Metodo que actualiza la nave en la que choca
	 * @param i
	 */
	public void setNave(int i) {
		nave = i;
	}
	
	/**
	 * Metodo que obtiene la nave que choco
	 * @return nave
	 */
	public int getNave() {
		return nave;
	}
	
	/**
	 * Metodo que obtiene el tipo de sorpresa
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Metodo que actualiza el tipo de sorpresa
	 * @param type
	 */
	@Override
	public void setTipo(String type) {
		tipo = type;
	}
}
