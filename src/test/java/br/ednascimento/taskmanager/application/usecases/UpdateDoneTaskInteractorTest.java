package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateDoneTaskInteractorTest {

    @Test
    void GIVEN_NullGateway_WHEN_CreateInteractor_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new UpdateDoneTaskInteractor(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway, never()).findById(anyLong());
        verify(gateway, never()).updateStatus(any());
    }

    @Test
    void GIVEN_TaskDoesNotExist_WHEN_Update_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new UpdateDoneTaskInteractor(gateway);
        var taskId = 1L;
        var expected = "Task not found";

        when(gateway.findById(taskId)).thenReturn(Optional.empty());

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> interactor.update(taskId));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway).findById(anyLong());
        verify(gateway, never()).updateStatus(any());
    }

    @Test
    void GIVEN_ValidTask_WHEN_Update_THEN_ShouldMarkTaskAsDone() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new UpdateDoneTaskInteractor(gateway);
        var taskId = 1L;
        var task = new Task("Title", "Description", null);
        task.setId(taskId);
        task.markInProgress();
        when(gateway.findById(taskId)).thenReturn(Optional.of(task));

        // WHEN
        interactor.update(taskId);

        // THEN
        assertThat(task.getStatus().isDone()).isTrue();
        verify(gateway).findById(anyLong());
        verify(gateway).updateStatus(any());
    }

    @Test
    void GIVEN_ValidTask_WHEN_Update_THEN_ShouldCallGatewayUpdateStatus() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new UpdateDoneTaskInteractor(gateway);
        var taskId = 1L;
        var task = new Task("Title", "Description", null);
        task.setId(taskId);
        task.markInProgress();
        when(gateway.findById(taskId)).thenReturn(Optional.of(task));

        // WHEN
        interactor.update(taskId);

        // THEN
        verify(gateway).findById(taskId);
        verify(gateway).updateStatus(task);
    }
}
