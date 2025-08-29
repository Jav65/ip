package stewie.task;

import stewie.util.Helper;

import java.time.LocalDateTime;

/**
 * Represents a stewie.task with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a new DeadlineTask with the specified description and deadline.
     *
     * @param description The stewie.task description.
     * @param deadline The deadline for the stewie.task.
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
