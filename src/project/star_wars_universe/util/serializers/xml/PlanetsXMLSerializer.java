package project.star_wars_universe.util.serializers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.exceptions.util.SerializationFailureException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.Set;

public class PlanetsXMLSerializer implements Serializer<List<Planet>, Element> {

    @Override
    public Element serialize(List<Planet> data) throws SerializationFailureException {
        try {
            Element planet, name, jediPopulation, jedi;

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();

            Element planetsList = document.createElement("planets-list");

            for(Planet item : data) {
                planet = document.createElement("planet");

                name = document.createElement("name");
                name.appendChild(document.createTextNode(item.getName()));
                planet.appendChild(name);

                jediPopulation = document.createElement("jedi-population");

                for(String jediName : item.getJediPopulation()) {
                    jedi = document.createElement("jedi");
                    jedi.appendChild(document.createTextNode(jediName));
                    jediPopulation.appendChild(jedi);
                }
                planet.appendChild(jediPopulation);

                planetsList.appendChild(planet);
            }

            return planetsList;
        }
        catch(ParserConfigurationException ex) {
            throw new SerializationFailureException(ex);
        }
    }
}
