package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.app.cli.exceptions.NoFileOpenedException;
import project.star_wars_universe.app.cli.exceptions.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class AddPlanet extends Command {
    List<String> input;

    public AddPlanet(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(2);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        if(!hasCorrectArgumentsCount(input)) {
            throw new WrongArgumentsCountException();
        }

        this.input = input;
    }
    @Override
    public void execute() throws Exception {
        PlanetsRepository.getInstance().add(new Planet(input.get(1)));
        System.out.println("Planet " + input.get(1) + " was successfully added!");
    }
}