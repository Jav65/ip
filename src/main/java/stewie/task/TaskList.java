package stewie.task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages a list of tasks with operations to add, remove, mark, and list tasks.
 */
public class TaskList {
    private static final int INDEX_OFFSET = 1;
    private ArrayList<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds one or more tasks to the task list.
     * Returns appropriate feedback message based on the number of tasks added.
     *
     * @param tasks The task(s) to be added to the list. At least one task must be provided.
     * @return A formatted response message indicating the task(s) have been added
     */
    public String addTask(Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));

        if (tasks.length == 1) {
            return "I've scribbled down your little task:\n"
                    + " " + tasks[0].getDescription() + "\n"
                    + "Now, do try to keep up, won't you?\n"
                    + "You have " + this.tasks.size() + " tasks left";
        } else {
            return "I've added " + tasks.length + " tasks to your endless list.\n"
                   + "You have " + this.tasks.size() + " tasks in total.";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The 1-based index of the task to mark.
     * @return Confirmation message about the marked task.
     */
    public String markTask(int index) {
        Task task = this.tasks.get(index - INDEX_OFFSET);
        task.markAsDone();
        return "Behold! I've declared this paltry task complete.\n"
                + " " + task.getDescription() + "\n"
                + "Don't get cocky. You still have a long way to go.";
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The 1-based index of the task to unmark.
     * @return Confirmation message about the unmarked task.
     */
    public String unmarkTask(int index) {
        Task task = this.tasks.get(index - INDEX_OFFSET);
        task.unmark();
        return "You're toying with me! I've marked this back as incomplete.\n"
                + " " + task.getDescription() + "\n"
                + "Don't think for a second I'll forget this betrayal.";
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @return String representation of all tasks in the list.
     */
    public String listTask() {
        if (this.tasks.isEmpty()) {
            return "No tasks? How utterly dreadful!\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("These, my dear simpleton, are the items on your agenda.\n");

        int index = 1;
        for (Task task : tasks) {
            sb.append(" ").append(index).append(". ").append(task.getDescription()).append("\n");
            index++;
        }
        sb.append("Failure is not an option.");

        return sb.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The 1-based index of the task to delete.
     * @return Confirmation message about the deleted task.
     */
    public String deleteTask(int index) {
        Task task = this.tasks.remove(index - INDEX_OFFSET);
        return "Poof! Begone with you, you insignificant little undertaking!\n"
               + " " + task.getDescription() + "\n"
               + "Don't get cocky. You still have a long way to go.\n"
               + "You have " + this.tasks.size() + " tasks left.";
    }

    /**
     * Finds tasks containing the specified description keyword.
     *
     * @param description The keyword to search for in task descriptions.
     * @return A formatted string containing all matching tasks, or a message if no matches found.
     */
    public String findTaskByDescription(String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hmph! Here are the pathetic, insignificant plans that you requested.\n");

        int displayIndex = 1;
        int matchCount = 0;

        // Search and format matching tasks
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                sb.append(" ").append(displayIndex).append(". ").append(task.getDescription()).append("\n");
                displayIndex++;
                matchCount++;
            }
        }
        sb.append(String.format("Found %d tasks in total.", matchCount));

        // Handle no matches case
        if (matchCount == 0) {
            return "You've wasted my time. Absolutely nothing of consequence was found.";
        }

        return sb.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
