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

    public void markTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        System.out.println("\t Behold! I've declared this paltry task complete.");
        System.out.println("\t  " + task.getDescriptionWithMark());
        System.out.println("\t Don't get cocky. You still have a long way to go.");
    }

    public void unmarkTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.unmark();
        System.out.println("\t You're toying with me! I've marked this back as incomplete.");
        System.out.println("\t  " + task.getDescriptionWithMark());
        System.out.println("\t Don't think for a second I'll forget this betrayal.");
    }

    public void listTask() {
        if (this.tasks.isEmpty()) {
            System.out.println("\t No tasks? How utterly dreadful!");
            return;
        }

        System.out.println("\t These, my dear simpleton, are the items on your agenda.");
        int i = 1;
        for (Task task : tasks) {
            System.out.println("\t  " + i + ". " + task.getDescriptionWithMark());
            i++;
        }
        System.out.println("\t Failure is not an option.");
    }
}
