/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.coti.tools.Rutas;
import static com.coti.tools.OpMat.importFromDisk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.err;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.lang.String.format;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.time.LocalDate;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



/**
 *
 * @author agarc
 */
public class Model implements java.io.Serializable{
    
    private final String folder = "IMDB21";
    private final String pelis = "peliculas.txt";
    private final String dirige = "directores.txt";
    private final String actua = "actores.txt";
    private final String separador = System.getProperty("file.separator");
    final File peliculasBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "peliculas.bin");
    final File directoresBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "directores.bin");
    final File actoresBin = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "actores.bin");

    
    private ArrayList<Pelicula> peliculas = null;
    private ArrayList<Director> directores = null;
    private ArrayList<Actor> actores = null;
    
    public Model(){
        this.peliculas = new ArrayList<>();
        this.directores = new ArrayList<>();
        this.actores = new ArrayList<>();
    }

    public void importDataFromDesktop() throws ParseException{
       
       Path p1 = Rutas.pathToFileInFolderOnDesktop(folder, pelis);
       File f1 = p1.toFile();
        String [][] tokens = null;
       
       try{
           tokens = importFromDisk(f1, "#");
       } catch (Exception ex){
           err.println("%nNo fue posible importar el archivo peliculas.txt%n");
       }
       
       for(String [] s : tokens){
           Pelicula peli = Pelicula.nuevaPelicula(s);
           if(null!=peli){
               peliculas.add(peli);
           }
       }
       
       Path p2 = Rutas.pathToFileInFolderOnDesktop(folder, dirige);
       File f2 = p2.toFile();
       String [][] tokens2 = null;
       
      try{
          tokens2 = importFromDisk(f2, "#");
      } catch (Exception ex){
            err.println("%nNo fue posible importar el archivo actores.txt%n");
      }
      for(String[] s : tokens2){
          Director d = Director.crearDirector(s);
          if(null != d){
             directores.add(d);
          }
      }
       
       
       Path p3 = Rutas.pathToFileInFolderOnDesktop(folder, actua);
       File f3 = p3.toFile();
       String [][] tokens3 = null;
       
       try{
           tokens3 = importFromDisk(f3, "#");
       } catch (Exception ex){
           err.println("%nNo fue posible importar el archivo actores.txt%n");
       }
       
       for(String[] s : tokens3){
           Actor act = Actor.crearActor(s);
           if (null!=act){
               actores.add(act);
           }
       }    
       
    }
    
//*********************************************************************************************************************************************
    public void exportDirectores() throws Exception {
        boolean muestraPelis = true;
        File f = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador +  "directores" + ".col");
        Path p = f.toPath();
        List<String> director = new ArrayList<>();
        for(Director d : directores){
            director.add(d.exportStateAsColumns(muestraPelis));
        }
        Files.write(p, director, Charset.forName("UTF8"));
    }
    
    public void exportPeliculasHTML() throws FileNotFoundException{
        File f = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador + "peliculas" + ".html");
        
            PrintWriter pw = new PrintWriter(f);
            pw.printf("<!DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset=\"UTF-8\">%n"
                    + "<HEAD>%n"
                    + "<BODY>"
                    + "<H1>PELICULAS</H1>%n"
                    + "<TABLE BORDER = 1>%n"
                    + "<TBODY>"
                    + "<TR>"
                    + "<TD>Titulo</TD>"
                    + "<TD>Año</TD>"
                    + "<TD>Duracion</TD>"
                    + "<TD>País</TD>"
                    + "<TD>Dirección</TD>"
                    + "<TD>Guionista</TD>"
                    + "<TD>Musica</TD>"
                    + "<TD>Reparto</TD>"
                    + "<TD>Productora</TD>"
                    + "<TD>Género</TD>"
                    + "<TD>Sinopsis</TD>"
                    + "</TR>"
                    + "<TR>"
                    + "</TR>");
            
            for(Pelicula p : peliculas){
                pw.printf("%s%n", p.exportAsHTMLRow());
            }
            pw.printf("</TBODY>%n</TABLE>%n</BODY>%n</HTML>%n");
            pw.close();
           
    }
    
   
    
//**********************************************************************************************************************************************************************************************
    
    
    public void nuevaPelicula(String titulo, String anno, String duracion, String pais, String[] direccion, String guionista, String musica, String[] reparto, String productora, String genero, String sinopsis) {
        String [] peli = new String[11];
        String dirige = new String();
        String actua = new String();
        Pelicula nuevaPeli = new Pelicula();
        peli [0] = titulo;
        peli [1] = anno;
        peli [2] = duracion;
        peli [3] = pais;
        for(int i = 0; i < direccion.length; i++){
          dirige += direccion[i] + "\t";  
        }
        peli [4] = dirige;
        peli [5] = guionista;
        peli [6] = musica;
        for(int i = 0; i < reparto.length; i++){
          actua += reparto[i] + "\t";  
        }
        peli [7] = actua;
        peli [8] = productora;
        peli [9] = genero;
        peli [10] = sinopsis;
        
        
        for(int i = 0; i < direccion.length; i++){
            if(directores.contains(direccion[i])){
                nuevaPeli.setTitulo(titulo);
                directores.get(directores.size()).getPeliculas().add(nuevaPeli);
            } else{
                directores.add(Director.nuevoDirector(direccion[i], titulo));
            }
        }
        
        
        for(int j = 0; j < reparto.length; j++){
            if(actores.contains(reparto[j])){
                nuevaPeli.setTitulo(titulo);
                actores.get(actores.size()).getPeliculas().add(nuevaPeli);
            } else {
                actores.add(Actor.nuevoActor(reparto[j], titulo));
            }
        }
        
        peliculas.add(Pelicula.nuevaPelicula(peli));
    }

    public void bajaPelicula(String nombreElimina) {
        String titulo = new String();
        for (int i = 0; i < peliculas.size(); i++){
            if (peliculas.get(i).getTitulo().equals(nombreElimina)){
                peliculas.remove(i);
                
            }
        }
        
        for(int j = 0; j < directores.size(); j++){
            for (int k = 0; k < directores.get(j).getPeliculas().size(); k++){
               if(directores.get(j).getPeliculas().get(k).getTitulo().equals(nombreElimina)){
               directores.get(j).getPeliculas().remove(k);
               }
            }
           
        }
        
        for (int k = 0; k < actores.size(); k++){
            if(actores.get(k).getPeliculas().get(k).getTitulo().equals(nombreElimina)){
                actores.get(k).getPeliculas().remove(10);
            }
        }
    }

    public void modificaPelicula(String nombreModifica,String datoModifica, String datoNuevo) {
        int newAnno = -1;
        int newDuracion = -1;
        for (int i = 0; i < peliculas.size(); i++){
            if (peliculas.get(i).getTitulo().equals(nombreModifica)){
                if (datoModifica.equals("anno")){
                    newAnno = Integer.parseInt(datoNuevo);
                    peliculas.get(i).setAnno(newAnno);
                } else if (datoModifica.equals("duracion")){
                    newDuracion = Integer.parseInt(datoNuevo);
                    peliculas.get(i).setDuracion(newDuracion);
                } else if (datoModifica.equals("pais")){
                    peliculas.get(i).setPais(datoNuevo);          
                } else if (datoModifica.equals("guionista")){
                    peliculas.get(i).setGuionista(datoNuevo);
                } else if (datoModifica.equals("musica")){
                    peliculas.get(i).setMusica(datoNuevo);
                } else if (datoModifica.equals("productora")){
                    peliculas.get(i).setProductora(datoNuevo);
                } else if (datoModifica.equals("genero")){
                    peliculas.get(i).setGenero(datoNuevo);
                } else if (datoModifica.equals("sinopsis")){
                    peliculas.get(i).setSinopsis(datoNuevo);
                }
            }
        }
        
    }

    public String[] consultaPelicula(String tituloPeli) {
       String [] datosPeli = new String[11];
       String dirige = new String();
       String actua = new String();
       for(int i = 0; i < peliculas.size(); i++){
           if(peliculas.get(i).getTitulo().equalsIgnoreCase(tituloPeli)){
               datosPeli = peliculas.get(i).obtenerDatos();
           }
       }
       return datosPeli;
    }

    //****************************************************************************************************************************************************

    public void altaDirector(String nombre, String fecha_nac, String nacionalidad, String ocupacion, String [] pelis)  throws ParseException{
        String director[] = new String[5];
        String titulos = new String();
        director [0] = nombre;
        director [1] = fecha_nac;
        director [2] = nacionalidad;
        director [3] = ocupacion;
        
        for(int i = 0; i < pelis.length; i++){
            titulos += pelis[i] + "\t";
        }
        
        director [4] = titulos;
        
        directores.add(Director.crearDirector(director));
        
        
    }

    public String bajaDirector(String nombreElimina) {
        String err = new String(); 
        String titulo = new String();
        int index = 0;
        
        for(int i = 0; i < directores.size(); i++){
            if(directores.get(i).getNombre().equals(nombreElimina)){
               index = i; 
        }
            }
            
        for(int j = 0; j < directores.get(index).getPeliculas().size(); j++){
            for(int k = 0; k < peliculas.size(); k++){
                if(directores.get(index).getPeliculas().get(j).getTitulo().equals(peliculas.get(k).getTitulo())){
                    titulo += peliculas.get(k).getTitulo() + "\t";
                }
            }
        }
        
        if(titulo.equals("")){
            directores.remove(index);
            return err = "Exito, se ha dado de baja el director solicitado. ";
        } 
            return err = "No fue posible dar de bajar el director solicitado." + " Estas son sus peliculas que siguen dadas de alta: " + titulo;
        

        
        
    
    }

    public void modificaDirector(String nombreModifica, String datoModifica, String datoNuevo)  throws ParseException{
        for(int i = 0; i < directores.size(); i++){
            if(directores.get(i).getNombre().equalsIgnoreCase(nombreModifica)){
                if(datoModifica.equals("fecha_nac")){
                    directores.get(i).setFecha_nac(LocalDate.parse(datoNuevo));
                } else if(datoModifica.equals("nacionalidad")){
                    directores.get(i).setNacionalidad(datoNuevo);
                } else if(datoModifica.equals("ocupacion")){
                    directores.get(i).setOcupacion(datoNuevo);
                }
            }
        }
    }
    
//****************************************************************************************************************************************************

    public void altaActor(String nombre, String fecha_nac, String nacionalidad, String anno_debut, String [] titulos) throws ParseException{
        String [] actor = new String [5];
        String pelis = new String();
        actor [0] = nombre;
        actor [1] = fecha_nac;
        actor [2] = nacionalidad;
        actor [3] = String.valueOf(anno_debut);
        
        for(int i = 0; i < titulos.length; i++){
            pelis += titulos[i] + "\t";
        }
        
        actor[4] = pelis;
        
        actores.add(Actor.crearActor(actor));
       
        
    }
    
    public String bajaActor(String nombreElimina){
       String err = new String(); 
        String titulo = new String();
        int index = 0;
        
        for(int i = 0; i < actores.size(); i++){
            if(actores.get(i).getNombre().equals(nombreElimina)){
               index = i; 
        }
            }
            
        for(int j = 0; j < actores.get(index).getPeliculas().size(); j++){
            for(int k = 0; k < peliculas.size(); k++){
                if(actores.get(index).getPeliculas().get(j).getTitulo().equals(peliculas.get(k).getTitulo())){
                    titulo += peliculas.get(k).getTitulo() + "\t";
                }
            }
        }
        
        if(titulo.equals("")){
            actores.remove(index);
            return err = "Exito, se ha dado de baja el actor solicitado. ";
        } 
            return err = "No fue posible dar de bajar el actor solicitado." + " Estas son sus peliculas que siguen dadas de alta: " + titulo;
        
    }

    public void modificaActor(String nombreModifica, String datoModifica, String datoNuevo) throws ParseException {
        int newAnno_debut = -1;
        for(int i = 0; i < actores.size(); i++){
            if(actores.get(i).getNombre().equalsIgnoreCase(nombreModifica)){
                if(datoModifica.equals("fecha_nac")){
                    actores.get(i).setFecha_nac(LocalDate.parse(datoNuevo));
                } else if(datoModifica.equals("nacionalidad")){
                    actores.get(i).setNacionalidad(datoNuevo);
                } else if(datoModifica.equals("anno_debut")){
                    newAnno_debut = Integer.parseInt(datoNuevo);
                    actores.get(i).setAnno_debut(newAnno_debut);
                }
            }
        }
    }

    public String[] obtenerPeliculasActor(String nombreActor) {
        
        int index = 0;

        Collections.sort(peliculas, new Comparator<Pelicula>(){
            public int compare(Pelicula p1, Pelicula p2){
                return new Integer (p1.getAnno()).compareTo(new Integer (p2.getAnno()));
            }
        });
        
        for(int i = 0; i < actores.size(); i++){
            if(actores.get(i).getNombre().equals(nombreActor)){
               index = i;
            }
        }
        
        String [] pelisOrdActor = new String[actores.get(index).getPeliculas().size()];
         
        for(int j = 0; j < actores.get(index).getPeliculas().size(); j++){
                   for(int k = 0; k < peliculas.size(); k++){
                       if(actores.get(index).getPeliculas().get(j).getTitulo().equals(peliculas.get(k).getTitulo())){
                           pelisOrdActor [j] = peliculas.get(k).exportStateAsColumns();
                       } else{
                           pelisOrdActor[j] = actores.get(index).getPeliculas().get(j).exportStateAsColumns();
                       }
                   }
               }
        
        
       
       return pelisOrdActor;
    }
    
//****************************************************************************************************************************************************

    public String [] muestraPelisOrd() {
        String [] pelisOrd = new String[peliculas.size()];
        Collections.sort(peliculas, new Comparator<Pelicula>(){
            public int compare(Pelicula p1, Pelicula p2){
                return p1.getTitulo().compareTo(p2.getTitulo());
            }
        });
       
        for(int i = 0; i < peliculas.size(); i++){
            pelisOrd [i] = peliculas.get(i).exportStateAsColumns();
        } 
        
        return pelisOrd;       
    }
    
    
    public String [] muestraDirectorOrd(boolean muestraPelis) throws ParseException{
        String [] directorOrd = new String[directores.size()];
        
        Collections.sort(directores, new Comparator<Director>(){
            public int compare(Director d1, Director d2){
                return d1.getFecha_nac().compareTo(d2.getFecha_nac());
            }
        });
        
        Collections.sort(directores, new Comparator<Director>(){
            public int compare(Director d3, Director d4){
                return d3.getNacionalidad().compareTo(d4.getNacionalidad());
            }
        });
        
        for(int j = 0; j < directores.size(); j++){
            directorOrd [j] = directores.get(j).exportStateAsColumns(muestraPelis);
        } 
        
        return directorOrd;  
        
         
    }
    
    public String[] muestraActorOrd(boolean muestraPelis) throws ParseException{
        String [] actorOrd = new String[actores.size()];
        Collections.sort(actores, new Comparator<Actor>(){
            public int compare(Actor a1, Actor a2){
                return a1.getNombre().compareTo(a2.getNombre());
            }
        });
        
        Collections.sort(actores, new Comparator<Actor>(){
            public int compare(Actor a1, Actor a2){
                return new Integer (a1.getAnno_debut()).compareTo(new Integer (a2.getAnno_debut()));
            }
        });
        
       
        for(int i = 0; i < actores.size(); i++){
            actorOrd [i] = actores.get(i).exportStateAsColumns(muestraPelis);
        } 
        
        return actorOrd;  
    }
    
//*******************************************************************************************************************************
    
    public void leerBin(){
       try{
           ObjectInputStream file = new ObjectInputStream(new FileInputStream(peliculasBin));
           peliculas = (ArrayList<Pelicula>) file.readObject();
           file.close();
       } catch(IOException | ClassNotFoundException e){
           System.err.printf("ERROR al cargar el fichero peliculas.bin.%n");
       }
       
       try{
           ObjectInputStream file2 = new ObjectInputStream(new FileInputStream(directoresBin));
           directores = (ArrayList<Director>) file2.readObject();
           file2.close();
       } catch(IOException | ClassNotFoundException e){
           System.err.printf("ERROR al cargar el fichero peliculas.bin.%n");
       }
       
       try{
           ObjectInputStream file3 = new ObjectInputStream(new FileInputStream(actoresBin));
           actores = (ArrayList<Actor>) file3.readObject();
           file3.close();
       } catch(IOException | ClassNotFoundException e){
           System.err.printf("ERROR al cargar el fichero peliculas.bin.%n");
       }
    }
    
    public void guardarBin(){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(peliculasBin));
            file.writeObject(peliculas);
            file.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            ObjectOutputStream file2 = new ObjectOutputStream(new FileOutputStream(directoresBin));
            file2.writeObject(directores);
            file2.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            ObjectOutputStream file3 = new ObjectOutputStream(new FileOutputStream(actoresBin));
            file3.writeObject(actores);
            file3.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
   
    
}
 