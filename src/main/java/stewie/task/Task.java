package stewie.task;

/**
 * Represents a basic stewie.task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * The stewie.task is initially marked as not done.
     *
     * @param description The stewie.task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the stewie.task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the stewie.task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the stewie.task to file format for stewie.storage.
     * Each subclass must implement this method to define its specific serialization format.
     *
     * @return The stewie.task in file format.
     */
    public String toFileFormat() {
        return ((this.isDone) ? "1" : "0" ) + " | " + this.description;
    }

    /**
     * Returns the stewie.task description with completion status indicator.
     *
     * @return The formatted stewie.task description.
     */
    public String getDescription() {
        return ((this.isDone) ? "[X] " : "[ ] " ) + this.description;
    }
}
