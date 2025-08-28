package task;

/**
 * Represents a task that occurs during a specific time period.
 */
public class EventTask extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Creates a new EventTask with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.startTime + " | " + this.endTime;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
