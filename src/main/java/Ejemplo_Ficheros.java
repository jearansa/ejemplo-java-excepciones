
import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;



public class Ejemplo_Ficheros{

	public static void main (String [] args){

		
		try{
        	File file = new File("entrada_salida.txt");
			// Lanza NullPointerException, si la cadena es vac�a;
			// No es obligatorio gestionarla

	    	// Crea el fichero si no existe
    		boolean success = file.createNewFile();
			// Lanza IOException o SecurityException
			// IOException es obligatorio gestionarla, SecurityException no
    		if (success) {
    			// El fichero no existe y se crea:
    			System.out.println("El fichero no existe y se ha creado");

    			// Comprueba que el fichero
				// se puede escribir y leer:
				System.out.println ("El fichero se puede escribir "
					+ file.canWrite());
				System.out.println ("El fichero se puede leer "
					+ file.canRead());

				// Le asociamos al fichero
				// un b�fer de escritura:
				BufferedWriter file_escribir = new BufferedWriter (new FileWriter (file));
				// Lanza IOException; gesti�n obligatoria

				// Escribimos cadenas
				// de caracteres en el fichero
				// Separadas por saltos de l�neas:
				file_escribir.write ("Una primera sentencia:");
				// Lanza IOException; gesti�n obligatoria
				file_escribir.newLine ();
				file_escribir.write ("8.5");
				// Lanza IOException; gesti�n obligatoria
				file_escribir.newLine();
				file_escribir.write("6");
				// Lanza IOException; gesti�n obligatoria
				file_escribir.newLine();
				// Vaciamos el b�fer de escritura antes de cerrarlo
				// En realidad la operaci�n "close()" ya llama a "flush()"
				// https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/io/Writer.html#close()
				file_escribir.flush(); // Lanza IOException; gesti�n obligatoria
				file_escribir.close(); // Lanza IOException; gesti�n obligatoria

				// Abrimos ahora el fichero para lectura
				// por medio de la clase Scanner:
				Scanner file_lectura = new Scanner (file);
				// Lanza FileNotFoundException
				// Es de gesti�n obligatoria, hereda de IOException

				// Leemos cadenas de caracteres del mismo:
				String leer = file_lectura.nextLine();
				// Lanza IllegalStateException
				// o NoSuchElementException
				// No son de gesti�n obigatoria
				String leer2 = file_lectura.nextLine();
				// Lanza IllegalStateException
				// o NoSuchElementException
				// No son de gesti�n obigatoria
				String leer3 = file_lectura.nextLine();
				// Lanza IllegalStateException
				// o NoSuchElementException
				// No son de gesti�n obigatoria
				
				// Nos aseguramos de cerrar el Scanner que hemos usado para lectura:
				file_lectura.close();
				
				// Intentamos convertir cada cadena
				// a su tipo de dato original:
				double leer_double;
				int leer_int;
				leer_double = Double.parseDouble(leer2);
				// Lanza NumberFormatException
				leer_int = Integer.parseInt (leer3);

				// Mostramos por la consola
				// las diversas cadenas:

    			System.out.println ("La cadena leida es " + leer);
				System.out.println ("El double leido " + leer_double);
				System.out.println ("El entero leido " + leer_int);
    		}
        	else {
    		    // El fichero ya existe:
				System.out.println("El fichero ya existe y no se cre�");
      		}
    	}
		catch (FileNotFoundException f_exception) {
			// Excepcion que surge si no encontramos el fichero
			// al crear el Scanner
			// Podr�a ser capturada por IOException, si cambiamos el orden
			System.out.println ("Las operaciones de lectura no se han podido llevar a cabo,");
			System.out.println ("ya que ha sucedido un problema al buscar el fichero para lectura");
			System.out.println (f_exception.toString());
		}
		catch (IOException io_exception){
			// Excepci�n que puede surgir en
			// alguna de las operaciones de escritura
			System.out.println("Ocurri� alg�n error de entrada y salida");
			System.out.println (io_exception.toString());
		}
		catch (NumberFormatException nb_exception){
			// Excepci�n que ocurre al realizar una
			// conversi�n de una cadena
			// de caracteres a un tipo num�rico
			System.out.println ("Ha ocurrido un error de conversi�n de cadenas a numeros");
			System.out.println (nb_exception.toString());
		}
		catch (NoSuchElementException nse_exception){
			// Excepci�n que ocurre cuando el m�todo
			// "nextLine(): String" no encuentra una cadena
			System.out.println ("No se ha podido encontrar el siguiente elemento del Scanner");
			System.out.println (nse_exception.toString());
		}
		catch (Exception e_exception){
			// Un �ltimo bloque que nos permita
			// capturar cualquier tipo de excepci�n
			System.out.println (e_exception.toString());
		}
		finally{
			// file.delete();
			// En este caso el finally no debe hacer nada, 
			// todas las operaciones "de limpieza", como "flush()" � "close()"
			// se realizaron dentro del "try"
		}
	}
}
