package com.theory.probability;

public class Main {
    public static void main(String[] args) {
        System.out.println("Игра с равными вероятностями по 50%:");
        Game game = new Game(0.5, 0.5);
        game.startGame('1');
        game.clean();

        System.out.println("Игра при которой B выбирает красный столбец втрое реже синего:");
        game.getPlayerA().setProbability(0.5);
        game.getPlayerB().setProbability(0.25);
        game.startGame('1');
        game.clean();

        System.out.println("Обучение с подкреплением игрока A:");
        game.getPlayerA().setProbability(0.5);
        game.getPlayerB().setProbability(0.25);
        game.startGame('2');
        game.clean();

        System.out.println("Обучение с наказанием для игрока A:");
        game.getPlayerA().setProbability(0.5);
        game.getPlayerB().setProbability(0.25);
        game.startGame('3');
        game.clean();

        System.out.println("Обучение с подкреплением обоих игроков:");
        game.getPlayerA().setProbability(0.5);
        game.getPlayerB().setProbability(0.25);
        game.startGame('4');
        game.clean();
    }
}
