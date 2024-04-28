package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;
import project.star_wars_universe.repository.JediRepository;

import java.util.List;

public class JediDataLoader implements DataLoader<List<Jedi>> {
    private JediRepository repository = JediRepository.getInstance();

    @Override
    public void load(List<Jedi> jedi) throws DataLoadingException {
        try {
            for(Jedi item : jedi) {
                repository.add(item);
            }
        }
        catch(JediAlreadyExistsException ex) {
            throw new DataLoadingException(ex);
        }
    }

    @Override
    public void unload() {
        repository.removeAll();
    }
}
