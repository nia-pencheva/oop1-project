package project.star_wars_universe.app.cli;

import project.star_wars_universe.app.cli.commands.CommandsList;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private String rawInput = "";
    private List<String> processedInput = new ArrayList<>();
    private CommandsList commandsList = CommandsList.getInstance();

    public void start() {
        Scanner in = new Scanner(System.in);

        while(true) {
            rawInput = in.nextLine();
            processInput();
            readInput();
            System.out.println();
        }
    }

    public void processInput() {
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
                if(!currentSegment.isEmpty()) {
                    processedInput.add(currentSegment.toString());
                }

                currentSegment.setLength(0);
                continue;
            }

            currentSegment.append(currentCharacter);
        }

        if(currentSegment.length() != 0) {
            processedInput.add(currentSegment.toString());
        }
    }

    public void readInput() {
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

    public void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
