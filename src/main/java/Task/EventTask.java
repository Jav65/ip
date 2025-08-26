package Task;

public class EventTask extends Task {
    protected String startTime;
    protected String endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
