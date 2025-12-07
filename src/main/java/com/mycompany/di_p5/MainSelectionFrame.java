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
 * Ventana intermedia que permite al usuario elegir entre ver Películas o Series.
 * Al seleccionar una opción, cierra esta ventana y abre el catálogo correspondiente.
 */
public class MainSelectionFrame extends JFrame implements ActionListener {

    private final JButton moviesButton;
    private final JButton seriesButton;
    private final List<Pelicula> peliculas;
    private final List<Serie> series;
    private final Cliente clienteActivo;

    /**
     * Constructor de la ventana de selección.
     * @param peliculas Lista de películas disponibles en el catálogo
     * @param series Lista de series disponibles en el catálogo
     * @param clienteActivo Cliente autenticado que puede guardar favoritos
     */
    public MainSelectionFrame(List<Pelicula> peliculas, List<Serie> series, Cliente clienteActivo) {
        super("Selección de Contenido");
        this.peliculas = peliculas;
        this.series = series;
        this.clienteActivo = clienteActivo;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(560, 220);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titleLabel = new JLabel("¿Qué deseas ver hoy?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Botón Películas
        moviesButton = new JButton("Películas");
        moviesButton.setFont(new Font("Arial", Font.BOLD, 18));
        moviesButton.setPreferredSize(new Dimension(180, 60));
        moviesButton.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(moviesButton, gbc);

        // Botón Series
        seriesButton = new JButton("Series");
        seriesButton.setFont(new Font("Arial", Font.BOLD, 18));
        seriesButton.setPreferredSize(new Dimension(180, 60));
        seriesButton.addActionListener(this);
        gbc.gridx = 1;
        add(seriesButton, gbc);

        setVisible(true);
    }

    /**
     * Gestiona la pulsación de los botones.
     * Abre la ventana del catálogo correspondiente y cierra esta ventana.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moviesButton) {
            if (peliculas != null && !peliculas.isEmpty()) {
                new CategoryFrame("Películas", peliculas, clienteActivo);
            } else {
                JOptionPane.showMessageDialog(this, "No hay películas disponibles.", 
                    "Catálogo vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } 
        else if (e.getSource() == seriesButton) {
            if (series != null && !series.isEmpty()) {
                new CategoryFrame("Series", series, clienteActivo);
            } else {
                JOptionPane.showMessageDialog(this, "No hay series disponibles.", 
                    "Catálogo vacío", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // Cerrar esta ventana una vez abierta la siguiente
        this.dispose();
    }
}