package project.star_wars_universe.util.serializers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.data.AppData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XMLSerializer implements Serializer<AppData, String> {
    @Override
    public String serialize(AppData appData) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("data");
        Element jediList = (new JediXMLSerializer()).serialize(appData.getJedi());
        Element planetsList = (new PlanetsXMLSerializer()).serialize(appData.getPlanets());

        document.appendChild(root);
        root.appendChild(document.adoptNode(jediList));
        root.appendChild(document.adoptNode(planetsList));

        return convertDOMtoString(document);
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
}
