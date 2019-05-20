package aplicacion;

public class ArkaPoobException  extends Exception {
	public static final String ERROR_DIMENSION = "Dimensiones/posiciones invalidas";
	public static final String ERROR_JUGADORES = "Error jugadores";
	public static final String NO_HAY_JUEGO = "No existe juego";
	public static final String NO_HAY_NOMBRE = "Por favor ingrese su nombre";
	public static final String COLORES_IGUALES = "Por favor elija diferentes colores";
	public ArkaPoobException(String msg){
		super(msg);
	}

}
