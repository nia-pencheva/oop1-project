package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class CreateJedi extends Command {
    List<String> input;

    public CreateJedi(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(7);

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
            String planetName = input.get(1);
            String name = input.get(2);
            String rank = input.get(3);
            int age = Integer.valueOf(input.get(4));
            String saberColor = input.get(5);
            double power = Double.valueOf(input.get(6));

            PlanetsRepository.getInstance().getPlanetByName(planetName).addJedi(name);
            JediRepository.getInstance().add(new Jedi(name, rank, age, saberColor, power));
            System.out.println("Jedi " + name + " was successfully created!");
        }
        catch(JediAlreadyExistsException | InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException | PlanetDoesNotExistException | JediExistsOnThisPlanetException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
