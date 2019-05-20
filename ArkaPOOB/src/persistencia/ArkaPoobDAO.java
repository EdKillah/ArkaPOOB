package persistencia;

import java.io.*;
import java.lang.reflect.*;
import aplicacion.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArkaPoobDAO implements Serializable{
	
	public ArkaPoobDAO() {}
	
	public void guardar(ArkaPOOB ac, File file) throws ArkaPoobException {
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ac);
		}catch (IOException e) {
			throw new ArkaPoobException("Ocurrio un error al salvar " + file.getName());
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el archivo.");
			}
		}
	}
	
	public ArkaPOOB abrir(File file) throws ArkaPoobException{
		ArkaPOOB auto = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			auto = (ArkaPOOB)in.readObject();
			in.close();
		}catch(Exception e) {
			//throw new ArkaPoobException("Ocurrio un error al abrir " + file.getName());
			registreError(e);
		}
		return auto;
	}
	
	public void exportar(ArkaPOOB ark,File file) throws ArkaPoobException{
		PrintWriter w = null;
		try{
			w = new PrintWriter(new FileOutputStream(file));

			w.println(ark.getNivel()-1);
			w.println(ark.getJugadores());
			w.println(ark.getColores()[0]+" "+ark.getColores()[1]+" "+ark.getColores()[2]+" "+ark.getColores()[3]+" "+ark.getColores()[4]);
			ArrayList<Elemento> elementos = ark.getElementos();
			for(Elemento e: elementos){
				w.println(e.getClass().getSimpleName()+ " "+ e.toString());
			}
		}
		catch(IOException e){
			//throw new ArkaPoobException("Ocurrio un error al abrir " + file.getName());
			registreError(e);
		}
		finally{
			w.close();
		}
	}
	
	public ArkaPOOB importe(File file) throws ArkaPoobException{
		BufferedReader in = null;
		ArkaPOOB ark = null;
		int nivel;
		try {
			in = new BufferedReader(new FileReader(file));	
			// nivel - jugadores
			nivel = Integer.parseInt(in.readLine());
			int jugadores = Integer.parseInt(in.readLine());
			String[] ar = in.readLine().split(" ");
			
			ark = new ArkaPOOB(jugadores,Boolean.valueOf(ar[0]),Boolean.valueOf(ar[1]),Boolean.valueOf(ar[2]),Boolean.valueOf(ar[3]),Boolean.valueOf(ar[4]));
			ark.setNivel(nivel);
			
			String ln = in.readLine();
			ark.setBloques(new ArrayList<ArrayList<Bloque>>());
			ark.setNaves(new ArrayList<Jugador>());
			while(ln!=null){
				ar = ln.split(" ");
				
				try {
					if(ar[0].equals("BloqueGris") || ar[0].equals("BloqueRojo") || ar[0].equals("BloqueVerde") || ar[0].equals("BloqueAmarillo") || ar[0].equals("BloqueAzul") || ar[0].equals("BloqueNaranja") || ar[0].equals("BloqueNegro")|| ar[0].equals("BloqueNegro")|| ar[0].equals("BloqueRosa")){
						ark.addBloque(Integer.parseInt(ar[1]),Integer.parseInt(ar[2]),Integer.parseInt(ar[3]),Integer.parseInt(ar[4]),Boolean.valueOf(ar[5]),ar[0]);
					}else if(ar[0].equals("Jugador") || ar[0].equals("JugadorMimo") || ar[0].equals("JugadorDestructor") || ar[0].equals("JugadorCurioso") ) {  
						ark.addJugador(Integer.parseInt(ar[1]),Integer.parseInt(ar[2]),Integer.parseInt(ar[3]),Integer.parseInt(ar[4]),Integer.parseInt(ar[5]),ar[6],Integer.parseInt(ar[7]),ar[8],ar[0]);
					}else if(ar[0].equals("Bola")) {
						ark.addBola(Integer.parseInt(ar[1]),Integer.parseInt(ar[2]));
					}
					else{
						throw new ArkaPoobException("Elemento inexistente.");
					}
				}catch (NumberFormatException e) {
					throw new ArkaPoobException("Los datos del archivo son erroneos.");
				}catch (ArkaPoobException e) {
					throw e;
				}
				catch(Exception e) {
					registreError(e);
					//throw new ArkaPoobException("Los datos del archivo son erroneos."+ e.getMessage());
				}
				ln = in.readLine();
			}
		}catch(IOException e){

			throw new ArkaPoobException("Error al realizar la lectura " + e.getMessage());
		}
		catch (NumberFormatException e) {
			throw new ArkaPoobException("Los datos del archivo son erroneos.");
		}
		finally{
			try {
				in.close();
			} catch (IOException e) {
				throw new ArkaPoobException("Error al cerrar el archivo.");
			}
		}
		return ark;
	}
	
	public static void registreError(Exception error){
		PrintWriter w = null;
		try{
			new File("logs").mkdirs();
			Logger logger = Logger.getLogger("errores");
			logger.setUseParentHandlers(false);
			FileHandler file2 = new FileHandler("logs/errores.log",true);
			file2.setFormatter(new SimpleFormatter());
			logger.addHandler(file2);
			logger.log(Level.SEVERE, error.toString(), error);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
