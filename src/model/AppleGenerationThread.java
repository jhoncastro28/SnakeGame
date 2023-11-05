package model;

import java.util.Random;

import view.GamePanel;

public class AppleGenerationThread extends Thread {
    private GamePanel gamePanel;
    private Random random;

    public AppleGenerationThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (gamePanel.isRunning()) {
            // Genera coordenadas aleatorias para la manzana dentro del área de juego
            int appleX = random.nextInt(gamePanel.getScreenWidth() / gamePanel.getUnitSize()) * gamePanel.getUnitSize();
            int appleY = random.nextInt(gamePanel.getScreenHeight() / gamePanel.getUnitSize()) * gamePanel.getUnitSize();

            gamePanel.setAppleCoordinates(appleX, appleY);

            // Genera un retraso ALEATORIO antes de generar la próxima manzana
            int randomDelay = random.nextInt(5000) + 1000;

            try {
                Thread.sleep(randomDelay); // Pausa el hilo durante el retraso aleatorio
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
