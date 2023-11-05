package presenter;

public class SnakeGame {
    private String playerName;

    public void run() {
        playerName = JOptionPane.showInputDialog(null, "¡Bienvenido! Por favor, ingresa tu nombre:");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Anónimo";
        }

        showMainMenu();
        new GameFrame();
    }

    public static void main(String[] args) {
        new SnakeGame().run();
    }
}
