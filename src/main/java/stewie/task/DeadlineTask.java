package stewie.task;

import java.time.LocalDateTime;

import stewie.util.Helper;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a new DeadlineTask with the specified description and deadline.
     *
     * @param description The task description.
     * @param deadline The deadline for the task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + Helper.dateTimeToFileFormat(deadline);
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + Helper.dateTimeToString(deadline) + ")";
    }
}
