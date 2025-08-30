package stewie.storage;

import stewie.task.*;
import stewie.util.Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void saveTasks(TaskList taskList) {
        try {
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : taskList.getTasks()) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("\t Error saving tasks: " + e.getMessage());
        }
    }

    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("\t Warning: Could not create data directory");
            return taskList;
        }

        if (!Files.exists(filePath)) {
            System.out.println("\t No existing data file found. Starting with empty task list.");
            return taskList;
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
                        taskList.addTask(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\t Warning: Skipping corrupted line " + lineNumber + ": " +
                            line + " (" + e.getMessage() + ")");
                    corruptedLines++;
                }
            }

            System.out.println("\t Loaded " + taskList.size() + " tasks from data file.");
            if (corruptedLines > 0) {
                System.out.println("\t Skipped " + corruptedLines + " corrupted lines. File will be cleaned up.");
                saveTasks(taskList);
            }

        } catch (IOException e) {
            System.out.println("\t Error reading data file: " + e.getMessage());
        }

        return taskList;
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
}
