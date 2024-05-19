package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;
import project.star_wars_universe.data.repository.JediRepository;

import java.util.List;

/**
 * Loads and unloads {@link Jedi} entities into the application. This class
 * serves as an intermediate layer between the more general
 * {@link project.star_wars_universe.data.AppDataManager} class and the
 * {@link JediRepository} in order to decrease coupling and encapsulate
 * the logic for loading and unloading the {@link Jedi} entities specifically.
 */
public class JediDataLoader implements DataLoader<List<Jedi>> {
    /**
     * A reference to the {@link JediRepository}.
     */
    private JediRepository repository = JediRepository.getInstance();

    /**
     * Loads {@link Jedi} into the application. It iterates through the
     * provided {@code List} of {@link Jedi} and adds them to the
     * {@link JediRepository} using its {@link JediRepository#add(Jedi)} method.
     * If a {@link Jedi} already exists in the repository,
     * a {@link JediAlreadyExistsException} exception is thrown from
     * the {@link JediRepository#add(Jedi)} method. This exception is then
     * passed to a {@link DataLoadingException}, from which the specific exception
     * can later be extracted using the {@link DataLoadingException#getException()} method.
     * @param jedi the {@code List} of {@link Jedi} that should be loaded in the {@link JediRepository}.
     * @throws DataLoadingException if an exception occurs during the loading of the jedi.
     */
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

    /**
     * Unloads the {@link Jedi} entities from the application. It invokes the {@link JediRepository#removeAll()}
     * method which empties the repository.
     */
    @Override
    public void unload() {
        repository.removeAll();
    }
}
