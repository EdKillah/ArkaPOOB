package persistencia;

import java.io.*;
import java.lang.reflect.*;
import aplicacion.*;

public class ArkaPoobDAO implements Serializable{
	
	public ArkaPoobDAO() {}
	
	public void guardar(ArkaPOOB ac, File file) throws ArkaPoobException {
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ac);
		}catch (IOException e) {
			throw new ArkaPoobException("Ocurrio un error al salvar " + file.getName()+ " " + e.getMessage());
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
			throw new ArkaPoobException("Ocurrio un error al abrir " + file.getName());
		}
		return auto;
	}
}
