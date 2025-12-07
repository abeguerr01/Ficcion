package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ventana que muestra el catálogo de películas o series.
 * Totalmente compatible con Java 8.
 */
public class CategoryFrame extends JFrame implements ActionListener {

    private JComboBox<String> titulosComboBox;
    private JLabel imagenLabel;
    private JTextArea infoTextArea;
    private JButton favoritoButton;
    private List<? extends Titulo> contenidos;
    private String categoryName;
    private Cliente clienteActivo;
    private int indiceActual = 0;

    /**
     * Constructor único que funciona para películas y series.
     */
    public CategoryFrame(String categoryName, List<? extends Titulo> contenidos, Cliente clienteActivo) {
        this.categoryName = categoryName;
        this.contenidos = contenidos;
        this.clienteActivo = clienteActivo;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Catálogo de " + categoryName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título principal
        JLabel titleLabel = new JLabel("Catálogo de " + categoryName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // ComboBox
        titulosComboBox = new JComboBox<>();
        for (Titulo t : contenidos) {
            titulosComboBox.addItem(t.getTitulo());
        }
        titulosComboBox.addActionListener(this);

        // Botón favorito
        favoritoButton = new JButton("Guardar como favorito");
        favoritoButton.setBackground(new Color(255, 215, 0));
        favoritoButton.setFont(new Font("Arial", Font.BOLD, 12));
        favoritoButton.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.add(new JLabel("Selecciona un título:"));
        topPanel.add(titulosComboBox);
        topPanel.add(favoritoButton);

        // Área de imagen e información
        imagenLabel = new JLabel("Selecciona un título", SwingConstants.CENTER);
        imagenLabel.setPreferredSize(new Dimension(300, 420));
        imagenLabel.setBorder(BorderFactory.createTitledBorder("Portada"));

        infoTextArea = new JTextArea(15, 40);
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollInfo = new JScrollPane(infoTextArea);
        scrollInfo.setBorder(BorderFactory.createTitledBorder("Información"));

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.add(imagenLabel, BorderLayout.WEST);
        centerPanel.add(scrollInfo, BorderLayout.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);

        if (!contenidos.isEmpty()) {
            mostrarInformacion(0);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == titulosComboBox) {
            indiceActual = titulosComboBox.getSelectedIndex();
            mostrarInformacion(indiceActual);
        } else if (e.getSource() == favoritoButton) {
            guardarComoFavorito();
        }
    }

    private void mostrarInformacion(int indice) {
        Titulo titulo = contenidos.get(indice);
        StringBuilder info = new StringBuilder();

        info.append("ID: ").append(getId(titulo)).append("\n\n");
        info.append("Título: ").append(titulo.getTitulo()).append("\n\n");

        // Aquí usamos instanceof + cast clásico (Java 8)
        if (titulo instanceof Pelicula) {
            Pelicula pelicula = (Pelicula) titulo;
            info.append("Duración: ").append(pelicula.getDuracion()).append(" minutos\n\n");
        } else if (titulo instanceof Serie) {
            Serie serie = (Serie) titulo;
            info.append("Temporadas: ").append(serie.getTemporadas()).append("\n");
            info.append("Capítulos por temporada: ");
            int[] caps = serie.getCapitulos();
            if (caps != null) {
                for (int i = 0; i < caps.length; i++) {
                    info.append("T").append(i + 1).append(": ").append(caps[i]);
                    if (i < caps.length - 1) info.append(", ");
                }
            }
            info.append("\n");
        }

        info.append("Ruta imagen: ").append(getRutaImagen(titulo).isEmpty() ? "No disponible" : getRutaImagen(titulo));

        infoTextArea.setText(info.toString());
        cargarImagen(getRutaImagen(titulo));
    }

    private void guardarComoFavorito() {
        if (clienteActivo == null) {
            JOptionPane.showMessageDialog(this, "No hay usuario conectado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Titulo seleccionado = contenidos.get(indiceActual);

        if (seleccionado instanceof Pelicula) {
            Pelicula p = (Pelicula) seleccionado;
            clienteActivo.guardarPelicula(p.id);
            JOptionPane.showMessageDialog(this, "Película '" + p.getTitulo() + "' añadida a favoritos");
        } else if (seleccionado instanceof Serie) {
            Serie s = (Serie) seleccionado;
            clienteActivo.guardarSerie(s.id);
            JOptionPane.showMessageDialog(this, "Serie '" + s.getTitulo() + "' añadida a favoritos");
        }
    }

    private void cargarImagen(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            imagenLabel.setIcon(null);
            imagenLabel.setText("No hay imagen");
            return;
        }

        try {
            ImageIcon icon = new ImageIcon(ruta);
            if (icon.getIconWidth() > 0) {
                Image img = icon.getImage().getScaledInstance(280, 400, Image.SCALE_SMOOTH);
                imagenLabel.setIcon(new ImageIcon(img));
                imagenLabel.setText("");
            } else {
                imagenLabel.setIcon(null);
                imagenLabel.setText("Imagen no encontrada");
            }
        } catch (Exception ex) {
            imagenLabel.setIcon(null);
            imagenLabel.setText("Error al cargar imagen");
        }
    }

    // Métodos auxiliares (compatibles con Java 8)
    private int getId(Titulo t) {
        if (t instanceof Pelicula) return ((Pelicula) t).id;
        if (t instanceof Serie) return ((Serie) t).id;
        return -1;
    }

    private String getRutaImagen(Titulo t) {
        if (t instanceof Pelicula) return ((Pelicula) t).getRutaImagen();
        if (t instanceof Serie) return ((Serie) t).getRutaImagen();
        return "";
    }
}