package project.star_wars_universe.cli.commands;

import project.star_wars_universe.cli.commands.main.GetMostUsedSaberColor;
import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.cli.commands.file.*;
import project.star_wars_universe.cli.commands.main.*;
import project.star_wars_universe.cli.commands.util.*;
import project.star_wars_universe.contracts.observer.planets_repository.PlanetsRepositoryObserver;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.models.planets.Planet;

import java.util.HashMap;
import java.util.Map;

/**
 * A singleton class which is responsible for adding, removing, initializing and
 * providing of all the commands of the application. It is also an observer of the
 * {@link project.star_wars_universe.repository.PlanetsRepository} class.
 */
public class CommandsList implements PlanetsRepositoryObserver {
    /**
     * The instance of the singleton is eagerly loaded.
     */
    private static CommandsList instance = new CommandsList();
    /**
     * A <code>Map</code> containing the <code>String</code>s for invoking the
     * commands as keys and the implementations of the {@link project.star_wars_universe.contracts.cli.Command} interface
     * for each corresponding command as values.
     */
    private Map<String, Command> commands = new HashMap<>();

    /**
     * At creation, the initial commands of the application
     * get added to the {@link CommandsList#commands} map.
     */
    private CommandsList() {
        initialize();
    }

    /**
     * @return the class singleton instance.
     */
    public static CommandsList getInstance() {
        return instance;
    }

    /**
     * Adds a new command to the {@link CommandsList#commands} map.
     * @param command the <code>String</code> for invoking the command in the CLI.
     * @param commandHandler the {@link project.star_wars_universe.contracts.cli.Command} implementation responsible for executing the command.
     */
    public void addCommand(String command, Command commandHandler) {
        commands.put(command, commandHandler);
    }

    /**
     * Removes a command from the {@link CommandsList#commands} map.
     * @param command the <code>String</code> for invoking the command in the CLI.
     */
    public void removeCommand(String command) {
        commands.remove(command);
    }

    /**
     * Gets the the {@link project.star_wars_universe.contracts.cli.Command} implementation responsible for executing the command.
     * @param command the <code>String</code> for invoking the command in the CLI.
     * @return the {@link project.star_wars_universe.contracts.cli.Command} implementation responsible for executing the command.
     * @throws UnknownCommandException if the command <code>String</code> does not exist as a key in the {@link CommandsList#commands} map.
     */
    public Command getCommand(String command) throws UnknownCommandException {
        Command commandHandler = commands.get(command);

        if(commandHandler == null) {
            throw new UnknownCommandException();
        }

        return commandHandler;
    }

    /**
     * The initial commands of the application get added to the {@link CommandsList#commands} map.
     */
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

    /**
     * When a {@link Planet} is added to the {@link project.star_wars_universe.repository.PlanetsRepository},
     * this method adds a new instance of the {@link PrintCombinedPlanetsInfo} command to the {@link CommandsList#commands} map
     * for the new planet. This is necessary because when the user enters a command in the console, only the first segment
     * of the input is used to find the corresponding {@link Command} handler in the {@link CommandsList#commands} map.
     * (e.g. when the user enters "Naboo + Dagobah", "Naboo" is considered as the <code>String</code> for invoking a command
     * in the CLI).
     * @param planet the new {@link Planet} added to the {@link project.star_wars_universe.repository.PlanetsRepository}.
     */
    @Override
    public void onPlanetAdded(Planet planet) {
        addCommand(planet.getName(), new PrintCombinedPlanetsInfo(planet));
    }

    /**
     * When a {@link Planet} is removed from the {@link project.star_wars_universe.repository.PlanetsRepository},
     * this method removes the {@link PrintCombinedPlanetsInfo} command from the {@link CommandsList#commands} map corresponding to
     * the removed planet.
     * @param planet the {@link Planet} removed to the {@link project.star_wars_universe.repository.PlanetsRepository}.
     */
    @Override
    public void onPlanetRemoved(Planet planet) {
        removeCommand(planet.getName());
    }
}
