/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.di_p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal de la aplicación que inicializa los datos y lanza la interfaz gráfica.
 * @author USER
 */
public class Main{

    /**
     * Lista estática que contiene todas las películas disponibles en el catálogo.
     */
    private static List<Pelicula> peliculas = new ArrayList<>();
    
    /**
     * Lista estática que contiene todas las series disponibles en el catálogo.
     */
    private static List<Serie> series = new ArrayList<>();
    
    /**
     * Método que crea e inicializa la información de películas, series y clientes
     * con datos de ejemplo para el funcionamiento de la aplicación.
     */
    private static void crearInfo(){
        // Crear serie: Juego de Tronos
        int[] capitulos = {1,2,3,4,5,6,7,8,9,10};
        int[] duracion = {55,55,55,55,55,55,55,55,55};
        Serie GOT = new Serie(1, "Juego de tronos", 10, capitulos , duracion, "imagenes/got.jpg");
        series.add(GOT);
        
        // Crear serie: Stranger Things
        int[] capitulos1 = {1,2,3,4,5,6,7,8};
        int[] duracion1 = {80,80,80,80,80,80,80,80,80};
        Serie StrangerThings = new Serie(2, "Stranger Things", 5, capitulos1 , duracion1, "imagenes/stranger_things.jpg");
        series.add(StrangerThings);
        
        // Crear película: Padre no hay mas que uno 29
        Pelicula PadreNoHayMasQueUno29 = new Pelicula(1, "Padre no hay mas que uno 29", 160, "imagenes/padre_no_hay_mas_que_uno.jpg");
        peliculas.add(PadreNoHayMasQueUno29);
        
        // Crear película: La guerra olvidada
        Pelicula LaGuerraOlvidada = new Pelicula(2, "La guerra olvidada", 124, "imagenes/guerra_olvidada.jpg");
        peliculas.add(LaGuerraOlvidada);
        
        // Crear clientes de ejemplo (no se utilizan actualmente en la interfaz)
        Cliente per1 = new Cliente(1 ,"Alejandro", 20, TipoCliente.PREMIUM);
        Cliente per2 = new Cliente(2, "Pau", 19, TipoCliente.BASICO);
        Cliente per3 = new Cliente(3, "Alvaro", 28, TipoCliente.ANUNCIOS);
    }
    
    /**
     * Método principal que inicia la aplicación.
     * Crea la información inicial y abre la ventana de selección principal.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        crearInfo();
        // Crear un cliente activo por defecto para poder guardar favoritos
        Cliente clienteActivo = new Cliente(1, "Alejandro", 20, TipoCliente.PREMIUM);
        // Abrir la ventana de selección principal con las listas de películas y series
        new MainSelectionFrame(peliculas, series, clienteActivo);
    }
}
