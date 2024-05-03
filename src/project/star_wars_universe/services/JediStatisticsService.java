package project.star_wars_universe.services;

import project.star_wars_universe.exceptions.jedi_statistics.NoGrandMastersOnPlanetException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.util.comparators.jedi.JediByAge;
import project.star_wars_universe.util.comparators.jedi.JediByName;
import project.star_wars_universe.util.comparators.jedi.JediByStrengthComparator;

import java.util.*;

public class JediStatisticsService {
    public static List<Jedi> sortJediByName(Set<Jedi> jedi) {
        List<Jedi> jediList = new ArrayList<Jedi>(jedi);
        Collections.sort(jediList, new JediByName());

        return jediList;
    }

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
