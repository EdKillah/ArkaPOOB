package aplicacion;




/**
 * Clase BloqueAmarillo que extiende de la clase Bloque para obtener todos sus atributos. 
 * @author Jimenez Eduard- Murillo Carlos
 *
 */
public class BloqueNaranja extends Bloque{
	private int con;
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
	public BloqueNaranja(int x, int y, int width, int height, ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setIsChocado(false);
		this.ark = ark;
		tipo = "naranja";
		con = 0;
	}
	
	
	/**
	 * Metodo sobreescrito que realiza los procedimientos necesarios para considerar si fue chocado por una bola o no.
	 */
	@Override
	public boolean isChocado(Bola bola) {
	    isChocado = bola.getBounds().intersects(this.getBounds());
	    if(isChocado)
		    for(int i=0;i<ark.getBloques().size();i++)
		    	for(int j=0;j<ark.getBloques().get(i).size();j++) {
		    		Bloque b = ark.getBloques().get(i).get(j);
		    		if(b.getX()!= getX() && b.getX()+b.getWidth()!= getX()+getWidth() && b.getY()!= getY()-getHeight()) {
		    			double aux = getY()-getHeight();
		    			System.out.println("getY: "+getY());
		    			System.out.println("getHeight: "+getHeight());
		    			System.out.println("Entra: "+aux);
		    			setY(getY()-getHeight());
		    		}
		    	}
	    //if(isChocado) con++;
	    //if(con>3) return isChocado; else
	    return false;
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
