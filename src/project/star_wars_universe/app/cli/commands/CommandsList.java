package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.commands.file.Close;
import project.star_wars_universe.app.cli.commands.file.Open;
import project.star_wars_universe.app.cli.commands.file.Save;
import project.star_wars_universe.app.cli.commands.file.SaveAs;
import project.star_wars_universe.app.cli.commands.main.*;
import project.star_wars_universe.app.cli.commands.util.Exit;
import project.star_wars_universe.app.cli.commands.util.Help;
import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.contracts.observer.planets_repository.PlanetsRepositoryObserver;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.models.planets.Planet;

import java.util.HashMap;
import java.util.Map;

public class CommandsList implements PlanetsRepositoryObserver {
    private static CommandsList instance = new CommandsList();
    private Map<String, Command> commands = new HashMap<>();

    private CommandsList() {
        initialize();
    }

    public static CommandsList getInstance() {
        return instance;
    }

    public void addCommand(String command, Command commandHandler) {
        commands.put(command, commandHandler);
    }

    public void removeCommand(String command) {
        commands.remove(command);
    }

    public Command getCommand(String command) throws UnknownCommandException {
        Command commandHandler = commands.get(command);

        if(commandHandler == null) {
            throw new UnknownCommandException();
        }

        return commandHandler;
    }

    public void initialize() {
        addCommand("help", new Help());
        addCommand("open", new Open());
        addCommand("save", new Save());
        addCommand("saveas", new SaveAs());
        addCommand("close", new Close());
        addCommand("exit", new Exit());
        addCommand("add_planet", new AddPlanet());
        addCommand("create_jedi", new CreateJedi());
        addCommand("remove_jedi", new RemoveJedi());
        addCommand("promote_jedi", new PromoteJedi());
        addCommand("demote_jedi", new DemoteJedi());
        addCommand("get_strongest_jedi", new GetStrongestJedi());
        addCommand("get_youngest_jedi", new GetYoungestJedi());
        addCommand("get_most_used_saber_color", new GetMostUsedSaberColor());
        addCommand("print", new Print());
    }

    @Override
    public void onPlanetAdded(Planet planet) {
        System.out.println(planet);
        addCommand(planet.getName(), new PrintCombinedPlanetsInfo(planet));
    }

    @Override
    public void onPlanetRemoved(Planet planet) {
        removeCommand(planet.getName());
    }
}
