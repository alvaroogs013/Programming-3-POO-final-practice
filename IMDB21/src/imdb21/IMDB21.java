/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package imdb21;

import static java.lang.System.out;
import view.View;
import java.text.ParseException;

/**
 *
 * @author agarc
 */
public class IMDB21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        View v = new View();
        
        out.println("Aplicacion para gestionar base de datos de paliculas");
        out.println("=====================================================");
        out.printf("%n%nArranque...%n%n");
        out.println("Para comenzar, se debe realizar la carga inicial de las peliculas");
        v.arranque();
        v.runMenu("1.- Archivos%n"
                + "2.- Peliculas%n"
                + "3.- Directores%n"
                + "4.- Actores%n"
                + "5.- Listados%n"
                + "q.- Salir%n"
                + " > ");
    }
    
     
    
}
