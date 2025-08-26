import java.util.Scanner;
import Task.Task;

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

        if (command.equals("list")) {
            System.out.print(hr);
            taskList.listTask();
            System.out.print(hr);
        } else if (command.startsWith("mark")) {
            System.out.print(hr);
            taskList.markTask(Integer.parseInt(command.split("\\s+")[1]));
            System.out.print(hr);
        } else if (command.startsWith("unmark")) {
            System.out.print(hr);
            taskList.unmarkTask(Integer.parseInt(command.split("\\s+")[1]));
            System.out.print(hr);
        } else {
            System.out.print(hr);
            taskList.addTask(new Task(command));
            System.out.print(hr);
        }

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
