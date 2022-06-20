package flashcards;

import java.io.File;
import java.util.List;

public class CardManager {
    void saveCard(Card card, CardStorage cardStorage, CardLookupTable cardLookupTable) {
        if (card != null) {
            cardStorage.storeCard(card);
            cardLookupTable.add(card.getTerm(), card.getDefinition());
        }
    }

    Card createCard(CardLookupTable cardLookupTable) {
        CardCreator cardCreator = new CardCreator();
        return cardCreator.createFromUserInput(cardLookupTable);
    }

    void removeCard(Screen screen, CardLookupTable cardLookupTable, CardStorage cardStorage) {
        String term = screen.askForRemoveCardTerm();
        if (cardLookupTable.isTermExist(term)) {
            Card card = cardStorage.findCardByTerm(term);
            cardStorage.removeCard(card);
            System.out.println("The card has been removed.");
            cardLookupTable.remove(term);
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", term);
        }
    }

    void importCards(Screen screen, CardStorage cardStorage, CardLookupTable cardLookupTable) {
        String fileName = screen.askForFileName();
        FileProcessor fileProcessor = new FileProcessor(fileName);
        File file = new File(fileProcessor.getName());
        List<Card> currentCards = cardStorage.getCards();
        List<Card> cards = fileProcessor.restoreFromFile(file, cardStorage);
        if (currentCards != null && cards != null) {
            cards.addAll(currentCards);
        }
        cardStorage.setCards(cards);
        cardLookupTable.updateCardLookupTable(cards);
    }

    void exportCards(CardStorage cardStorage, Screen screen) {
        List<Card> cards = cardStorage.getCards();
        String fileName = screen.askForFileName();
        FileProcessor fileProcessor = new FileProcessor(fileName);
        File file = new File(fileProcessor.getName());
        fileProcessor.backupToFile(file, cards);
    }

}
