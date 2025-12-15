package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.util.Objects;

public class UpdateInProgressTaskInteractor {

    private final TaskGateway gateway;

    public UpdateInProgressTaskInteractor(TaskGateway gateway) {
        requireGateway(gateway);
        this.gateway = gateway;
    }

    private void requireGateway(TaskGateway taskGateway) {
        if (Objects.isNull(taskGateway))
            throw new InvalidCreateGatewayException("error create");
    }

    public void update(Long taskId) {
        var task = gateway.findById(taskId).orElseThrow(() -> new InvalidTaskException("Task not found"));
        task.markInProgress();
        task.setId(taskId);
        gateway.updateStatus(task);
    }
}
