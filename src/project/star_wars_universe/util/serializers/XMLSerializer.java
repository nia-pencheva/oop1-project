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

/**
 * Serializes {@link AppData} into an XML {@code String}, which can then be written to a medium.
 */
public class XMLSerializer implements Serializer<String> {
    /**
     * Serializes an {@link AppData} object into an XML {@code String}. First, a {@link Document} is
     * created, as well as a {@code <root>} node. Using the {@link XMLSerializer#serializeJedi(List)} method
     * the jedi {@code List} obtained using the {@link AppData#getJedi()} method is converted to a DOM Element. Using the {@link XMLSerializer#serializePlanets(List)}
     * method the planets {@code List} obtained using the {@link AppData#getPlanets()} method is converted to a DOM Element.
     * Then the two elements are appended to the root element, which is then is appended to the document.
     * The document is converted to a {@code String} using the {@link XMLSerializer#convertDOMtoString(Document)}
     * and it is returned. If an exception occurs during the process ({@link ParserConfigurationException}, {@link TransformerException}),
     * the exception is caught and passed to a {@link SerializationFailureException}, from which the specific
     * exception can later be extracted using the {@link SerializationFailureException#getException()} method.
     * @param appData the {@link AppData} that should be serialized.
     * @return the resulting XML {@code String}
     * @throws SerializationFailureException if an exception occurs during the serialization process.
     */
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

    /**
     * Converts a {@link Document} object to an XML {@code String}.
     * @param document the {@link Document} object that should be converted.
     * @return the XML {@code String} derived from the provided {@link Document} object.
     * @throws TransformerException if an exception occurred during the transformation process.
     */
    private String convertDOMtoString(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    /**
     * Converts a {@code List} of {@link Jedi} into an {@link Element} object representing
     * the {@code <jedi-list>} element of the desired XML output. First, a {@link Document} object is
     * created, as well as a {@code <jedi-list>} {@link Element}. For each jedi in the {@code List}
     * a new {@code <jedi>} element is created and the jedi's properties are converted to document elements:
     * <ul>
     *     <li>jedi's name -> {@code <name>}</li>
     *     <li>jedi's rank -> {@code <rank>}</li>
     *     <li>jedi's age -> {@code <age>}</li>
     *     <li>jedi's saber color -> {@code <saber-color>}</li>
     *     <li>jedi's power -> {@code <power>}</li>
     * </ul>
     * The converted properties are appended to the {@code <jedi>} element, which is appended
     * to the {@code <jedi-list>} element. Finally, the {@link Element} representing the jedi
     * list is returned.
     * @param data the {@code List} of {@link Jedi} to be converted.
     * @return the {@code <jedi-list>} {@link Element}.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     */
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

    /**
     * Converts a {@code List} of {@link Planet}s into an {@link Element} object representing
     * the {@code <planets-list>} element of the desired XML output. First, a {@link Document} object is
     * created, as well as a {@code <planets-list>} {@link Element}. For each planet in the {@code List}
     * a new {@code <planet>} element is created and the planet's properties are converted to {@link Element}s:
     * <ul>
     *     <li>planet's name -> {@code <name>}</li>
     *     <li>
     *         planet's population -> {@code <jedi-population}. For each item in the {@link Planet#jediPopulation}
     *         a new {@code <jedi>} {@link Element} is created, to which the name of the jedi is appended. Then,
     *         the {@code <jedi>} element is appended to the {@code <jedi-population>} element. When this is done for all
     *         jedi, the {@code <jedi-population} element is appended to the {@code <planet>} element.
     *     </li>
     * </ul>
     * When the creation of each {@code <planet>} element is finished, it is appended to the {@code <planets-list> element}.
     * Finally, the {@link Element} representing the planets list is returned.
     * @param data the {@code List} of {@link Planet}s to be converted.
     * @return the {@code <planets-list>} {@link Element}.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     */
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
