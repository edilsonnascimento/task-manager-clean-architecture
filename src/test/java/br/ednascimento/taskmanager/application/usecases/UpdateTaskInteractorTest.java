package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateTaskInteractorTest {

    @Test
    void GIVEN_NullGateway_WHEN_CreateInteractor_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new UpdateTaskInteractor(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void GIVEN_NullTask_WHEN_Update_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new UpdateTaskInteractor(gateway);
        var expected = "Not created";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> interactor.update(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway, never()).update(any());
    }

    @Test
    void GIVEN_TaskDoesNotExist_WHEN_Update_THEN_ShouldThrowException() {

        // GIVEN
        var  gateway = mock(TaskGateway.class);
        var interactor = new UpdateTaskInteractor(gateway);
        var task = new Task("Title", "Description");
        task.setId(1L);
        when(gateway.findById(1L)).thenReturn(Optional.empty());
        var expected = "Task not found";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> interactor.update(task));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway, never()).update(any());
    }

    @Test
    void GIVEN_ValidTask_WHEN_Update_THEN_ShouldCallGatewayUpdate() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new UpdateTaskInteractor(gateway);
        var task = new Task("Updated title", "Updated description");
        task.setId(1L);

        when(gateway.findById(1L)).thenReturn(Optional.of(task));

        // WHEN
        interactor.update(task);

        // THEN
        verify(gateway).findById(1L);
        verify(gateway).update(task);
    }
}