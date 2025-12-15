package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTaskInteractorTest {

    @Test
    void GIVEN_ValidRepository_WHEN_CreateService_THEN_ServiceShouldBeCreated() {

        // GIVEN
        TaskGateway taskGateway = mock(TaskGateway.class);

        // WHEN
        var service = new CreateTaskInteractor(taskGateway);

        // THEN
        assertThat(service).isNotNull();
    }

    @Test
    void GIVEN_NullRepository_WHEN_CreateService_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "error create";

        // WHEN
        var exception = assertThrows(InvalidCreateGatewayException.class, () -> new CreateTaskInteractor(null));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_ValidCommand_WHEN_CreateTask_THEN_ShouldSaveTaskAndReturnId() {

        // GIVEN
        TaskGateway taskGateway = mock(TaskGateway.class);
        CreateTaskInteractor service = new CreateTaskInteractor(taskGateway);
        var command = new CreateTaskCommand("Create service test", "Testing happy path");
        var id = 1L;
        when(taskGateway.save(any(Task.class))).thenReturn(Optional.of(id));
        var expected = 1L;

        // WHEN
        var actual = service.create(command);

        // THEN
        assertThat(actual).isEqualTo(expected);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskGateway).save(captor.capture());
        Task savedTask = captor.getValue();
        assertThat(savedTask.getTitle()).isEqualTo(command.title());
        assertThat(savedTask.getDescription()).isEqualTo(command.description());
    }

    @Test
    void GIVEN_RepositoryReturnsEmpty_WHEN_CreateTask_THEN_ShouldThrowException() {

        // GIVEN
        TaskGateway taskGateway = mock(TaskGateway.class);
        CreateTaskInteractor service = new CreateTaskInteractor(taskGateway);
        var command = new CreateTaskCommand("Fail create", "Repository returns empty");
        when(taskGateway.save(any(Task.class))).thenReturn(Optional.empty());
        var expected = "Task error create";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> service.create(command));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }
}
