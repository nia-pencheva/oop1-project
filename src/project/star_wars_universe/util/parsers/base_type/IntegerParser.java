package project.star_wars_universe.util.parsers.base_type;

import project.star_wars_universe.exceptions.util.ParsingFailureException;

/**
 * Parses values to type {@link Integer}.
 */
public class IntegerParser {

    /**
     * Converts a {@code String} to an {@code Integer}. In case of a {@link NumberFormatException},
     * the exception is caught and an {@link Exception} with a more appropriate error message is instantiated and passed to
     * a {@link ParsingFailureException} instance, from which it can later be obtained. This is because the
     * {@link NumberFormatException} does not give sufficient information about the expected number format
     * (whether it should be int or double).
     * @param content the {@code String} that should be converted.
     * @return the input {@code String} parsed into an {@code Integer} value.
     * @throws ParsingFailureException if an exception occurs during the parsing process.
     */
    public static Integer parse(String content) throws ParsingFailureException {
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
