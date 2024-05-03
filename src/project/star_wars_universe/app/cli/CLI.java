package project.star_wars_universe.app.cli;

import project.star_wars_universe.app.cli.commands.CommandsList;
import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static String rawInput = "";
    private static List<String> processedInput = new ArrayList<>();
    private static CommandsList commandsList = CommandsList.getInstance();

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
            if(currentCharacter == '"') {
                inQuotes = (inQuotes) ? false : true;
                continue;
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

    public static void readInput() {
        try {
            commandsList.getCommand(processedInput.get(0)).execute(processedInput);
        }
        catch(CommandExecutionException ex) {
            System.out.println(ex.getMessage());

            if(ex instanceof WrongArgumentsCountException || ex instanceof UnknownCommandException) {
                printHelpMessage();
            }
        }
    }

    public static void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
