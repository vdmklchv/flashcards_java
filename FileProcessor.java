package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
    private final String name;

    public String getName() {
        return name;
    }

    public FileProcessor(String name) {
        this.name = name;
    }

    void backupToFile(File file, List<Card> cardList) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            int counter = 0;
            for (Card card: cardList) {
                counter++;
                fileWriter.write(card.getTerm() + ";" + card.getDefinition() + ";" + card.getErrors() + "\n");
            }
            String savedCardsMessage = counter + " cards have been saved.";
            System.out.println(savedCardsMessage);
            Logger.addToLog(savedCardsMessage);
        } catch (IOException e) {
            System.out.println("File not found.");
            Logger.addToLog("File not found.");
        }
    }

    List<Card> restoreFromFile(File file) {
        List<Card> cards = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            int counter = 0;
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(";");
                String term = splitLine[0];
                String definition = splitLine[1];
                int errors = Integer.parseInt(splitLine[2]);
                Card card = new Card(term, definition, errors);
                cards.add(card);
                counter++;
            }
            String loadedCardsMessage = counter + " cards have been loaded";
            System.out.println(loadedCardsMessage);
            Logger.addToLog(loadedCardsMessage);
            return cards;
        } catch (IOException e) {
            System.out.println("File not found.");
            Logger.addToLog("File not found.");
        }


        return null;
    }
}
