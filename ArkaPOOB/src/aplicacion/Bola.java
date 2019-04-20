package aplicacion;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bola implements Elemento{
	private int x;
	private int y;
	private double dx;
	private double dy;
	private static final int TAMX=25;
	private static final int TAMY=25;
	private Plataforma nave;
	private ArkaPOOB ark;
	private int velocidad;
	private int damage;
	private boolean vivo;
	private double angulo;
	private boolean isInAire;
	private boolean isChocado;
	private ImageIcon imagen;

	
	public Bola(int x, int y, Plataforma nave, int velocidad, int damage,int angulo, ArkaPOOB ark) {
		dy = 1;
		dx = 1;
		vivo = true;
		isChocado = false;
		isInAire = false;
		this.ark = ark;
		this.x = x;
		this.y = 630; //mirar como poner esto para que sea extensible 
		this.nave = nave;
		this.velocidad = velocidad;
		this.damage = damage;
		this.angulo = angulo;
		prepareImagen();
	}
	
	private void prepareImagen() {
		imagen = new ImageIcon(getClass().getResource("/imagenes/pelota.png"));
	}
	
	public Rectangle getBounds() {
        Rectangle borde = new Rectangle(this.getX(), this.getY(), TAMX, TAMY);
        return borde;
    }   
	
	public boolean isChocado(Bloque bloque) {
        isChocado = bloque.getBounds().intersects(this.getBounds());
        return isChocado;
    }
	
	public void setIsChocado(boolean choque) {
		isChocado = choque;
	}
	
	public Bloque confirmeChoque() { //otra forma de hacerlo es enviarle desde arkaPOOB el bloque con el cual choco y con eso no habria que hacer este
		for(int i=0;i<ark.getBloques().size();i++) {
			for(int j=0;j<ark.getBloques().get(i).size();j++) {
				if(this.isChocado(ark.getBloques().get(i).get(j))) {
					return ark.getBloques().get(i).get(j);	
				}
			}
		}
		return null;
	}
	
	public void muevase(double widthT, double heightT){ //tienen la T porque significa tablero.
		x+=dx;
		y+=dy;
		if(x<=0){
			System.out.println("X<=0: ");
			x=0;
			dx=-dx;
		}
		
		if(x + TAMX>=widthT){
			System.out.println("x>=1200: ");
			x=(int)widthT - TAMX;
			dx=-dx;
		}
		
		if(y<0){
			System.out.println("Toca borde superior: ");
			y=0;
			dy=-dy;
		}
		
		if(y + TAMY>=heightT){ 
			System.out.println("Toca borde EN Y: ");
			if(isInNave()) {
				y=(int)heightT-TAMY;
				dy=-dy;
			}
			else setVivo(false);
		}
		
		Bloque b = confirmeChoque();
		if(b!=null) {
			y=b.getY()+b.getHeight()-3;//+TAMY //+b.getHeight())/2
			dy=-dy;
		}
		
	}
	
	public boolean isVivo() {
		return vivo;
	}
	
	public void setVivo(boolean vida) { //mirar si se cambia el nombre
		vivo = vida; 
	}
	
	public boolean isInNave() {
		if(getX()>=nave.getX() && getX()<=nave.getX()+nave.getWidth()) {
			return true;
		}
		return false;
	}
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public void setX(int x) {
		if(x==2)
			this.x -= 20;
		else
			this.x += 20;
	}
	
	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public Plataforma getNave() {
		return nave;
	}

	public void setNave(Plataforma nave) {
		this.nave = nave;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
	
	public boolean isInAire() {
		return isInAire;
	}
	
	public void setInAire(boolean enAire) {
		isInAire = enAire;
	}
	
	@Override
	public Image getImagen() {
		return imagen.getImage();
	}

	public static int getTamX() {
		return TAMX;
	}

	public static int getTamY() {
		return TAMY;
	}
	
	@Override
	public int getWidth() {
		return 25;
	}
}
