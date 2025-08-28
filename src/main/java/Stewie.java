import java.time.LocalDateTime;
import java.util.Scanner;

import command.CommandType;
import exceptions.CommandException;
import exceptions.InvalidCommandException;
import exceptions.OutOfRangeException;
import exceptions.UnknownCommandException;
import task.DeadlineTask;
import task.EventTask;
import task.TaskList;
import task.ToDoTask;
import util.Helper;

/**
 * Main class for the Stewie chatbot.
 * Handles user input, command parsing, and task operations.
 */
public class Stewie {
    public static final String LOGO =
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡶⠦⢤⠴⠒⠒⠚⠛⠒⢶⠤⠤⠤⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡦⠴⣒⠉⠁⠀⠀⠀⠀⠑⠀⠀⠀⠀⠀⠈⠃⠀⠀⠀⠀⠹⡝⠒⠤⣄⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⢀⡾⠋⠙⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠈⠓⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠙⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⡾⠛⠓⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⠈⠳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⣠⣶⡾⢿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠉⠁⢒⣒⣒⡒⠒⠒⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⢹⠃⢠⡄⠙⢿⣿⣷⣤⡀⠀⠀⠀⠀⡴⠊⠁⠀⠀⠈⠑⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠤⠤⣀⣀⠀⠀⠀⠀⠀⠀⠈⢿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠈⠣⣈⣀⠀⠀⠙⠻⣿⣿⣶⣄⠀⡞⠀⠀⢰⣶⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠤⠤⠤⢍⠉⠒⠀⠀⠀⠀⠈⣆⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠹⡄⠀⠀⠀⠈⠻⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠁⢀⣄⠀⠀⠀⠙⢄⠀⠀⠀⠀⠀⢸⣆⣀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠹⡄⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠛⢻⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⣯⣶⣶⣾⣷⣶⣤⣤⣤⣈⣆⠀⠀⠀⠀⣼⣟⣤⢳⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠘⢆⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⢸⣿⣿⠿⠛⠛⢿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣼⣿⣋⣀⡜⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠳⡄⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣥⣤⣤⣶⣾⣿⣿⠃⠀⠀⠤⠊⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠉⢹⣿⣿⡿⠉⠁⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⣀⣠⢋⠟⠉⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠢⢄⣀⣀⢆⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀⣀⣠⣾⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠁⠀⠼⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠐⠋⠉⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⣀⣀⣀⡀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠉⢳⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢁⣴⣯⣴⣶⣶⣮⠕⠢⡀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡷⡀⠀⠀⢀⣏⠙⢒⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣙⠿⠿⠿⠿⠟⠛⠁⣰⣿⣿⣿⣿⠿⣟⣧⣤⣶⣾\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣷⣮⣽⣾⣿⣿⣾⣿⣿⣿⣿⣿⣿⣶⣶⡦⠤⠤⢤⣤⣤⣤⡤⠤⣤⣶⣶⣾⣏⠁⠀⠀⠀⠀⠀⢀⠤⣴⣿⣿⣿⣻⣷⣿⣿⣿⣟⣁⣹\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⡼⠁⠑⢤⠊⠈⢦⣿⣿⣿⣿⣿⣿⣦⣠⣤⣶⢶⠁⢀⠀⡟⠛⢻⠉⠉⠀⠀⠀⠀⠀⠁\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠉⠃⠀⠀⣤⢣⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡸⡀⣸⣏⠑⡲⠊⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠉⢸⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣷⣤⣭⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢸⠀⠀⣿⣿⣿⣿⣿⠿⠿⠿⠿⢻⠿⢿⣿⣿⠟⢟⢦⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠿⢸⠀⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⢺⢤⡼⠿⠿⠦⠬⠆⢣⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢸⠀⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⢸⡀⠙⠶⠿⠿⠉⢻⠉⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠛⠉⢹⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀\n";

    public static final String HR = "\t________________________________________________________________________\n";

    private static TaskList taskList = new TaskList();

    /**
     * Displays the greeting message when the app starts.
     */
    public static void greetings() {
        System.out.printf(HR + "\t Ah, a new face. Hullo! I'm Stewie!\n" + LOGO + HR +
                "\t Don't just stand there, minion.\n" +
                "\t State your purpose before I lose what's left of my patience.\n" + HR);
    }

    /**
     * Displays the goodbye message when the app exits.
     */
    public static void bye() {
        System.out.printf(HR + "\t Finally, you're leaving. Do try not to get lost on the way out.\n" + HR);
    }

    /**
     * Parses and executes a command from user input.
     *
     * @param input The user input command string.
     * @return true if the application should continue, false if it should exit.
     * @throws CommandException If the command is invalid or cannot be executed.
     */
    public static boolean parse_command(String input) throws CommandException {
        if (input == null) {
            return true;
        }

        input = input.trim();
        if (input.isBlank()) {
            return true;
        }

        if (input.equals("bye")) {
            return false;
        }

        String[] cmdNArgs = input.split("\\s+", 2);
        CommandType commandType = Helper.parseCommandType(cmdNArgs[0]);
        String args = cmdNArgs.length > 1 ? cmdNArgs[1] : "";

        System.out.print(HR);
        switch (commandType) {
            case LIST: {
                taskList.listTask();
                break;
            }
            case MARK: {
                handleMarkCommand(args);
                break;
            }
            case UNMARK: {
                handleUnmarkCommand(args);
                break;
            }
            case TODO: {
                handleTodoCommand(args);
                break;
            }
            case DEADLINE: {
                handleDeadlineCommand(args);
                break;
            }
            case EVENT: {
                handleEventCommand(args);
                break;
            }
            case DELETE: {
                handleDeleteCommand(args);
                break;
            }
            default:
                throw new UnknownCommandException("\t\tlist, mark <i>, unmark <i>, todo <desc>, deadline <desc> /by <time>," +
                                                    "\n\t\tevent <desc> /from <start> /to <end>, delete <i>, bye");
        }
        System.out.print(HR);

        return true;
    }

    /**
     * Handles the mark command to mark a task as done.
     *
     * @param args The command arguments containing the task index.
     * @throws CommandException If the command format is invalid or the task doesn't exist.
     */
    private static void handleMarkCommand(String args) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "mark <index>\n\t Expected an integer index!");
        try {
            taskList.markTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx);
        }
    }

    /**
     * Handles the unmark command to mark a task as not done.
     *
     * @param args The command arguments containing the task index.
     * @throws CommandException If the command format is invalid or the task doesn't exist.
     */
    private static void handleUnmarkCommand(String args) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "unmark <index>\n\t Expected an integer index!");
        try {
            taskList.unmarkTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx + ".");
        }
    }

    /**
     * Handles the todo command to add a new todo task.
     *
     * @param args The command arguments containing the task description.
     * @throws InvalidCommandException If the description is blank.
     */
    private static void handleTodoCommand(String args) throws InvalidCommandException {
        if (args.isBlank()) {
            throw new InvalidCommandException("todo <description>");
        }
        taskList.addTask(new ToDoTask(args.trim()));
    }

    /**
     * Handles the deadline command to add a new deadline task.
     *
     * @param args The command arguments containing the task description and deadline.
     * @throws InvalidCommandException If the command format is invalid.
     */
    private static void handleDeadlineCommand(String args) throws InvalidCommandException {
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new InvalidCommandException("deadline <description> /by <dd/MM/yyyy> <HH:mm>");
        }
        LocalDateTime deadline = Helper.parseDateTime(parts[1].trim());
        if (deadline == null) {
            throw new InvalidCommandException("deadline <description> /by <dd/MM/yyyy> <HH:mm>");
        }

        taskList.addTask(new DeadlineTask(parts[0].trim(), deadline));
    }

    /**
     * Handles the event command to add a new event task.
     *
     * @param args The command arguments containing the task description, start time, and end time.
     * @throws InvalidCommandException If the command format is invalid.
     */
    private static void handleEventCommand(String args) throws InvalidCommandException {
        String[] parts = args.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new InvalidCommandException("event <description> /from <dd/MM/yyyy> <HH:mm> /to <dd/MM/yyyy> <HH:mm>");
        }
        LocalDateTime startTime = Helper.parseDateTime(parts[1].trim());
        LocalDateTime endTime = Helper.parseDateTime(parts[2].trim());
        if (startTime == null || endTime == null) {
            throw new InvalidCommandException("event <description> /from <dd/MM/yyyy> <HH:mm> /to <dd/MM/yyyy> <HH:mm>");
        }

        taskList.addTask(new EventTask(parts[0].trim(), startTime, endTime));
    }

    /**
     * Handles the delete command to remove a task.
     *
     * @param args The command arguments containing the task index.
     * @throws CommandException If the command format is invalid or the task doesn't exist.
     */
    private static void handleDeleteCommand(String args) throws CommandException {
        int idx = Helper.parseIndexOrThrow(args, "delete <index>\n\t Expected an integer index!");
        try {
            taskList.deleteTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("\t There's no task at index " + idx);
        }
    }

    /**
     * Main method that starts the application and handles the main program loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        greetings();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String command = scanner.nextLine();
            try {
                if (!parse_command(command)) {
                    break;
                }
            } catch (CommandException e) {
                System.out.println(e.getMessage());
                System.out.print(HR);
            }
        }

        bye();
    }
}
