package com.mycompany.di_p5;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase que representa a un cliente de la plataforma, heredando de Persona
 * e implementando la interfaz AccionesCliente para gestionar películas y series.
 * <h2>PARAMETROS DE LA CLASE</h2>
 * <ul>
 *      <li>String (heredado) - nombre: Nombre del cliente</li>
 *      <li>int (heredado) - edad: Edad del cliente</li>
 *      <li>TipoCliente - nivel: Nivel de suscripción del cliente</li>
 *      <li>int - id: Identificador único del cliente</li>
 *      <li>ArrayList&lt;Integer&gt; - peliculasVistas: Lista de IDs de películas vistas</li>
 *      <li>ArrayList&lt;Integer&gt; - peliculasGuardadas: Lista de IDs de películas favoritas</li>
 *      <li>ArrayList&lt;Integer&gt; - seriesVistas: Lista de IDs de series vistas</li>
 *      <li>ArrayList&lt;Integer&gt; - seriesGuardadas: Lista de IDs de series favoritas</li>
 * </ul>
 * 
 */
public class Cliente extends Persona implements AccionesCliente{
    /**
     * Nivel de suscripción del cliente (ANUNCIOS, BASICO, PREMIUM).
     */
    TipoCliente nivel;
    
    /**
     * Identificador único del cliente.
     */
    int id;
    
    /**
     * Lista de IDs de películas marcadas como vistas por el cliente.
     */
    ArrayList<Integer> peliculasVistas = new ArrayList<>();
    
    /**
     * Lista de IDs de películas guardadas como favoritas por el cliente.
     */
    ArrayList<Integer> peliculasGuardadas = new ArrayList<>();
    
    /**
     * Lista de IDs de series marcadas como vistas por el cliente.
     */
    ArrayList<Integer> seriesVistas = new ArrayList<>();
    
    /**
     * Lista de IDs de series guardadas como favoritas por el cliente.
     */
    ArrayList<Integer> seriesGuardadas = new ArrayList<>();
    
    /**
     * Constructor para crear un nuevo cliente.
     * @param id Identificador único del cliente.
     * @param nombre Nombre del cliente.
     * @param edad Edad del cliente.
     * @param nivel Nivel de suscripción del cliente.
     */
    public Cliente(int id, String nombre, int edad, TipoCliente nivel){
        super(nombre, edad);
        this.nivel = nivel;
        this.id = id;
    }

    /**
     * Obtiene el nivel de suscripción del cliente.
     * @return El tipo de cliente (ANUNCIOS, BASICO, PREMIUM).
     */
    public TipoCliente getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel de suscripción del cliente.
     * @param nivel El nuevo nivel de suscripción del cliente.
     */
    public void setNivel(TipoCliente nivel) {
        this.nivel = nivel;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del cliente.
     * @return La edad del cliente en años.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del cliente.
     * @param edad La nueva edad del cliente en años.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Marca una película como vista por el cliente.
     * @param id ID de la película a marcar como vista.
     */
    @Override
    public void marcarPeliculaVista(int id) {
        if (!peliculasVistas.contains(id)) {
            peliculasVistas.add(id);
            System.out.println("Película con ID " + id + " marcada como vista.");
        } else {
            System.out.println("La película con ID " + id + " ya está marcada como vista.");
        }
    }

    /**
     * Desmarca una película como vista por el cliente.
     * @param id ID de la película a desmarcar como vista.
     */
    @Override
    public void desmarcarPeliculaVista(int id) {
        if (peliculasVistas.contains(id)) {
            peliculasVistas.remove(Integer.valueOf(id));
            System.out.println("Película con ID " + id + " desmarcada como vista.");
        } else {
            System.out.println("La película con ID " + id + " no está marcada como vista.");
        }
    }

    /**
     * Guarda una película como favorita añadiendo su ID a la lista de favoritos.
     * Si la película ya está guardada, no la añade de nuevo.
     * @param id ID de la película a guardar como favorita.
     */
    @Override
    public void guardarPelicula(int id) {
        if (!peliculasGuardadas.contains(id)) {
            peliculasGuardadas.add(id);
            System.out.println("Película con ID " + id + " guardada como favorita.");
        } else {
            System.out.println("La película con ID " + id + " ya está en favoritos.");
        }
    }

    /**
     * Elimina una película de la lista de favoritos.
     * @param id ID de la película a eliminar de favoritos.
     */
    @Override
    public void desguardarPelicula(int id) {
        if (peliculasGuardadas.contains(id)) {
            peliculasGuardadas.remove(Integer.valueOf(id));
            System.out.println("Película con ID " + id + " eliminada de favoritos.");
        } else {
            System.out.println("La película con ID " + id + " no está en favoritos.");
        }
    }

    /**
     * Marca una serie como vista por el cliente.
     * @param id ID de la serie a marcar como vista.
     */
    @Override
    public void marcarSerieVista(int id) {
        if (!seriesVistas.contains(id)) {
            seriesVistas.add(id);
            System.out.println("Serie con ID " + id + " marcada como vista.");
        } else {
            System.out.println("La serie con ID " + id + " ya está marcada como vista.");
        }
    }

    /**
     * Desmarca una serie como vista por el cliente.
     * @param id ID de la serie a desmarcar como vista.
     */
    @Override
    public void desmarcarSerieVista(int id) {
        if (seriesVistas.contains(id)) {
            seriesVistas.remove(Integer.valueOf(id));
            System.out.println("Serie con ID " + id + " desmarcada como vista.");
        } else {
            System.out.println("La serie con ID " + id + " no está marcada como vista.");
        }
    }

    /**
     * Guarda una serie como favorita añadiendo su ID a la lista de favoritos.
     * Si la serie ya está guardada, no la añade de nuevo.
     * @param id ID de la serie a guardar como favorita.
     */
    @Override
    public void guardarSerie(int id) {
        if (!seriesGuardadas.contains(id)) {
            seriesGuardadas.add(id);
            System.out.println("Serie con ID " + id + " guardada como favorita.");
        } else {
            System.out.println("La serie con ID " + id + " ya está en favoritos.");
        }
    }

    /**
     * Elimina una serie de la lista de favoritos.
     * @param id ID de la serie a eliminar de favoritos.
     */
    @Override
    public void desguardarSerie(int id) {
        if (seriesGuardadas.contains(id)) {
            seriesGuardadas.remove(Integer.valueOf(id));
            System.out.println("Serie con ID " + id + " eliminada de favoritos.");
        } else {
            System.out.println("La serie con ID " + id + " no está en favoritos.");
        }
    }

    /**
     * Obtiene una cadena con la lista de películas marcadas como vistas.
     * @param id ID del cliente (no utilizado, mantenido por compatibilidad con la interfaz).
     * @return Cadena con la lista de IDs de películas vistas.
     */
    @Override
    public String getPeliculasVistas(int id) {
        if (peliculasVistas.isEmpty()) {
            return "No hay películas marcadas como vistas.";
        }
        return "Películas vistas (IDs): " + peliculasVistas.toString();
    }

    /**
     * Obtiene una cadena con la lista de películas guardadas como favoritas.
     * @param id ID del cliente (no utilizado, mantenido por compatibilidad con la interfaz).
     * @return Cadena con la lista de IDs de películas favoritas.
     */
    @Override
    public String getPeliculasGuardadas(int id) {
        if (peliculasGuardadas.isEmpty()) {
            return "No hay películas guardadas como favoritas.";
        }
        return "Películas favoritas (IDs): " + peliculasGuardadas.toString();
    }
    
    /**
     * Obtiene una cadena con la lista de series marcadas como vistas.
     * @param id ID del cliente (no utilizado, mantenido por compatibilidad con la interfaz).
     * @return Cadena con la lista de IDs de series vistas.
     */
    @Override
    public String getSeriesVistas(int id) {
        if (seriesVistas.isEmpty()) {
            return "No hay series marcadas como vistas.";
        }
        return "Series vistas (IDs): " + seriesVistas.toString();
    }

    /**
     * Obtiene una cadena con la lista de series guardadas como favoritas.
     * @param id ID del cliente (no utilizado, mantenido por compatibilidad con la interfaz).
     * @return Cadena con la lista de IDs de series favoritas.
     */
    @Override
    public String getSeriesGuardadas(int id) {
        if (seriesGuardadas.isEmpty()) {
            return "No hay series guardadas como favoritas.";
        }
        return "Series favoritas (IDs): " + seriesGuardadas.toString();
    }
    
    /**
     * Obtiene la lista de IDs de películas guardadas como favoritas.
     * @return ArrayList con los IDs de las películas favoritas.
     */
    public ArrayList<Integer> getPeliculasGuardadasList() {
        return peliculasGuardadas;
    }
    
    /**
     * Obtiene la lista de IDs de series guardadas como favoritas.
     * @return ArrayList con los IDs de las series favoritas.
     */
    public ArrayList<Integer> getSeriesGuardadasList() {
        return seriesGuardadas;
    }
}
