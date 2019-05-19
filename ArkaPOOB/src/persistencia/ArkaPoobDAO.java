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

			w.println((ark.getNivel()-1)+" "+ ark.getJugadores());
			ArrayList<Elemento> elementos = ark.getElementos();
			for(Elemento e: elementos){
				w.println(e.getClass().getSimpleName()+ " "+ e.toString());
			}
			if (ark.getMaquina() != null)w.println(ark.getMaquina().getTipo());
		}
		catch(IOException e){
			//throw new ArkaPoobException("Ocurrio un error al abrir " + file.getName());
			registreError(e);
		}
		finally{
			w.close();
		}

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
