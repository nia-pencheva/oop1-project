package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class CreateJedi extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public CreateJedi() {
        super(7);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            String name = input.get(2);
            Rank rank = Rank.getValue(input.get(3));
            int age = Integer.parseInt(input.get(4));
            SaberColor saberColor = SaberColor.getValue(input.get(5));
            double power = Double.parseDouble(input.get(6));
            Jedi newJedi = new Jedi(name, rank, age, saberColor, power);

            PlanetsRepository.getInstance().getPlanetByName(planetName).addJedi(newJedi);
            JediRepository.getInstance().add(newJedi);
            System.out.println("Jedi " + name + " was successfully created!");
        }
        catch(JediAlreadyExistsException | InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException | PlanetDoesNotExistException | JediExistsOnThisPlanetException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
