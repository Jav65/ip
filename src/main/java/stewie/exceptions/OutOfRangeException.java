package stewie.exceptions;

/**
 * Represents an exception thrown when a parameter is outside the acceptable range.
 */
public class OutOfRangeException extends CommandException {

    /**
     * Creates a new OutOfRangeException with the specified detail message.
     *
     * @param detail Additional details about the range violation.
     */
    public OutOfRangeException(String detail) {
        super("The numbers you've provided are outside the acceptable parameters.\n"
                + detail);
    }
}
