package flashcards;
import flashcards.Enums.*;

public class App {

    APP_STATE appState = APP_STATE.ON;
    CardLookupTable cardLookupTable = new CardLookupTable();
    CardStorage cardStorage = new CardStorage();
    CardManager cardManager = new CardManager();
    Screen screen = new Screen();
    void start() {
        while (isAppOn()) {
            screen.showMenu();
            String command = screen.getMenuUserCommand();
            action(command);
        }
    }

    private void action(String command) {
        switch (command) {
            case "add":
                Card card = cardManager.createCard(cardLookupTable);
                cardManager.saveCard(card, cardStorage, cardLookupTable);
                break;
            case "remove":
                cardManager.removeCard(screen, cardLookupTable, cardStorage);
                break;
            case "import":
                cardManager.importCards(screen, cardStorage, cardLookupTable);
                break;
            case "export":
                cardManager.exportCards(cardStorage, screen);
                break;
            case "ask":
                int numberOfQuestions = screen.askForNumberOfQuestions();
                Quiz quiz = new Quiz();
                quiz.start(numberOfQuestions, cardStorage, screen, cardLookupTable);
                break;
            case "exit":
                switchAppState();
                System.out.println("Bye bye!");
                break;
            case "default":
                System.out.println("Strange command");
                break;
        }
    }

    private void switchAppState() {
        appState = appState == APP_STATE.ON ? APP_STATE.OFF : APP_STATE.ON;
    }

    boolean isAppOn() {
        return appState == APP_STATE.ON;
    }
}
