package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteTaskHttpAdapterTest {

    @Test
    void GIVEN_ValidId_WHEN_Delete_THEN_ShouldCallInteractor() {

        // GIVEN
        var interactor = mock(DeleteTaskInteractor.class);
        var adapter = new DeleteTaskHttpAdapter(interactor);
        var taskId = 1L;

        // WHEN
        adapter.delete(taskId);

        // THEN
        verify(interactor).delete(taskId);
        verifyNoMoreInteractions(interactor);
    }

}