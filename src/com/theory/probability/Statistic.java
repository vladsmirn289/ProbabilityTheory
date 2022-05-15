package com.theory.probability;

public class Statistic {
    private Player playerA;
    private Player playerB;
    private final int[][] gameField;

    public Statistic(Player playerA, Player playerB, int[][] gameField) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.gameField = gameField;
    }

    public void printStatistic() {
        setPlayersAverage();
        double mathExpect = calculateMathExpect();
        double deviationA = calculateDeviation(playerA);
        double deviationB = calculateDeviation(playerB);
        double dispersion = calculateDispersion(mathExpect);
        double theoryDeviation = calculateTheoryDeviation(dispersion);

        System.out.println("----------------------------------------------");
        System.out.println("Очки игроков A и B: " + playerA.getScore() + ":" + playerB.getScore());
        if (playerA.getScore() > playerB.getScore()) {
            System.out.println("Выиграл игрок A!");
        } else if (playerA.getScore() < playerB.getScore()) {
            System.out.println("Выиграл игрок B!");
        } else {
            System.out.println("Ничья!");
        }
        System.out.println("Среднее арифметическое очков игрока A: " + playerA.getScoreAverage());
        System.out.println("Среднее арифметическое очков игрока B: " + playerB.getScoreAverage());
        System.out.println("Математическое ожидание: " + mathExpect);
        System.out.println("СКО для игрока A: " + deviationA);
        System.out.println("СКО для игрока B: " + deviationB);
        System.out.println("Дисперсия: " + dispersion);
        System.out.println("Теоретическое СКО: " + theoryDeviation);
        System.out.println("----------------------------------------------");
        System.out.println();
    }

    public void setPlayersAverage() {
        playerA.setScoreAverage((double)playerA.getScore() / 100);
        playerB.setScoreAverage((double)playerB.getScore() / 100);
    }

    public double calculateMathExpect() {
        double aProbability = playerA.getProbability();
        double bProbability = playerB.getProbability();

        return gameField[0][0] * aProbability * bProbability +
               gameField[0][1] * aProbability * (1 - bProbability) +
               gameField[1][0] * (1 - aProbability) * bProbability +
               gameField[1][1] * (1 - aProbability) * (1 - bProbability);
    }

    public double calculateDeviation(Player player) {
        int cellsCount = 4;
        return Math.sqrt((Math.pow(gameField[0][0] - player.getScoreAverage(), 2) +
                          Math.pow(gameField[0][1] - player.getScoreAverage(), 2) +
                          Math.pow(gameField[1][0] - player.getScoreAverage(), 2) +
                          Math.pow(gameField[1][1] - player.getScoreAverage(), 2)) / (double)(cellsCount-1));
    }

    public double calculateDispersion(double mathExpect) {
        double aProbability = playerA.getProbability();
        double bProbability = playerB.getProbability();

        double mX2 = Math.pow(gameField[0][0], 2) * aProbability * bProbability +
                     Math.pow(gameField[0][1], 2) * aProbability * (1 - bProbability) +
                     Math.pow(gameField[1][0], 2) * (1 - aProbability) * bProbability +
                     Math.pow(gameField[1][1], 2) * (1 - aProbability) * (1 - bProbability);

        return mX2 - Math.pow(mathExpect, 2);
    }

    public double calculateTheoryDeviation(double dispersion) {
        return Math.sqrt(dispersion);
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }
}
