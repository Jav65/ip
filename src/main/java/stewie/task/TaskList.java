package stewie.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks with operations to add, remove, mark, and list tasks.
 */
public class TaskList {
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
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @return Confirmation message about the added task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "\t I've scribbled down your little task:\n"
                + "\t  " + task.getDescription() + "\n"
                + "\t Now, do try to keep up, won't you?\n"
                + "\t You have " + this.tasks.size() + " tasks left.\n";
    }

    /**
     * Marks a task as done.
     *
     * @param index The 1-based index of the task to mark.
     * @return Confirmation message about the marked task.
     */
    public String markTask(int index) {
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        return "\t Behold! I've declared this paltry task complete.\n"
                + "\t  " + task.getDescription() + "\n"
                + "\t Don't get cocky. You still have a long way to go.\n";
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The 1-based index of the task to unmark.
     * @return Confirmation message about the unmarked task.
     */
    public String unmarkTask(int index) {
        Task task = this.tasks.get(index - 1);
        task.unmark();
        return "\t You're toying with me! I've marked this back as incomplete.\n"
                + "\t  " + task.getDescription() + "\n"
                + "\t Don't think for a second I'll forget this betrayal.\n";
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @return String representation of all tasks in the list.
     */
    public String listTask() {
        if (this.tasks.isEmpty()) {
            return "\t No tasks? How utterly dreadful!\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\t These, my dear simpleton, are the items on your agenda.\n");

        int index = 1;
        for (Task task : tasks) {
            sb.append("\t  ").append(index).append(". ").append(task.getDescription()).append("\n");
            index++;
        }
        sb.append("\t Failure is not an option.\n");

        return sb.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The 1-based index of the task to delete.
     * @return Confirmation message about the deleted task.
     */
    public String deleteTask(int index) {
        Task task = this.tasks.remove(index - 1);
        return "\t Poof! Begone with you, you insignificant little undertaking!\n" +
                "\t  " + task.getDescription() + "\n" +
                "\t Don't get cocky. You still have a long way to go.\n" +
                "\t You have " + this.tasks.size() + " tasks left.\n";
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