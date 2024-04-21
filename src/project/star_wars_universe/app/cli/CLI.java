package project.star_wars_universe.app.cli;

import project.star_wars_universe.app.cli.commands.file.Close;
import project.star_wars_universe.app.cli.commands.file.Open;
import project.star_wars_universe.app.cli.commands.file.Save;
import project.star_wars_universe.app.cli.commands.file.SaveAs;
import project.star_wars_universe.app.cli.commands.main.AddPlanet;
import project.star_wars_universe.app.cli.commands.main.CreateJedi;
import project.star_wars_universe.app.cli.commands.main.RemoveJedi;
import project.star_wars_universe.app.cli.commands.util.Exit;
import project.star_wars_universe.app.cli.commands.util.Help;
import project.star_wars_universe.exceptions.cli.FileAlreadyOpenedException;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

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

    public static void readInput() throws Exception {
        try {
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
                case "add_planet":
                    (new AddPlanet(processedInput)).execute();
                    break;
                case "create_jedi":
                    (new CreateJedi(processedInput)).execute();
                    break;
                case "remove_jedi":
                    (new RemoveJedi(processedInput)).execute();
                    break;
                default:
                    wrongCommand();
            }
        }
        catch(WrongArgumentsCountException ex) {
            System.out.println(ex.getMessage());
            printHelpMessage();
        }
        catch(FileAlreadyOpenedException | NoFileOpenedException ex) {
            System.out.println(ex.getMessage());
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
