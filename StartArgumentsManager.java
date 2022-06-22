package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;

public class StartArgumentsManager {

    Map<String, String> getStartArguments(String[] args) {
        if (!isArgLengthCorrect(args)) {
            return null;
        }
        Map<String, String> argsMap = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            argsMap.putIfAbsent(args[i], args[i + 1]);
        }
        return argsMap;
    }

    private boolean isArgLengthCorrect(String[] args) {
        return args.length % 2 == 0;
    }

    boolean hasArgument(Map<String, String> argMap, String argument) {
        return argMap.containsKey(argument);
    }
}
