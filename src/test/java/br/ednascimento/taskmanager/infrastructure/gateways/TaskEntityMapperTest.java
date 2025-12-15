package br.ednascimento.taskmanager.infrastructure.gateways;

import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskEntityMapperTest {

    @Test
    void GIVEN_ValidTaskDomain_WHEN_ToEntity_THEN_ShouldMapCorrectly() {

        // GIVEN
        var mapper = new TaskEntityMapper();
        var task = new Task("Task title", "Task description");

        // WHEN
        var entity = mapper.toEntity(task);

        // THEN
        assertThat(entity).isNotNull();
        assertThat(entity.getTitle()).isEqualTo(task.getTitle());
        assertThat(entity.getDescription()).isEqualTo(task.getDescription());
        assertThat(entity.getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    void GIVEN_ValidTaskEntity_WHEN_ToTaskDomain_THEN_ShouldMapCorrectly() {

        // GIVEN
        var mapper = new TaskEntityMapper();
        var entity = TaskEntity.builder()
                               .title("Entity title")
                               .description("Entity description")
                               .status(TaskStatus.DONE)
                               .build();

        // WHEN
        var task = mapper.toTaskDomain(entity);

        // THEN
        assertThat(task).isNotNull();
        assertThat(task.getTitle()).isEqualTo(entity.getTitle());
        assertThat(task.getDescription()).isEqualTo(entity.getDescription());
    }
}
