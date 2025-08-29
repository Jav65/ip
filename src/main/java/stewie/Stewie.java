package stewie;

import java.util.Scanner;

import stewie.command.Command;
import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.parser.Parser;

import static stewie.ui.Ui.HR;
import stewie.ui.Ui;

/**
 * Main class for the stewie.Stewie chatbot.
 * Handles user input, stewie.command parsing, and stewie.task operations.
 */
public class Stewie {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private static Storage storage = new Storage(DATA_FILE_PATH);
    private static TaskList taskList = storage.loadTaskList();

    /**
     * Main method that starts the application and handles the main program loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Ui.showGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            try {
                Command command = Parser.parse_command(input);
                String response = command.execute(taskList, storage);
                System.out.print(HR);
                System.out.print(response);
                System.out.print(HR);

                if (command.isExit()) {
                    break;
                }
            } catch (CommandException e) {
                System.out.print(HR);
                System.out.println(e.getMessage());
                System.out.print(HR);
            }
        }


        Ui.showBye();
    }
}
