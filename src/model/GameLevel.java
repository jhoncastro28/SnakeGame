package model;

public class GameLevel {
    private int requiredScore;
    private int appleRatio;
    private int snakeSpeed;

    public GameLevel(int requiredScore, int appleRatio, int snakeSpeed) {
        this.requiredScore = requiredScore;
        this.appleRatio = appleRatio;
        this.snakeSpeed = snakeSpeed;
    }

    public int getRequiredScore() {
        return requiredScore;
    }

    public int getAppleRatio() {
        return appleRatio;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }
}