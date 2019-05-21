package aplicacion;

public class SorpresaBola extends Sorpresa{
	private String tipo;
	private ArkaPOOB ark;
	private boolean isChocado;
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
	
	/**
	 * Metodo que  mira si es chocado con una nave
	 * @param nave
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
	 * Metodo que escoje una sorpresa al azar.
	 */
	private void escojaTipo() {
		double x = Math.random()*3;
		int azar = (int)x;
		if(azar%2==0) tipo="minus";
		else tipo = "plus";
		
	}
	
	/**
	 * Metodo que activa la sorpresa
	 * @param nave
	 */
	private void activeSorpresa(Jugador nave) {
		ark.getBola().activeSorpresa(tipo);
	}
	
	/**
	 * Metodo que actualiza la nave que choco
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
	 * Metodo que actualiza el tipo sorpresa
	 * @param nave
	 */
	@Override
	public void setTipo(String type) {
		tipo = type;
	}
}
