package project.star_wars_universe.parser.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.parser.Parser;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.jedi.attributes.Rank;
import project.star_wars_universe.entities.jedi.attributes.SaberColor;

import java.util.HashSet;
import java.util.Set;

public class JediXMLParser implements Parser<Set<Jedi>, NodeList> {

    @Override
    public Set<Jedi> parse(NodeList content) {
        Set<Jedi> jediSet = new HashSet<>();
        Node currentNode = null;

        for(int i = 0; i < content.getLength(); i++) {
            currentNode = content.item(i);
            if(currentNode.getNodeName().equals("jedi")) {
                if(currentNode instanceof Element) {
                    Element el = (Element) currentNode;
                    Jedi jedi = new Jedi(el.getElementsByTagName("name").item(0).getTextContent());
                    jedi.setRank(Rank.getValue(el.getElementsByTagName("rank").item(0).getTextContent()));
                    jedi.setAge((Integer.parseInt(el.getElementsByTagName("age").item(0).getTextContent())));
                    jedi.setSaberColor(SaberColor.getValue(el.getElementsByTagName("saber-color").item(0).getTextContent()));
                    jedi.setPower(Double.parseDouble(el.getElementsByTagName("power").item(0).getTextContent()));
                    jedi.setPlanet(el.getElementsByTagName("planet").item(0).getTextContent());

                    jediSet.add(jedi);
                }
            }
        }

        return jediSet;
    }
}
