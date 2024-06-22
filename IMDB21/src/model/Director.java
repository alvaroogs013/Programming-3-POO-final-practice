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
public class Director implements java.io.Serializable{
    
    private String nombre;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private String ocupacion;
    private ArrayList<Pelicula> titulos = new ArrayList<>();
    
    
    
    static Director crearDirector(String [] s) throws ParseException{
        Director d = new Director();
        for(int i = 0; i < s.length; i++){
            if(!s[1].isEmpty()){
                d.setFecha_nac(LocalDate.parse(s[1]));
            } 
        }
        d.setNombre(s[0]);
        if (!s[2].isEmpty()){
            d.setNacionalidad(s[2]);
        }
        if (!s[3].isEmpty()){
            d.setOcupacion(s[3]);
        }
        
        String [] pelis = s[4].split("\t");
        for(int j = 0; j < pelis.length; j++){
            Pelicula peli = new Pelicula();
            peli.setTitulo(pelis[j]);
            if(peli != null){
                d.titulos.add(peli);
            }
        }
        
           
        
         return d;
    }
    
    static Director nuevoDirector(String nombre, String titulo){
        Director d = new Director();
        Pelicula peli = new Pelicula();
        d.nombre = nombre ;
        d.fecha_nac = null;
        d.nacionalidad = "";
        d.ocupacion = "";
        peli.setTitulo(titulo);
        d.titulos.add(peli);
        
        return d;
    }
    // Constructor de la clase Director
    
    public Director(String nombre, LocalDate fecha_nac, String nacionalidad, String ocupacion) {
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
    }
    
     public Director() {
         this.nombre = "";
         this.fecha_nac = LocalDate.parse("0001-01-01");
         this.nacionalidad = "";
         this.ocupacion = "";
         this.titulos = new ArrayList();

    }
    
   

   public String exportStateAsColumns(boolean muestraPelis) throws ParseException{
       String titulo = new String();
       if (muestraPelis == true){
           for (int i = 0; i < this.titulos.size(); i++){
                titulo += this.titulos.get(i).getTitulo() + "\t";
           }
        } else {
            return String.format("%-30s%-20s%-20s%-70s", 
                    this.nombre,
                    this.fecha_nac,
                    this.nacionalidad,
                    this.ocupacion);
          }
       
       return String.format("%-30s%-20s%-20s%-70s%-80s", 
                this.nombre,
                this.fecha_nac,
                this.nacionalidad,
                this.ocupacion,
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

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return titulos;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.titulos = peliculas;
    }

    
}