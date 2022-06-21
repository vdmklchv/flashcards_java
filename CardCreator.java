package flashcards;

import java.util.Scanner;

public class CardCreator {
    Scanner sc = new Scanner(System.in);
    Card createFromUserInput(CardLookupTable cardLookupTable) {
        System.out.println("The card:");
        Logger.addToLog("The card:");
        String term = sc.nextLine();
        Logger.addToLog(term);
        if (cardLookupTable.isTermExist(term)) {
            String alreadyExistingCardMessage = "The card \"" + term +"\" already exists.";
            System.out.println(alreadyExistingCardMessage);
            Logger.addToLog(alreadyExistingCardMessage);
            return null;
        }

        System.out.println("The definition of the card:");
        Logger.addToLog("The definition of the card:");
        String definition = sc.nextLine();
        Logger.addToLog(definition);

        if (cardLookupTable.isDefinitionExist(definition)) {
            String definitionMessage = "The definition \"" + definition + "\" already exists.";
            System.out.println(definitionMessage);
            Logger.addToLog(definitionMessage);
            return null;
        }

        String pairAddedMessage = "The pair (\"" + term + "\":\"" + definition +"\") has been added.";
        System.out.println(pairAddedMessage);
        Logger.addToLog(pairAddedMessage);
        return new Card(term, definition);
    }

}
