package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class Print extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public Print() {
        super(2);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        String name = input.get(1);

        try {
            Jedi jedi = JediRepository.getInstance().getJediByName(name);
            System.out.println(jedi.toString());
        }
        catch(JediDoesNotExistException ex) {
            try {
                Planet planet = PlanetsRepository.getInstance().getPlanetByName(name);
                System.out.println(planet.toString());
            }
            catch (PlanetDoesNotExistException exception) {
                System.out.println("There is no jedi or planet with that name!");
            }
        }
    }
}
