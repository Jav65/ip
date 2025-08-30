package stewie.parser;

import stewie.command.*;
import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.exceptions.UnknownCommandException;
import stewie.util.Helper;

/**
 * Parses user input into executable commands.
 */
public class Parser {
    /**
     * Parses the given input string into a Command object.
     *
     * @param input The user input string to parse.
     * @return The corresponding Command object.
     * @throws CommandException If the input is invalid or command is unknown.
     */
    public static Command parse_command(String input) throws CommandException {
        if (input == null || input.isBlank()) {
            throw new InvalidCommandException("Empty input!");
        }

        String[] cmdNArgs = input.trim().split("\\s+", 2);
        CommandType commandType = Helper.parseCommandType(cmdNArgs[0]);
        String args = cmdNArgs.length > 1 ? cmdNArgs[1] : "";

        switch (commandType) {
            case LIST:     return new ListCommand();
            case MARK:     return new MarkCommand(args);
            case UNMARK:   return new UnmarkCommand(args);
            case TODO:     return new ToDoCommand(args);
            case DEADLINE: return new DeadlineCommand(args);
            case EVENT:    return new EventCommand(args);
            case DELETE:   return new DeleteCommand(args);
            case BYE:      return new ByeCommand();
            default: throw new UnknownCommandException("\t\tlist, mark <i>, unmark <i>, todo <desc>, deadline <desc> /by <time>," +
                    "\n\t\tevent <desc> /from <start> /to <end>, delete <i>, bye");
        }
    }
}
