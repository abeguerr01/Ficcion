/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.di_p5;

/**
 * Clase base que representa un título (película o serie).
 * Esta clase es heredada por Pelicula y Serie.
 * @author USER
 */
public class Titulo {
    
    /**
     * Título del contenido (nombre de la película o serie).
     */
    String titulo;
    
    /**
     * Constructor para crear un nuevo título.
     * @param titulo Nombre del título (película o serie).
     */
    public Titulo(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
