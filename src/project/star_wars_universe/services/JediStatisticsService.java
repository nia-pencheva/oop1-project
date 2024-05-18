package project.star_wars_universe.services;

import project.star_wars_universe.exceptions.jedi_statistics.NoGrandMastersOnPlanetException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.util.comparators.JediByAge;
import project.star_wars_universe.util.comparators.JediByName;
import project.star_wars_universe.util.comparators.JediByStrength;

import java.util.*;

/**
 * Contains methods for processing and gathering information about
 * the characteristics of different sets of {@link Jedi}. It's methods are all
 * static because they only look over some sets of data for which the class itself
 * does not need to be instantiated.
 */
public class JediStatisticsService {
    /**
     * Combines the {@link Planet#jediPopulation}s of two planets and
     * sorts them lexicographically (ascending) using the {@link JediByName} comparator.
     * @param planet1 the first planet.
     * @param planet2 the second planet.
     * @return a set of the jedi populating the two planets, ordered lexicographically (ascending).
     */
    public static Set<Jedi> getCombinedJedi(Planet planet1, Planet planet2) {
        Set<Jedi> combinedJedi = new TreeSet<>(new JediByName());
        combinedJedi.addAll(planet1.getJediPopulation());
        combinedJedi.addAll(planet2.getJediPopulation());
        return combinedJedi;
    }

    /**
     * Gets the strongest {@link Jedi} on a specified {@link Planet} using the
     * {@link Collections#max(Collection)} method and a {@link JediByStrength} comparator.
     * @param planet the planet whose strongest jedi needs to be found.
     * @return the strongest jedi on the specified planet.
     * @throws PlanetDoesNotExistException is the specified planet does not exist.
     */
    public static Jedi getStrongestJediOnPlanet(Planet planet) throws PlanetDoesNotExistException {
        return Collections.max(planet.getJediPopulation(), new JediByStrength());
    }

    /**
     * Gets the youngest jedi of a specified rank on a given planet. The {@link Planet#jediPopulation}
     * is first filtered by the specified rank. If no jedi remain after the filtering, a {@link NoJediOfThisRankOnPlanetException}
     * exception is thrown. Otherwise, the method gets the youngest jedi of the filtered ones using
     * the {@link Collections#min(Collection)} method and a {@link JediByAge} comparator.
     * @param rank the rank by which the search for the youngest jedi should be filtered.
     * @param planet the planet whose youngest jedi of a specified rank needs to be found.
     * @return the youngest jedi of a specified rank on the given planet.
     * @throws NoJediOfThisRankOnPlanetException
     */
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

    /**
     * Gets the most used saber color by at least one Grand Master on a specified planet. First, the {@link Planet#jediPopulation}
     * is iterated over in order to gather all the saber colors used by Grand Masters in a <code>saberColorsUsedByGrandMasters</code>
     * map where the key is the {@link SaberColor} and the value is the number of jedi using sabers of that color, which is initialy 0.
     * If the map is empty after the loop, a {@link NoGrandMastersOnPlanetException} is thrown. Otherwise, the specified planet's
     * population is once again iterated over. At each repetition it is checked whether the current jedi's saber color exists as a key
     * in the <code>saberColorsUsedByGrandMasters</code> map and if yes the number of jedi using sabers of that color (the map corresponding value)
     * is incremented. Lastly, using the {@link Collections#max(Collection)} method and passing to it the <code>saberColorsUsedByGrandMasters</code>'s
     * entry set retrieved using the {@link Map#entrySet()} method and the comparator retrieved using the {@link Map.Entry#comparingByValue()} method,
     * we get the key of the returned entry set instance, which is the wanted {@link SaberColor}.
     * @param planet the planet whose jedi population should be searched over.
     * @return the most used saber color by at least one Grand Master on the specified planet.
     * @throws NoGrandMastersOnPlanetException if no Grand Masters exist on the specified planet.
     */
    public static SaberColor getMostUsedSaberColorByGrandMasterOnPlanet(Planet planet) throws NoGrandMastersOnPlanetException {
        Map<SaberColor, Integer> saberColorsUsedByGrandMasters = new HashMap<>();
        Set<Jedi> jediPopulation = planet.getJediPopulation();
        SaberColor saberColor;

        for(Jedi jedi : jediPopulation) {
            if(jedi.getRank().equals(Rank.GRAND_MASTER)) {
                saberColorsUsedByGrandMasters.put(jedi.getSaberColor(), 0);
            }
        }

        if(saberColorsUsedByGrandMasters.isEmpty()) {
            throw new NoGrandMastersOnPlanetException();
        }

        for(Jedi jedi : jediPopulation) {
            saberColor = jedi.getSaberColor();

            if(saberColorsUsedByGrandMasters.containsKey(saberColor)) {
                saberColorsUsedByGrandMasters.put(saberColor, saberColorsUsedByGrandMasters.get(saberColor) + 1);
            }
        }

        return Collections.max(saberColorsUsedByGrandMasters.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Gets the most used saber color used by the jedi of a specified rank on a given planet.
     * First, the {@link Planet#jediPopulation} is iterated over in order to accumulate the numbers
     * of jedi using different saber colors inside a <code>saberColorsUsedByRank</code> map
     * where the key is the {@link SaberColor} and the value is the number of jedi using sabers of that color.
     * If the map is empty after the loop, a {@link NoJediOfThisRankOnPlanetException} exception is thrown.
     * Otherwise, using the {@link Collections#max(Collection)} method and passing to it the <code>saberColorsUsedByRank</code>'s
     * entry set retrieved using the {@link Map#entrySet()} method and the comparator retrieved using the {@link Map.Entry#comparingByValue()} method,
     * we get the key of the returned entry set instance, which is the wanted {@link SaberColor}.
     * @param rank the rank by which the search for the most used saber color should be filtered.
     * @param planet the planet whose jedi population should be searched over.
     * @return the most used saber color by the jedi of the specified rank on the given planet.
     * @throws NoJediOfThisRankOnPlanetException
     */
    public static SaberColor getMostUsedSaberColorOfRankOnPlanet(Rank rank, Planet planet) throws NoJediOfThisRankOnPlanetException {
        Map<SaberColor, Integer> saberColorsUsedByRank = new HashMap<>();
        SaberColor saberColor;

        for(Jedi jedi : planet.getJediPopulation()) {
            if(jedi.getRank().equals(rank)) {
                saberColor = jedi.getSaberColor();

                if(!saberColorsUsedByRank.containsKey(saberColor)) {
                    saberColorsUsedByRank.put(jedi.getSaberColor(), 0);
                }
                else {
                    saberColorsUsedByRank.put(jedi.getSaberColor(), saberColorsUsedByRank.get(saberColor) + 1);
                }
            }
        }

        if(saberColorsUsedByRank.isEmpty()) {
            throw new NoJediOfThisRankOnPlanetException();
        }

        return Collections.max(saberColorsUsedByRank.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
