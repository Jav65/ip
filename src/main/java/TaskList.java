import java.util.ArrayList;
import Task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\t I've scribbled down your little task: " + task.getDescription());
        System.out.println("\t Now, do try to keep up, won't you?");
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
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
}
