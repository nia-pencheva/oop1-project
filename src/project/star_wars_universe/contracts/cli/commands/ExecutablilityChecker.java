package project.star_wars_universe.contracts.cli.commands;

public interface ExecutablilityChecker {
    boolean isExecutable();
    void printNotExecutableMessage();
}
