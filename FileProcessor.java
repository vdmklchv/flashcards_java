package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                fileWriter.write(card.getTerm() + " " + card.getDefinition() + "\n");
            }
            System.out.printf("%d cards have been saved.\n", counter);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    List<Card> restoreFromFile(File file, CardStorage cardStorage) {
        List<Card> cards = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            int counter = 0;
            while (scanner.hasNext()) {
                String[] splitLine = scanner.nextLine().split(" ");
                String term = splitLine[0];
                String definition = splitLine[1];
                Card card = new Card(term, definition);
                cards.add(card);
                counter++;
            }
            System.out.printf("%d cards have been loaded.\n", counter);
            return cards;
        } catch (IOException e) {
            System.out.println("File not found.");
        }


        return null;
    }
}
