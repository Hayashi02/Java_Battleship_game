package main.java;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game1 = new Game();

        boolean status = true;
        do{
            System.out.println("S to Start . Q to quit: ");
            String input = scanner.nextLine().toUpperCase().trim();

            if(input.equals("S")){
                game1.StartGame();
            }else if(input.equals("Q")){
                status = false;
            }
        }while(status);
        
        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
