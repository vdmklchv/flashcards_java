package flashcards;

public class Card {
    private final String term;
    private final String definition;

    private int errors;

    public Card(String term, String definition) {
        this(term, definition, 0);
    }

    public Card(String term, String definition, int errors) {
        this.term = term;
        this.definition = definition;
        this.errors = errors;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getErrors() {
        return this.errors;
    }

    public void setErrors(int numberOfErrors) {
        this.errors = numberOfErrors;
    }
    void incrementErrors() {
        this.errors++;
    }
    public String toString() {
        return "Card:\n" + this.term + "\n" + "Definition\n" + this.definition;
    }
}
