package flashcards;
import flashcards.Enums.*;

import java.util.List;
import java.util.Map;

public class App {

    APP_STATE appState = APP_STATE.ON;
    CardLookupTable cardLookupTable = new CardLookupTable();
    CardStorage cardStorage = new CardStorage();
    CardManager cardManager = new CardManager();
    Screen screen = new Screen();


    void start(String[] args) {
        StartArgumentsManager startArgumentsManager = new StartArgumentsManager();

        boolean hasExportParameter = false;
        String exportParameter = "";

        Map<String, String> startArgumentsMap = startArgumentsManager.getStartArguments(args);
        if (startArgumentsManager.hasArgument(startArgumentsMap, "-import")) {
            String fileName = startArgumentsMap.get("-import");
            cardManager.importCards(screen, cardStorage, cardLookupTable, fileName);
        }
        if (startArgumentsManager.hasArgument(startArgumentsMap, "-export")) {
            hasExportParameter = true;
            exportParameter = startArgumentsMap.get("-export");
        }
        while (isAppOn()) {
            screen.showMenu();
            String command = screen.getMenuUserCommand();
            action(command, hasExportParameter, exportParameter);
        }
    }

    private void action(String command, boolean hasExportParameter, String exportParameter) {
        StatsManager statsManager = new StatsManager();

        switch (command) {
            case "add":
                Card card = cardManager.createCard(cardLookupTable);
                cardManager.saveCard(card, cardStorage, cardLookupTable);
                break;
            case "remove":
                cardManager.removeCard(screen, cardLookupTable, cardStorage);
                break;
            case "import":
                cardManager.importCards(screen, cardStorage, cardLookupTable, "");
                break;
            case "export":
                cardManager.exportCards(cardStorage, screen, exportParameter);
                break;
            case "ask":
                int numberOfQuestions = screen.askForNumberOfQuestions();
                Quiz quiz = new Quiz();
                quiz.start(numberOfQuestions, cardStorage, screen, cardLookupTable);
                break;
            case "exit":
                if (hasExportParameter) {
                    cardManager.exportCards(cardStorage, screen, exportParameter);
                }
                switchAppState();
                System.out.println("Bye bye!");
                break;
            case "log":
                Logger.saveLog(screen);
                break;
            case "hardest card":
                List<Card> hardestCards = statsManager.getHardestCards(cardStorage.getCards());
                screen.printHardestCards(hardestCards);
                break;
            case "reset stats":
                statsManager.resetStats(cardStorage);
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
