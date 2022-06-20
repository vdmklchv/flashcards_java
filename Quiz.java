package flashcards;

public class Quiz {
    void start(int numberOfCards, CardStorage cardStorage, Screen screen, CardLookupTable cardLookupTable) {
        for (int i = 1; i <= numberOfCards; i++) {
            Card currentCard = cardStorage.getCardAtIndex(i - 1);
            System.out.printf("Print the definition of \"%s\":\n", currentCard.getTerm());
            String answer = screen.getUserAnswer();
            if (isAnswerCorrect(answer, currentCard.getDefinition())) {
                System.out.println("Correct!");
            } else if (cardLookupTable.getTermByDefinition(cardStorage.getCards(), answer) != null) {
                System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n",
                        currentCard.getDefinition(), cardLookupTable.getTermByDefinition(cardStorage.getCards(), answer));
            } else {
                System.out.printf("Wrong. The right answer is \"%s\".\n", currentCard.getDefinition());
            }
        }
    }

    boolean isAnswerCorrect(String answer, String definition) {
        return answer.equalsIgnoreCase(definition);
    }
}
