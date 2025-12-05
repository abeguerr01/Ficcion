package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ventana final de la aplicación que muestra el tipo de contenido seleccionado
 * (Películas o Series). Permite seleccionar un título y ver su información e imagen,
 * además de guardarlo como favorito.
 */
public class CategoryFrame extends JFrame implements ActionListener {

    private JComboBox<String> titulosComboBox;
    private JLabel imagenLabel;
    private JTextArea infoTextArea;
    private JButton favoritoButton;
    private List<Pelicula> peliculas;
    private List<Serie> series;
    private String categoryName;
    private boolean esPeliculas;
    private Cliente clienteActivo;
    private int indiceActual;

    /**
     * Constructor para crear la ventana de categoría con películas.
     */
    public CategoryFrame(String categoryName, List<Pelicula> peliculas, Cliente clienteActivo) {
        this.categoryName = categoryName;
        this.peliculas = peliculas;
        this.series = null;
        this.esPeliculas = true;
        this.clienteActivo = clienteActivo;
        this.indiceActual = 0;
        inicializarComponentes();
    }

    /**
     * Constructor para crear la ventana de categoría con series.
     */
    public CategoryFrame(String categoryName, List<Serie> series, Cliente clienteActivo) {
        this.categoryName = categoryName;
        this.series = series;
        this.peliculas = null;
        this.esPeliculas = false;
        this.clienteActivo = clienteActivo;
        this.indiceActual = 0;
        inicializarComponentes();
    }

    /**
     * Inicializa todos los componentes de la ventana.
     */
    private void inicializarComponentes() {
        setTitle("Catálogo de " + categoryName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Catálogo de " + categoryName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        titulosComboBox = new JComboBox<>();
        if (esPeliculas && peliculas != null) {
            for (Pelicula pelicula : peliculas) {
                titulosComboBox.addItem(pelicula.getTitulo());
            }
        } else if (!esPeliculas && series != null) {
            for (Serie serie : series) {
                titulosComboBox.addItem(serie.getTitulo());
            }
        }
        titulosComboBox.addActionListener(this);
        titulosComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel seleccionLabel = new JLabel("Selecciona un título:");
        seleccionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        favoritoButton = new JButton("⭐ Guardar como favorito");
        favoritoButton.setFont(new Font("Arial", Font.BOLD, 12));
        favoritoButton.addActionListener(this);
        favoritoButton.setBackground(new Color(255, 215, 0));
        favoritoButton.setForeground(Color.BLACK);
        
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.add(seleccionLabel);
        comboPanel.add(titulosComboBox);
        comboPanel.add(favoritoButton);
        
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(comboPanel, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        imagenLabel = new JLabel("", SwingConstants.CENTER);
        imagenLabel.setBorder(BorderFactory.createTitledBorder("Imagen"));
        imagenLabel.setPreferredSize(new Dimension(300, 400));
        
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setWrapStyleWord(true);
        infoTextArea.setLineWrap(true);
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        infoTextArea.setBorder(BorderFactory.createTitledBorder("Información"));
        
        centerPanel.add(imagenLabel, BorderLayout.WEST);
        centerPanel.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
        if (titulosComboBox.getItemCount() > 0) {
            mostrarInformacion(0);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == titulosComboBox) {
            int indiceSeleccionado = titulosComboBox.getSelectedIndex();
            indiceActual = indiceSeleccionado;
            mostrarInformacion(indiceSeleccionado);
        } else if (e.getSource() == favoritoButton) {
            guardarFavorito();
        }
    }

    private void mostrarInformacion(int indice) {
        if (esPeliculas && peliculas != null && indice >= 0 && indice < peliculas.size()) {
            Pelicula pelicula = peliculas.get(indice);
            
            String info = "ID: " + pelicula.id + "\n\n";
            info += "Título: " + pelicula.getTitulo() + "\n\n";
            info += "Duración: " + pelicula.getDuracion() + " minutos\n\n";
            info += "Ruta de imagen: " + (pelicula.getRutaImagen().isEmpty() ? "No especificada" : pelicula.getRutaImagen());
            infoTextArea.setText(info);
            
            cargarImagen(pelicula.getRutaImagen());
            
        } else if (!esPeliculas && series != null && indice >= 0 && indice < series.size()) {
            Serie serie = series.get(indice);
            
            String info = "ID: " + serie.id + "\n\n";
            info += "Título: " + serie.getTitulo() + "\n\n";
            info += "Temporadas: " + serie.getTemporadas() + "\n\n";
            info += "Capítulos por temporada: ";
            if (serie.getCapitulos() != null) {
                for (int i = 0; i < serie.getCapitulos().length; i++) {
                    info += "T" + (i + 1) + ": " + serie.getCapitulos()[i];
                    if (i < serie.getCapitulos().length - 1) info += ", ";
                }
            }
            info += "\n\n";
            info += "Ruta de imagen: " + (serie.getRutaImagen().isEmpty() ? "No especificada" : serie.getRutaImagen());
            infoTextArea.setText(info);
            
            cargarImagen(serie.getRutaImagen());
        }
    }

    private void guardarFavorito() {
        if (clienteActivo == null) {
            JOptionPane.showMessageDialog(this, 
                "No hay cliente activo para guardar favoritos.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (esPeliculas && peliculas != null && indiceActual >= 0 && indiceActual < peliculas.size()) {
            Pelicula pelicula = peliculas.get(indiceActual);
            clienteActivo.guardarPelicula(pelicula.id);
            JOptionPane.showMessageDialog(this, 
                "Película '" + pelicula.getTitulo() + "' guardada como favorita.", 
                "Favorito guardado", 
                JOptionPane.INFORMATION_MESSAGE);
        } else if (!esPeliculas && series != null && indiceActual >= 0 && indiceActual < series.size()) {
            Serie serie = series.get(indiceActual);
            clienteActivo.guardarSerie(serie.id);
            JOptionPane.showMessageDialog(this, 
                "Serie '" + serie.getTitulo() + "' guardada como favorita.", 
                "Favorito guardado", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarImagen(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) {
            imagenLabel.setIcon(null);
            imagenLabel.setText("No hay imagen disponible");
            return;
        }
        
        try {
            ImageIcon icono = new ImageIcon(rutaImagen);
            if (icono.getIconWidth() > 0) {
                Image imagen = icono.getImage();
                Image imagenRedimensionada = imagen.getScaledInstance(280, 380, Image.SCALE_SMOOTH);
                imagenLabel.setIcon(new ImageIcon(imagenRedimensionada));
                imagenLabel.setText("");
            } else {
                imagenLabel.setIcon(null);
                imagenLabel.setText("Imagen no encontrada");
            }
        } catch (Exception e) {
            imagenLabel.setIcon(null);
            imagenLabel.setText("Error al cargar la imagen");
            e.printStackTrace();
        }
    }
}