package project.star_wars_universe.util.parsers.base_type;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

public class DoubleParser implements Parser<String, Double> {
    public Double parse(String content) throws ParsingFailureException {
        try {
            Double doubleNum = Double.parseDouble(content);
            return doubleNum;
        }
        catch(NumberFormatException ex) {
            String message = ex.getMessage();
            throw new ParsingFailureException(new Exception("Invalid format " + Character.toLowerCase(message.charAt(0)) + message.substring(1) + " (must be a floating point value)"));
        }
    }

}
