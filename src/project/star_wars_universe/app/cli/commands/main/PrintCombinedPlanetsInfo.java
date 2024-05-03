package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.controllers.JediController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintCombinedPlanetsInfo implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
    private Planet planet1;

    public PrintCombinedPlanetsInfo(Planet planet1) {
        this.planet1 = planet1;
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException, UnknownCommandException {
        if(input.size() != 3 || !input.get(1).equals("+")) {
            throw new UnknownCommandException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planet2Name = input.get(2);
            Planet planet2 = planetsRepository.getPlanetByName(planet2Name);
            Set<Jedi> combinedJedi = new HashSet<>();
            combinedJedi.addAll(planet1.getJediPopulation());
            combinedJedi.addAll(planet2.getJediPopulation());
            JediController.sortJediByName(combinedJedi);

            System.out.println("The jedi populating " + planet1.getName() + " and " + planet2.getName());
            for(Jedi jedi : combinedJedi) {
                System.out.println(jedi);
            }

        }
        catch(PlanetDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
