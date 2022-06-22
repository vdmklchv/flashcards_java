package flashcards;

public class Quiz {
    void start(int numberOfCards, CardStorage cardStorage, Screen screen, CardLookupTable cardLookupTable) {
        if (numberOfCards > cardStorage.getCards().size()) {
            System.out.println("You asked for more cards than exists in database.");
            return;
        }
        for (int i = 1; i <= numberOfCards; i++) {
            Card currentCard = cardStorage.getCardAtIndex(i - 1);
            String printCardMessage = "Print the definition of \"" + currentCard.getTerm() + "\":";
            System.out.println(printCardMessage);
            Logger.addToLog(printCardMessage);
            String answer = screen.getUserAnswer();
            Logger.addToLog(answer);
            if (isAnswerCorrect(answer, currentCard.getDefinition())) {
                System.out.println("Correct!");
                Logger.addToLog("Correct!");
            } else if (cardLookupTable.getTermByDefinition(cardStorage.getCards(), answer) != null) {
                String definitionCorrectForAnotherTerm = "Wrong. The right answer is \"" + currentCard.getDefinition()
                        + "\", but your definition is correct for \""
                        + cardLookupTable.getTermByDefinition(cardStorage.getCards(), answer) + "\".";
                System.out.println(definitionCorrectForAnotherTerm);
                Logger.addToLog(definitionCorrectForAnotherTerm);
                currentCard.incrementErrors();
            } else {
                String wrongAnswerMessage = "Wrong. The right answer is \"" + currentCard.getDefinition() + "\".";
                System.out.println(wrongAnswerMessage);
                Logger.addToLog(wrongAnswerMessage);
                currentCard.incrementErrors();
            }
        }
    }

    boolean isAnswerCorrect(String answer, String definition) {
        return answer.equalsIgnoreCase(definition);
    }
}
