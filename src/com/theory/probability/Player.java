package com.theory.probability;

import java.util.Random;

public class Player {
    private static final Random random = new Random(System.nanoTime());

    private double probability;
    private int blueBalls = 0;
    private int redBalls = 0;
    private int score = 0;
    private double scoreAverage = 0;

    public Player(double probability) {
        this.probability = probability;
    }

    public int choose() {
        double choice = random.doubles(0, 1)
                .findFirst()
                .getAsDouble();

        if (choice > probability) {
            return 1;
        } else {
            return 0;
        }
    }

    public void performReinforcement(int color, int value) {
        if (color == 0) {
            redBalls += value;
        } else {
            blueBalls += value;
        }

        probability = (double)redBalls / (redBalls + blueBalls);
    }

    public void performPunishment(int color, int value) {
        value = Math.abs(value);
        if (color == 0) {
            redBalls -= value;
        } else {
            blueBalls -= value;
        }

        blueBalls = Math.max(blueBalls, 0);
        redBalls = Math.max(redBalls, 0);

        if (redBalls + blueBalls == 0) {
            probability = 0;
        } else {
            probability = (double)redBalls / (redBalls + blueBalls);
        }
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getBlueBalls() {
        return blueBalls;
    }

    public void setBlueBalls(int blueBalls) {
        this.blueBalls = blueBalls;
    }

    public int getRedBalls() {
        return redBalls;
    }

    public void setRedBalls(int redBalls) {
        this.redBalls = redBalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getScoreAverage() {
        return scoreAverage;
    }

    public void setScoreAverage(double scoreAverage) {
        this.scoreAverage = scoreAverage;
    }
}
