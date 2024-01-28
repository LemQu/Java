package max.vanach.lesson_1;

import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.util.Random; // Import Random class to generate random numbers

/**
 *
 * @max
 */ 
public class Lesson_1 {
    
    static void setSay() {
        System.out.println("saing: I'm procedure !!!");
    }

    static String getSay() {
        return "saing: I'm function !!!";
    }
   
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj swoje imię: ");
        String name = scan.nextLine();
        
        System.out.print("Podaj swoje nazwisko: ");
        String lastname = scan.nextLine();
        
        person person = new person(name, lastname);
        person.sayHello();
        
        System.out.println("Wybierz tryb gry:");
        System.out.println("1: Łatwy (Przedział od 0 do 100)");
        System.out.println("2: Trudny (Przedział: od 0 do 1000)");
        System.out.print("Wybierz (1 lub 2): ");
        int gameMode = scan.nextInt();

        int rangeMax;
        if (gameMode == 1) {
            rangeMax = 100;
        } else if (gameMode == 2) {
            rangeMax = 1000;
        } else {
            System.out.println("Błędny wybór. Wybrano domyślny tryb Łatwy.");
            rangeMax = 100;
        }

        Random random = new Random();
        int numberToGuess = random.nextInt(rangeMax + 1);

        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Myślę pomiędzy cyfrą 0 a " + rangeMax + "...");
        System.out.println("Powodzenia!");

        while (!hasGuessedCorrectly) {
            System.out.print("Twój strzał: ");
            int userGuess = scan.nextInt();
            numberOfTries++;

            if (userGuess < 0 || userGuess > rangeMax) {
                System.out.println("Proszę odgadnij cyfrę pomiędzy 0 a " + rangeMax + "!");
            } else if (userGuess < numberToGuess) {
                System.out.println("Wyżej " + userGuess + ". spróbuj jeszcze raz!");
            } else if (userGuess > numberToGuess) {
                System.out.println("Niżej " + userGuess + ". spróbuj jeszcze raz!");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Brawo! Odgadłeś cyfrę: " + numberToGuess);
                System.out.println("Zajeło ci to " + numberOfTries + " prób.");
            }
        }
        
        // ... (rest of the existing code)
    }
}
