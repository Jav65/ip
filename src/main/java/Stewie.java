import java.util.Scanner;
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

    public static final String hr = "\t____________________________________________________________\n";

    public static void greetings() {
        System.out.printf("Ah, a new face. Hullo! I'm Stewie!\n" + logo + hr +
                "\t Don't just stand there, minion.\n" +
                "\t State your purpose before I lose what's left of my patience.\n" + hr);
    }

    public static void bye() {
        System.out.printf(hr + "\t Finally, you're leaving. Do try not to get lost on the way out.\n" + hr);
    }

    private static TaskList taskList = new TaskList();

    public static boolean parse_command(String command) {
        if (command.equals("bye")) {
            return false;
        }

        System.out.print(hr);
        if (command.equals("list")) {
            taskList.listTask();
        } else if (command.startsWith("mark")) {
            taskList.markTask(Integer.parseInt(command.split("\\s+")[1]));
        } else if (command.startsWith("unmark")) {
            taskList.unmarkTask(Integer.parseInt(command.split("\\s+")[1]));
        } else if (command.startsWith("todo")){
            String[] args = command.split("\\s+",2);
            String description = args[1];
            taskList.addTask(new ToDoTask(description));
        } else if (command.startsWith("deadline")){
            String[] args = command.split("\\s+/by\\s+");
            String description = args[0].substring("deadline".length()).trim();
            taskList.addTask(new DeadlineTask(description, args[1]));
        } else if (command.startsWith("event")){
            String[] args = command.split("\\s+/(from|to)\\s+");
            String description = args[0].substring("event".length()).trim();
            taskList.addTask(new EventTask(description, args[1], args[2]));
        }
        System.out.print(hr);

        return true;
    }


    public static void main(String[] args) {
        greetings();

        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (parse_command(command)) {
            System.out.print("$ ");
            command = scanner.nextLine();
        }

        bye();
    }
}
