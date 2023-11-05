package view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.util.Random;
import model.AppleGenerationThread;
import model.ScoreThread;
import presenter.SnakeGame;

public class GamePanel extends JPanel implements ActionListener {
    static final int screenWidth = 1300;
    static final int screenHeight = 750;
    static final int unitSize = 50;
    static final int gameUnits = (screenWidth * screenHeight) / (unitSize * unitSize);
    static final int delay = 175;
    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];
    int bodyParts = 4;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random, aleatorio;
    private int intervaloComida;
    private Color appleColor;
    private JLabel scoreLabel; // Etiqueta para mostrar la puntuación
    private SnakeGame sn;

    public GamePanel(SnakeGame sn) {
        this.sn = sn;
    }

    GamePanel() {
        random = new Random();
        aleatorio = new Random();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
        // Iniciar el hilo de puntuación
        ScoreThread scoreThread = new ScoreThread(this);
        scoreThread.start();
        // Iniciar el hilo de generación de manzanas
        AppleGenerationThread appleThread = new AppleGenerationThread(this);
        appleThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(appleColor); // Establece el color de la manzana
            g.fillOval(appleX, appleY, unitSize, unitSize);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (screenWidth - metrics.stringWidth("Score: " + applesEaten)) / 2,
                    g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleColor = Color.red;

        /*
         * int randomValue = aleatorio.nextInt(100);
         *
         * if (randomValue <= intervaloComida) {
         * appleColor = Color.red; // Manzana buena
         * }else if (randomValue > intervaloComida) {
         * appleColor = Color.blue; // Manzana mala
         * }
         */
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
        /*
         * if ((x[0] == appleX) && (y[0] == appleY)) {
         * if (appleColor.equals(Color.blue)) {
         * running = false;
         * } else if (appleColor.equals(Color.red)) {
         * bodyParts++;
         * applesEaten++;
         * newApple();
         * }
         * }
         */
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = (y[0] - unitSize + screenHeight) % screenHeight;
                break;
            case 'D':
                y[0] = (y[0] + unitSize) % screenHeight;
                break;
            case 'L':
                x[0] = (x[0] - unitSize + screenWidth) % screenWidth;
                break;
            case 'R':
                x[0] = (x[0] + unitSize) % screenWidth;
                break;
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if (!running) {
            timer.stop();
        }
        /*
         * // Esta parte revisa que si se choca con alguno de los bordes, pues acabe el
         * juego.
         * // Por si se quiere integrar
         * if (x[0] < 0) {
         * running = false;
         * }
         *
         * if (x[0] > screenWidth) {
         * running = false;
         * }
         *
         * if (y[0] < 0) {
         * running = false;
         * }
         *
         * if (y[0] > screenHeight) {
         * running = false;
         * }
         */
    }

    public void gameOver(Graphics g) {
        // Puntuación
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (screenWidth - metrics1.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize());
        // Aviso de Juego Terminado
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Has Perdido", (screenWidth - metrics2.stringWidth("Has Perdido")) / 2, screenHeight / 2);

        /*
         * String playerName = sn.getPlayerName();
         * int score = applesEaten;
         * PlayerData playerData = new PlayerData(playerName, score);
         * try {
         * ObjectOutputStream outputStream = new ObjectOutputStream(
         * new FileOutputStream("src\\resources\\scores.txt"));
         * outputStream.writeObject(playerData);
         * outputStream.close();
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
    }

    // Getter y setter de la etiqueta de puntuación
    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    // Setter para actualizar el número de manzanas que se ha comido la serpiente
    public void setApplesEaten(int applesEaten) {
        this.applesEaten = applesEaten;
    }

    // Método para actualizar la etiqueta de puntuación en la interfaz gráfica
    public void updateScoreLabel(int score) {
        if (scoreLabel != null) {
            scoreLabel.setText("Puntuación: " + score);
        }
    }

    // Métodos para manejar las coordenadas de la manzana
    public void setAppleCoordinates(int x, int y) {
        this.appleX = x;
        this.appleY = y;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    // Métodos para obtener el tamaño de las unidades y otras propiedades
    public int getUnitSize() {
        return unitSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getGameUnits() {
        return gameUnits;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public char getDirection() {
        return direction;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getApplesEaten() {
        return applesEaten;
    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    public void Configuration() {
        // Valor predeterminado para el intervalo de comida
        int defaultIntervaloComida = 70;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\resources\\config.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("IntervaloComida=")) {
                    intervaloComida = Integer.parseInt(line.split("=")[1]);
                    setIntervaloComida(intervaloComida);
                    break; // Una vez que se encuentra el valor, se puede salir del bucle.
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de configuración: " + e.getMessage());
            intervaloComida = defaultIntervaloComida;
        }
    }

    public int getIntervaloComida() {
        return intervaloComida;
    }

    public void setIntervaloComida(int intervaloComida) {
        this.intervaloComida = intervaloComida;
    }
}