/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ventana de selección principal, que permite al usuario elegir entre
 * Películas o Series.
 * Cierra esta ventana y abre la ventana de categoría al pulsar un botón.
 */
public class MainSelectionFrame extends JFrame implements ActionListener {

    /**
     * Botón para acceder al catálogo de películas.
     */
    private final JButton moviesButton;
    
    /**
     * Botón para acceder al catálogo de series.
     */
    private final JButton seriesButton;
    
    /**
     * Lista de películas disponibles en el catálogo.
     */
    private final List<Pelicula> peliculas;
    
    /**
     * Lista de series disponibles en el catálogo.
     */
    private final List<Serie> series;
    
    /**
     * Cliente activo que puede guardar favoritos.
     */
    private final Cliente clienteActivo;

    /**
     * Constructor para crear la ventana de selección.
     * @param peliculas Lista de películas disponibles.
     * @param series Lista de series disponibles.
     * @param clienteActivo Cliente activo que puede guardar favoritos.
     */
    public MainSelectionFrame(List<Pelicula> peliculas, List<Serie> series, Cliente clienteActivo) {
        super("Selección de Contenido");
        this.peliculas = peliculas;
        this.series = series;
        this.clienteActivo = clienteActivo;

        // Configuración básica
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usamos DISPOSE para cerrar esta ventana
        setSize(500, 200);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 50));
        setLocationRelativeTo(null); // Centrar la ventana

        JLabel instructionLabel = new JLabel("Selecciona el tipo de contenido que quieres ver:");
        add(instructionLabel);

        // 1. Botón Películas
        moviesButton = new JButton("Películas");
        moviesButton.setFont(new Font("Arial", Font.BOLD, 16));
        moviesButton.addActionListener(this);
        add(moviesButton);

        // 2. Botón Series
        seriesButton = new JButton("Series");
        seriesButton.setFont(new Font("Arial", Font.BOLD, 16));
        seriesButton.addActionListener(this);
        add(seriesButton);

        setVisible(true);
    }

    /**
     * Maneja los eventos de pulsación de los botones de selección.
     * Cierra esta ventana y abre la correspondiente ventana de categoría.
     * @param e El evento de acción que se ha producido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String category = "";
        
        if (e.getSource() == moviesButton) {
            category = "Películas";
        } else if (e.getSource() == seriesButton) {
            category = "Series";
        }

        if (!category.isEmpty()) {
            // Abrir la nueva ventana de categoría con las listas correspondientes
            if (category.equals("Películas") && peliculas != null) {
                new CategoryFrame(category, peliculas, clienteActivo);
            } else if (category.equals("Series") && series != null) {
                new CategoryFrame(category, series, clienteActivo);
            }
            
            // Cerrar la ventana actual (DISPOSE_ON_CLOSE)
            this.dispose(); 
        }
    }
}