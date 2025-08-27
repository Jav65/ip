package Task;

public class DeadlineTask extends Task{
    protected String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + this.deadline + ")";
    }
}
