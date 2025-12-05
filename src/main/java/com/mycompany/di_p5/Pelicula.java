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
 *      <li>int - duracion</li>
 *      <li>String - rutaImagen: Ruta del archivo de imagen de la película</li>
 * </ul>
 * 
 */
public class Pelicula extends Titulo{
    int duracion;
    int id;
    /**
     * Ruta del archivo de imagen de la película.
     * Almacena la ubicación del archivo de imagen asociado a esta película.
     */
    String rutaImagen;
    
    public Pelicula(int id, String nombre, int duracion){
        super(nombre);
        this.duracion = duracion;
        this.id = id;
        this.rutaImagen = "";
    }
    
    public Pelicula(int id, String nombre, int duracion, String rutaImagen){
        super(nombre);
        this.duracion = duracion;
        this.id = id;
        this.rutaImagen = rutaImagen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
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
