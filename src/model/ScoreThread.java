package model;

import view.GamePanel;

public class ScoreThread extends Thread {
    private GamePanel gamePanel;

    public ScoreThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (gamePanel.isRunning()) {
            // Obtener la puntuación actual del GamePanel
            int score = gamePanel.getApplesEaten();

            // Actualizar la etiqueta de puntuación en la interfaz gráfica
            gamePanel.updateScoreLabel(score);

            try {
                Thread.sleep(1000); // Actualizar la puntuación cada segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
