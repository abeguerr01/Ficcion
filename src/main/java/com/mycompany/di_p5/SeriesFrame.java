package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class SeriesFrame extends JFrame implements ActionListener {

    private JComboBox<String> comboTitulos;
    private JLabel lblImagen;
    private JTextArea areaInfo;
    private JButton btnFavorito, btnFavoritos, btnTodo, btnVolver;
    private List<Serie> series;
    private List<Serie> listaActual;
    private Cliente cliente;
    private int indice = 0;

    public SeriesFrame(List<Serie> series, Cliente cliente) {
        this.series = new ArrayList<>(series);
        this.listaActual = new ArrayList<>(series);
        this.cliente = cliente;

        initUI();
        cargarCombo();
        if (comboTitulos.getItemCount() > 0) mostrarSerie(0);
    }

    private void initUI() {
        setTitle("Catálogo de Series");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SERIES", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 36));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel controles superiores
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelControles.add(new JLabel("Selecciona serie:"));
        comboTitulos = new JComboBox<>();
        comboTitulos.setPreferredSize(new Dimension(400, 35));
        comboTitulos.addActionListener(this);
        panelControles.add(comboTitulos);

        btnFavorito = new JButton("Guardar como favorito");
        btnFavorito.setBackground(new Color(255, 215, 0));
        btnFavorito.addActionListener(this);
        panelControles.add(btnFavorito);

        // Panel central con imagen e información
        lblImagen = new JLabel("Selecciona una serie", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(320, 450));
        lblImagen.setBorder(BorderFactory.createTitledBorder("Portada"));

        areaInfo = new JTextArea(20, 50);
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBorder(BorderFactory.createTitledBorder("Información"));

        JPanel centro = new JPanel(new BorderLayout(20, 20));
        centro.add(lblImagen, BorderLayout.WEST);
        centro.add(scroll, BorderLayout.CENTER);

        // Panel que contiene controles y contenido central
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelControles, BorderLayout.NORTH);
        panelCentral.add(centro, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnFavoritos = new JButton("Mis Favoritos");
        btnFavoritos.setBackground(Color.GREEN.darker());
        btnFavoritos.setForeground(Color.WHITE);
        btnFavoritos.addActionListener(this);
        panelBotones.add(btnFavoritos);

        btnTodo = new JButton("Ver Todo");
        btnTodo.setBackground(Color.BLUE);
        btnTodo.setForeground(Color.WHITE);
        btnTodo.addActionListener(this);
        panelBotones.add(btnTodo);

        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(Color.RED);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarCombo() {
        comboTitulos.removeAllItems();
        for (Serie s : listaActual) {
            comboTitulos.addItem(s.getTitulo());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboTitulos) {
            indice = comboTitulos.getSelectedIndex();
            if (indice >= 0) mostrarSerie(indice);
        }
        else if (e.getSource() == btnFavorito) {
            Serie s = listaActual.get(indice);
            cliente.guardarSerie(s.id);
            JOptionPane.showMessageDialog(this, "¡'" + s.getTitulo() + "' añadida a favoritos!");
        }
        else if (e.getSource() == btnFavoritos) {
            filtrarFavoritos();
        }
        else if (e.getSource() == btnTodo) {
            listaActual = new ArrayList<>(series);
            cargarCombo();
            if (comboTitulos.getItemCount() > 0) mostrarSerie(0);
        }
        else if (e.getSource() == btnVolver) {
            this.dispose();
            new MenuFrame(Main.peliculas, Main.series, cliente);
        }
    }

    private void filtrarFavoritos() {
        List<Integer> favs = cliente.getSeriesGuardadasList();
        if (favs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes series favoritas aún.");
            return;
        }
        listaActual = series.stream()
                .filter(s -> favs.contains(s.id))
                .collect(Collectors.toList());
        cargarCombo();
        if (comboTitulos.getItemCount() > 0) mostrarSerie(0);
    }

    private void mostrarSerie(int i) {
        if (i < 0 || i >= listaActual.size()) return;
        Serie s = listaActual.get(i);
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(s.id).append("\n\n");
        info.append("Título: ").append(s.getTitulo()).append("\n\n");
        info.append("Temporadas: ").append(s.getTemporadas()).append("\n");
        info.append("Capítulos por temporada: ");
        int[] caps = s.getCapitulos();
        if (caps != null) {
            for (int j = 0; j < caps.length; j++) {
                info.append("T").append(j+1).append(": ").append(caps[j]);
                if (j < caps.length-1) info.append(", ");
            }
        }
        info.append("\n\nRuta imagen: ").append(s.getRutaImagen().isEmpty() ? "No disponible" : s.getRutaImagen());
        areaInfo.setText(info.toString());
        cargarImagen(s.getRutaImagen());
    }

    private void cargarImagen(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            lblImagen.setIcon(null);
            lblImagen.setText("Sin imagen");
            return;
        }
        
        // Intentar cargar desde diferentes ubicaciones posibles
        String[] rutasPosibles = {
            ruta,  // Ruta original
            "src/main/" + ruta,  // Desde el directorio del proyecto
            System.getProperty("user.dir") + "/src/main/" + ruta,  // Ruta absoluta desde el directorio de trabajo
            System.getProperty("user.dir") + "/" + ruta  // Ruta absoluta directa
        };
        
        ImageIcon icon = null;
        for (String rutaIntento : rutasPosibles) {
            try {
                java.io.File archivo = new java.io.File(rutaIntento);
                if (archivo.exists() && archivo.isFile()) {
                    icon = new ImageIcon(rutaIntento);
                    if (icon.getIconWidth() > 0) {
                        break;  // Imagen cargada correctamente
                    }
                }
            } catch (Exception e) {
                // Continuar con la siguiente ruta
            }
        }
        
        // Si no se encontró, intentar como recurso del classpath
        if (icon == null || icon.getIconWidth() <= 0) {
            try {
                java.net.URL url = getClass().getClassLoader().getResource(ruta);
                if (url != null) {
                    icon = new ImageIcon(url);
                }
            } catch (Exception e) {
                // Ignorar
            }
        }
        
        // Mostrar la imagen si se cargó correctamente
        try {
            if (icon != null && icon.getIconWidth() > 0) {
                Image img = icon.getImage().getScaledInstance(300, 430, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(img));
                lblImagen.setText("");
            } else {
                lblImagen.setIcon(null);
                lblImagen.setText("No encontrada: " + ruta);
            }
        } catch (Exception ex) {
            lblImagen.setIcon(null);
            lblImagen.setText("Error al cargar");
        }
    }
}