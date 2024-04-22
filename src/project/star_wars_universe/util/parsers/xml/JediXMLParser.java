package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.jedi.enums.Rank;
import project.star_wars_universe.entities.jedi.enums.SaberColor;
import project.star_wars_universe.exceptions.jedi.InvalidAgeException;
import project.star_wars_universe.exceptions.jedi.InvalidPowerException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi.InvalidSaberColorException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

import java.util.ArrayList;
import java.util.List;

public class JediXMLParser implements Parser<NodeList, List<Jedi>> {

    @Override
    public List<Jedi> parse(NodeList content) throws ParsingFailureException {
        try {
            List<Jedi> jediSet = new ArrayList<>();
            Node currentNode = null;
            String name, rank, saberColor;
            int age;
            double power;

            for(int i = 0; i < content.getLength(); i++) {
                currentNode = content.item(i);
                if(currentNode.getNodeName().equals("jedi")) {
                    if(currentNode instanceof Element) {
                        Element el = (Element) currentNode;
                        name = el.getElementsByTagName("name").item(0).getTextContent();
                        rank = el.getElementsByTagName("rank").item(0).getTextContent();
                        age = Integer.parseInt(el.getElementsByTagName("age").item(0).getTextContent());
                        saberColor = el.getElementsByTagName("saber-color").item(0).getTextContent();
                        power = Double.parseDouble(el.getElementsByTagName("power").item(0).getTextContent());
                        jediSet.add(new Jedi(name, rank, age, saberColor, power));
                    }
                }
            }

            return jediSet;
        }
        catch(InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException ex) {
            throw new ParsingFailureException(ex);
        }
    }
}
