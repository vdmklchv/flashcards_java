package flashcards;

public class Card {
    private final String term;
    private final String definition;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public String toString() {
        return "Card:\n" + this.term + "\n" + "Definition\n" + this.definition;
    }
}
