package project.star_wars_universe.app.cli;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.app.cli.commands.file.Close;
import project.star_wars_universe.app.cli.commands.file.Open;
import project.star_wars_universe.app.cli.commands.file.Save;
import project.star_wars_universe.app.cli.commands.file.SaveAs;
import project.star_wars_universe.app.cli.commands.main.*;
import project.star_wars_universe.app.cli.commands.util.Exit;
import project.star_wars_universe.app.cli.commands.util.Help;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;

import java.util.*;

public class CLI {
    private static String rawInput = "";
    private static List<String> processedInput = new ArrayList<>();
    private static Map<String, Command> commands = new HashMap<>();

    public static void start() throws Exception {
        generateCommandsMap();

        while(true) {
            Scanner in = new Scanner(System.in);
            rawInput = in.nextLine();
            processInput();
            readInput();
        }
    }

    private static void generateCommandsMap() {
        commands.put("help", new Help());
        commands.put("open", new Open());
        commands.put("save", new Save());
        commands.put("saveas", new SaveAs());
        commands.put("close", new Close());
        commands.put("exit", new Exit());
        commands.put("add_planet", new AddPlanet());
        commands.put("create_jedi", new CreateJedi());
        commands.put("remove_jedi", new RemoveJedi());
        commands.put("promote_jedi", new PromoteJedi());
        commands.put("demote_jedi", new DemoteJedi());
        commands.put("get_strongest_jedi", new GetStrongestJedi());
        commands.put("get_youngest_jedi", new GetYoungestJedi());
        commands.put("print", new Print());
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
        Command command = commands.get(processedInput.get(0));

        try {
            if(command != null) {
                if(command.hasCorrectArgumentsCount(processedInput)) {
                    command.execute(processedInput);
                }
                else {
                    System.out.println("Wrong arguments count!");
                    printHelpMessage();
                }
            }
            else {
                unknownCommand();
            }
        }
        catch(CommandExecutionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void unknownCommand() {
        System.out.println("No such command!");
        printHelpMessage();
    }

    public static void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
