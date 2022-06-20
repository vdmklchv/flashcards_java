package flashcards;

import java.util.Scanner;

public class CardCreator {
    Scanner sc = new Scanner(System.in);
    Card createFromUserInput(CardLookupTable cardLookupTable) {
        System.out.println("The card:");
        String term = sc.nextLine();
        if (cardLookupTable.isTermExist(term)) {
            System.out.printf("The card \"%s\" already exists.\n", term);
            return null;
        }

        System.out.println("The definition of the card:");
        String definition = sc.nextLine();

        if (cardLookupTable.isDefinitionExist(definition)) {
            System.out.printf("The definition \"%s\" already exists.\n", definition);
            return null;
        }

        System.out.printf("The pair (\"%s\":\"%s\") has been added\n", term, definition);
        return new Card(term, definition);
    }

}
