package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.exception.NotFoundException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;

import java.util.Objects;

public class FindTaskByIdInteractor {

    private final TaskGateway gateway;

    public FindTaskByIdInteractor(TaskGateway gateway) {
        requireGateway(gateway);
        this.gateway = gateway;
    }

    private void requireGateway(TaskGateway taskGateway) {
        if (Objects.isNull(taskGateway))
            throw new InvalidCreateGatewayException("error create");
    }

    public Task finOne(Long id) {
        return gateway.findById(id).orElseThrow(()-> new NotFoundException("task not found"));
    }
}
