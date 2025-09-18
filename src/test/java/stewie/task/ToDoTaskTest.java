package stewie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link ToDoTask}.
 */
class ToDoTaskTest {

    @Test
    void toFileFormat_validTodo_correctFormat() {
        ToDoTask task = new ToDoTask("Buy milk");
        String expected = "T | 0 | Buy milk";
        assertEquals(expected, task.toFileFormat());
    }

    @Test
    void getDescription_validTodo_correctFormat() {
        ToDoTask task = new ToDoTask("Buy milk");
        String expected = "[T][ ] Buy milk";
        assertEquals(expected, task.getDescription());
    }
}
