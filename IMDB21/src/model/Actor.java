/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author agarc
 */
public class Actor implements java.io.Serializable{
    
    private String nombre;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private int anno_debut;
    private ArrayList<Pelicula> peliculas = null;

    
    static Actor crearActor(String [] s) throws ParseException{
        Actor a = new Actor();
        for(int i = 0; i < s.length; i++){
            if(!s[1].isEmpty()){
                a.setFecha_nac(LocalDate.parse(s[1]));
            } 
        }
        a.setNombre(s[0]);
        if (!s[2].isEmpty()){
            a.setNacionalidad(s[2]);
        }
        if (!s[3].isEmpty()){
            a.setAnno_debut(Integer.parseInt(s[3]));
        }
        
        String [] pelis = s[4].split("\t");
        for(int j = 0; j < pelis.length; j++){
            Pelicula peli = new Pelicula();
            peli.setTitulo(pelis[j]);
            if(peli != null){
                a.peliculas.add(peli);
            }
        }
        
           
        
         return a;
    }
    
    static Actor nuevoActor(String nombre, String titulo){
        Actor a = new Actor();
        Pelicula peli = new Pelicula();
        a.nombre = nombre ;
        a.fecha_nac = null;
        a.nacionalidad = "";
        a.anno_debut = -1;
        peli.setTitulo(titulo);
        a.peliculas.add(peli);
        
        return a;
    }
    // Constructor de la clase Actor
    
    public Actor(String nombre, LocalDate fecha_nac, String nacionalidad, int anno_debut, ArrayList<Pelicula> peliculas) {
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.anno_debut = anno_debut;
        this.peliculas = peliculas;
    }
    
     public Actor() {
        this.nombre = "-";
        this.fecha_nac = LocalDate.parse("0001-01-01");
        this.nacionalidad = "-";
        this.anno_debut = 0;
        this.peliculas = new ArrayList();
    }
     
    

    public String exportStateAsColumns(boolean muestraPelis){
        String titulo = new String();
        
        if (muestraPelis == true){
            for (int i = 0; i < peliculas.size(); i++){
                titulo += this.peliculas.get(i).getTitulo() + "\t";
           }
           
        } else {
            return String.format("%-30s%-20s%-20s%-70d", 
                this.nombre,
                this.fecha_nac,
                this.nacionalidad,
                this.anno_debut);
        }
        return String.format("%-30s%-20s%-20s%-70d%-80s", 
                this.nombre,
                this.fecha_nac,
                this.nacionalidad,
                this.anno_debut,
                titulo);
        
    }
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAnno_debut() {
        return anno_debut;
    }

    public void setAnno_debut(int anno_debut) {
        this.anno_debut = anno_debut;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

   
}
