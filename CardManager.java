package flashcards;

import java.io.File;
import java.util.ArrayList;
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
        Logger.addToLog(term);
        if (cardLookupTable.isTermExist(term)) {
            Card card = cardStorage.findCardByTerm(term);
            cardStorage.removeCard(card);
            System.out.println("The card has been removed.");
            Logger.addToLog("The card has been removed.");
            cardLookupTable.remove(term);
        } else {
            String noSuchCardMessage = "Can't remove \"" + term + "\": there is no such card.";
            System.out.println(noSuchCardMessage);
            Logger.addToLog(noSuchCardMessage);
        }
    }

    void importCards(Screen screen, CardStorage cardStorage, CardLookupTable cardLookupTable) {
        String fileName = screen.askForFileName();
        Logger.addToLog(fileName);
        FileProcessor fileProcessor = new FileProcessor(fileName);
        File file = new File(fileProcessor.getName());
        List<Card> currentCards = cardStorage.getCards();
        if (currentCards == null) {
            currentCards = new ArrayList<>();
        }
        List<Card> cards = fileProcessor.restoreFromFile(file);
        List<Integer> indices = new ArrayList<>();
        if (cards != null) {
            for (Card card: cards) {
                for (Card oldCard: currentCards) {
                    if (oldCard.getTerm().equals(card.getTerm())) {
                        indices.add(currentCards.indexOf(oldCard));
                    }
                }
            }
        }
        for (int index: indices) {
            currentCards.remove(index);
        }
        if (cards != null) {
            currentCards.addAll(cards);
        }
        cardStorage.setCards(currentCards);
        cardLookupTable.updateCardLookupTable(currentCards);
    }

    void exportCards(CardStorage cardStorage, Screen screen) {
        List<Card> cards = cardStorage.getCards();
        String fileName = screen.askForFileName();
        Logger.addToLog(fileName);
        FileProcessor fileProcessor = new FileProcessor(fileName);
        File file = new File(fileProcessor.getName());
        fileProcessor.backupToFile(file, cards);
    }
}
