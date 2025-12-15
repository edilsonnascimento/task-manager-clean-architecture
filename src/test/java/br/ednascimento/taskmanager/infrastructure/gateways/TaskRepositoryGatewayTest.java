package br.ednascimento.taskmanager.infrastructure.gateways;

import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskEntity;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskJdbcRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TaskRepositoryGatewayTest {

    @Test
    void GIVEN_ValidTask_WHEN_Save_THEN_ShouldPersistAndReturnId() {

        // GIVEN
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);
        var task = new Task("Task title", "Task description");
        var taskEntity = TaskEntity.builder()
                                   .title("Task title")
                                   .description("Task description")
                                   .status(TaskStatus.PENDING)
                                   .build();
        when(taskEntityMapper.toEntity(task)).thenReturn(taskEntity);
        when(taskJdbcRepository.create(taskEntity)).thenReturn(Optional.of(1L));

        // WHEN
        var result = gateway.save(task);

        // THEN
        assertThat(result).contains(1L);
        verify(taskEntityMapper, times(1)).toEntity(task);
        verify(taskJdbcRepository, times(1)).create(taskEntity);
    }

    @Test
    void GIVEN_ExistingTaskId_WHEN_FindById_THEN_ShouldReturnMappedTask() {

        // GIVEN
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);
        var taskEntity = TaskEntity.builder()
                .title("Entity title")
                .description("Entity description")
                .status(TaskStatus.DONE)
                .build();
        var taskDomain = new Task("Entity title", "Entity description");
        when(taskJdbcRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        when(taskEntityMapper.toTaskDomain(taskEntity)).thenReturn(taskDomain);

        // WHEN
        var result = gateway.findById(1L);

        // THEN
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Entity title");
        verify(taskJdbcRepository).findById(1L);
        verify(taskEntityMapper).toTaskDomain(taskEntity);
    }

    @Test
    void GIVEN_ExistingTasks_WHEN_FindAll_THEN_ShouldReturnMappedList() {

        // GIVEN
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);
        var entity1 = TaskEntity.builder().title("Task 1").description("Desc 1").build();
        var entity2 = TaskEntity.builder().title("Task 2").description("Desc 2").build();
        var task1 = new Task("Task 1", "Desc 1");
        var task2 = new Task("Task 2", "Desc 2");
        var expected = List.of(task1, task2);
        when(taskJdbcRepository.findAll()).thenReturn(Optional.of(List.of(entity1, entity2)));
        when(taskEntityMapper.toTaskDomain(entity1)).thenReturn(task1);
        when(taskEntityMapper.toTaskDomain(entity2)).thenReturn(task2);

        // WHEN
        var actual = gateway.findAll();

        // THEN
        assertThat(actual).isPresent();
        assertThat(actual.get()).containsExactlyElementsOf(expected);
        verify(taskJdbcRepository).findAll();
        verify(taskEntityMapper, times(2)).toTaskDomain(any(TaskEntity.class));
    }


    @Test
    void GIVEN_ExistingTask_WHEN_Update_THEN_ShouldUpdateTask() {

        // GIVEN
        var task = new Task("New title", "New description");
        task.setId(1L);
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);
        var taskEntity = TaskEntity.builder()
                                   .title("Old title")
                                   .description("Old description")
                                   .build();
        when(taskJdbcRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        var expected = TaskEntity.builder().build();


        // WHEN
        gateway.update(task);

        // THEN
        var  captor = ArgumentCaptor.forClass(TaskEntity.class);
        verify(taskJdbcRepository).update(captor.capture());
        var updatedEntity = captor.getValue();
        assertThat(updatedEntity).isEqualTo(task);
    }

    @Test
    void GIVEN_NonExistingTask_WHEN_Update_THEN_ShouldThrowException() {

        // GIVEN
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);

        var task = new Task("Title", "Description");
        task.setId(99L);

        when(taskJdbcRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN
        var exception = assertThrows(
                InvalidTaskException.class,
                () -> gateway.update(task)
        );

        // THEN
        assertThat(exception.getMessage()).isEqualTo("Task not found");
        verify(taskJdbcRepository, never()).update(any());
    }

    @Test
    void GIVEN_TaskId_WHEN_Delete_THEN_ShouldCallRepositoryDelete() {

        // GIVEN
        var taskJdbcRepository = mock(TaskJdbcRepository.class);
        var taskEntityMapper = mock(TaskEntityMapper.class);
        var gateway = new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);

        // WHEN
        gateway.delete(1L);

        // THEN
        verify(taskJdbcRepository, times(1)).delete(1L);
    }
}
