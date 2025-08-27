package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\t I've scribbled down your little task:");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Now, do try to keep up, won't you?");
        System.out.println("\t You have " + this.tasks.size() + " tasks left.");
    }

    public void markTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        System.out.println("\t Behold! I've declared this paltry task complete.");
        System.out.println("\t  " + task.getDescription());
        System.out.println("\t Don't get cocky. You still have a long way to go.");
    }

    public void unmarkTask(int i) {
        Task task = this.tasks.get(i - 1);
        task.unmark();
        System.out.println("\t You're toying with me! I've marked this back as incomplete.");
        System.out.println("\t  " + task.getDescription());
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
    }
}
