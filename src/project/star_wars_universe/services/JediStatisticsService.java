package project.star_wars_universe.services;

import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.util.comparators.jedi.JediByAge;
import project.star_wars_universe.util.comparators.jedi.JediByStrengthComparator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JediStatisticsService {
    public static Jedi getStrongestJediOnPlanet(Planet planet) throws PlanetDoesNotExistException {
        return Collections.max(planet.getJediPopulation(), new JediByStrengthComparator());
    }

    public static Jedi getYoungestJediOfRankOnPlanet(Rank rank, Planet planet) throws NoJediOfThisRankOnPlanetException {
        Set<Jedi> jediOfRank = new HashSet<>();

        for(Jedi jedi : planet.getJediPopulation()) {
            if(jedi.getRank().equals(rank)) {
                jediOfRank.add(jedi);
            }
        }

        if(jediOfRank.isEmpty()) {
            throw new NoJediOfThisRankOnPlanetException();
        }

        return Collections.min(jediOfRank, new JediByAge());
    }

    /* public static Jedi getMostUsedSaberColorOfRankOnPlanet(Rank rank, Planet planet) {

    } */
}
