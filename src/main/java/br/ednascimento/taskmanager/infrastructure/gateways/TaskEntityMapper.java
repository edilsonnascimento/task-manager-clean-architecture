package br.ednascimento.taskmanager.infrastructure.gateways;

import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskEntityMapper {

    public TaskEntity toEntity(Task taskDomain) {
        return TaskEntity.builder()
                .title(taskDomain.getTitle())
                .description(taskDomain.getDescription())
                .status(taskDomain.getStatus())
                .build();
    }

    public Task toTaskDomain(TaskEntity taskEntity) {
        var task = new Task(taskEntity.getTitle(),
                            taskEntity.getDescription(),
                            taskEntity.getCreatedDate());
        task.setId(task.getId());
        if(taskEntity.getStatus().isInProgress())
            task.markInProgress();
        if(taskEntity.getStatus().isDone()) {
            task.markInProgress();
            task.markDone();
        }
        return task;
    }
}