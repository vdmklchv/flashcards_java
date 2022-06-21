package flashcards;
import flashcards.Enums.*;

import java.util.List;

public class App {

    APP_STATE appState = APP_STATE.ON;
    CardLookupTable cardLookupTable = new CardLookupTable();
    CardStorage cardStorage = new CardStorage();
    CardManager cardManager = new CardManager();
    Screen screen = new Screen();
    StatsManager statsManager = new StatsManager();


    void start() {
        while (isAppOn()) {
            screen.showMenu();
            String command = screen.getMenuUserCommand();
            action(command);
        }
    }

    private void action(String command) {
        switch (command) {
            case "add" -> {
                Card card = cardManager.createCard(cardLookupTable);
                cardManager.saveCard(card, cardStorage, cardLookupTable);
            }
            case "remove" -> cardManager.removeCard(screen, cardLookupTable, cardStorage);
            case "import" -> cardManager.importCards(screen, cardStorage, cardLookupTable);
            case "export" -> cardManager.exportCards(cardStorage, screen);
            case "ask" -> {
                int numberOfQuestions = screen.askForNumberOfQuestions();
                Quiz quiz = new Quiz();
                quiz.start(numberOfQuestions, cardStorage, screen, cardLookupTable);
            }
            case "exit" -> {
                switchAppState();
                System.out.println("Bye bye!");
            }
            case "log" -> Logger.saveLog(screen);
            case "hardest card" -> {
                List<Card> hardestCards = statsManager.getHardestCards(cardStorage.getCards());
                screen.printHardestCards(hardestCards);
            }
            case "reset stats" -> statsManager.resetStats(cardStorage);
            case "default" -> System.out.println("Strange command");
        }
    }

    private void switchAppState() {
        appState = appState == APP_STATE.ON ? APP_STATE.OFF : APP_STATE.ON;
    }

    boolean isAppOn() {
        return appState == APP_STATE.ON;
    }
}
