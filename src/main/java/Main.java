package main.java;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game1 = new Game();
        boolean quit = false;

        while(!quit){
            System.out.println("S to Start . Q to quit: ");
            String input = scanner.nextLine().toUpperCase().trim();

            switch(input){
                case "S":
                    game1.StartGame();
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input, Please enter 's' to start or 'q' to quit.");
            }
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
