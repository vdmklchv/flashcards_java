package flashcards;

import java.util.ArrayList;
import java.util.List;

public class StatsManager {
    List<Card> getHardestCards(List<Card> cards) {
        int maxErrorCount = -1;
        List<Card> hardestCards = new ArrayList<>();
        for (Card card: cards) {
            if (card.getErrors() == 0) {
                continue;
            }
            if (card.getErrors() == maxErrorCount) {
                hardestCards.add(card);
            } else if (card.getErrors() > maxErrorCount) {
                maxErrorCount = card.getErrors();
                hardestCards.clear();
                hardestCards.add(card);
            }
        }
        return hardestCards;
    }

    void resetStats(CardStorage cardStorage) {
        List<Card> cards = cardStorage.getCards();
        for (Card card: cards) {
            card.setErrors(0);
        }
        System.out.println("Card statistics have been reset.");
        Logger.addToLog("Card statistics have been reset.");
    }
}
