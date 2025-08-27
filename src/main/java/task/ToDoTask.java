package Task;

public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}
