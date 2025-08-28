package task;

import util.Helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTask();
    }

    private void saveTasks() {
        Path filePath = Paths.get(DATA_FILE_PATH);

        try {
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("\t Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTask() {
        Path filePath = Paths.get(DATA_FILE_PATH);
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("\t Warning: Could not create data directory");
            return;
        }

        if (!Files.exists(filePath)) {
            System.out.println("\t No existing data file found. Starting with empty task list.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int lineNumber = 0;
            int corruptedLines = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    Task task = parseTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\t Warning: Skipping corrupted line " + lineNumber + ": " +
                                        line + " (" + e.getMessage() + ")");
                    corruptedLines++;
                }
            }

            System.out.println("\t Loaded " + tasks.size() + " tasks from data file.");
            if (corruptedLines > 0) {
                System.out.println("\t Skipped " + corruptedLines + " corrupted lines. File will be cleaned up.");
                saveTasks();
            }

        } catch (IOException e) {
            System.out.println("\t Error reading data file: " + e.getMessage());
        }
    }

    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid format");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Missing deadline");
            }
            task = new DeadlineTask(description, Helper.parseDateTime(parts[3]));
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Missing event time");
            }
            task = new EventTask(description, Helper.parseDateTime(parts[3]), Helper.parseDateTime(parts[4]));
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\t I've scribbled down your little task:");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Now, do try to keep up, won't you?");
        System.out.println("\t You have " + this.tasks.size() + " tasks left.");
        saveTasks();
    }

    public void markTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        System.out.println("\t Behold! I've declared this paltry task complete.");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Don't get cocky. You still have a long way to go.");
        saveTasks();
    }

    public void unmarkTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.unmark();
        System.out.println("\t You're toying with me! I've marked this back as incomplete.");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Don't think for a second I'll forget this betrayal.");
        saveTasks();
    }

    public void listTask() {
        if (this.tasks.isEmpty()) {
            System.out.println("\t No tasks? How utterly dreadful!");
            return;
        }

        System.out.println("\t These, my dear simpleton, are the items on your agenda.");
        int i = 1;
        for (Task task : tasks) {
            System.out.println("\t  " + i + ". " + task.getDescription());
            i++;
        }
        System.out.println("\t Failure is not an option.");
    }

    public void deleteTask(int i) {
        Task task = this.tasks.remove(i - 1);
        System.out.println("\t Poof! Begone with you, you insignificant little undertaking!");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Don't get cocky. You still have a long way to go.");
        System.out.println("\t You have " + this.tasks.size() + " tasks left.");
        saveTasks();
    }
}
