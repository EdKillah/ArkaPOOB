package pruebas;
import aplicacion.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;



class ArkaPOOBTest {
	@Test
	void deberiaMoverPlataformaDerecha() {
		ArkaPOOB ark = new ArkaPOOB(1,"red",false,false, true, true, false);
		Jugador nave = ark.getJugador().get(0);
		int x = nave.getX();
		nave.moverX(1);
		assertTrue(x<nave.getX());
	}
	
	@Test
	void deberiaMoverPlataformaIzquierda() {
		ArkaPOOB ark = new ArkaPOOB(1,"red",false,false, true, true, false);
		Jugador nave = ark.getJugador().get(0);
		int x = nave.getX();
		nave.moverX(2);
		assertTrue(x>=nave.getX());
	}
	
	
	@Test
	void deberiaCrearBloques() {
		ArkaPOOB ark = new ArkaPOOB(1,"red",false,false, true, true, false);
		ArrayList<ArrayList<Bloque>>blocks = ark.getBloques();
		assertTrue(blocks.size()>0);
	}

}