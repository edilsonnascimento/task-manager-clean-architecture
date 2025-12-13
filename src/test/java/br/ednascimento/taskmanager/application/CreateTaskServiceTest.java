package br.ednascimento.taskmanager.application;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.application.port.out.TaskRepositoryPort;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidRepositoryPortException;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTaskServiceTest {

    @Test
    void GIVEN_ValidRepository_WHEN_CreateService_THEN_ServiceShouldBeCreated() {

        // GIVEN
        TaskRepositoryPort repository = mock(TaskRepositoryPort.class);

        // WHEN
        var service = new CreateTaskService(repository);

        // THEN
        assertThat(service).isNotNull();
    }

    @Test
    void GIVEN_NullRepository_WHEN_CreateService_THEN_ShouldThrowException() {

        // GIVEN
        var expected = "repository null";

        // WHEN
        var exception = assertThrows(InvalidRepositoryPortException.class, () -> new CreateTaskService(null));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_ValidCommand_WHEN_CreateTask_THEN_ShouldSaveTaskAndReturnId() {

        // GIVEN
        TaskRepositoryPort repository = mock(TaskRepositoryPort.class);
        CreateTaskService service = new CreateTaskService(repository);
        var command = new CreateTaskCommand("Create service test", "Testing happy path");
        var id = 1L;
        when(repository.save(any(Task.class))).thenReturn(Optional.of(id));
        var expected = 1L;

        // WHEN
        var actual = service.create(command);

        // THEN
        assertThat(actual).isEqualTo(expected);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(repository).save(captor.capture());
        Task savedTask = captor.getValue();
        assertThat(savedTask.getTitle()).isEqualTo(command.title());
        assertThat(savedTask.getDescription()).isEqualTo(command.description());
    }

    @Test
    void GIVEN_RepositoryReturnsEmpty_WHEN_CreateTask_THEN_ShouldThrowException() {

        // GIVEN
        TaskRepositoryPort repository = mock(TaskRepositoryPort.class);
        CreateTaskService service = new CreateTaskService(repository);
        var command = new CreateTaskCommand("Fail create", "Repository returns empty");
        when(repository.save(any(Task.class))).thenReturn(Optional.empty());
        var expected = "Task error create";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> service.create(command));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }
}
