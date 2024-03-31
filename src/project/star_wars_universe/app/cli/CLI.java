package project.star_wars_universe.app.cli;

import project.star_wars_universe.app.cli.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static String rawInput = "";
    private static List<String> processedInput = new ArrayList<>();

    public static void start() throws Exception {
        while(true) {
            Scanner in = new Scanner(System.in);
            rawInput = in.nextLine();
            processInput();
            readInput();
        }
    }

    public static void processInput() {
        int segmentsCount = 0;
        char currentCharacter;
        StringBuilder currentSegment = new StringBuilder();
        boolean inQuotes = false;

        processedInput.clear();

        for(int i = 0; i < rawInput.length(); i++) {
            currentCharacter = rawInput.charAt(i);
            if(currentCharacter == '"' && !inQuotes) {
                inQuotes = (inQuotes) ? false : true;
            }

            if((currentCharacter == ' ' && !inQuotes)){
                processedInput.add(currentSegment.toString());
                currentSegment.setLength(0);
                continue;
            }

            currentSegment.append(currentCharacter);
        }

        if(currentSegment.length() != 0) {
            processedInput.add(currentSegment.toString());
        }
    }

    public static void readInput() throws Exception {
        switch(processedInput.get(0)) {
            case "help":
                (new Help()).execute();
                break;
            case "open":
                (new Open(processedInput)).execute();
                break;
            case "save":
                (new Save()).execute();
                break;
            case "saveas":
                (new SaveAs(processedInput)).execute();
            case "close":
                (new Close()).execute();
                break;
            case "exit":
                (new Exit()).execute();
                break;
            default:
                wrongCommand();
        }
    }

    public static void wrongCommand() {
        System.out.println("No such command!");
        printHelpMessage();
    }

    public static void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
