package aplicacion;



/**
 * Clase BloqueRosa que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueRosa extends Bloque{
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
	public BloqueRosa(int x, int y, int width, int height, ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		tipo = "rosa";
		this.ark = ark;
	}
	
	
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 */
	@Override
	public boolean isChocado(Bola bola) {
        isChocado = bola.getBounds().intersects(this.getBounds());
        //if(isChocado) ark.prepareBloques();
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
}


