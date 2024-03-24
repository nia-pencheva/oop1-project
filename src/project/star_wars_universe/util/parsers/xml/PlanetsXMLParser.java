package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.entities.planets.Planet;

import java.util.HashSet;
import java.util.Set;

public class PlanetsXMLParser implements Parser<Set<Planet>, NodeList> {
    @Override
    public Set<Planet> parse(NodeList content) {
        Set<Planet> planetsSet = new HashSet<>();
        Node currentNode = null;

        for(int i = 0; i < content.getLength(); i++) {
            currentNode = content.item(i);
            if(currentNode.getNodeName().equals("planet")) {
                if(currentNode instanceof Element) {
                    Element el = (Element) currentNode;
                    Planet planet = new Planet(el.getElementsByTagName("name").item(0).getTextContent());

                    planetsSet.add(planet);
                }
            }
        }

        return planetsSet;
    }
}
