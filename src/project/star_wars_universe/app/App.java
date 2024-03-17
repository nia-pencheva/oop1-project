package project.star_wars_universe.app;

import project.star_wars_universe.data.managers.XMLDataManager;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.resource.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private String rawInput = "";
    private List<String> processedInput = new ArrayList<>();
    private XMLDataManager dataManager = XMLDataManager.getInstance();

    public void start() throws Exception {
        while(true) {
            Scanner in = new Scanner(System.in);
            rawInput = in.nextLine();
            processInput();
            readInput();
        }
    }

    public void processInput() {
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

    public void readInput() throws Exception {
        switch(processedInput.get(0)) {
            case "open":
                if(enoughArguments(2)) {
                    dataManager.load(new File(processedInput.get(1).replaceAll("\"", "")));
                    JediRepository jediRepository = JediRepository.getInstance();
                    jediRepository.printJedi();
                    PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
                    planetsRepository.printPlanets();
                }
                break;
            case "exit":
                System.out.println("Exiting the program...");
                System.exit(0);
            default:
                wrongCommand();
        }
    }

    public boolean enoughArguments(int count) {
        if(processedInput.size() < count) {
            System.out.println("Not enough arguments!");
            printHelpMessage();
            return false;
        }
        return true;
    }

    public void wrongCommand() {
        System.out.println("No such command!");
        printHelpMessage();
    }

    public void printHelpMessage() {
        System.out.println("Type \"help\" to get a list of available commands");
    }
}
