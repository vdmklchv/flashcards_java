package flashcards;

import java.util.*;

public class CardLookupTable {

    Map<String, String> cardLookupTable;

    public CardLookupTable() {
        this.cardLookupTable = new HashMap<>();
    }

    public void updateCardLookupTable(List<Card> cards) {
        if (cards == null || cards.size() == 0) {
            return;
        }
        cardLookupTable.clear();
        for (Card card: cards) {
            if (card != null) {
                cardLookupTable.put(card.getTerm(), card.getDefinition());
            }
        }
    }

    void add(String term, String definition) {
        cardLookupTable.put(term, definition);
    }

    void remove(String term) {
        this.cardLookupTable.remove(term);
    }

    boolean isTermExist(String term) {
        return cardLookupTable.containsKey(term);
    }

    boolean isDefinitionExist(String definition) {
        return cardLookupTable.containsValue(definition);
    }

    public String getTermByDefinition(List<Card> cards, String definition) {
        for (Card card: cards) {
            if (card.getDefinition().equals(definition)) {
                return card.getTerm();
            }
        }
        return null;
    }
}
