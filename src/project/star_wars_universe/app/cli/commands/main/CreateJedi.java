package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.entities.jedi.enums.Rank;
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
    public void execute() throws Exception {
        String planetName = input.get(1);
        String jediName = input.get(2);
        String jediRank = input.get(3);
        String jediAge = input.get(4);
        String saberColor = input.get(5);
        String jediPower = input.get(6);

        Jedi jedi = new Jedi(jediName);
        jedi.setRank(jediRank);
        jedi.setAge(Integer.valueOf(jediAge));
        jedi.setSaberColor(saberColor);
        jedi.setPower(Double.valueOf(jediPower));

        JediRepository.getInstance().add(jedi);
        PlanetsRepository.getInstance().getPlanetByName(planetName).addJedi(jedi);
    }
}
