/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.di_p5;

/**
 * Clase base que representa a una persona con nombre y edad.
 * Esta clase es heredada por otras clases como Cliente y Actor.
 * @author USER
 */
public class Persona {
    /**
     * Nombre de la persona.
     */
    String nombre;
    
    /**
     * Edad de la persona en años.
     */
    int edad;
    
    /**
     * Constructor para crear una nueva persona.
     * @param nombre Nombre de la persona.
     * @param edad Edad de la persona en años.
     */
    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }
}
