package project.star_wars_universe.cli;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.cli.commands.CommandsList;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class of the CLI. It is responsible for reading, validating and processing
 * the user input and invoking the correct {@link Command} handler for executing the desired behaviours.
 */
public class CLI {
    /**
     * Stores the raw input {@code String}, exactly as it was entered by the user.
     */
    private String rawInput = "";
    /**
     * Stores the processed input in the form of an {@code ArrayList}
     * where each space separated or quotation marks enclosed segment is
     * a different list element of type {@code String}.
     */
    private List<String> processedInput = new ArrayList<>();
    /**
     * Stores a reference to the {@link project.star_wars_universe.cli.commands.CommandsList}
     */
    private CommandsList commandsList = CommandsList.getInstance();

    /**
     * This is the entry point of the CLI. It takes care of initial preparations,
     * such as displaying a welcome message, initializing a {@link Scanner} class and
     * starting a loop for reading, processing and validating user input and
     * passing it to the logic responsible for choosing the correct command
     * based on this input.
     */
    public void start() {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while(true) {
            rawInput = in.nextLine();
            processInput();

            if(!processedInput.isEmpty()) {
                readInput();
                System.out.println();
            }
        }
    }

    /**
     * Processes the raw user input. Firstly, the {@link CLI#processedInput} is
     * cleared. Secondly, the {@link CLI#rawInput} is read character by character.
     * If a quotation mark is encountered, the {@code inQuotes} variable, responsible
     * for noting whether the subsequent input should be separated by spaces or should
     * be treated as a whole segment, gets toggled. If a space is encountered and the current character
     * is NOT a part of a quotes enclosed segment, the current segment is added to the {@link CLI#processedInput},
     * the current segment gets cleared and the loop continues on to the next character. If not, the current character
     * gets added to the current segment. Lastly, after the last character of the {@link CLI#rawInput} is read,
     * it is checked whether the current segment is empty and if it is not it gets added to the {@link CLI#processedInput}.
     */
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

    /**
     * Extracts the correct command from the {@link project.star_wars_universe.cli.commands.CommandsList}
     * based on the first segment of the {@link CLI#processedInput} and execute it, passing the {@link CLI#processedInput} it it's {@link Command#execute(List)} method.
     * If it fails to find such a command of another type of {@link project.star_wars_universe.exceptions.cli.CommandExecutionException} occurs,
     * an appropriate error message and a help message are displayed in the console.
     */ 
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

    /**
     * Prints the welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("A long time ago in a galaxy far, far away...");
        System.out.println("Welcome to Star Wars Universe! Type \"help\" to get started.");
    }

    /**
     * Prints the help message
     */
    public void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
