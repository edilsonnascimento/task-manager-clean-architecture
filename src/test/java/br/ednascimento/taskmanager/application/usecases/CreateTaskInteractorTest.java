package br.ednascimento.taskmanager.application.usecases;

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
        var taskGateway = mock(TaskGateway.class);

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
    void GIVEN_ValidTask_WHEN_CreateTask_THEN_ShouldSaveTaskAndReturnId() {

        // GIVEN
        var taskGateway = mock(TaskGateway.class);
        var service = new CreateTaskInteractor(taskGateway);
        var task = new Task("Create task test", "Description happy path");
        var id = 1L;
        when(taskGateway.save(any(Task.class))).thenReturn(Optional.of(id));
        var expected = 1L;

        // WHEN
        var actual = service.create(task);

        // THEN
        assertThat(actual).isEqualTo(expected);
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskGateway).save(captor.capture());
        var savedTask = captor.getValue();
        assertThat(savedTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(savedTask.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    void GIVEN_RepositoryReturnsEmpty_WHEN_CreateTask_THEN_ShouldThrowException() {

        // GIVEN
        var taskGateway = mock(TaskGateway.class);
        var service = new CreateTaskInteractor(taskGateway);
        var task = new Task("Title teste", "Description Teste");
        when(taskGateway.save(any(Task.class))).thenReturn(Optional.empty());
        var expected = "Task error create";

        // WHEN
        var exception = assertThrows(InvalidTaskException.class, () -> service.create(task));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }
}
