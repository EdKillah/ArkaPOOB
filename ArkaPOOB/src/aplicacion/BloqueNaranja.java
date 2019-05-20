package aplicacion;




/**
 * Clase BloqueAmarillo que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueNaranja extends Bloque{
	private String tipo;
	private boolean isChocado;
	private final int puntos = 500;
	private ArkaPOOB ark;
	
	
	/**
	 * Constructor de la clase, que instancia la misma. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BloqueNaranja(int x, int y, int width, int height, ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		setVivo(true);
		this.ark = ark;
		tipo = "naranja";
	}
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 */
	@Override
	public boolean isChocado(Bola bola) {
	    isChocado = bola.getBounds().intersects(this.getBounds());
	    if(isChocado) {
	    	if(ark.existeBloque(this)) {
	    		setY(getY()-getHeight()-3);
	    		return false;
	    	}
	    	setVivo(false);
	    }
	    
	    return isChocado;
    }
	

	
	@Override
	public int getPuntos() {
		return puntos;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public void setTipo(String type) {
		tipo = type;
	}

	@Override
	public String toString(){
		return getX()+" "+getY()+ " "+getWidth()+" "+getHeight()+" " +isVivo();
	}
}
