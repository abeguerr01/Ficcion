/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.di_p5;

/**
 * 
 * <h2>PARAMETROS DE LA CLASE</h2>
 * <ul>
 *      <li>String (heredado) - nombre</li>
 *      <li>int[] - temporadas</li>
 *      <li>int[] - duracion</li>
 *      <li>String - rutaImagen: Ruta del archivo de imagen de la serie</li>
 * </ul>
 * 
 */
public class Serie extends Titulo{
    int id;
    int temporadas;
    int[] capitulos;
    int[] duracion;
    /**
     * Ruta del archivo de imagen de la serie.
     * Almacena la ubicación del archivo de imagen asociado a esta serie.
     */
    String rutaImagen;
    
    public Serie(int id, String nombre, int temporadas, int[] capitulos, int[] duracion){
        super(nombre);
        this.temporadas = temporadas;
        this.capitulos = capitulos;
        this.duracion = duracion;
        this.id = id;
        this.rutaImagen = "";
    }
    
    public Serie(int id, String nombre, int temporadas, int[] capitulos, int[] duracion, String rutaImagen){
        super(nombre);
        this.temporadas = temporadas;
        this.capitulos = capitulos;
        this.duracion = duracion;
        this.id = id;
        this.rutaImagen = rutaImagen;
    }
    
    public int duracionTotal(int mostrar){
        int totalTiempo = 0;
        for (int i = 0; i < temporadas; i++){
            for (int j: duracion){
                totalTiempo = totalTiempo + j;
            }
        }
        
        if(mostrar != 0){
            int horas = 0;
            int min = totalTiempo;

            while(min>=60){
                horas++;
                min = min - 60;
            }
            System.out.println("La serie dura"+horas+" horas y "+min+" minutos.");
        }
        return totalTiempo;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int[] getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int[] capitulos) {
        this.capitulos = capitulos;
    }

    public int[] getDuracion() {
        return duracion;
    }

    public void setDuracion(int[] duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}
