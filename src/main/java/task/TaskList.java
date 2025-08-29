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
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        return "\t I've scribbled down your little task:\n" +
                "\t  " + task.getDescription() + "\n" +
                "\t Now, do try to keep up, won't you?\n" +
                "\t You have " + this.tasks.size() + " tasks left.\n";
    }

    public String markTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        return "\t Behold! I've declared this paltry task complete.\n" +
                "\t  " + task.getDescription() + "\n" +
                "\t Don't get cocky. You still have a long way to go.\n";
    }

    public String unmarkTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.unmark();
        return "\t You're toying with me! I've marked this back as incomplete.\n" +
                "\t  " + task.getDescription() + "\n" +
                "\t Don't think for a second I'll forget this betrayal.\n";
    }

    public String listTask() {
        if (this.tasks.isEmpty()) {
            return "\t No tasks? How utterly dreadful!\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\t These, my dear simpleton, are the items on your agenda.\n");

        int i = 1;
        for (Task task : tasks) {
            sb.append("\t  ").append(i).append(". ").append(task.getDescription()).append("\n");
            i++;
        }
        sb.append("\t Failure is not an option.\n");

        return sb.toString();
    }

    public String deleteTask(int i) {
        Task task = this.tasks.remove(i - 1);
        return "\t Poof! Begone with you, you insignificant little undertaking!\n" +
                "\t  " + task.getDescription() + "\n" +
                "\t Don't get cocky. You still have a long way to go.\n" +
                "\t You have " + this.tasks.size() + " tasks left.\n";
    }

    public int size() {
        return tasks.size();
    }
}
