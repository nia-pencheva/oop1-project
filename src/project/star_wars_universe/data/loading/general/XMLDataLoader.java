package project.star_wars_universe.data.loading.general;

import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.util.parsers.xml.XMLDataParser;

import java.util.Map;
import java.util.Set;

public class XMLDataLoader extends GeneralDataLoader {
    @Override
    public void load(Resource resource) throws Exception {
        Map<String, Set> data = (new XMLDataParser()).parse(resource.getData());
        getJediDataLoader().load(data.get("jediList"));
        getPlanetsDataLoader().load(data.get("planetsList"));
    }
}
