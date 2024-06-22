/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package view;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import static com.coti.tools.Esdia.yesOrNo;
import controller.Controller;
import static java.lang.System.err;
import static java.lang.System.out;
import java.text.ParseException;



/**
 *
 * @author agarc
 */
public class View {

    Controller c = new Controller();
    
    public void arranque(){
        
         if(!c.bfEmpty()){
            System.out.println("RECUPERANDO LOS DATOS GUARDADOS ANTERIORMENTE");
            try{
              c.leerBin();
            } catch(Exception ex){
                err.printf("%nError. No hay datos guardados anteriormente.%n");
            }
            
        } else{
            out.printf("%nSe cargarán los datos de los ficheros .txt del escritorio.%n");
            try{
               c.importDataFromDesktop(); 
            } catch (Exception ex){
                
            }
           
         }
    }
    
    public void runMenu(String menu) throws ParseException{
        
        boolean salir = false;
        String option;
        
        
        do {
            option = readString(menu);
            switch(option){
                case "1" -> this.Archivos(
                                        "1.- Exportar directores (.col)%n"
                                        + "2.- Exportar peliculas HTML%n"
                                        + "q.- Salir%n"
                                        + " > ");
                case "2" -> this.gestionPeliculas(
                                        "1.- Dar pelicula de alta%n"
                                        + "2.- Dar pelicula de baja%n"
                                        + "3.- Modificar pelicula%n"
                                        + "4.- Consultar pelicula%n"
                                        + "q.- Salir%n"
                                        + " > ");
                case "3" -> this.gestionDirectores(
                                        "1.- Dar director de alta%n"
                                        + "2.- Dar director de baja%n"
                                        + "3.- Modificar director%n"
                                        + "q.- Salir%n"
                                        + " > ");
                case "4" -> this.gestionActores(
                                        "1.- Dar actor de alta%n"
                                        + "2.- Dar actor de baja%n"
                                        + "3.- Modificar datos actor%n"
                                        + "4.- Mostrar peliculas actor%n"
                                        + "q.- Salir%n"
                                        + " > ");          
                case "5" -> this.Listados(
                                        "1.- Imprime peliculas%n"
                                        + "2.- Imprime directores%n"
                                        + "3.- Imprime actores%n"
                                        + "q.- Salir%n"
                                        + " > ");            
                case "q" -> salir = this.runSalida();
                default -> out.printf("%n%nOpcion incorrecta%n%n"); 
            }
            
        } while (!salir);
        
    }
//*******************************************************************************************************
    private void Archivos(String menu1) {
       boolean salir = false;
       
       out.printf("%nSeleccione por orden cada una de las opciones para exportar los datos%n");
       
       do{
           String option = readString_ne(menu1);
           switch(option){
               case "1" -> this.exportDirectores();
               case "2" -> this.exportPeliculasHTML();
               case "q" -> salir = yesOrNo("Quiere volver al menu principal?");
               default -> out.printf("%n%nOpcion incorrecta%n%n");
           }
       } while (!salir);
       
    }
    
    private void exportDirectores(){
       out.printf("%nSe guardara en un fichero la lista de directores en formato (.col)%n");
       
       try{
           c.exportDirectores();
       } catch (Exception ex){
           out.printf("%nError: no se pudo exportar%n");
           out.printf("%nError: %s%n", ex.toString());
       }
    }
    
    private void exportPeliculasHTML(){
        out.printf("%nSe guardara en un fichero HTML la lista de todas las peliculas%n");
        
        try {
            c.exportPeliculasHTML();
        } catch (Exception ex){
            err.printf("%nNo fue posible crear el fichero HTML");
        }
    }
//*******************************************************************************************************
    private void gestionPeliculas(String menu2) {
        boolean salir = false;
        out.printf("%nSeleccione una opcion%n");
        
        do{
            String option = readString_ne(menu2);
            switch (option){
                case "1" -> this.altaPelicula();
                case "2" -> this.bajaPelicula();
                case "3" -> this.modificaPelicula();
                case "4" -> this.consultaPelicula();
                case "q" -> salir = yesOrNo("Quiere volver al menu principal?");
                default -> out.printf("%n%nOpcion incorrecta%n%n");
            }  
        } while (!salir);
    }
    
    private void altaPelicula(){
       out.printf("%nSe piden a continuacion todos los datos de la pelicula%n");
       
       String titulo = readString("%nIntroduce el titulo de la pelicula%n");
       String anno = readString("%nIntroduce el año de estreno de la pelicula%n");
       String duracion = readString("%nIntroduce la duracion en minutos de la pelicula%n");
       String pais = readString("%nIntroduce el pais de produccion de la pelicula%n");
       String guionista = readString("%nIntroduce el nombre del guionista de la pelicula%n");
       String musica = readString("%nIntroduce el nombre de la persona encargada de la musica de la pelicula%n");
       String productora = readString("%nIntroduce el nombre de la productora de la pelicula%n");
       String genero = readString("%nIntroduce el genero de la pelicula%n"); // Comprobar que unicamente se introduce uno de los generos listados en la clase Pelicula
       String sinopsis = readString("%nIntroduce una breve descripcion sobre la pelicula%n");
       int num_directores = readInt("%nCuantos directores desea introducir? :%n");
       String [] direccion = new String[num_directores];
       for(int i = 0; i < num_directores; i++){
           out.printf("%nDirector %d%n", i+1);
           direccion [i] = readString("%nNombre:%n");
       }
       
       int num_actores = readInt("%nCuantos actores desea introducir? :%n");
       String [] reparto = new String[num_actores];
       for(int i = 0; i < num_actores; i++){
            out.printf("%nActor %d%n", i+1);
            reparto [i] = readString("%nNombre:%n");
        }
        
       c.nuevaPelicula(titulo, anno, duracion, pais, direccion, guionista, musica, reparto, productora, genero, sinopsis);
    }
    
    private void bajaPelicula(){
        String nombreElimina = readString("%nIntroduce el titulo de la pelicula para darla de baja%n");
        c.bajaPelicula(nombreElimina);
    }
    
    private void modificaPelicula(){
        String datoNuevo = new String ("Dato nuevo");
        String datoModifica = new String ("Dato modifica");
        boolean salir = false;
        String nombreModifica = readString("%nIntroduce el titulo de la pelicula que desea modificar:%n");
        out.printf("%nSelecciona un dato a modificar");
        String datos = new String ("%n1.- Año%n"
                + "2.- Duracion%n"
                + "3.- Pais%n"
                + "4.- Guionista%n"
                + "5.- Musica%n"
                + "6.- Productora%n"
                + "7.- Genero%n"
                + "8.- Sinopsis%n"
                + "q.- Salir%n"
                + " > ");
        
        do{
            String option = readString_ne(datos);
            switch (option){
                case "1" : datoNuevo = readString("%nIntroduce el nuevo año de estreno de la pelicula:%n");
                           datoModifica = "anno";
                           salir = true;
                           break;
                case "2" : datoNuevo = readString("%nIntroduce la nueva duracion de la pelicula:%n");
                           datoModifica = "duracion";
                           salir = true;
                           break;
                case "3" : datoNuevo = readString("%nIntroduce el nuevo pais de produccion de la pelicula:%n");
                           datoModifica = "pais";
                           salir = true;
                           break;
                case "4" : datoNuevo = readString("%nIntroduce el nombre del nuevo guionista de la pelicula:%n");
                           datoModifica = "guionista";
                           salir = true;
                           break;
                case "5" : datoNuevo = readString("%nIntroduce el nombre del nuevo encargado de la musica de la pelicula:%n");
                           datoModifica = "musica";
                           salir = true;
                           break;
                case "6" : datoNuevo = readString("%nIntroduce el nombre de la nueva productora de la pelicula:%n");
                           datoModifica = "productora";
                           salir = true;
                           break;
                case "7" : datoNuevo = readString("%nIntroduce el nuevo genero de la pelicula:%n");
                           datoModifica = "genero";
                           salir = true;
                           break;
                case "8" : datoNuevo = readString("%nIntroduce una nueva sinopsis de la pelicula:%n");
                           datoModifica = "sinopsis";
                           salir = true;
                           break;
                case "q" : salir = yesOrNo("Quiere volver al menu principal?");
                default : out.printf("%n%nOpcion incorrecta%n%n");
            }  
        } while (!salir);
       
        try{
            c.modificaPelicula(nombreModifica,datoModifica, datoNuevo);
            out.printf("%nSe ha cambiado el dato %s en la pelicula %s con éxito.%n%n", datoModifica, nombreModifica);
        } catch (Exception ex){
            err.printf("%nNo fue posible cambiar el dato solicitado. Intentalo de nuevo.%n");
        }
        
    }
    
    private void consultaPelicula(){
        String tituloPeli = readString("%nIntroduce el nombre de la pelicula para consultar sus datos:%n");
        String [] datosPeli = c.consultaPelicula(tituloPeli);
        if (datosPeli[0] == null){
            out.printf("%n%nError. Esta pelicula no está dada de alta.%n%n");
        } else {
            out.printf("%n");
            out.printf("%nTitulo: %-50s", datosPeli[0]);
            out.printf("%nAño: %-4s", datosPeli[1]);
            out.printf("%nDuración: %-4s", datosPeli[2]);
            out.printf("%nPaís: %-30s", datosPeli[3]);
            out.printf("%nDirección: %-100s", datosPeli[4]);
            out.printf("%nGuionista: %-20s", datosPeli[5]);
            out.printf("%nMusica: %-50s", datosPeli[6]);
            out.printf("%nReparto: %-300s", datosPeli[7]);
            out.printf("%nProductora: %-50s", datosPeli[8]); 
            out.printf("%nGenero: %-30s", datosPeli[9]);
            out.printf("%nSinopsis: %-500s", datosPeli[10]); 
            out.printf("%n%n");
        }
        
    }
//*******************************************************************************************************
    private void gestionDirectores(String menu3) throws ParseException{
        boolean salir = false;
        out.printf("%nSeleccione una opcion%n");
        
        do{
            String option = readString_ne(menu3);
            switch (option){
                case "1" -> this.altaDirector();
                case "2" -> this.bajaDirector();
                case "3" -> this.modificaDirector();
                case "q" -> salir = yesOrNo("Quiere volver al menu principal?");
                default -> out.printf("%n%nOpcion incorrecta%n%n");
            }  
        } while (!salir);
    }

    private void altaDirector() throws ParseException {
        String nombre = readString("%nIntroduce el nombre del director:%n");
        String fecha_nac = readString("%nIntroduce la fecha de nacimiento del director:%n");
        String nacionalidad = readString("%nIntroduce la nacionalidad del director:%n");
        String ocupacion = readString("%nIntroduce la ocupacion del director:%n");
        int num_pelis = readInt("%nCuantas peliculas desea introducir de este director?:%n");
        String [] pelis = new String [num_pelis];
        
        for (int i = 0; i < num_pelis; i++){
            out.printf("%nPelicula %d%n", i+1);
            pelis [i] = readString("%nIntroduce el titulo:%n");
        }
        
        c.altaDirector(nombre, fecha_nac, nacionalidad, ocupacion, pelis);
        
    }

    private void bajaDirector() {
        String err = null;
        String nombreElimina = readString("%nIntroduce el nombre del director a dar de baja:%n");
        err = c.bajaDirector(nombreElimina);
        out.printf("%n%s%n", err);
        out.printf("%n");
    }

    private void modificaDirector() {
        String datoNuevo = new String ("Dato Nuevo");
        String datoModifica = new String ("Dato Modifica");
        boolean salir = false;
        String nombreModifica = readString("%nIntroduce el nombre del director que desea modificar:%n");
        out.printf("%nSelecciona un dato a modificar");
        String datos2 = new String ("%n1.- Fecha de nacimiento%n"
                + "2.- Nacionalidad%n"
                + "3.- Ocupacion%n"
                + "q.- Salir%n"
                + " > ");
         do{
            String option = readString_ne(datos2);
            switch (option){
                case "1" : datoNuevo = readString("%nIntroduce la nueva fecha de nacimiento del director:%n");
                           datoModifica = "fecha_nac";
                           salir = true;
                           break;
                case "2" : datoNuevo = readString("%nIntroduce la nueva nacionalidad del director:%n");
                           datoModifica = "nacionalidad";
                           salir = true;
                           break;
                case "3" : datoNuevo = readString("%nIntroduce la nueva ocupacion del director:%n");
                           datoModifica = "ocupacion";
                           salir = true;
                           break;
                case "q" : salir = yesOrNo("Quiere volver al menu principal?");
                default : out.printf("%n%nOpcion incorrecta%n%n");
            }
            
         } while (!salir);
         
        try{
            c.modificaDirector(nombreModifica, datoModifica, datoNuevo);
            out.printf("%nSe ha cambiado el dato %s en el director %s con éxito.%n%n", datoModifica, nombreModifica);
        } catch (Exception ex){
            err.printf("%nNo fue posible cambiar el dato solicitado. Intentalo de nuevo.%n");
        }
        
    }
    
//*******************************************************************************************************
    private void gestionActores(String menu4) throws ParseException{
        boolean salir = false;
        out.printf("%nSeleccione una opcion%n");
        
        do{
            String option = readString_ne(menu4);
            switch (option){
                case "1" -> this.altaActor();
                case "2" -> this.bajaActor();
                case "3" -> this.modificaActor();
                case "4" -> this.muestraPeliculasActor();
                case "q" -> salir = yesOrNo("Quiere volver al menu principal?");
                default -> out.printf("%n%nOpcion incorrecta%n%n");
            }  
        } while (!salir);
    }
    
    private void altaActor() throws ParseException {
        String nombre = readString("%nIntroduce el nombre del actor:%n");
        String fecha_nac = readString("%nIntroduce la fecha de nacimiento del actor (yyyy-mm-dd):%n");
        String nacionalidad = readString("%nIntroduce la nacionalidad del actor:%n");
        String anno_debut = readString("%nIntroduce el año del debut del actor:%n");
        int num_pelis = readInt("%nCuantas peliculas desea introducir de este actor?:%n");
        String [] pelis = new String[num_pelis];
        
        for(int i = 0; i < num_pelis; i++){
            out.printf("%nPelicula numero %d", i+1);
            pelis[i] = readString("%nTitulo: %n");
        }
        
        
        c.altaActor(nombre, fecha_nac, nacionalidad, anno_debut, pelis);
    }

    private void bajaActor() {
        String err = null;
        String nombreElimina = readString("%nIntroduce el nombre del actor a dar de baja:%n");
        err = c.bajaActor(nombreElimina);
        out.printf("%n%s%n", err);
        out.printf("%n");
    }

    private void modificaActor()  throws ParseException {
        String datoNuevo = new String();
        String datoModifica = new String();
        boolean salir = false;
        String nombreModifica = readString("%nIntroduce el nombre del actor que desea modificar:%n");
        out.printf("%nSelecciona un dato a modificar");
        String datos3 = new String ("%n1.- Fecha de nacimiento%n"
                + "2.- Nacionalidad%n"
                + "3.- Año debut%n"
                + "q.- Salir%n"
                + " > ");
         do{
            String option = readString_ne(datos3);
            switch (option){
                case "1" : datoNuevo = readString("%nIntroduce la nueva fecha de nacimiento del actor (yyyy-mm-dd):%n");
                           datoModifica = "fecha_nac";
                           salir = true;
                           break;
                case "2" : datoNuevo = readString("%nIntroduce la nueva nacionalidad del actor:%n");
                           datoModifica = "nacionalidad";
                           salir = true;
                           break;
                case "3" : datoNuevo = readString("%nIntroduce el nuevo año de debut del actor:%n");
                           datoModifica = "anno_debut";
                           salir = true;
                           break;
                case "q" : salir = yesOrNo("Quiere volver al menu principal?");
                           break;
                default  : out.printf("%n%nOpcion incorrecta%n%n");
            }
            
         } while (!salir);
         
          
        try{
            c.modificaActor(nombreModifica, datoModifica, datoNuevo);
            out.printf("%nSe ha cambiado el dato %s en el actor %s con éxito.%n%n", datoModifica, nombreModifica);
        } catch (Exception ex){
            err.printf("%nNo fue posible cambiar el dato solicitado. Intentalo de nuevo.%n");
        }
    }

    private void muestraPeliculasActor()  throws ParseException{
        boolean salir = false;
        String titulo = "Titulo";
        String anno = "Año";
        String duracion = "Duracion";
        String pais = "Pais";
        String genero = "Genero";
        String nombreActor = readString("%nIntroduce el nombre del actor para mostrar sus peliculas:%n");
        String [] peliculas = c.obtenerPeliculasActor(nombreActor);
        out.printf("%n%-50s  %-4s   %-9s    %-30s    %-10s%n%n", titulo, anno, duracion, pais, genero);
        
        for(String s : peliculas){
            out.println(s);
        }
        
        
    }

    
//*******************************************************************************************************
    private void Listados(String menu5)  throws ParseException{
        boolean salir = false;
        out.printf("%nSeleccione una opcion%n");
        
        do{
            String option = readString_ne(menu5);
            switch (option){
                case "1" -> this.muestraPelisOrd();
                case "2" -> this.muestraDirectorOrd();
                case "3" -> this.muestraActorOrd();
                case "q" -> salir = yesOrNo("Quiere volver al menu principal?");
                default -> out.printf("%n%nOpcion incorrecta%n%n");
            }  
        } while (!salir);
    }

    private void muestraPelisOrd() {
        String [] ord = null;
        String titulo = "Titulo";
        String anno = "Año";
        String duracion = "Duracion";
        String pais = "Pais";
        String genero = "Genero";
        out.printf("%nSe muestra a continuacion un listado con todas las peliculas ordenadas alfabeticamente por titulo:%n");
       
        ord = c.muestraPelisOrd();
        out.printf("%n%-50s  %-4s   %-9s    %-30s    %-10s%n%n", titulo, anno, duracion, pais, genero);
        for(String s : ord){
            out.println(s);  
        }
    }
    
    private void muestraDirectorOrd()  throws ParseException{
        String [] directorOrd = null;
        String nombre = "Nombre";
        String fecha_nac = "Fecha Nacimiento";
        String nacionalidad = "Nacionalidad";
        String ocupacion = "Ocupacion";
        String peliculas = "Peliculas";
        out.printf("%nSe muestra a continuacion un listado con todos los directores ordenados por nacionalidad y año de nacimiento:%n");
        boolean muestraPelis = yesOrNo("Desea que se muestren los titulos de sus peliculas?%n");
       
        directorOrd = c.muestraDirectorOrd(muestraPelis);
        out.printf("%n%-30s%-20s%-20s%-70s%-70s%n%n", nombre, fecha_nac, nacionalidad, ocupacion, peliculas);

        for(String s : directorOrd){
            out.println(s);
        }
    }
    

    private void muestraActorOrd() throws ParseException {
       String [] actorOrd = null;
       String nombre = "Nombre";
       String fecha_nac = "Fecha Nacimiento";
       String nacionalidad = "Nacionalidad";
       String anno_debut = "Año debut";
       String peliculas = "Peliculas";
       out.printf("%nSe muestra a continuacion un listado con todos los actores ordenados por año de debut y nombre:%n");
        boolean muestraPelis = yesOrNo("Desea que se muestren los titulos de sus peliculas?%n");
       
        actorOrd = c.muestraActorOrd(muestraPelis);
        out.printf("%n%-30s%-20s%-20s%-70s%-70s%n%n", nombre, fecha_nac, nacionalidad, anno_debut, peliculas);
        for(String s : actorOrd){
            out.println(s);
        }
    }

    
//*******************************************************************************************************
    private boolean runSalida() {
        boolean salir = false;
        salir = Esdia.yesOrNo("Desea realmente salir? Si es asi, los datos se guardaran. ");
        if (salir){
            c.guardar();
        }
        
        return salir;
    }

   
}
