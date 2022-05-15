package com.theory.probability;

public class Game {
    private final int[][] gameField = {{2, -3},
                                       {-1, 2}};
    private Player playerA;
    private Player playerB;
    private Statistic statistic;

    public Game(double aProbability, double bProbability) {
        playerA = new Player(aProbability);
        playerB = new Player(bProbability);
        statistic = new Statistic(playerA, playerB, gameField);
    }

    public void startGame(char type) {
        if (type == '1') {
            basicGame();
        } else if (type == '2') {
            learn(LearnStrategy.REINFORCEMENT_STRATEGY);
        } else if (type == '3') {
            learn(LearnStrategy.PUNISHMENT_STRATEGY);
        } else if (type == '4') {
            learn(LearnStrategy.BOTH_REINFORCEMENT_STRATEGY);
        }
    }

    private void basicGame() {
        for (int i = 0; i < 100; ++i) {
            int choiceA = playerA.choose();
            int choiceB = playerB.choose();

            int element = gameField[choiceA][choiceB];
            if (element > 0) {
                playerA.setScore(playerA.getScore() + element);
            } else {
                playerB.setScore(playerB.getScore() - element);
            }
        }

        statistic.printStatistic();
    }

    private void learn(LearnStrategy strategy) {
        if (strategy == LearnStrategy.REINFORCEMENT_STRATEGY) {
            playerA.setRedBalls(10);
            playerA.setBlueBalls(10);

            for (int i = 0; i < 100; ++i) {
                int choiceA = playerA.choose();
                int choiceB = playerB.choose();

                int element = gameField[choiceA][choiceB];
                if (element > 0) {
                    playerA.performReinforcement(choiceA, element);
                }
            }

            basicGame();
        } else if (strategy == LearnStrategy.PUNISHMENT_STRATEGY) {
            playerA.setRedBalls(100);
            playerA.setBlueBalls(100);

            for (int i = 0; i < 100; ++i) {
                int choiceA = playerA.choose();
                int choiceB = playerB.choose();

                int element = gameField[choiceA][choiceB];
                if (element < 0) {
                    playerA.performPunishment(choiceA, element);
                }
            }

            basicGame();
        } else if (strategy == LearnStrategy.BOTH_REINFORCEMENT_STRATEGY) {
            playerA.setRedBalls(10);
            playerA.setBlueBalls(10);
            playerB.setRedBalls(10);
            playerB.setBlueBalls(10);

            for (int i = 0; i < 100; ++i) {
                int choiceA = playerA.choose();
                int choiceB = playerB.choose();

                int element = gameField[choiceA][choiceB];
                if (element > 0) {
                    playerA.performReinforcement(choiceA, element);
                } else {
                    playerB.performReinforcement(choiceB, element);
                }
            }

            basicGame();
        }
    }

    public void clean() {
        playerA.setScore(0);
        playerB.setScore(0);
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

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
