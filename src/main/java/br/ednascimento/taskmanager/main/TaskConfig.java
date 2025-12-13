package br.ednascimento.taskmanager.main;

import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.application.usecases.*;
import br.ednascimento.taskmanager.infrastructure.controller.TaskDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    TaskDTOMapper createTaskDTOMapper() {
        return new TaskDTOMapper();
    }

    @Bean
    CreateTaskInteractor createTaskInteractor(TaskGateway gateway) {
        return new CreateTaskInteractor(gateway);
    }

    @Bean
    ListTasksInteractor listTasksInteractor(TaskGateway gateway) {
        return new ListTasksInteractor(gateway);
    }

    @Bean
    FindTaskByIdInteractor findTaskByIdInteractor(TaskGateway gateway) {
        return new FindTaskByIdInteractor(gateway);
    }

    @Bean
    UpdateTaskInteractor updateTaskInteractor(TaskGateway gateway) {
        return new UpdateTaskInteractor(gateway);
    }

    @Bean
    DeleteTaskInteractor deleteTaskInteractor(TaskGateway gateway) {
        return new DeleteTaskInteractor(gateway);
    }
}
