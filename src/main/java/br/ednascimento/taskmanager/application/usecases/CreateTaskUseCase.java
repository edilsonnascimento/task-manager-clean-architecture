package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;

import java.util.Optional;

public interface CreateTaskUseCase {
    Optional<Long> create(CreateTaskCommand command);
}
