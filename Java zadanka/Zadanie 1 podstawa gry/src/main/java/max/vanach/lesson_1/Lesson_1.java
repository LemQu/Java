/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package max.vanach.lesson_1;

import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.util.Random;

class Player {
    private String nickname;
    private int bestScore;

    public Player(String nickname) {
        this.nickname = nickname;
        this.bestScore = Integer.MAX_VALUE;
    }

    public String getNickname() {
        return nickname;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}

class Game {
    private Player player;
    private final static String DIRECTORY = "./players/";
    private final static int MAX_NUMBER = 100;

    public Game(String nickname) {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File playerFile = new File(DIRECTORY + nickname + ".txt");
        if (playerFile.exists()) {
            // Load player data.
            try (Scanner reader = new Scanner(playerFile)) {
                String loadedNickname = reader.nextLine();
                int loadedBestScore = reader.nextInt();
                player = new Player(loadedNickname);
                player.setBestScore(loadedBestScore);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // New player.
            player = new Player(nickname);
        }
    }

    public void start() {
        System.out.println("Witaj " + player.getNickname() + "!");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(MAX_NUMBER + 1);
        int guessCount = 0;

        System.out.println("Wybrałem cyfrę pomiędzy 0 a " + MAX_NUMBER + ". Zgaduj!");
        while (true) {
            System.out.print("Twoja cyfra: ");
            int playerGuess = scanner.nextInt();
            guessCount++;

            if (playerGuess == randomNumber) {
                System.out.println("Brawo! Odgadłeś cyfrę w " + guessCount + " próbach.");
                if (guessCount < player.getBestScore()) {
                    player.setBestScore(guessCount);
                    System.out.println("Nowy najlepszy wynik!");
                }
                break;
            } else if (playerGuess < randomNumber) {
                System.out.println("za nisko!");
            } else {
                System.out.println("za wysoko!");
            }
        }

        // Save player data.
        try (PrintWriter writer = new PrintWriter(DIRECTORY + player.getNickname() + ".txt")) {
            writer.println(player.getNickname());
            writer.println(player.getBestScore());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Lesson_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Jak się nazywasz: ");
        String nickname = scanner.nextLine();

        Game game = new Game(nickname);
        game.start();
    }
}
