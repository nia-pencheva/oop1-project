package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;

import java.util.HashSet;
import java.util.Set;

public class PlanetsXMLParser implements Parser<NodeList, Set<Planet>> {
    @Override
    public Set<Planet> parse(NodeList content) throws JediExistsOnThisPlanetException {
        Set<Planet> planetsSet = new HashSet<>();
        Node currentNode = null;
        Element el = null;

        for(int i = 0; i < content.getLength(); i++) {
            currentNode = content.item(i);

            if(currentNode.getNodeName().equals("planet")) {
                if(currentNode instanceof Element) {
                    el = (Element) currentNode;
                    Planet planet = new Planet(el.getElementsByTagName("name").item(0).getTextContent());
                    NodeList jediPopulation = el.getElementsByTagName("jedi-population").item(0).getChildNodes();

                    for(int j = 0; j < jediPopulation.getLength(); j++) {
                        currentNode = jediPopulation.item(j);

                        if(currentNode.getNodeName().equals("jedi")) {
                            planet.addJedi(currentNode.getTextContent());
                        }
                    }

                    planetsSet.add(planet);
                }
            }
        }

        return planetsSet;
    }
}
