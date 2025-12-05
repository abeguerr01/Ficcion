/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.di_p5;

/**
 * Clase que representa a un actor, heredando de Persona.
 * <h2>PARAMETROS DE LA CLASE</h2>
 * <ul>
 *      <li>String (heredado) - nombre: Nombre del actor</li>
 *      <li>int (heredado) - edad: Edad del actor</li>
 *      <li>int - id: Identificador único del actor</li>
 * </ul>
 * 
 */
public class Actor extends Persona{
    
    /**
     * Identificador único del actor.
     */
    int id;
    
    /**
     * Constructor para crear un nuevo actor.
     * @param id Identificador único del actor.
     * @param nombre Nombre del actor.
     * @param edad Edad del actor en años.
     */
    public Actor(int id, String nombre, int edad){
        super(nombre, edad);
        this.id = id;
    }
    
}
