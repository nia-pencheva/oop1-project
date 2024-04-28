package project.star_wars_universe.services;

import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.util.comparators.jedi.JediByStrengthComparator;

import java.util.Collections;

public class JediStatisticsService {
    public static Jedi getStrongestJediOnPlanet(Planet planet) throws PlanetDoesNotExistException {
        return Collections.max(planet.getJediPopulation(), new JediByStrengthComparator());
    }
}
