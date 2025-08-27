package util;

import command.CommandType;
import exceptions.InvalidCommandException;

/**
 * Utility class providing helper methods for parsing and validation.
 */
public class Helper {

    /**
     * Parses a string argument to an integer index.
     *
     * @param args The string argument to parse.
     * @param usage The usage message to display if parsing fails.
     * @return The parsed integer index.
     * @throws InvalidCommandException If the argument is null, blank, or not a valid integer.
     */
    public static int parseIndexOrThrow(String args, String usage) throws InvalidCommandException {
        if (args == null || args.isBlank()) {
            throw new InvalidCommandException(usage);
        }
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(usage);
        }
    }

    /**
     * Parses a string input to a CommandType enum value.
     *
     * @param input The string input to parse.
     * @return The corresponding CommandType, or UNKNOWN if the input is not recognized.
     */
    public static CommandType parseCommandType(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }
}
