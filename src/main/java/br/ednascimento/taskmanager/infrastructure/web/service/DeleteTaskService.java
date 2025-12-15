package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteTaskService {

    private final DeleteTaskInteractor deleteTaskInteractor;

    public void delete(Long id) {
        deleteTaskInteractor.delete(id);
    }
}
