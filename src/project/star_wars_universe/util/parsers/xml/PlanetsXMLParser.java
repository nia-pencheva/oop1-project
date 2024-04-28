package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanetsXMLParser implements Parser<NodeList, List<Planet>> {
    private Map<String, Jedi> jedi = new HashMap<>();

    public PlanetsXMLParser(List<Jedi> jedi) {
        for(Jedi item : jedi) {
            this.jedi.put(item.getName(), item);
        }
    }

    @Override
    public List<Planet> parse(NodeList content) throws ParsingFailureException {
        try {
            List<Planet> planetsSet = new ArrayList<>();
            Node currentNode = null;
            Element el = null;
            String jediName;

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
                                jediName = currentNode.getTextContent();
                                if(jedi.containsKey(jediName)) {
                                    planet.addJedi(jedi.get(jediName));
                                    jedi.remove(currentNode.getTextContent());
                                }
                                else {
                                    throw new JediDoesNotExistException();
                                }
                            }
                        }

                        planetsSet.add(planet);
                    }
                }
            }

            return planetsSet;
        }
        catch(JediExistsOnThisPlanetException | JediDoesNotExistException ex) {
            throw new ParsingFailureException(ex);
        }
    }
}
