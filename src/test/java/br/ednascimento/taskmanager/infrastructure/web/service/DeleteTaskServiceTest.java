package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteTaskServiceTest {

    @Test
    void GIVEN_ValidId_WHEN_Delete_THEN_ShouldCallInteractor() {

        // GIVEN
        var interactor = mock(DeleteTaskInteractor.class);
        var service = new DeleteTaskService(interactor);
        var taskId = 1L;

        // WHEN
        service.delete(taskId);

        // THEN
        verify(interactor).delete(taskId);
        verifyNoMoreInteractions(interactor);
    }

}