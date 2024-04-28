package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class AddPlanet extends Command {
    private List<String> input;

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
    public void execute() {
        try {
            PlanetsRepository.getInstance().add(new Planet(input.get(1)));
            System.out.println("Planet " + input.get(1) + " was successfully added!");
        }
        catch(PlanetAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
