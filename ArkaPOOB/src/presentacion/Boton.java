package presentacion;

import javax.swing.*;
import java.awt.*;

public class Boton extends JButton{
	public Boton(Icon icono){
		super(icono);
	}
	
	public void setTransparent(){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
	}
}
