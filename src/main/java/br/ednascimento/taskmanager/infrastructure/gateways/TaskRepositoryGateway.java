package br.ednascimento.taskmanager.infrastructure.gateways;

import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskJdbcRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskRepositoryGateway implements TaskGateway {

    private final TaskJdbcRepository taskJdbcRepository;
    private final TaskEntityMapper taskEntityMapper;

    @Override
    public Optional<Long> save(Task taskDomain) {
        var taskEntity = taskEntityMapper.toEntity(taskDomain);
        return taskJdbcRepository.create(taskEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJdbcRepository.findById(id).map(taskEntityMapper::toTaskDomain);
    }

    @Override
    public Optional<List<Task>> findAll() {
        return taskJdbcRepository.findAll()
                .map(taskEntities -> taskEntities.stream()
                        .map(taskEntityMapper::toTaskDomain)
                        .toList());
    }

    @Override
    public void update(Task task) {
        var taskEntity = taskJdbcRepository.findById(task.getId()).orElseThrow(() -> new InvalidTaskException("Task not found"));
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskJdbcRepository.update(taskEntity);
    }

    @Override
    public void updateStatus(Task task) {
        var taskEntity = taskJdbcRepository.findById(task.getId()).orElseThrow(() -> new InvalidTaskException("Task not found"));
        taskEntity.setStatus(task.getStatus());
        taskJdbcRepository.updateStatus(taskEntity);
    }

    @Override
    public void delete(Long id) {
        taskJdbcRepository.delete(id);
    }
}
