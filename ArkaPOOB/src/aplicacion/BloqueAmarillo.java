package aplicacion;


/**
 * Clase BloqueAmarillo que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueAmarillo extends Bloque{
	private String tipo;
	private boolean isChocado;
	private final int puntos = 300;
	private ArkaPOOB ark;
	
	/**
	 * Constructor de la clase, que instancia la misma. 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BloqueAmarillo(int x, int y, int width, int height, ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		setVivo(true);
		tipo = "amarillo";
		this.ark = ark;
	}
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 * @return true si lo choca,false dlc.
	 */
	@Override
	public boolean isChocado(Bola bola) {
        isChocado = bola.getBounds().intersects(this.getBounds());
        if(isChocado) {
        	ark.setUltimoBloque(new BloqueAmarillo(getX(),getY(),getWidth(),getHeight(),ark));
        	ark.adicioneVida();
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
		return getX()+" "+getY()+ " "+getWidth()+" "+getHeight()+ " " +isVivo();
	}

}
