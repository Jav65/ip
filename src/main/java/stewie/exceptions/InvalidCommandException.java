package stewie.exceptions;

/**
 * Represents an exception thrown when a stewie.command has invalid format or parameters.
 */
public class InvalidCommandException extends CommandException {

    /**
     * Creates a new InvalidCommandException with the specified message.
     *
     * @param message The correct format message to display.
     */
    public InvalidCommandException(String message) {
        super("\t I've provided the correct format for your simpleton mind.\n" +
                "\t Follow it precisely: " + message);
    }
}
