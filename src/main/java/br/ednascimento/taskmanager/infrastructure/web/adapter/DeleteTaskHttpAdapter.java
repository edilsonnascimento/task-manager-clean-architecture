package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class DeleteTaskHttpAdapter {

    private final DeleteTaskInteractor deleteTaskInteractor;

    public void delete(Long id) {
        deleteTaskInteractor.delete(id);
    }
}
