package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.application.usecases.UpdateDoneTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.UpdateInProgressTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.UpdateTaskInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatchStatusService {

    private final UpdateInProgressTaskInteractor updateInProgressStatus;
    private final UpdateDoneTaskInteractor updateDonesStatus;

    public void updateInProgressStatus(Long id) {
        updateInProgressStatus.update(id);
    }

    public void updateDoneStatus(Long id) {
        updateDonesStatus.update(id);
    }
}
