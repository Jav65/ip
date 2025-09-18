package stewie.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeleteCommand}.
 */
class DeleteCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validIndex_taskDeletedAndSaved() throws CommandException {
        String expectedResponse = "Task deleted";
        when(taskList.deleteTask(1)).thenReturn(expectedResponse);

        DeleteCommand command = new DeleteCommand("1");
        String result = command.execute(taskList, storage);

        assertEquals(expectedResponse, result);
        verify(taskList).deleteTask(1);
        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_outOfBoundsIndex_throwsOutOfRangeException() {
        when(taskList.deleteTask(99)).thenThrow(new IndexOutOfBoundsException());

        DeleteCommand command = new DeleteCommand("99");
        assertThrows(OutOfRangeException.class, () -> command.execute(taskList, storage));
    }
}
