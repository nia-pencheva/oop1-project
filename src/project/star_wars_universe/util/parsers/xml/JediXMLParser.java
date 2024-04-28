package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;

import java.util.ArrayList;
import java.util.List;

public class JediXMLParser implements Parser<NodeList, List<Jedi>> {

    @Override
    public List<Jedi> parse(NodeList content) throws ParsingFailureException {
        try {
            List<Jedi> jediList = new ArrayList<>();
            Node currentNode = null;
            Jedi newJedi;
            String name;
            Rank rank;
            int age;
            SaberColor saberColor;
            double power;

            for(int i = 0; i < content.getLength(); i++) {
                currentNode = content.item(i);
                if(currentNode.getNodeName().equals("jedi")) {
                    if(currentNode instanceof Element) {
                        Element el = (Element) currentNode;
                        name = el.getElementsByTagName("name").item(0).getTextContent();
                        rank = Rank.getValue(el.getElementsByTagName("rank").item(0).getTextContent());
                        age = Integer.parseInt(el.getElementsByTagName("age").item(0).getTextContent());
                        saberColor = SaberColor.getValue(el.getElementsByTagName("saber-color").item(0).getTextContent());
                        power = Double.parseDouble(el.getElementsByTagName("power").item(0).getTextContent());
                        newJedi = new Jedi(name, rank, age, saberColor, power);

                        if(jediList.contains(newJedi)) {
                            throw new JediAlreadyExistsException();
                        }

                        jediList.add(newJedi);
                    }
                }
            }

            return jediList;
        }
        catch(InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException | JediAlreadyExistsException ex) {
            throw new ParsingFailureException(ex);
        }
    }
}
