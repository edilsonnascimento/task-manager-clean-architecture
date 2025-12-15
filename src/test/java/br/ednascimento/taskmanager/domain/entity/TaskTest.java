package br.ednascimento.taskmanager.domain.entity;

import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    @Test
    void GIVEN_ValidTitleAndDescription_WHEN_CreateTask_THEN_TaskShouldBeInitializedCorrectly() {

        // GIVEN
        var title = "Study Clean Architecture";
        var description = "Read and practice clean architecture concepts";
        var expected = new Task(title, description, null);

        // WHEN
        var actual = new Task(title, description, null);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void GIVEN_TaskInPendingStatus_WHEN_MarkInProgress_THEN_StatusShouldBeInProgress() {

        // GIVEN
        var title = "Write tests";
        var description = "Create unit tests for domain layer";
        var task = new Task(title, description, null);
        var expected = new Task(title, description, null);
        expected.markInProgress();

        // WHEN
        task.markInProgress();

        // THEN
        assertThat(task).isEqualTo(expected);
    }

    @Test
    void GIVEN_TaskInProgress_WHEN_MarkDone_THEN_StatusShouldBeDone() {

        // GIVEN
        var title = "Finish feature";
        var description = "Complete ST-001 implementation";
        var task = new Task(title, description, null);
        task.markInProgress();
        var expected = new Task(title, description, null);
        expected.markInProgress();
        expected.markDone();

        // WHEN
        task.markDone();

        // THEN
        assertThat(task.getStatus()).isEqualTo(TaskStatus.DONE);
    }

    @Test
    void GIVEN_TaskNotInPending_WHEN_MarkInProgress_THEN_ShouldThrowException() {

        // GIVEN
        var title = "Invalid transition";
        var description = "Test invalid state";
        var task = new Task(title, description, null);
        task.markInProgress();
        var expected = "Only PENDING tasks can be started";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, task::markInProgress);

       // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_NullTitle_WHEN_CreateTask_THEN_ShouldThrowException() {

        // GIVEN
        var description = "Invalid task";
        var expected = "title must not be null";

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class, () -> new Task(null, description, null));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_BlankTitle_WHEN_CreateTask_THEN_ShouldThrowException() {

        // GIVEN
        var description = "Invalid task";
        var expected = "title must not be blank";

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class, () -> new Task("", description, null));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_TaskNotInProgress_WHEN_MarkDone_THEN_ShouldThrowException() {

        // GIVEN
        var title = "Invalid done";
        var description = "Task not started";
        var task = new Task(title, description, null);
        var expected = "Only IN_PROGRESS tasks can be completed";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, task::markDone);

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_Task_WHEN_SetId_THEN_IdShouldBeReturnedCorrectly() {

        // GIVEN
        var title = "Title default";
        var description = "Description default";
        var task = new Task(title, description, null);
        var id = 10L;
        var expected = 10L;

        // WHEN
        task.setId(id);

        // THEN
        assertThat(task.getId()).isEqualTo(expected);
    }

    @Test
    void GIVEN_TwoTasksWithSameId_WHEN_Compare_THEN_ShouldBeEqual() {

        // GIVEN
        var task1 = new Task("Task 1", "Description", null);
        var task2 = new Task("Task 2", "Another description", null);
        task1.setId(1L);
        task2.setId(1L);

        // WHEN / THEN
        assertThat(task1).isEqualTo(task2);
    }

    @Test
    void GIVEN_Task_WHEN_CompareWithNull_THEN_ShouldNotBeEqual() {

        // GIVEN
        var task = new Task("Task", "Description", null);

        // WHEN / THEN
        assertThat(task.equals(null)).isFalse();
    }

    @Test
    void GIVEN_Task_WHEN_GetDescription_THEN_ShouldReturnDescription() {

        // GIVEN
        var title = "Task title";
        var description = "Task description";
        var task = new Task(title, description, null);

        // WHEN
        var actual = task.getDescription();

        // THEN
        assertThat(actual).isEqualTo(description);
    }

    @Test
    void GIVEN_NewTask_WHEN_GetCreatedAt_THEN_ShouldReturnNull() {

        // GIVEN
        var title = "Task title";
        var description = "Task description";
        var task = new Task(title, description, null);

        // WHEN
        var actual = task.getCreatedDate();

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void GIVEN_TaskWithId_WHEN_HashCodeCalled_THEN_ShouldReturnHashBasedOnId() {

        // GIVEN
        var task = new Task("Task", "Hash test", null);
        task.setId(100L);
        var expected = Objects.hashCode(100L);

        // WHEN
        var actual = task.hashCode();

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void GIVEN_Task_WHEN_GetTitle_THEN_ShouldReturnTitle() {

        // GIVEN
        var title = "Task title";
        var description = "Task description";
        var task = new Task(title, description, null);
        var expected = "Task title";

        // WHEN
        var actualTitle = task.getTitle();

        // THEN
        assertThat(actualTitle).hasToString(expected);
    }











}
