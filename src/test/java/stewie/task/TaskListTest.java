package stewie.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private TaskList taskList;
    private ToDoTask todoTask;
    private DeadlineTask deadlineTask;
    private EventTask eventTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todoTask = new ToDoTask("Read a book");
        deadlineTask = new DeadlineTask("Pay bills", LocalDateTime.of(2025, 10, 26, 18, 0));
        eventTask = new EventTask("Team meeting", LocalDateTime.of(2025, 11, 1, 10, 0), LocalDateTime.of(2025, 11, 1, 12, 0));
    }

    @Test
    public void testAddTask() {
        String expectedMessage = "\t I've scribbled down your little task:\n" +
                "\t  [T][ ] Read a book\n" +
                "\t Now, do try to keep up, won't you?\n" +
                "\t You have 1 tasks left.\n";
        String actualMessage = taskList.addTask(todoTask);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testMarkTask() {
        taskList.addTask(todoTask);
        String expectedMessage = "\t Behold! I've declared this paltry task complete.\n" +
                "\t  [T][X] Read a book\n" +
                "\t Don't get cocky. You still have a long way to go.\n";
        String actualMessage = taskList.markTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void testUnmarkTask() {
        taskList.addTask(todoTask);
        taskList.markTask(1);
        String expectedMessage = "\t You're toying with me! I've marked this back as incomplete.\n" +
                "\t  [T][ ] Read a book\n" +
                "\t Don't think for a second I'll forget this betrayal.\n";
        String actualMessage = taskList.unmarkTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(false, taskList.getTasks().get(0).isDone);
    }

    @Test
    public void testDeleteTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        String expectedMessage = "\t Poof! Begone with you, you insignificant little undertaking!\n" +
                "\t  [T][ ] Read a book\n" +
                "\t Don't get cocky. You still have a long way to go.\n" +
                "\t You have 1 tasks left.\n";
        String actualMessage = taskList.deleteTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(1, taskList.size());
        assertEquals("[D][ ] Pay bills (by: 26 Oct 2025 18:00)", taskList.getTasks().get(0).getDescription());
    }

    @Test
    public void testListTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);
        String expectedMessage = "\t These, my dear simpleton, are the items on your agenda.\n" +
                "\t  1. [T][ ] Read a book\n" +
                "\t  2. [D][ ] Pay bills (by: 26 Oct 2025 18:00)\n" +
                "\t  3. [E][ ] Team meeting (from: 01 Nov 2025 10:00 to: 01 Nov 2025 12:00)\n" +
                "\t Failure is not an option.\n";
        String actualMessage = taskList.listTask();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testListEmptyTask() {
        String expectedMessage = "\t No tasks? How utterly dreadful!\n";
        String actualMessage = taskList.listTask();
        assertEquals(expectedMessage, actualMessage);
    }
}