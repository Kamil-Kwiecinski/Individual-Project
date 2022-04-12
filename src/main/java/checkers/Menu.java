package checkers;

import java.util.Scanner;

public class Menu {
    private String name;
    private String withComputer;

    public Menu(String name, String withComputer) {
        this.name = name;
        this.withComputer = withComputer;
    }

    public void welcomeMenu(){
        System.out.println("Welcome to the Checkers!\nIn this game you can play with friend " +
                "or with computer.\\n");
    }

    public String nameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        name = scanner.next();
        return name;
    }

    public String chooseOpponentMenu(){
        boolean isTrue = false;
        while(!isTrue){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose your opponent:\n- Player (P) \n- Computer(C)");
            withComputer = scanner.next();
            if (withComputer.equals("P") || withComputer.equals("C")) {
                isTrue = true;
            } else {
                System.out.println("Wprowadź poprawne oznaczenie!");
            }
        }
        return withComputer;
    }

    public String quitMenu(){
        String quit = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to quit? Y/N");
        quit = scanner.next();
        return quit;
    }

    public void rulesMenu(){
        System.out.println("Rules of game:\n" +
                "-Standard board  8x8\n" +
                "-Whites start first\n" +
                "-Move only diagonal\n" +
                "-If there is an empty space behind the opponent's piece, it can be jumped over and the opponent's piece is eliminated\n" +
                "-The capture can be done by more than one pawn, as well as forwards and backwards.\n" +
                "-The last piece on the board becomes a queen. It can move any number of fields.\n" +
                "-The one who captures all opponent's pieces or blocks all pieces wins.\n");
    }

    public String getName() {
        return name;
    }

    public String getWithComputer() {
        return withComputer;
    }
}
