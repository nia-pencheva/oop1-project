package project.star_wars_universe.util.serializers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.entities.jedi.Jedi;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Set;

public class JediXMLSerializer implements Serializer<Set<Jedi>, Element> {

    @Override
    public Element serialize(Set<Jedi> data) throws Exception {
        Element jedi, name, rank, age, saberColor, power, planet;

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

            planet = document.createElement("planet");
            planet.appendChild(document.createTextNode(item.getPlanet()));
            jedi.appendChild(planet);

            jediList.appendChild(jedi);
        }

        return jediList;
    }
}
