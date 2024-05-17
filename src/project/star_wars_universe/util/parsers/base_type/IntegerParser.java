package project.star_wars_universe.util.parsers.base_type;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

public class IntegerParser implements Parser<String, Integer> {

    @Override
    public Integer parse(String content) throws ParsingFailureException {
        try {
            Integer integerNum = Integer.parseInt(content);
            return integerNum;
        }
        catch(NumberFormatException ex) {
            String message = ex.getMessage();
            throw new ParsingFailureException(new Exception("Invalid format " + Character.toLowerCase(message.charAt(0)) + message.substring(1) + " (must be an integer value)"));
        }
    }
}
