/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package controller;

import model.Model;
import com.coti.tools.Rutas;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;


/**
 *
 * @author agarc
 */
public class Controller {

    Model m = new Model();
    private final String folder = "IMDB21";
    private final String separador = System.getProperty("file.separator");
    final File peliculasBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "peliculas.bin");
    final File directoresBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "directores.bin");
    final File actoresBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "actores.bin");
    
//***************************************************************************************************************
    
    
    public boolean bfEmpty() {
        if(!peliculasBin.exists()  && !directoresBin.exists() && !actoresBin.exists()){
            return true;
        } else{
            return false;
        }
    }

    public void leerBin() {
        m.leerBin();
    }

    public void importDataFromDesktop() throws ParseException{
        m.importDataFromDesktop();
    }
    
    public void guardar() {
        m.guardarBin();
        
    }

    
//***************************************************************************************************************
    
    
    public void exportDirectores() throws Exception{
        m.exportDirectores();
    }

    public void exportPeliculasHTML() throws FileNotFoundException{
        m.exportPeliculasHTML();
    }

//***************************************************************************************************************


    public void nuevaPelicula(String titulo, String anno, String duracion, String pais, String[] direccion, String guionista, String musica, String[] reparto, String productora, String genero, String sinopsis) {
        
        m.nuevaPelicula(titulo, anno, duracion, pais, direccion, guionista, musica, reparto, productora, genero, sinopsis);
    }
    
     public void bajaPelicula(String nombreElimina) {
         m.bajaPelicula(nombreElimina);
    }

    public void modificaPelicula(String nombreModifica, String datoModifica, String datoNuevo) {
        m.modificaPelicula(nombreModifica, datoModifica, datoNuevo);
    }

    public String[] consultaPelicula(String tituloPeli) {
        return m.consultaPelicula(tituloPeli);
    }
//***************************************************************************************************************
    
    
    public void altaDirector(String nombre, String fecha_nac, String nacionalidad, String ocupacion, String [] pelis)  throws ParseException {
        m.altaDirector(nombre, fecha_nac, nacionalidad, ocupacion, pelis);
    }

    public String bajaDirector(String nombreElimina){
        return  m.bajaDirector(nombreElimina);
    }

    public void modificaDirector(String nombreModifica, String datoModifica, String datoNuevo)  throws ParseException{
        m.modificaDirector(nombreModifica, datoModifica, datoNuevo);
    }
//***************************************************************************************************************
    
    
    public void altaActor(String nombre, String fecha_nac, String nacionalidad, String anno_debut, String[] pelis)  throws ParseException{
        m.altaActor(nombre, fecha_nac, nacionalidad, anno_debut, pelis);
    }

    public String bajaActor(String nombreElimina) {
        String err = null;
        err = m.bajaActor(nombreElimina);
        return err;
    }

    public void modificaActor(String nombreModifica, String datoModifica, String datoNuevo)  throws ParseException{
        m.modificaActor(nombreModifica, datoModifica, datoNuevo);
    }

    public String[] obtenerPeliculasActor(String nombreActor) {
        return m.obtenerPeliculasActor(nombreActor);
    }
    
//***************************************************************************************************************

    public String[] muestraPelisOrd() {
        return m.muestraPelisOrd();
        
    }
    
    public String[] muestraDirectorOrd(boolean muestraPelis) throws ParseException{
        return m.muestraDirectorOrd(muestraPelis);
    }
    
    public String[] muestraActorOrd(boolean muestraPelis) throws ParseException{
        return m.muestraActorOrd(muestraPelis);
    }
    
   
}
