package project.star_wars_universe.util.serializers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;

public class XMLSerializer implements Serializer<String> {
    @Override
    public String serialize(AppData appData) throws SerializationFailureException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("data");
            Element jediList = serializeJedi(appData.getJedi());
            Element planetsList = serializePlanets(appData.getPlanets());

            document.appendChild(root);
            root.appendChild(document.adoptNode(jediList));
            root.appendChild(document.adoptNode(planetsList));

            return convertDOMtoString(document);
        }
        catch(ParserConfigurationException | TransformerException ex) {
            throw new SerializationFailureException(ex);
        }
    }

    private String convertDOMtoString(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    private Element serializeJedi(List<Jedi> data) throws ParserConfigurationException {
        Element jedi, name, rank, age, saberColor, power;

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();

        Element jediList = document.createElement("jedi-list");

        for(Jedi item : data) {
            jedi = document.createElement("jedi");

            name = document.createElement("name");
            name.appendChild(document.createTextNode(item.getName()));
            jedi.appendChild(name);

            rank = document.createElement("rank");
            rank.appendChild(document.createTextNode(item.getRank().getRank()));
            jedi.appendChild(rank);

            age = document.createElement("age");
            age.appendChild(document.createTextNode(String.valueOf(item.getAge())));
            jedi.appendChild(age);

            saberColor = document.createElement("saber-color");
            saberColor.appendChild(document.createTextNode(item.getSaberColor().getColor()));
            jedi.appendChild(saberColor);

            power = document.createElement("power");
            power.appendChild(document.createTextNode(String.valueOf(item.getPower())));
            jedi.appendChild(power);

            jediList.appendChild(jedi);
        }

        return jediList;
    }

    private Element serializePlanets(List<Planet> data) throws ParserConfigurationException {
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

            for(Jedi jediItem : item.getJediPopulation()) {
                jedi = document.createElement("jedi");
                jedi.appendChild(document.createTextNode(jediItem.getName()));
                jediPopulation.appendChild(jedi);
            }
            planet.appendChild(jediPopulation);

            planetsList.appendChild(planet);
        }

        return planetsList;
    }
}
