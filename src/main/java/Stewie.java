import java.util.Scanner;

import Exceptions.CommandException;
import Exceptions.InvalidCommandException;
import Exceptions.OutOfRangeException;
import Exceptions.UnknownCommandException;
import Task.ToDoTask;
import Task.DeadlineTask;
import Task.EventTask;

public class Stewie {
    public static final String logo =
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

    public static final String hr = "\t________________________________________________________________________\n";

    public static void greetings() {
        System.out.printf("Ah, a new face. Hullo! I'm Stewie!\n" + logo + hr +
                "\t Don't just stand there, minion.\n" +
                "\t State your purpose before I lose what's left of my patience.\n" + hr);
    }

    public static void bye() {
        System.out.printf(hr + "\t Finally, you're leaving. Do try not to get lost on the way out.\n" + hr);
    }

    private static TaskList taskList = new TaskList();

    private static int parseIndexOrThrow(String args, String usage) throws InvalidCommandException {
        if (args == null || args.isBlank()) {
            throw new InvalidCommandException(usage);
        }
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(usage);
        }
    }

    public static boolean parse_command(String input) throws CommandException {
        if (input == null) return true;
        input = input.trim();
        if (input.isBlank()) return true;

        if (input.equals("bye")) {
            return false;
        }

        String[] cmdNArgs = input.split("\\s+", 2);
        String command = cmdNArgs[0];
        String args = cmdNArgs.length > 1 ? cmdNArgs[1] : "";

        System.out.print(hr);
        switch (command) {
            case "list": {
                taskList.listTask();
                break;
            }
            case "mark": {
                int idx = parseIndexOrThrow(args, "mark <index>\n\t Expected an integer index!");
                try {
                    taskList.markTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfRangeException("\t There's no task at index " + idx);
                }
                break;
            }
            case "unmark": {
                int idx = parseIndexOrThrow(args, "unmark <index>\n\t Expected an integer index!");
                try {
                    taskList.unmarkTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfRangeException("\t There's no task at index " + idx + ".");
                }
                break;
            }
            case "todo": {
                if (args.isBlank()) {
                    throw new InvalidCommandException("todo <description>");
                }
                taskList.addTask(new ToDoTask(args.trim()));
                break;
            }
            case "deadline": {
                String[] parts = args.split("\\s+/by\\s+", 2);
                if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
                    throw new InvalidCommandException("deadline <description> /by <time>");
                }
                taskList.addTask(new DeadlineTask(parts[0].trim(), parts[1].trim()));
                break;
            }
            case "event": {
                String[] parts = args.split("\\s+/from\\s+|\\s+/to\\s+");
                if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
                    throw new InvalidCommandException("event <description> /from <start> /to <end>");
                }
                taskList.addTask(new EventTask(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                break;
            }
            default:
                throw new UnknownCommandException("\t\tlist, mark <i>, unmark <i>, todo <desc>, deadline <desc> /by <time>," +
                                                    "\n\t\tevent <desc> /from <start> /to <end>, bye");
        }
        System.out.print(hr);

        return true;
    }


    public static void main(String[] args) {
        greetings();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String command = scanner.nextLine();
            try {
                if (!parse_command(command)) break;
            } catch (CommandException e) {
                System.out.println(e.getMessage());
                System.out.print(hr);
            }
        }

        bye();
    }
}
