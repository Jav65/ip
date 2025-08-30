package stewie.util;

import stewie.command.CommandType;
import stewie.exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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

    /**
     * Parses a date-time string in various formats to LocalDateTime.
     *
     * @param dateTimeString The date-time string to parse.
     * @return LocalDateTime object if parsing succeeds, null otherwise.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(dateTimeString, dateFormatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    /**
     * Converts a LocalDateTime to a human-readable string format.
     *
     * @param dateTime The LocalDateTime to convert.
     * @return Formatted date-time string for display.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

        if (dateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return dateTime.format(dateFormatter);
        } else {
            return dateTime.format(fullFormatter);
        }
    }

    /**
     * Converts a LocalDateTime to file storage format.
     *
     * @param dateTime The LocalDateTime to convert.
     * @return Formatted date-time string for file storage.
     */
    public static String dateTimeToFileFormat(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(dateTimeFormatter);
    }
}
