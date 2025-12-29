package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import br.ednascimento.taskmanager.infrastructure.web.adapter.DeleteTaskHttpAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteTaskInteractorTest {

    @Test
    void GIVEN_ExistingTask_WHEN_Delete_THEN_ShouldCallGatewayDelete() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new DeleteTaskInteractor(gateway);
        var createDate = LocalDateTime.of(2025, 12,15, 14,0,0);
        var task = new Task("Title", "Description", createDate);
        task.setId(1L);
        when(gateway.findById(1L)).thenReturn(Optional.of(task));

        // WHEN
        interactor.delete(1L);

        // THEN
        verify(gateway).findById(1L);
        verify(gateway).delete(1L);
    }


    @Test
    void GIVEN_NullGateway_WHEN_CreateInteractor_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new DeleteTaskInteractor(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void GIVEN_NullId_WHEN_Delete_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new DeleteTaskInteractor(gateway);
        var expected = "Task id must not be null";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> interactor.delete(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway, never()).delete(any());
    }

    @Test
    void GIVEN_TaskDoesNotExist_WHEN_Delete_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new DeleteTaskInteractor(gateway);
        when(gateway.findById(10L)).thenReturn(Optional.empty());
        var expected = "Task not found";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> interactor.delete(10L));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
        verify(gateway, never()).delete(any());
    }
}