package exceptions;

/**
 * Represents an exception thrown when a command-related error occurs.
 */
public class CommandException extends Exception {

    /**
     * Creates a new CommandException with the specified message.
     *
     * @param message The error message to display.
     */
    public CommandException(String message) {
        super("\t What's this? You've uttered a complete and utter nonsense phrase.\n"
                + message);
    }
}
