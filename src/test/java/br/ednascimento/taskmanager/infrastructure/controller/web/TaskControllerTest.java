package br.ednascimento.taskmanager.infrastructure.controller.web;

import br.ednascimento.taskmanager.application.usecases.*;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @LocalServerPort
    private int port;
    @MockitoBean
    private CreateTaskInteractor createTask;
    @MockitoBean
    private ListTasksInteractor listTasks;
    @MockitoBean
    private FindTaskByIdInteractor findTaskById;
    @MockitoBean
    private UpdateTaskInteractor updateTask;
    @MockitoBean
    private DeleteTaskInteractor deleteTask;

    private RestClient client() {
        return RestClient.create("http://localhost:" + port);
    }

    @Test
    void GIVEN_ValidRequest_WHEN_CreateTask_THEN_Returns201() {

        // GIVEN
        when(createTask.create(any())).thenReturn(1L);
        var request = """
                      {
                         "title": "Task",
                          "description": "Desc"
                      }
                      """;

        // WHEN
        var response = client().post()
                .uri("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation().toString()).hasToString("/v1/tasks/1");
        verify(createTask).create(any());
    }

    @Test
    void GIVEN_TasksExist_WHEN_ListTasks_THEN_Returns200() {

        // GIVEN
        var createDate = LocalDateTime.of(2025, 12,15, 14,0,0);
        var task = new Task("Title", "Description", createDate);
        when(listTasks.findAll()).thenReturn(List.of(task));

        // WHEN
        var response = client().get()
                .uri("/v1/tasks")
                .retrieve()
                .toEntity(List.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(listTasks).findAll();
    }

    @Test
    void GIVEN_TaskExists_WHEN_FindById_THEN_Returns200() {

        // GIVEN
        var createDate = LocalDateTime.of(2025, 12,15, 14,0,0);
        var task = new Task("Title", "Description", createDate);
        when(findTaskById.finOne(1L)).thenReturn(task);

        // WHEN
        var response = client().get()
                .uri("/v1/tasks/1")
                .retrieve()
                .toEntity(TaskResponseDto.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(findTaskById).finOne(1L);
    }

    @Test
    void WHEN_UpdateTask_THEN_Returns200() {

        var request = """
        {
          "title": "Updated",
          "description": "Updated"
        }
        """;

        var response = client().put()
                .uri("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(updateTask).update(any(Task.class));
    }

    @Test
    void WHEN_DeleteTask_THEN_Returns200() {

        var response = client().delete()
                .uri("/v1/tasks/1")
                .retrieve()
                .toBodilessEntity();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(deleteTask).delete(1L);
    }
}

