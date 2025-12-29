package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.UpdateDoneTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.UpdateInProgressTaskInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class PatchStatusHttpAdapter {

    private final UpdateInProgressTaskInteractor updateInProgressStatus;
    private final UpdateDoneTaskInteractor updateDonesStatus;

    public void updateInProgressStatus(Long id) {
        updateInProgressStatus.update(id);
    }

    public void updateDoneStatus(Long id) {
        updateDonesStatus.update(id);
    }
}
