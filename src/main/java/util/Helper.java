import exceptions.InvalidCommandException;

public class Helper {
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

    public static CommandType parseCommandType(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }
}
