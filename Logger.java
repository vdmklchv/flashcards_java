package flashcards;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    static List<String> log = new ArrayList<>();

    static List<String> getLog() {
        return log;
    }

    static void addToLog(String content) {
        log.add(content);
    }

    static void saveLog(Screen screen) {
        String fileName = screen.askForFileName();
        Logger.addToLog(fileName);
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String entry: log) {
                fileWriter.append(entry + "\n");
            }
            System.out.println("The log has been saved.");
            Logger.addToLog("The log has been saved.");
        } catch (IOException e) {
            System.out.println("File not found");
            Logger.addToLog("File not found");
        }
    }
}
