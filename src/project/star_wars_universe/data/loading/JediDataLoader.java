package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;

import java.util.Set;

public class JediDataLoader implements DataLoader<Set<Jedi>> {
    private JediRepository repository = JediRepository.getInstance();

    @Override
    public void load(Set<Jedi> jedi) {
        for(Jedi item : jedi) {
            repository.add(item);
        }
    }

    @Override
    public void unload() {
        repository.removeAll();
    }
}
