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
        return new Task(taskEntity.getTitle(), taskEntity.getDescription());
    }
}