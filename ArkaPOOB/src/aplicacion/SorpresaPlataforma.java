package aplicacion;




public class SorpresaPlataforma extends Sorpresa{
	private int x;
	private int y;
	private int width;
	private int height;
	private String tipo;
	private ArkaPOOB ark;
	private boolean isChocado;
	
	public SorpresaPlataforma(int x, int y,ArkaPOOB ark) {
		setX(x);
		setY(y);
		setWidth(70);
		setHeight(34);
		setIsChocado(false);
		tipo = "sorpresaPlataformaPegajosa";
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
        if(isChocado)
        {	
        	//activeSorpresa();
        	System.out.println("sadsadasdasdsa");
        }	
        return isChocado;
    }
	
	private void activeSorpresa() {
		ark.activeSorpresa();
		//System.out.println("Entra en activar SOPRESA");
		//System.out.println("Kolor nave "+ark.getPlataforma().get(0).getColor());
		//nave.setWidth(138);
		//nave.setHeight(48);
		//nave.setHeight(20);
		//nave.setImagen(new ImageIcon(getClass().getResource("/imagenes/big_vaus_"+nave.getColor()+".gif")));
		//nave.setImagen(new ImageIcon(getClass().getResource("/imagenes/big_vaus_"+nave.getColor()+".gif")));
		
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
