package flashcards;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Screen {
    Scanner sc = new Scanner(System.in);

    void printCard(Card card) {
        System.out.println(card.toString());
    }

    String getUserAnswer() {
        String userAnswer = sc.nextLine();
        Logger.addToLog(userAnswer);
        return userAnswer;
    }

    String askForRemoveCardTerm() {
        System.out.println("Which card?");
        Logger.addToLog("Which card?");
        String removeCardTerm = sc.nextLine();
        Logger.addToLog(removeCardTerm);
        return removeCardTerm;
    }

    String askForFileName() {
        System.out.println("File name:");
        Logger.addToLog("File name:");
        String fileName = sc.nextLine();
        Logger.addToLog(fileName);
        return fileName.toLowerCase();
    }

    int askForNumberOfQuestions() {
        int number = 0;

        while (number <= 0) {
            try {
                System.out.println("How many times to ask?");
                Logger.addToLog("How many times to ask?");
                number = Integer.parseInt(sc.nextLine());
                Logger.addToLog(String.valueOf(number));
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("You should provide a number");
                Logger.addToLog("You should provide a number");
            }
        }

        return number;
    }

    void showMenu() {
        System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        Logger.addToLog("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
    }

    String getMenuUserCommand() {
        Set<String> commands = Set.of("add", "remove", "import", "export", "ask", "exit", "log", "hardest card", "reset stats");
        String command = sc.nextLine().toLowerCase();
        Logger.addToLog(command);
        while (!commands.contains(command)) {
            System.out.println("Wrong command.");
            Logger.addToLog("Wrong command.");
            showMenu();
            command = sc.nextLine().toLowerCase();
            Logger.addToLog(command);
        }
        return command;
    }

    void printHardestCards(List<Card> cards) {
        if (cards.size() == 0) {
            System.out.println("There are no cards with errors.");
            Logger.addToLog("There are no cards with errors.");
        } else if (cards.size() == 1) {
            String hardestCardMessage = "The hardest card is \"" + cards.get(0).getTerm() + "\". You " +
                    "have " + cards.get(0).getErrors() + " errors answering it.";
            System.out.println(hardestCardMessage);
            Logger.addToLog(hardestCardMessage);
        } else {
            String hardestCards = cards.stream().map(Card::getTerm).collect(Collectors.joining("\", \""));
            String hardestCardsMessage = "The hardest cards are \"" + hardestCards + "\". You have "
                    + cards.get(0).getErrors() + " errors answering them.";
            System.out.println(hardestCardsMessage);
            Logger.addToLog(hardestCardsMessage);
        }
    }

}
