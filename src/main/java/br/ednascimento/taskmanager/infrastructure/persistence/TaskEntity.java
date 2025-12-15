package br.ednascimento.taskmanager.infrastructure.persistence;

import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdDate;
}
