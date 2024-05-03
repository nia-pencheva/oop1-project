package project.star_wars_universe.app.cli.enums;

import project.star_wars_universe.contracts.cli.Command;

public enum Commands {
    HELP("help", "prints this information"),
    OPEN("open <file>", "opens <file>"),
    CLOSE("close", "closes the currently opened file"),
    SAVE("save", "saves the currently opened file"),
    SAVE_AS("saveas <file>", "saves the currently opened file in <file>"),
    EXIT( "exit", "exits the program"),
    ADD_PLANET("add_planet <planet_name>", "adds a new planet"),
    CREATE_JEDI( "create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>", "adds a new jedi"),
    REMOVE_JEDI( "remove_jedi <jedi_name> <planet_name>", "removes a jedi from a planet"),
    PROMOTE_JEDI( "promote_jedi <jedi_name> <multiplier>", "increases a jedi's strength with <multiplier> * <current_strength> and increments their rank"),
    DEMOTE_JEDI("demote_jedi <jedi_name> <multiplier>", "decreases a jedi's strength with <multiplier> * <current_strength> and decrements their rank"),
    GET_STRONGEST_JEDI("get_strongest_jedi <planet_name>", "print the strongest jedi on that planet"),
    GET_YOUNGEST_JEDI("get_youngest_jedi <planet_name> <jedi_rank>", "print the youngest jedi of that rank on that planet"),
    GET_MOST_USED_SABER_COLOR_RANK("get_most_used_saber_color <planet_name> <jedi_rank>", "print the most used saber color on that planet by the jedi of that rank"),
    GET_MOST_USED_SABER_COLOR("get_most_used_saber_color <planet_name>", "print the most used saber color on that planet"),
    PRINT_PLANET("print <planet_name>", "print information about that planet"),
    PRINT_JEDI("print <jedi_name>", "print information about that jedi"),
    PRINT_PLANETS("<planet_name> + <planet_name>", "print information about the jedi populating the two planets");

    private String syntax;
    private String description;

    Commands(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getDescription() {
        return description;
    }
}
