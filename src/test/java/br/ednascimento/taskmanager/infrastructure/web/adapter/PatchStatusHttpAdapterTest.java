package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.UpdateDoneTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.UpdateInProgressTaskInteractor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PatchStatusHttpAdapterTest {

    @Test
    void GIVEN_ValidId_WHEN_UpdateInProgressStatus_THEN_ShouldCallInteractor() {

        // GIVEN
        var updateInProgressInteractor = mock(UpdateInProgressTaskInteractor.class);
        var updateDoneInteractor = mock(UpdateDoneTaskInteractor.class);
        var adapter = new PatchStatusHttpAdapter(updateInProgressInteractor, updateDoneInteractor);
        var taskId = 1L;

        // WHEN
        adapter.updateInProgressStatus(taskId);

        // THEN
        verify(updateInProgressInteractor).update(taskId);
        verifyNoInteractions(updateDoneInteractor);
    }

    @Test
    void GIVEN_ValidId_WHEN_UpdateDoneStatus_THEN_ShouldCallInteractor() {

        // GIVEN
        var updateInProgressInteractor = mock(UpdateInProgressTaskInteractor.class);
        var updateDoneInteractor = mock(UpdateDoneTaskInteractor.class);
        var adapter = new PatchStatusHttpAdapter(updateInProgressInteractor, updateDoneInteractor);
        var taskId = 1L;

        // WHEN
        adapter.updateDoneStatus(taskId);

        // THEN
        verify(updateDoneInteractor).update(taskId);
        verifyNoInteractions(updateInProgressInteractor);
    }
}
