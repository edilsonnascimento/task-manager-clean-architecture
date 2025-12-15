package br.ednascimento.taskmanager.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

public interface TaskJdbcRepository {
    Optional<Long> create(TaskEntity taskEntity);
    Optional<TaskEntity> findById(Long id);
    Optional<List<TaskEntity>> findAll();
    void update(TaskEntity taskEntity);
    void delete(Long id);
    void updateStatus(TaskEntity taskEntity);
}
