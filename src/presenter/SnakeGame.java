package presenter;

import view.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jhonm
 */
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

    private void showMainMenu() {
        String[] options = { "Iniciar Juego", "Historial de Puntuaciones", "Información del Desarrollador" };
        int choice = JOptionPane.showOptionDialog(null, "Hola, " + playerName + "!\nPor favor, selecciona una opción:",
                "Menú Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0:
                // Inicia el Juego
                break;
            case 1:
                // showScoreHistory(); // Se suponía que muestra el historial de puntuaciones
                String mensaje = "No pude hacer que funcionara esta vuelta, quedó comentariada la lógica :c";
                JOptionPane.showMessageDialog(null, mensaje, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                showMainMenu();
                break;
            case 2:
                showDeveloperInfo();
                showMainMenu();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Hasta Pronto :)",
                        "Cerrando Programa...",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                break;
        }
    }

    /*
     * public void showScoreHistory() {
     * // Leer el archivo de puntuaciones
     * try {
     * BufferedReader reader = new BufferedReader(new
     * FileReader("src\\resources\\scores.txt"));
     * String line;
     * while ((line = reader.readLine()) != null) {
     * // Tokenizar la línea
     * String[] tokens = line.split(",");
     *
     * // Obtener los datos del jugador
     * String name = tokens[0];
     * int score = Integer.parseInt(tokens[1]);
     * Date date = new Date(Long.parseLong(tokens[2]));
     *
     * // Mostrar los datos del jugador
     * System.out.println("Nombre: " + name);
     * System.out.println("Puntuación: " + score);
     * System.out.println("Fecha: " + date);
     * }
     * reader.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */

    private void showDeveloperInfo() {
        ImageIcon icon = new ImageIcon("src\\resources\\logo.jpg"); // Carga el logo de la Gloriosa
        JLabel label = new JLabel();
        label.setIcon(icon);

        String developerInfo = "Nombre y Apellidos: Jhon Castro Mancipe\n" +
                "Identificación del Estudiante: 202127417\n" +
                "Carrera: Ingeniería de Sistemas y Computación\n" +
                "Año: 2023";

        JOptionPane.showMessageDialog(null, new Object[] { developerInfo, label }, "Información del Desarrollador",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}