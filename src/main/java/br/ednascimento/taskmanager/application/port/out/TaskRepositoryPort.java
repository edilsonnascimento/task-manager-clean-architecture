package br.ednascimento.taskmanager.application.port.out;

import br.ednascimento.taskmanager.domain.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Optional<Long> save(Task task);
    Optional<Task> findById(Long id);
    Optional<List<Task>> findAll();
    void update(Task task);
    void deleteById(Long id);
}
