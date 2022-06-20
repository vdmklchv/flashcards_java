package flashcards;

import java.util.ArrayList;
import java.util.List;

public class CardStorage {
    List<Card> cards;

    List<Card> getCards() {
        return cards;
    }

    void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardStorage() {
        this.cards = new ArrayList<>();
    }

    void storeCard(Card card) {
        this.cards.add(card);
    }

    Card getCardAtIndex(int index) {
        return this.cards.get(index);
    }

    void removeCard(Card card) {
        this.cards.remove(card);
    }

    Card findCardByTerm(String term) {
        for (Card card: this.cards) {
            if (card.getTerm().equals(term)) {
                return card;
            }
        }
        return null;
    }
}
