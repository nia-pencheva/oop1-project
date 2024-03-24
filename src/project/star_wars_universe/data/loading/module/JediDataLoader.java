package project.star_wars_universe.data.loading.module;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;

import java.util.Set;

public class JediDataLoader implements DataLoader<Set<Jedi>> {

    @Override
    public void load(Set<Jedi> jedi) {
        JediRepository repository = JediRepository.getInstance();

        for(Jedi item : jedi) {
            repository.add(item);
        }
    }

    @Override
    public void unload() {

    }
}
