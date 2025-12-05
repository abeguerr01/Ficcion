package com.mycompany.di_p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Componente de botón personalizado que hereda de JButton.
 * Por defecto, establece un color de fondo azul y un color de texto blanco.
 * Implementa {@code ActionListener} para autogestionar una acción predefinida.
 * * @author USER
 */
public class jButtonCustom extends JButton implements ActionListener {

    /**
     * Constructor para crear un botón personalizado con un texto específico.
     * <p>
     * Inicializa el botón y establece las propiedades de color por defecto:
     * <ul>
     * <li>Fondo: Azul ({@code Color.BLUE})</li>
     * <li>Letra: Blanco ({@code Color.WHITE})</li>
     * </ul>
     * </p>
     * * @param texto El texto que se mostrará en el botón.
     */
     public jButtonCustom(String texto) {
         super(texto);
         
         this.setBackground(Color.BLUE);
         this.setForeground(Color.WHITE);
         
         addActionListener(this);
     }
    
    /**
     * Implementación del método {@code actionPerformed} de la interfaz {@code ActionListener}.
     * Define la acción predeterminada que ocurre al hacer clic en este botón.
     * * @param e El evento de acción que se ha producido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "¡Botón pulsado!");
    }

    /**
     * Método adicional para establecer un color de texto (a modo de ejemplo).
     * Nota: En este ejemplo no se implementa la lógica completa de cambio de color,
     * sino que solo se muestra un mensaje en consola.
     * * @param color El nombre o código del color deseado como {@code String}.
     */
    public void setColorTexto(String color) {
        System.out.println("Color de texto no implementado aquí, ejemplo de color pasado: " + color);
    }
    
}