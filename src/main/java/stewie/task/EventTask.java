package stewie.task;

import stewie.util.Helper;

import java.time.LocalDateTime;

/**
 * Represents a task that occurs during a specific time period.
 */
public class EventTask extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates a new EventTask with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + Helper.dateTimeToFileFormat(startTime)
                + " | " + Helper.dateTimeToFileFormat(endTime);
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + Helper.dateTimeToString(startTime)
                + " to: " + Helper.dateTimeToString(endTime) + ")";
    }
}