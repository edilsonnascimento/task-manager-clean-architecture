package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListTasksInteractorTest {

    @Test
    void GIVEN_NullGateway_WHEN_CreateInteractor_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new ListTasksInteractor(null));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_TasksExist_WHEN_FindAll_THEN_ShouldReturnTaskList() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new ListTasksInteractor(gateway);
        Task task1 = new Task("Task 1", "Description 1", null);
        Task task2 = new Task("Task 2", "Description 2", null);
        when(gateway.findAll()).thenReturn(Optional.of(List.of(task1, task2)));
        var expected = List.of(task1, task2);

        // WHEN
        var actual = interactor.findAll();

        // THEN
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        verify(gateway).findAll();
    }

    @Test
    void GIVEN_NoTasksExist_WHEN_FindAll_THEN_ShouldReturnEmptyList() {

        // GIVEN
        var gateway = mock(TaskGateway.class);
        var interactor = new ListTasksInteractor(gateway);
        when(gateway.findAll()).thenReturn(Optional.empty());

        // WHEN
        List<Task> result = interactor.findAll();

        // THEN
        assertThat(result).isEmpty();
        verify(gateway).findAll();
    }





}