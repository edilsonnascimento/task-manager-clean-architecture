package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;

import java.util.List;
import java.util.Objects;

public class ListTasksInteractor {

    private final TaskGateway gateway;

    public ListTasksInteractor(TaskGateway gateway) {
        validateRepository(gateway);
        this.gateway = gateway;
    }



    public List<Task> findAll() {
        return gateway.findAll().orElse(List.of());
    }
}
