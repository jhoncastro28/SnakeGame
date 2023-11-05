package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author jhonm
 */

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel gamePanel = new GamePanel();

        // Crear y configurar la etiqueta de puntuación
        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Ink Free", Font.BOLD, 20));
        // Otros ajustes de estilo según tus preferencias

        // Configurar la etiqueta de puntuación en el GamePanel
        gamePanel.setScoreLabel(scoreLabel);

        // Agregar el panel de juego al marco
        this.add(gamePanel);

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
