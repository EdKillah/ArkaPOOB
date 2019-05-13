package aplicacion;




public class SorpresaPlataforma extends Sorpresa{
	private int x;
	private int y;
	private int width;
	private int height;
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
		tipo = "Pegajosa";
		this.ark = ark;
	}
	
	/**
	 * Metodo encargado de preparar la imagen del bloque dependiendo de su tipo.
	 */
	private void prepareImagen() {
		//System.out.println("ENTRA EN PREPAREIMAGEN SORPRESA");
		//imagen = new ImageIcon(getClass().getResource("/imagenes/sorpresa_especial.gif"));
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
	
	private void activeSorpresa(Plataforma nave) {
		nave.activeSorpresa(tipo,ark);
	}
	
	public void setNave(int i) {
		nave = i;
	}
	
	public int getNave() {
		return nave;
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
