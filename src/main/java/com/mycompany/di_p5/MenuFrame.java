package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuFrame extends JFrame implements ActionListener {

    private final List<Pelicula> peliculas;
    private final List<Serie> series;
    private final Cliente clienteActivo;
    
    // Declaramos los botones como campos para poder compararlos
    private final JButton btnPeliculas;
    private final JButton btnSeries;

    public MenuFrame(List<Pelicula> peliculas, List<Serie> series, Cliente clienteActivo) {
        this.peliculas = peliculas;
        this.series = series;
        this.clienteActivo = clienteActivo;

        setTitle("Menú Principal - " + clienteActivo.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("¿Qué quieres ver hoy?", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        // Botón Películas
        btnPeliculas = new JButton("Películas");
        btnPeliculas.setFont(new Font("Arial", Font.BOLD, 20));
        btnPeliculas.setBackground(new Color(30, 144, 255));
        btnPeliculas.setForeground(Color.WHITE);
        btnPeliculas.setPreferredSize(new Dimension(200, 80));
        btnPeliculas.addActionListener(this);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(btnPeliculas, gbc);

        // Botón Series
        btnSeries = new JButton("Series");
        btnSeries.setFont(new Font("Arial", Font.BOLD, 20));
        btnSeries.setBackground(new Color(255, 69, 0));
        btnSeries.setForeground(Color.WHITE);
        btnSeries.setPreferredSize(new Dimension(200, 80));
        btnSeries.addActionListener(this);
        gbc.gridx = 1;
        add(btnSeries, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Comparar directamente con el objeto botón (100% seguro)
        if (e.getSource() == btnPeliculas) {
            this.dispose();
            new PeliculasFrame(peliculas, clienteActivo);
        } 
        else if (e.getSource() == btnSeries) {
            this.dispose();
            new SeriesFrame(series, clienteActivo);
        }
    }
}