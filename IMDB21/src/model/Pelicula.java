/*
* Alumno: Álvaro García Sánchez
* DNI: 70924450V
* Correo: agarsan@usal.es
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author agarc
 */
public class Pelicula implements java.io.Serializable{
    
    private String titulo;
    private int anno;
    private int duracion;
    private String pais;
    private ArrayList<Director> direccion = new ArrayList<>();
    private String guionista;
    private String musica;
    private ArrayList<Actor> reparto = new ArrayList<>();
    private String productora;
    private String genero;
    private String sinopsis;
    
    
    static Pelicula nuevaPelicula(String[] tokens) {
        Pelicula peli = new Pelicula();
        String [] tokens1 = tokens[4].split("\t");
        String [] tokens2 = tokens[7].split("\t");
        peli.titulo = tokens[0];
        peli.anno = Integer.parseInt(tokens[1]);
        peli.duracion = Integer.parseInt(tokens[2]);
        peli.pais = tokens[3];
        for(int i = 0; i < tokens1.length; i++){
            Director d = new Director();
            d.setNombre(tokens1[i]);
            if(d != null){
                peli.direccion.add(d);
            }
        }
        peli.guionista = tokens[5];
        peli.musica = tokens[6];
        for(int j = 0; j < tokens2.length; j++){
            Actor a = new Actor();
            a.setNombre(tokens2[j]);
            if(a != null){
                peli.reparto.add(a);
            }
        }
        peli.productora = tokens[8];
        peli.genero = tokens[9];
        peli.sinopsis = tokens[10];
        
        return peli;
    }
    
    
    public String[] obtenerDatos(){
        String [] datosPeli = new String[11];
        String dirige = new String();
        String actua = new String();
        datosPeli[0] = this.titulo;
        datosPeli[1] = String.valueOf(this.anno);
        datosPeli[2] = String.valueOf(this.duracion);
        datosPeli[3] = this.pais;
        for (int j = 0; j < direccion.size(); j++){
            dirige += direccion.get(j).getNombre()  + "\t";
        }
        datosPeli[4] = dirige;
        datosPeli[5] = this.guionista;
        datosPeli[6] = this.musica;
        for (int k = 0; k < reparto.size(); k++){
             actua += reparto.get(k).getNombre() + "\t";
        }
        datosPeli[7] = actua;
        datosPeli[8] = this.productora;
        datosPeli[9] = this.genero;
        datosPeli[10] = this.sinopsis;
        
        return datosPeli;
    }
    
    // Constructor de la clase Pelicula
    public Pelicula(String titulo, int anno, int duracion, String pais, String guionista, String musica, String productora, String genero, String sinopsis) {
        this.titulo = titulo;
        this.anno = anno;
        this.duracion = duracion;
        this.pais = pais;
        this.guionista = guionista;
        this.musica = musica;
        this.productora = productora;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }
    
    public Pelicula() {
        this.titulo = "-";
        this.anno = 0;
        this.duracion = 0;
        this.pais = "-";
        this.direccion = new ArrayList();
        this.guionista = "-";
        this.musica = "-";
        this.reparto = new ArrayList();
        this.productora = "-";
        this.genero = "-";
        this.sinopsis = "-";
    }
    
    public String exportStateAsColumns(){
        return String.format("%-50s  %-4d   %-4d         %-30s    %-10s", 
                this.titulo,
                this.anno,
                this.duracion,
                this.pais,
                this.genero);
    }
    
    public String exportAsHTMLRow(){
        String dirige = new String();
        String actua = new String();
        for(int i = 0; i < direccion.size(); i++){
            dirige += direccion.get(i).getNombre() + ", ";
        }
        for(int i = 0; i < reparto.size(); i++){
            actua += reparto.get(i).getNombre() + ", ";
        }
        return String.format("<TR>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>" 
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "</TR>"
                + "<TR>"
                + "</TR>",
                this.titulo,
                this.anno,
                this.duracion,
                this.pais,
                dirige,
                this.guionista,
                this.musica,
                actua,
                this.productora,
                this.genero,
                this.sinopsis);
    }
    
    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Director> getDireccion() {
        return direccion;
    }

    public void setDireccion(ArrayList<Director> direccion) {
        this.direccion = direccion;
    }

    public String getGuionista() {
        return guionista;
    }

    public void setGuionista(String guionista) {
        this.guionista = guionista;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public ArrayList<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    
}
