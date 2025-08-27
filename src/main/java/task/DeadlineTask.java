package task;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected String deadline;

    /**
     * Creates a new DeadlineTask with the specified description and deadline.
     *
     * @param description The task description.
     * @param deadline The deadline for the task.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + this.deadline + ")";
    }
}
