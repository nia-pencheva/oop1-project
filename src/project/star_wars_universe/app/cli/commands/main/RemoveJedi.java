package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class RemoveJedi extends Command {
    private List<String> input;

    public RemoveJedi(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(3);

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
            String name = input.get(1);
            String planetName = input.get(2);

            JediRepository repository = JediRepository.getInstance();
            Jedi jedi = repository.getJediByName(name);

            PlanetsRepository.getInstance().getPlanetByName(planetName).removeJedi(jedi);
            repository.remove(jedi);
            System.out.println("Jedi " + name + " was successfully removed!");
        }
        catch(PlanetDoesNotExistException | JediDoesNotExistOnThisPlanetException | JediDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
