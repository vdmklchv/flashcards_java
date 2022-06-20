package flashcards;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Screen {
    Scanner sc = new Scanner(System.in);

    void printCard(Card card) {
        System.out.println(card.toString());
    }

    String getUserAnswer() {
        return sc.nextLine();
    }

    String askForRemoveCardTerm() {
        System.out.println("Which card?");
        return sc.nextLine();
    }

    String askForFileName() {
        System.out.println("File name:\n");
        return sc.nextLine().toLowerCase();
    }

    int askForNumberOfQuestions() {
        int number = 0;

        while (number <= 0) {
            try {
                System.out.println("How many times to ask?");
                number = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("You should provide a number");
            }
        }

        return number;
    }

    void showMenu() {
        System.out.println("Input the action (add, remove, import, export, ask, exit):");
    }

    String getMenuUserCommand() {
        Set<String> commands = Set.of("add", "remove", "import", "export", "ask", "exit");
        String command = sc.nextLine().toLowerCase();
        while (!commands.contains(command)) {
            System.out.println("Wrong command.");
            showMenu();
            command = sc.nextLine().toLowerCase();
        }
        return command;
    }
}
