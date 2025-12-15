package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.exception.NotFoundException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FindTaskByIdInteractorTest {

    @Test
    void GIVEN_TaskExists_WHEN_FindById_THEN_ShouldReturnTask() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        FindTaskByIdInteractor interactor = new FindTaskByIdInteractor(gateway);
        var task = new Task("Task title", "Task description");
        task.setId(1L);
        when(gateway.findById(1L)).thenReturn(Optional.of(task));
        var expected = new Task("Task title", "Task description");
        expected.setId(1L);

        // WHEN
        var actual = interactor.finOne(1L);

        // THEN
        assertThat(actual).isEqualTo(expected);
        verify(gateway).findById(1L);
    }

    @Test
    void GIVEN_TaskDoesNotExist_WHEN_FindById_THEN_ShouldThrowException() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var id = 1L;
        var interactor = new FindTaskByIdInteractor(gateway);
        when(gateway.findById(anyLong())).thenReturn(Optional.empty());
        var expected = "task not found";

        // WHEN
        var actual = assertThrows(NotFoundException.class, ()-> interactor.finOne(id));

        // THEN
        assertThat(actual.getMessage()).hasToString(expected);
        verify(gateway).findById(anyLong());
    }

    @Test
    void GIVEN_NullGateway_WHEN_CreateInteractor_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new FindTaskByIdInteractor(null));

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expected);
    }




}