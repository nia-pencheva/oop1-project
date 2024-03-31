package project.star_wars_universe.util.serializers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.entities.planets.Planet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Set;

public class PlanetsXMLSerializer implements Serializer<Set<Planet>, Element> {

    @Override
    public Element serialize(Set<Planet> data) throws Exception {
        Element planet, name;

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();

        Element planetsList = document.createElement("planets-list");

        for(Planet item : data) {
            planet = document.createElement("planet");

            name = document.createElement("name");
            name.appendChild(document.createTextNode(item.getName()));
            planet.appendChild(name);

            planetsList.appendChild(planet);
        }

        return planetsList;
    }
}