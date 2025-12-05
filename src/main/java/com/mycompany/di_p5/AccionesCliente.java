/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.di_p5;

/**
 * Interfaz que define las acciones que puede realizar un cliente
 * sobre películas y series (marcar como vistas, guardar favoritos, etc.).
 * @author USER
 */
public interface AccionesCliente {
    /**
     * Marca una película como vista por el cliente.
     * @param id ID de la película a marcar como vista.
     */
    void marcarPeliculaVista(int id);
    
    /**
     * Desmarca una película como vista por el cliente.
     * @param id ID de la película a desmarcar como vista.
     */
    void desmarcarPeliculaVista(int id);
    
    /**
     * Guarda una película como favorita del cliente.
     * @param id ID de la película a guardar como favorita.
     */
    void guardarPelicula(int id);
    
    /**
     * Elimina una película de los favoritos del cliente.
     * @param id ID de la película a eliminar de favoritos.
     */
    void desguardarPelicula(int id);

    /**
     * Marca una serie como vista por el cliente.
     * @param id ID de la serie a marcar como vista.
     */
    void marcarSerieVista(int id);
    
    /**
     * Desmarca una serie como vista por el cliente.
     * @param id ID de la serie a desmarcar como vista.
     */
    void desmarcarSerieVista(int id);
    
    /**
     * Guarda una serie como favorita del cliente.
     * @param id ID de la serie a guardar como favorita.
     */
    void guardarSerie(int id);
    
    /**
     * Elimina una serie de los favoritos del cliente.
     * @param id ID de la serie a eliminar de favoritos.
     */
    void desguardarSerie(int id);
    
    /**
     * Obtiene una cadena con la lista de películas vistas por el cliente.
     * @param id ID del cliente (puede no ser utilizado dependiendo de la implementación).
     * @return Cadena con la información de las películas vistas.
     */
    String getPeliculasVistas(int id);
    
    /**
     * Obtiene una cadena con la lista de películas guardadas como favoritas por el cliente.
     * @param id ID del cliente (puede no ser utilizado dependiendo de la implementación).
     * @return Cadena con la información de las películas favoritas.
     */
    String getPeliculasGuardadas(int id);
    
    /**
     * Obtiene una cadena con la lista de series vistas por el cliente.
     * @param id ID del cliente (puede no ser utilizado dependiendo de la implementación).
     * @return Cadena con la información de las series vistas.
     */
    String getSeriesVistas(int id);
    
    /**
     * Obtiene una cadena con la lista de series guardadas como favoritas por el cliente.
     * @param id ID del cliente (puede no ser utilizado dependiendo de la implementación).
     * @return Cadena con la información de las series favoritas.
     */
    String getSeriesGuardadas(int id);
}
